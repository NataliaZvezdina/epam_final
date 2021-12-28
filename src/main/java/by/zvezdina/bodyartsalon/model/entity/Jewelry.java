package by.zvezdina.bodyartsalon.model.entity;

import java.math.BigDecimal;

public class Jewelry {

    private long jewelryId;
    private String type;
    private String imageUrl;
    private String description;
    private String manufacturer;
    private BigDecimal price;

    public Jewelry() {
    }

    public Jewelry(long jewelryId, String type, String imageUrl, String description, String manufacturer,
                   BigDecimal price) {
        this.jewelryId = jewelryId;
        this.type = type;
        this.imageUrl = imageUrl;
        this.description = description;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public static class Builder {
        private Jewelry jewelry = new Jewelry();

        public Builder jewelryId(long id) {
            jewelry.jewelryId = id;
            return this;
        }

        public Builder type(String type) {
            jewelry.type = type;
            return this;
        }

        public Builder imageUrl(String url) {
            jewelry.imageUrl = url;
            return this;
        }

        public Builder description(String description) {
            jewelry.description = description;
            return this;
        }

        public Builder manufacturer(String manufacturer) {
            jewelry.manufacturer = manufacturer;
            return this;
        }

        public Builder price(BigDecimal price) {
            jewelry.price = price;
            return this;
        }

        public Jewelry build() {
            return jewelry;
        }
    }

    public long getJewelryId() {
        return jewelryId;
    }

    public void setJewelryId(long jewelryId) {
        this.jewelryId = jewelryId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jewelry jewelry = (Jewelry) o;

        if (jewelryId != jewelry.jewelryId) return false;
        if (type != null ? !type.equals(jewelry.type) : jewelry.type != null) return false;
        if (imageUrl != null ? !imageUrl.equals(jewelry.imageUrl) : jewelry.imageUrl != null) return false;
        if (description != null ? !description.equals(jewelry.description) : jewelry.description != null) return false;
        if (manufacturer != null ? !manufacturer.equals(jewelry.manufacturer) : jewelry.manufacturer != null)
            return false;
        return price != null ? price.equals(jewelry.price) : jewelry.price == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (jewelryId ^ (jewelryId >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Jewelry{");
        sb.append("jewelryId=").append(jewelryId);
        sb.append(", type='").append(type).append('\'');
        sb.append(", imageUrl='").append(imageUrl).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", manufacturer='").append(manufacturer).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
