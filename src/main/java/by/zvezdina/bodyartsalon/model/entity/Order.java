package by.zvezdina.bodyartsalon.model.entity;

import java.time.LocalDate;

public class Order {

    private long orderId;
    private LocalDate date;
    private OrderStatus status;
    private long clientId;

    public Order() {
    }

    public Order(long orderId, LocalDate date, OrderStatus status, long clientId) {
        this.orderId = orderId;
        this.date = date;
        this.status = status;
        this.clientId = clientId;
    }

    public static class Builder {
        private final Order order = new Order();

        public Builder orderId(long orderId) {
            order.orderId = orderId;
            return this;
        }

        public Builder date(LocalDate date) {
            order.date = date;
            return this;
        }

        public Builder status(OrderStatus status) {
            order.status = status;
            return this;
        }

        public Builder clientId(long clientId) {
            order.clientId = clientId;
            return this;
        }

        public Order build() {
            return order;
        }
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (clientId != order.clientId) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        return status == order.status;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (int) (clientId ^ (clientId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", date=").append(date);
        sb.append(", status=").append(status);
        sb.append(", clientId=").append(clientId);
        sb.append('}');
        return sb.toString();
    }
}
