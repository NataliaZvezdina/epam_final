package by.zvezdina.bodyartsalon.model.entity;


import java.sql.Timestamp;

public class Appointment extends AbstractEntity {

    private long appointmentId;
    private Timestamp dateTime;
    private String notes;
    private long serviceId;
    private long clientId;
    private long piercerId;

    public Appointment() {
    }

    public Appointment(long appointmentId, Timestamp dateTime, String notes, long serviceId, long clientId,
                       long piercerId) {
        this.appointmentId = appointmentId;
        this.dateTime = dateTime;
        this.notes = notes;
        this.serviceId = serviceId;
        this.clientId = clientId;
        this.piercerId = piercerId;
    }

    public static class Builder {
        private Appointment appointment = new Appointment();

        public Builder appointmentId(long appointmentId) {
            appointment.appointmentId = appointmentId;
            return this;
        }

        public Builder datetime(Timestamp dateTime) {
            appointment.dateTime = dateTime;
            return this;
        }

        public Builder notes(String notes) {
            appointment.notes = notes;
            return this;
        }

        public Builder serviceId(long serviceId) {
            appointment.serviceId = serviceId;
            return this;
        }

        public Builder clientId(long clientId) {
            appointment.clientId = clientId;
            return this;
        }

        public Builder piercerId(long piercerId) {
            appointment.piercerId = piercerId;
            return this;
        }

        public Appointment build() {
            return appointment;
        }
    }

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getPiercerId() {
        return piercerId;
    }

    public void setPiercerId(long piercerId) {
        this.piercerId = piercerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Appointment that = (Appointment) o;

        if (appointmentId != that.appointmentId) return false;
        if (serviceId != that.serviceId) return false;
        if (clientId != that.clientId) return false;
        if (piercerId != that.piercerId) return false;
        if (dateTime != null ? !dateTime.equals(that.dateTime) : that.dateTime != null) return false;
        return notes != null ? notes.equals(that.notes) : that.notes == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (appointmentId ^ (appointmentId >>> 32));
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (int) (serviceId ^ (serviceId >>> 32));
        result = 31 * result + (int) (clientId ^ (clientId >>> 32));
        result = 31 * result + (int) (piercerId ^ (piercerId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Appointment{");
        sb.append("appointmentId=").append(appointmentId);
        sb.append(", dateTime=").append(dateTime);
        sb.append(", notes='").append(notes).append('\'');
        sb.append(", serviceId=").append(serviceId);
        sb.append(", clientId=").append(clientId);
        sb.append(", piercerId=").append(piercerId);
        sb.append('}');
        return sb.toString();
    }
}
