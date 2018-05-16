import React, { Component } from 'react';

import Aux from '../../hoc/Aux';
import NavBar from '../NavBar/NavBar';
import TopBar from '../TopBar/TopBar';
import LogoutModal from '../../components/UI/Modal/LogoutModal/LogoutModal';
import LoginModal from '../../components/UI/Modal/LoginModal/LoginModal';

import './Layout.css';

import { ScreenStatus } from '../App';

export const AuthContext = React.createContext(false);

class Layout extends Component {

    state = {
        user: {
            name: 'Francisco',
            login: 'chico',
            password: '123456'
        },
        dialogLogout:{
            active: false,
            funConfirm: null
        },
        dialogLogin:{
            active: true,
            funConfirm: null
        },
        userAccess:{
            login: '',
            password: ''
        }
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
            },
            userAccess: {
                login: '',
                password: ''
            }
        });
    }

    login = () => {
        if(this.state.userAccess.login === 'admin' && this.state.userAccess.password === '123456'){
            
            console.log("login = "+this.state.userAccess.login);
            console.log("password = "+this.state.userAccess.password);
            console.log("Acesso autorizado.");
        }else{
            console.log("login = "+this.state.userAccess.login);
            console.log("password = "+this.state.userAccess.password);
            console.log("Acesso negado.");
        }
    }

    onChangeLoginField = (event) => {
        this.setState({
            userAccess:{
                login: event.target.value
            }
        });

        // console.log("login: "+this.state.userAccess.login);
    }

    onChangePasswordField = (event) => {
        this.setState({
            userAccess:{
                password: event.target.value
            }
        });

        // console.log("password: "+this.state.userAccess.password);
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
            },
            user: null
        });

        this.props.screenStatusEvent(ScreenStatus.SimpleTemplate);
    }

    render() {
        return (
            <Aux>
                <NavBar home={this.props.url}
                    title={this.props.appName}
                    itemClick={this.props.navItemClick} />

                <div id='main-content'>
                    <AuthContext.Provider value={this.state.user !== null}>
                        <TopBar home={this.state.url} 
                            user={this.state.user}
                            logout={this.dialogLogout}
                            login={this.dialogLogin}
                            screenStatus={this.props.screenStatus}
                            screenStatusEvent={this.props.screenStatusEvent} />
                    </AuthContext.Provider>

                    <main id='container'>{this.props.children}</main>
                </div>
   
                <LogoutModal 
                    active={this.state.dialogLogout.active}
                    confirm={this.state.dialogLogout.funConfirm} 
                    cancel={this.closeDialogLogout} />

                <LoginModal 
                    active={this.state.dialogLogin.active}
                    onchangelogin={this.onChangeLoginField}
                    onchangepassword={this.onChangePasswordField}
                    confirm={this.login} 
                    cancel={this.closeDialogLogin} />
            </Aux>
        );
    }  
}

export default Layout;