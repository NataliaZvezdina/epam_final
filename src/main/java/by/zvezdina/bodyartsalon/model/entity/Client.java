package by.zvezdina.bodyartsalon.model.entity;

import java.math.BigDecimal;

public class Client extends User {

    private BigDecimal money;
    private long discountId;

    public Client() {
    }

    public Client(BigDecimal money, long discountId) {
        this.money = money;
        this.discountId = discountId;
    }

    public Client(long userId, String login, String password, String firstName, String lastName, String email,
                  Role role, UserStatus userStatus, boolean isVerified, BigDecimal money, long discountId) {
        super(userId, login, password, firstName, lastName, email, role, userStatus, isVerified);
        this.money = money;
        this.discountId = discountId;
    }

    public static class Builder {
        private final Client client = new Client();

        public Builder clientId(long clientId) {
            client.setUserId(clientId);
            return this;
        }

        public Builder login(String login) {
            client.setLogin(login);
            return this;
        }

        public Builder password(String password) {
            client.setPassword(password);
            return this;
        }

        public Builder firstName(String firstName) {
            client.setFirstName(firstName);
            return this;
        }

        public Builder lastName(String lastName) {
            client.setLastName(lastName);
            return this;
        }

        public Builder email(String email) {
            client.setEmail(email);
            return this;
        }

        public Builder role(Role role) {
            client.setRole(role);
            return this;
        }

        public Builder userStatus(UserStatus status) {
            client.setUserStatus(status);
            return this;
        }

        public Builder isVerified(boolean isVerified) {
            client.setVerified(isVerified);
            return this;
        }

        public Builder money(BigDecimal money) {
            client.money = money;
            return this;
        }

        public Builder discountId(long discountId) {
            client.discountId = discountId;
            return this;
        }

        public Client build() {
            return client;
        }
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Client client = (Client) o;

        if (discountId != client.discountId) return false;
        return money != null ? money.equals(client.money) : client.money == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (int) (discountId ^ (discountId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("clientId=").append(getUserId());
        sb.append(", login='").append(getLogin()).append('\'');
        sb.append(", firstName='").append(getFirstName()).append('\'');
        sb.append(", lastName='").append(getLastName()).append('\'');
        sb.append(", email='").append(getEmail()).append('\'');
        sb.append(", role=").append(getRole());
        sb.append(", userStatus=").append(getUserStatus());
        sb.append(", isVerified=").append(isVerified());
        sb.append("money=").append(money);
        sb.append(", discountId=").append(discountId);
        sb.append('}');
        return sb.toString();
    }
}
