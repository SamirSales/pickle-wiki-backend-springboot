package io.github.samirsales.Dao;

import io.github.samirsales.Entity.Picture;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Qualifier("fakeData")
public class FakePictureDaoImpl implements PictureDao {

    private static ArrayList<Picture> pictures = new ArrayList<>();

    public FakePictureDaoImpl(){
        Picture picture1 = new Picture(1L, "Desenho 1");
        pictures.add(picture1);

        Picture picture2 = new Picture(2L, "Image 2");
        pictures.add(picture2);

        Picture picture3 = new Picture(3L, "Figura 3");
        pictures.add(picture3);
    }

    @Override
    public Picture getPictureById(Long id) {

        for(Picture picture : pictures){
            if(picture.getId().equals(id)){
                return picture;
            }
        }
        return null;
    }

    @Override
    public List<Picture> getAll() {
        return pictures;
    }

    @Override
    public List<Picture> getPicturesByToken(String token) {
        ArrayList<Picture> pictureArrayList = new ArrayList<>();

        for(Picture picture : pictures){
            if(picture.getLabel().contains(token)){
                pictureArrayList.add(picture);
            }
        }

        return pictureArrayList;
    }

    @Override
    public void insertPicture(Picture picture) {
        long newId = 1;

        for(Picture pic : pictures){
            newId = pic.getId() > newId ? pic.getId() : newId;
        }

        picture.setId(newId + 1);
        pictures.add(picture);
    }

    @Override
    public void deletePicture(Picture picture) {

        for(int i = 0; i<pictures.size(); i++){
            if(pictures.get(i).getId().equals(picture.getId())){
                pictures.remove(i);
                break;
            }
        }
    }

    @Override
    public void editPicture(Picture picture) {
        for(int i = 0; i<pictures.size(); i++){
            if(pictures.get(i).getId().equals(picture.getId())){
                pictures.remove(i);
                pictures.add(i, picture);
                break;
            }
        }
    }
}
