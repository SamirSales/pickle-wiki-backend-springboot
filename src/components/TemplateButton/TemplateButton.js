
import React from 'react';

import './TemplateButton.css';

const templateButton = ( props ) => {

    let layout = <p className='temp-btn-text'>Lorem ipsum dolor sit amet, nam quas neglegentur in, duis dicunt causae has eu. Eos eu cotidieque disputando. Dico efficiendi ei his. Ea per iriure singulis voluptatibus, ea alia nulla quo.
        Est ex sale ceteros. Ius te malis expetenda, ex paulo similique cum. Ea quis oporteat torquatos sed. Quod vivendum vituperata ut vix. In quod adversarium mediocritatem sit, ut utroque persequeris definitionem eum. Nonumy soluta cu vim, ad nobis patrioque scriptorem qui.</p>;

    if(props.type === 'image'){
        layout = <p className='temp-btn-text'></p>;
    }else if(props.type === 'text-image'){
        layout = <p className='temp-btn-text'></p>;
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
