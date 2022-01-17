package by.zvezdina.bodyartsalon.model.entity;

import java.math.BigDecimal;

public class OrderItem {

    private long orderId;
    private long jewelryId;
    private int quantity;
    private BigDecimal itemPrice;

    public OrderItem() {
    }

    public OrderItem(long orderId, long jewelryId, int quantity, BigDecimal itemPrice) {
        this.orderId = orderId;
        this.jewelryId = jewelryId;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public static class Builder {
        private OrderItem orderItem = new OrderItem();

        public Builder orderId(long orderId) {
            orderItem.orderId = orderId;
            return this;
        }

        public Builder jewelryId(long jewelryId) {
            orderItem.jewelryId = jewelryId;
            return this;
        }

        public Builder quantity(int quantity) {
            orderItem.quantity = quantity;
            return this;
        }

        public Builder itemPrice(BigDecimal itemPrice) {
            orderItem.itemPrice = itemPrice;
            return this;
        }

        public OrderItem build() {
            return orderItem;
        }
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getJewelryId() {
        return jewelryId;
    }

    public void setJewelryId(long jewelryId) {
        this.jewelryId = jewelryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (orderId != orderItem.orderId) return false;
        if (jewelryId != orderItem.jewelryId) return false;
        if (quantity != orderItem.quantity) return false;
        return itemPrice != null ? itemPrice.equals(orderItem.itemPrice) : orderItem.itemPrice == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (int) (jewelryId ^ (jewelryId >>> 32));
        result = 31 * result + quantity;
        result = 31 * result + (itemPrice != null ? itemPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderItem{");
        sb.append("orderId=").append(orderId);
        sb.append(", jewelryId=").append(jewelryId);
        sb.append(", quantity=").append(quantity);
        sb.append(", itemPrice=").append(itemPrice);
        sb.append('}');
        return sb.toString();
    }
}
