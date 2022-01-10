package by.zvezdina.bodyartsalon.model.entity;

import java.math.BigDecimal;

public class Facility extends AbstractEntity {

    private long facilityId;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean isAccessible;

    public Facility() {
    }

    public Facility(long facilityId, String name, String description, BigDecimal price, boolean isAccessible) {
        this.facilityId = facilityId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAccessible = isAccessible;
    }

    public static class Builder {
        private Facility facility = new Facility();

        public Builder facilityId(long facilityId) {
            facility.facilityId = facilityId;
            return this;
        }

        public Builder name(String name) {
            facility.name = name;
            return this;
        }

        public Builder description(String description) {
            facility.description = description;
            return this;
        }

        public Builder price(BigDecimal price) {
            facility.price = price;
            return this;
        }

        public Builder accessible(boolean isAccessible) {
            facility.isAccessible = isAccessible;
            return this;
        }

        public Facility build() {
            return facility;
        }
    }

    public long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(long facilityId) {
        this.facilityId = facilityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isAccessible() {
        return isAccessible;
    }

    public void setAccessible(boolean accessible) {
        isAccessible = accessible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Facility facility = (Facility) o;

        if (facilityId != facility.facilityId) return false;
        if (isAccessible != facility.isAccessible) return false;
        if (name != null ? !name.equals(facility.name) : facility.name != null) return false;
        if (description != null ? !description.equals(facility.description) : facility.description != null)
            return false;
        return price != null ? price.equals(facility.price) : facility.price == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (facilityId ^ (facilityId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (isAccessible ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Facility{");
        sb.append("facilityId=").append(facilityId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", isAccessible=").append(isAccessible);
        sb.append('}');
        return sb.toString();
    }
}
