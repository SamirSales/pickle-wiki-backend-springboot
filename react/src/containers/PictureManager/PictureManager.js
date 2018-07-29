import React, { Component } from 'react';

import * as axios from '../../axios-orders';

import { connect } from 'react-redux';
import Thumbnail from '../../components/Thumbnail/Thumbnail';
import Aux from '../../hoc/Aux/Aux';
import './PictureManager.css';

// import { showSnackBar } from '../Layout/Layout';

class PictureManager extends Component {

    state = {
        loading: false,
        pictures: []
    }

    componentDidMount(){
        this.setState({loading: true});
        // eslint-disable-next-line
        axios.getPictures(this.props.tkn).then(res => {
          console.log("pictures", res.data);
          this.setState({pictures: res.data});
          this.setState({loading: false});
        }).catch(error => {
          console.log("error", error);
          this.setState({loading: false});
          this.errorModal('Não foi possível carregar as imagens.');
        });
      }

    imageUpload = () =>{
        console.log("image upload");
        const fd = new FormData();
        fd.append('image', this.state.selectedFile, this.state.selectedFile.name);

        //post request here...
    }

    fileSelectedHandler = event => {
        this.setState({
            selectedFile: event.target.files[0]
        });
    }
 
    render() {

        let thumbnails = this.state.pictures.map(pic => {
            return <Thumbnail key={pic.id} fileName={pic.fileName} alt={pic.label} />;
        });

        if(this.state.pictures.length === 0){
            thumbnails = <p className='empty-content-message'>Nenhuma imagem foi importada para esse artigo.</p>;
        }

        return (
            <Aux>
                <div className='text-editor-markdown'>
                    <h1 className='simple-template-title'><i className="fa fa-image"></i> Imagens</h1>
                </div>  

                {/* className='article-builder-div-content' */}
                <div style={{marginTop: '10px'}}>
                    {thumbnails}
                </div>           

                <br/>
                <input className='fileContainer' type="file" onChange={this.fileSelectedHandler} />
                <button className='article-btn article-btn-topic' onClick={this.imageUpload} >Carregar imagem</button>
            </Aux>
        )
    }    
}

const mapStateToProps = state => {
    return{
        usr: state.usr.user,
        appName: state.app.appName,
        tkn: state.usr.token
    };
}

export default connect(mapStateToProps)(PictureManager);

