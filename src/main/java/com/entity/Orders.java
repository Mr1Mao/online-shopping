package com.entity;

public class Orders {
    private Integer order_id;
    private Integer num;
    private String leaving_message;
    private Integer id;
    private Integer pro_id;
    private Integer add_id;
    private Product product;
    private char status;

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getLeaving_message() {
        return leaving_message;
    }

    public void setLeaving_message(String leaving_message) {
        this.leaving_message = leaving_message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPro_id() {
        return pro_id;
    }

    public void setPro_id(Integer pro_id) {
        this.pro_id = pro_id;
    }

    public Integer getAdd_id() {
        return add_id;
    }

    public void setAdd_id(Integer add_id) {
        this.add_id = add_id;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "order_id=" + order_id +
                ", num=" + num +
                ", leaving_message='" + leaving_message + '\'' +
                ", id=" + id +
                ", pro_id=" + pro_id +
                ", add_id=" + add_id +
                ", product=" + product +
                ", status=" + status +
                '}';
    }
}
