import React, { PureComponent } from 'react';

import Aux from '../../../../hoc/Aux';
import Backdrop from '../../Backdrop/Backdrop';
import './EditUserModal.css';

class EditUserModal extends PureComponent {
    
    render(){
        return (
            <Aux>
                <Backdrop active={this.props.active} click={this.props.cancel} />
                
                <div className="editUserModal edit-user-modal-animation" 
                    style={{ display: this.props.active ? 'block' : 'none' }}>      
    
                    <div className="container">
                        <h3>{this.props.title}</h3>
                        <input type="text" placeholder="Nome" />
                        <input type="text" placeholder="Login" />

                        <select>
                            <option value="MALE">Masculino</option>
                            <option value="FEMALE">Feminino</option>
                        </select>

                        <input type="password" placeholder="Senha" />
                        <input type="password" placeholder="Confirme a senha" />
    
                        <button type="submit">Salvar</button>
                    </div>
                </div>    
            </Aux>   
        );
    }

}

export default EditUserModal;
