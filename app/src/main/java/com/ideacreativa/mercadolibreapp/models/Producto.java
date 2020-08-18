package com.ideacreativa.mercadolibreapp.models;

public class Producto {
    public String id;
    public String title;
    public String thumbnail;
    public String price;
    public String sold_quantity;

    public Producto(String id, String title, String thumbnail, String price, String sold_quantity) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.price = price;
        this.sold_quantity = sold_quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSold_quantity() {
        return sold_quantity;
    }

    public void setSold_quantity(String sold_quantity) {
        this.sold_quantity = sold_quantity;
    }
}
