
import React from 'react';

import image from '../../assets/img/image-icon.png';

import './TemplateButton.css';

const templateButton = ( props ) => {

    let text = "Lorem ipsum dolor sit amet, nam quas neglegentur in, duis dicunt causae has eu. Eos eu cotidieque disputando. Dico efficiendi ei his. Ea per iriure singulis voluptatibus, ea alia nulla quo.Est ex sale ceteros. Ius te malis expetenda, ex paulo similique cum. Ea quis oporteat torquatos sed. Quod vivendum vituperata ut vix. In quod adversarium mediocritatem sit, ut utroque persequeris definitionem eum. Nonumy soluta cu vim, ad nobis patrioque scriptorem qui.";

    let layout = <p className='temp-btn-text'>{text} {text}</p>;

    if(props.type === 'image'){
        layout = <img src={image} alt="Template com imagem" height="90" width="115" />;
    }else if(props.type === 'text-image'){
        layout = <div>
                <div className='temp-btn-div-img-text-text'><p className='temp-btn-text'>{text}</p></div>
                <div className='temp-btn-div-img-text-img'><img src={image} alt="Template com imagem" height="90" width="50" /></div>
            </div>;
    }

    return (
        <button className='templateButton'>
            <div className='temp-btn-little-div'>
            <p className='temp-btn-little-title'>{props.title}</p>
            {layout}               
            </div>        
        </button>
    )
};
export default templateButton;
