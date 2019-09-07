package com.response;;
import java.util.List;


public class Item {

    private String name;
    private int id;
    private Float price;
    private String modeShop;
    private int stock;
    private List<String> img;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
    public Float getPrice() {
        return price;
    }

    public void setModeShop(String modeShop) {
        this.modeShop = modeShop;
    }
    public String getModeShop() {
        return modeShop;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    public int getStock() {
        return stock;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }
    public List<String> getImg() {
        return img;
    }

}