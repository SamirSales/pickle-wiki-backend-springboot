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
        Picture picture1 = new Picture(1L, "Lua","1.png");
        pictures.add(picture1);

        Picture picture2 = new Picture(2L, "Vênus","2.jpg");
        pictures.add(picture2);

        Picture picture3 = new Picture(3L, "Saturno", "3.jpeg");
        pictures.add(picture3);

        Picture picture4 = new Picture(4L, "Terra", "4.gif");
        pictures.add(picture4);
    }

    @Override
    public Picture getPictureById(Long id) {

        for(Picture picture : pictures){
            if(picture.get_id().equals(id)){
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
    public List<Picture> getPicturesBySearch(String search) {
        ArrayList<Picture> pictureArrayList = new ArrayList<>();

        for(Picture picture : pictures){
            if(picture.getLabel().toLowerCase().contains(search.toLowerCase())){
                pictureArrayList.add(picture);
            }
        }

        return pictureArrayList;
    }

    @Override
    public Picture insertPicture(Picture picture) {
        long newId = 1;

        for(Picture pic : pictures){
            newId = pic.get_id() > newId ? pic.get_id() : newId;
        }

        picture.set_id(newId + 1);
        pictures.add(picture);
        return picture;
    }

    @Override
    public void removePictureById(long id) {

        for(int i = 0; i<pictures.size(); i++){
            if(pictures.get(i).get_id().equals(id)){
                pictures.remove(i);
                break;
            }
        }
    }

    @Override
    public void updatePicture(Picture picture) {
        for(int i = 0; i<pictures.size(); i++){
            if(pictures.get(i).get_id().equals(picture.get_id())){
                pictures.remove(i);
                pictures.add(i, picture);
                break;
            }
        }
    }
}
