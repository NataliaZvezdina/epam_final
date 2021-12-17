package by.zvezdina.bodyartsalon.model.entity;

public class Service extends AbstractEntity {

    private long serviceId;
    private String name;
    private String description;

    public Service() {
    }

    public Service(long serviceId, String name, String description) {
        this.serviceId = serviceId;
        this.name = name;
        this.description = description;
    }

    public static class Builder {
        private Service service = new Service();

        public Builder serviceId(long serviceId) {
            service.serviceId = serviceId;
            return this;
        }

        public Builder name(String name) {
            service.name = name;
            return this;
        }

        public Builder description(String description) {
            service.description = description;
            return this;
        }

        public Service build() {
            return service;
        }
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Service service = (Service) o;

        if (serviceId != service.serviceId) return false;
        if (name != null ? !name.equals(service.name) : service.name != null) return false;
        return description != null ? description.equals(service.description) : service.description == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (serviceId ^ (serviceId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Service{");
        sb.append("serviceId=").append(serviceId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
