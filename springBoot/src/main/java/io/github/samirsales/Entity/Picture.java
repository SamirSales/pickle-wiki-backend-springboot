package io.github.samirsales.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    // image's title used for image research by text
    private String label;

    private String fileName;

    public Picture(Long _id, String label, String fileName) {
        this._id = _id;
        this.label = label;
        this.fileName = fileName;
    }

    public Picture(){
        this.label = "default label";
        this.fileName = "default file name";
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
