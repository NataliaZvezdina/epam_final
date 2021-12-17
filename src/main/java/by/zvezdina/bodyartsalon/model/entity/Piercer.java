package by.zvezdina.bodyartsalon.model.entity;

public class Piercer extends User {

    private String photoUrl;
    private Category category;
    private int rating;

    public Piercer() {
    }

    public Piercer(String photoUrl, Category category, int rating) {
        this.photoUrl = photoUrl;
        this.category = category;
        this.rating = rating;
    }

    public Piercer(long userId, String login, String password, String firstName, String lastName, String email,
                   String phone, Role role, UserStatus userStatus, String photoUrl, Category category,
                   int rating) {
        super(userId, login, password, firstName, lastName, email, phone, role, userStatus);
        this.photoUrl = photoUrl;
        this.category = category;
        this.rating = rating;
    }

    public static class Builder {
        private Piercer piercer = new Piercer();

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

        public Builder phone(String phone) {
            piercer.setPhone(phone);
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

        public Builder photoUrl(String photoUrl) {
            piercer.setPhotoUrl(photoUrl);
            return this;
        }

        public Builder category(Category category) {
            piercer.setCategory(category);
            return this;
        }

        public Builder rating(int rating) {
            piercer.setRating(rating);
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Piercer piercer = (Piercer) o;

        if (rating != piercer.rating) return false;
        if (photoUrl != null ? !photoUrl.equals(piercer.photoUrl) : piercer.photoUrl != null) return false;
        return category == piercer.category;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (photoUrl != null ? photoUrl.hashCode() : 0);
        result = 31 * result + category.hashCode();
        result = 31 * result + rating;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Piercer{");
        sb.append("piercerId=").append(getUserId());
        sb.append(", login='").append(getLogin());
        sb.append(", password='").append("********");
        sb.append(", firstName='").append(getFirstName());
        sb.append(", lastName='").append(getLastName());
        sb.append(", email='").append(getEmail());
        sb.append(", phone='").append(getPhone());
        sb.append(", role=").append(getRole());
        sb.append(", userStatus=").append(getUserStatus());
        sb.append("photoUrl='").append(photoUrl);
        sb.append(", category=").append(category);
        sb.append(", rating=").append(rating);
        sb.append('}');
        return sb.toString();
    }
}
