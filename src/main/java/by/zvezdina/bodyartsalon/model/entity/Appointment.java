package by.zvezdina.bodyartsalon.model.entity;

import java.time.LocalDateTime;

public class Appointment {

    private long appointmentId;
    private LocalDateTime dateTime;
    private String notes;
    private long facilityId;
    private long clientId;
    private long piercerId;

    public Appointment() {
    }

    public Appointment(long appointmentId, LocalDateTime dateTime, String notes, long facilityId,
                       long clientId, long piercerId) {
        this.appointmentId = appointmentId;
        this.dateTime = dateTime;
        this.notes = notes;
        this.facilityId = facilityId;
        this.clientId = clientId;
        this.piercerId = piercerId;
    }

    public static class Builder {
        private final Appointment appointment = new Appointment();

        public Builder appointmentId(long appointmentId) {
            appointment.appointmentId = appointmentId;
            return this;
        }

        public Builder datetime(LocalDateTime dateTime) {
            appointment.dateTime = dateTime;
            return this;
        }

        public Builder notes(String notes) {
            appointment.notes = notes;
            return this;
        }

        public Builder facilityId(long facilityId) {
            appointment.facilityId = facilityId;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(long facilityId) {
        this.facilityId = facilityId;
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
        if (facilityId != that.facilityId) return false;
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
        result = 31 * result + (int) (facilityId ^ (facilityId >>> 32));
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
        sb.append(", facilityId=").append(facilityId);
        sb.append(", clientId=").append(clientId);
        sb.append(", piercerId=").append(piercerId);
        sb.append('}');
        return sb.toString();
    }
}
