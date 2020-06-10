package com.example.customlistview;

public class dataProvider {
    private int images;
    private String names;

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public dataProvider(int images, String names) {
        this.setImages(images);
        this.setNames(names);
    }
}
