package by.zvezdina.bodyartsalon.model.entity;

public class Admin extends User {

    public Admin() {
    }

    public static class Builder {
        private Admin admin = new Admin();

        public Builder adminId(long adminId) {
            admin.setUserId(adminId);
            return this;
        }

        public Builder login(String login) {
            admin.setLogin(login);
            return this;
        }

        public Builder password(String password) {
            admin.setPassword(password);
            return this;
        }

        public Builder firstName(String firsName) {
            admin.setFirstName(firsName);
            return this;
        }

        public Builder lastName(String lastName) {
            admin.setLastName(lastName);
            return this;
        }

        public Builder email(String email) {
            admin.setEmail(email);
            return this;
        }

        public Builder role(Role role) {
            admin.setRole(role);
            return this;
        }

        public Builder userStatus(UserStatus status) {
            admin.setUserStatus(status);
            return this;
        }

        public Admin build() {
            return admin;
        }
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Admin{");
        sb.append("adminId=").append(getUserId());
        sb.append(", login='").append(getLogin());
        sb.append(", password='").append("********");
        sb.append(", firstName='").append(getFirstName());
        sb.append(", lastName='").append(getLastName());
        sb.append(", email='").append(getEmail());
        sb.append(", role=").append(getRole());
        sb.append(", userStatus=").append(getUserStatus());
        sb.append('}');
        return sb.toString();
    }
}
