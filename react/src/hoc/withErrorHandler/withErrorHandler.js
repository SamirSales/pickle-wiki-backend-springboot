import React, {Component} from 'react';

import Modal from '../../components/UI/Modal/Modal';
import Aux from '../Aux/Aux';

const withErrorHandler = (WrappedComponent, axios) => {
    return class extends Component {

        state = {
            error: null,
            modal: false
        }

        componentDidMount(){
            axios.interceptors.request.use(req => {
                this.setState({error: null, modal: false});
                console.log('req', req);
                return req;
            });

            axios.interceptors.response.use(res => res, error => {
                this.setState({error: error, modal: true});
                console.log('error', error);
            });
        }

        errorConfirmedHandler = () => {
            this.setState({error: null, modal: false});
        }
        
        render() {

            return (
                <Aux>
                    <Modal active={this.state.modal}
                        marginTop='15%'
                        marginLeft='calc(50% - 221px)'
                        cancel={this.errorConfirmedHandler}>
                        {this.state.error ? this.state.error.message : ''}

                        Erro aqui
                    </Modal>
                    <WrappedComponent {...this.props}/>
                </Aux>
            );
        }
    }
}

export default withErrorHandler;