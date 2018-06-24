import React from 'react';

import './NotFoundPage.css';
import logo from '../../assets/img/pickles.png';

const notFoundPage = ( props ) => {

    return (
        <div className="not-found-page">
            <h2>Erro 404</h2>
            <img src={logo} alt="Pickle Wiki" height="126" width="126" />
            <p>A URL requisitada não pode ser encontrada.</p>
        </div>
    )
};
export default notFoundPage;
