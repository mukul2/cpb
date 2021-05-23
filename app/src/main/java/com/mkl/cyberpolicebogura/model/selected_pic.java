package com.mkl.cyberpolicebogura.model;

/**
 * Created by mkl on 2/13/2019.
 */

public class selected_pic {
    String link;
    boolean isSelected=false;

    public selected_pic(String link, boolean isSelected) {
        this.link = link;
        this.isSelected = isSelected;
    }

    public selected_pic() {
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
