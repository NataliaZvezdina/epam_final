package by.zvezdina.bodyartsalon.model.entity;

import java.sql.Date;

public class Feedback extends AbstractEntity {

    private long feedbackId;
    private String content;
    private Date createdAt;
    private long piercerId;
    private long clientId;

    public Feedback() {
    }

    public Feedback(long feedbackId, String content, Date createdAt, long piercerId, long clientId) {
        this.feedbackId = feedbackId;
        this.content = content;
        this.createdAt = createdAt;
        this.piercerId = piercerId;
        this.clientId = clientId;
    }

    public static class Builder {
        private Feedback feedback = new Feedback();

        public Builder feedbackId(long feedbackId) {
            feedback.feedbackId = feedbackId;
            return this;
        }

        public Builder content(String content) {
            feedback.content = content;
            return this;
        }

        public Builder createdAt(Date createdAt) {
            feedback.createdAt = createdAt;
            return this;
        }

        public Builder piercerId(long piercerId) {
            feedback.piercerId = piercerId;
            return this;
        }

        public Builder clientId(long clientId) {
            feedback.clientId = clientId;
            return this;
        }

        public Feedback build() {
            return feedback;
        }
    }

    public long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public long getPiercerId() {
        return piercerId;
    }

    public void setPiercerId(long piercerId) {
        this.piercerId = piercerId;
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

        Feedback feedback = (Feedback) o;

        if (feedbackId != feedback.feedbackId) return false;
        if (piercerId != feedback.piercerId) return false;
        if (clientId != feedback.clientId) return false;
        if (content != null ? !content.equals(feedback.content) : feedback.content != null) return false;
        return createdAt != null ? createdAt.equals(feedback.createdAt) : feedback.createdAt == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (feedbackId ^ (feedbackId >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (int) (piercerId ^ (piercerId >>> 32));
        result = 31 * result + (int) (clientId ^ (clientId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Feedback{");
        sb.append("feedbackId=").append(feedbackId);
        sb.append(", content='").append(content).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append(", piercerId=").append(piercerId);
        sb.append(", clientId=").append(clientId);
        sb.append('}');
        return sb.toString();
    }
}
