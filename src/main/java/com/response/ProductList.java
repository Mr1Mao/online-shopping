package com.response;

 public class ProductList {

    private int id;
    private String name;
    private String img;
    private Float price;

     public Float getPrice() {
         return price;
     }

     public void setPrice(Float price) {
         this.price = price;
     }

     public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setImg(String img) {
        this.img = img;
    }
    public String getImg() {
        return img;
    }

}