import React, { Component } from 'react';

import * as axios from '../../axios-orders';
import * as cookie from '../../cookie-handler';

import { connect } from 'react-redux';
import Aux from '../../hoc/Aux/Aux';
import NavBar from '../NavBar/NavBar';
import TopBar from '../TopBar/TopBar';
import ConfirmModal from '../../components/UI/Modal/ConfirmModal/ConfirmModal';
import LoginModal from '../../components/UI/Modal/LoginModal/LoginModal';
import Spinner from '../../components/UI/Spinner/Spinner';

import * as actionCreators from '../../store/actions/index';

import './Layout.css';

export const AuthContext = React.createContext(false);

export const showSnackBar = (text) => {
    var x = document.getElementById("snackbar");
    x.innerHTML = text;
    x.className = "show";

    // After 3 seconds, remove the show class from DIV
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}

class Layout extends Component {

    state = {
        tokenRefresherActive: false
    }

    componentDidMount(){
        //getting token from cookies
        const token = cookie.getToken();

        if(token && token !== ''){
            this.props.onToken(token);
            axios.getAuthUser(cookie.getToken()).then(res =>{
                const userAuth = res.data;
                this.props.onLogin(userAuth);
            }).catch(err => {
                if(err.message === 'Network Error'){
                    showSnackBar(err.message);
                }
            });
        }

        this.setState({tokenRefresherActive: true});
        this.refreshToken();
        this.loopRefreshToken(this);
    }

    loopRefreshToken = (context) =>{
        setTimeout(function(){ context.loopRefreshTokenRepetition(context); }, 60 * 1000);
    }

    // this method represents each loop of the loopRefreshToken method
    loopRefreshTokenRepetition = (context) =>{
        if(context.state.tokenRefresherActive){
            context.refreshToken();
            context.loopRefreshToken(context);
        }        
    }

    refreshToken = () =>{
        if(this.props.tkn && this.props.tkn !== ""){
            axios.refreshToken(this.props.tkn).then(res => {     
                const newToken = res.headers.authorization;
                cookie.saveToken(newToken);
                this.props.onToken(newToken);
            }).catch(err => {
                // console.log("err", err);
            });
        }      
    }

    state = {
        dialogLogout:{
            active: false,
            funConfirm: null
        },
        dialogLogin:{
            active: false
        },
        loading: false
    }
    
    /* Log in Methods */

    dialogLogin = () => {
        this.setState( {
            dialogLogin: {
                active: true
            }
        });
    }

    closeDialogLogin = () => {
        this.setState( {
            dialogLogin: {
                active: false
            }
        });

        // clean the fields
        document.getElementById("inputLoginModal").value = "";
        document.getElementById("inputPasswordModal").value = "";
    }

    login = (login, password) => {

        const user = {
            login: login,
            password: password
        }

        this.closeDialogLogin();
        this.setState({loading: true});

        axios.login(user).then(res => {

            const token = res.headers.authorization;
            this.props.onToken(token);
            cookie.saveToken(token);

            axios.getAuthUser(token).then(res =>{
                const userAuth = res.data;
                this.props.onLogin(userAuth);

                this.closeDialogLogin();
                showSnackBar('Bem vindo, '+userAuth.name+'!');
                this.setState({loading: false});
                return true;
            });

        }).catch(err =>{
            console.log("Error", err);
            showSnackBar('Acesso Negado');
            this.setState({loading: false});
            this.dialogLogin();
            return false;
        });
    }

    /* Log out methods */

    dialogLogout = () => {
        this.setState( {
            dialogLogout: {
                active: true, 
                funConfirm: this.logout
            }
        });
    }
    
    closeDialogLogout = () => {
        this.setState( {
            dialogLogout: {
                active: false
            }
        });
    }

    logout = () => {
        this.setState( {
            dialogLogout: {
                active: false
            }
        });

        this.props.onToken("");
        cookie.saveToken("");
        this.props.onLogin(null);
    }

    render() {
        return (
            <Aux>
                <NavBar home={this.props.url}
                    title={this.props.appName}
                    itemClick={this.props.navItemClick} />

                <div id='main-content'>
                    <AuthContext.Provider value={this.props.usr !== null}>
                        <TopBar home={this.state.url} 
                            user={this.props.usr}
                            logout={this.dialogLogout}
                            login={this.dialogLogin}
                            {...this.props} />
                    </AuthContext.Provider>

                    <main id='container'>{this.props.children}</main>
                </div>
   
                <Spinner
                    marginTop='13%'
                    marginLeft='calc(50% - 221px)'
                    active={this.state.loading} />

                <ConfirmModal 
                    title="Fazer logout"
                    question="Tem certeza que deseja fazer logout?"
                    active={this.state.dialogLogout.active}
                    marginTop='15%'
                    marginLeft='calc(50% - 221px)'
                    confirm={this.state.dialogLogout.funConfirm} 
                    cancel={this.closeDialogLogout} />

                <LoginModal 
                    active={this.state.dialogLogin.active}
                    marginTop='15%'
                    marginLeft='calc(50% - 221px)'
                    confirm={this.login} 
                    cancel={this.closeDialogLogin} />

                <div id="snackbar"></div>
            </Aux>
        );
    }  
}

const mapStateToProps = state => {
    return{
        usr: state.usr.user,
        appName: state.app.appName,
        tkn: state.usr.token
    };
}

const mapDispathToProps = dispatch => {
    return{
        onLogin: (usr) => dispatch(actionCreators.userLogin(usr)),
        onToken: (tkn) => dispatch(actionCreators.token(tkn)),
        getAppName: () => dispatch(actionCreators.appName()),
        onAuth: (login, password) => dispatch(actionCreators.auth(login, password))
    };
}

export default connect(mapStateToProps, mapDispathToProps, null, {pure:false})(Layout);