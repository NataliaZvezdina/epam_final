package by.zvezdina.bodyartsalon.model.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Client extends User {

    private BigDecimal money;
    private long discountId;


    public Client() {
    }

    public Client(BigDecimal money, long discountId) {
        this.money = money;
        this.discountId = discountId;
    }

    public Client(long userId, String login, String password, String firstName, String lastName, String email, String phone, Role role, UserStatus userStatus, boolean isVerified, BigDecimal money, long discountId) {
        super(userId, login, password, firstName, lastName, email, phone, role, userStatus, isVerified);
        this.money = money;
        this.discountId = discountId;
    }

    public static class Builder {
        private Client client = new Client();

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

        public Builder phone(String phone) {
            client.setPhone(phone);
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



}
