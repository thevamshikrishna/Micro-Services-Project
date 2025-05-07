package com.mentorassignment.RetailApplication.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="orders")
public class Order{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int orderId;

    public int userId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;

    public double total;
    public String status;
    public String orderDate;

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "Order{" +
                "items=" + items +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", total=" + total +
                ", status='" + status + '\'' +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }
}

// class Root{
//    @JsonProperty("Order")
//    public Order order;
//}

