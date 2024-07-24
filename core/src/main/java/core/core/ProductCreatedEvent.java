package core.core;

import java.math.BigDecimal;

public class ProductCreatedEvent {

    private String productId;
    private String tittle;
    private BigDecimal price;
    private Integer quantity;


    public ProductCreatedEvent() {
    }

    public ProductCreatedEvent(String productId, String tittle, BigDecimal price, Integer quantity) {
        this.productId = productId;
        this.tittle = tittle;
        this.price = price;
        this.quantity = quantity;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductCreatedEvent{" +
                "productId='" + productId + '\'' +
                ", tittle='" + tittle + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}