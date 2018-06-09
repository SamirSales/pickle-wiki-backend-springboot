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
            active: false
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
            }
        });

        // clean the fields
        document.getElementById("inputLoginModal").value = "";
        document.getElementById("inputPasswordModal").value = "";
    }

    login = (login, password) => {
        if(login === 'admin' && password === '123456'){
            
            console.log("login = "+login);
            console.log("password = "+password);
            console.log("Acesso autorizado.");

            this.setState( {
                user: {
                    name: 'Francisco',
                    login: 'chico',
                    password: '123456'
                }
            });

            this.closeDialogLogin();
            return true;
        }
        
        console.log("login = "+login);
        console.log("password = "+password);
        console.log("Acesso negado.");
        return false;
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
                    confirm={this.login} 
                    cancel={this.closeDialogLogin} />
            </Aux>
        );
    }  
}

export default Layout;