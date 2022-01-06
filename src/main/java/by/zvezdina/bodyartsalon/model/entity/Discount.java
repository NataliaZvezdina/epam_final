package by.zvezdina.bodyartsalon.model.entity;

public class Discount {

    private long discountId;
    private int value;

    public Discount() {
    }

    public Discount(long discountId, int value) {
        this.discountId = discountId;
        this.value = value;
    }

    public long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount = (Discount) o;

        if (discountId != discount.discountId) return false;
        return value == discount.value;
    }

    @Override
    public int hashCode() {
        int result = (int) (discountId ^ (discountId >>> 32));
        result = 31 * result + value;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Discount{");
        sb.append("discountId=").append(discountId);
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}
