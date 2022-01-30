package by.zvezdina.bodyartsalon.model.entity;

public class Piercer extends User {

    private String photoUrl;
    private String infoAbout;

    public Piercer() {
    }

    public Piercer(String photoUrl, String infoAbout) {
        this.photoUrl = photoUrl;
        this.infoAbout = infoAbout;
    }

    public Piercer(long userId, String login, String password, String firstName, String lastName, String email,
                   Role role, UserStatus userStatus, boolean isVerified, String photoUrl, String infoAbout) {
        super(userId, login, password, firstName, lastName, email, role, userStatus, isVerified);
        this.photoUrl = photoUrl;
        this.infoAbout = infoAbout;
    }

    public static class Builder {
        private final Piercer piercer = new Piercer();

        public Builder piercerId(long id) {
            piercer.setUserId(id);
            return this;
        }

        public Builder login(String login) {
            piercer.setLogin(login);
            return this;
        }

        public Builder password(String password) {
            piercer.setPassword(password);
            return this;
        }

        public Builder firstName(String firstName) {
            piercer.setFirstName(firstName);
            return this;
        }

        public Builder lastName(String lastName) {
            piercer.setLastName(lastName);
            return this;
        }

        public Builder email(String email) {
            piercer.setEmail(email);
            return this;
        }

        public Builder role(Role role) {
            piercer.setRole(role);
            return this;
        }

        public Builder userStatus(UserStatus status) {
            piercer.setUserStatus(status);
            return this;
        }

        public Builder isVerified(boolean isVerified) {
            piercer.setVerified(isVerified);
            return this;
        }

        public Builder photoUrl(String photoUrl) {
            piercer.setPhotoUrl(photoUrl);
            return this;
        }

        public Builder infoAbout(String infoAbout) {
            piercer.setInfoAbout(infoAbout);
            return this;
        }

        public Piercer build() {
            return piercer;
        }
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getInfoAbout() {
        return infoAbout;
    }

    public void setInfoAbout(String infoAbout) {
        this.infoAbout = infoAbout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Piercer piercer = (Piercer) o;

        if (photoUrl != null ? !photoUrl.equals(piercer.photoUrl) : piercer.photoUrl != null) return false;
        return infoAbout != null ? infoAbout.equals(piercer.infoAbout) : piercer.infoAbout == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (photoUrl != null ? photoUrl.hashCode() : 0);
        result = 31 * result + (infoAbout != null ? infoAbout.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Piercer{");
        sb.append("piercerId=").append(getUserId());
        sb.append(", login='").append(getLogin()).append('\'');
        sb.append(", firstName='").append(getFirstName()).append('\'');
        sb.append(", lastName='").append(getLastName()).append('\'');
        sb.append(", email='").append(getEmail()).append('\'');
        sb.append(", role=").append(getRole());
        sb.append(", userStatus=").append(getUserStatus());
        sb.append(", isVerified=").append(isVerified());
        sb.append(", photoUrl='").append(photoUrl).append('\'');
        sb.append(", infoAbout='").append(infoAbout).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
