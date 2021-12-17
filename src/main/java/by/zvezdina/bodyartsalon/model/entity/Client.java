package by.zvezdina.bodyartsalon.model.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Client extends User {

    private Date registrationDate;
    private Date birthDate;
    private BigDecimal money;
    private long discountId;


    public Client() {
    }

    public Client(Date registrationDate, Date birthDate, BigDecimal money, long discountId) {
        this.registrationDate = registrationDate;
        this.birthDate = birthDate;
        this.money = money;
        this.discountId = discountId;
    }

    public Client(long userId, String login, String password, String firstName, String lastName, String email, String phone, Role role, UserStatus userStatus, Date registrationDate, Date birthDate, BigDecimal money, long discountId) {
        super(userId, login, password, firstName, lastName, email, phone, role, userStatus);
        this.registrationDate = registrationDate;
        this.birthDate = birthDate;
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

        public Builder registrationDate(Date registrationDate) {
            client.registrationDate = registrationDate;
            return this;
        }

        public Builder birthDate(Date birthDate) {
            client.birthDate = birthDate;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
        if (registrationDate != null ? !registrationDate.equals(client.registrationDate) : client.registrationDate != null)
            return false;
        if (birthDate != null ? !birthDate.equals(client.birthDate) : client.birthDate != null) return false;
        return money != null ? money.equals(client.money) : client.money == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (int) (discountId ^ (discountId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("registrationDate=").append(registrationDate);
        sb.append(", birthDate=").append(birthDate);
        sb.append(", money=").append(money);
        sb.append(", discountId=").append(discountId);
        sb.append('}');
        return sb.toString();
    }
}
