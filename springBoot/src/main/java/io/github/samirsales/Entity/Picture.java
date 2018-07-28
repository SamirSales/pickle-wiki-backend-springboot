package io.github.samirsales.Entity;

public class Picture {

    private Long id;
    private String label;
    private String fileName;

    public Picture(Long id, String label, String fileName) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
