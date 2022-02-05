package by.zvezdina.bodyartsalon.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FormValidatorTest {

    private FormValidator formValidator;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String money;
    private String toRecharge;
    private String imageUrl;
    private String description;

    @BeforeEach
    public void setUp() {
        formValidator = FormValidator.getInstance();
        login = "bob";
        password = "12345678Qwe";
        firstName = "Bob";
        lastName = "Bobson";
        email = "email@gmail.com";
        money = "199.55";
        toRecharge = "99.55";
        imageUrl = "pic.jpg";
        description = "Some long description to be checked on its length";
    }

    @Test
    public void checkLoginTest() {
        boolean actual = formValidator.checkLogin(login);
        assertThat(actual).isTrue();
    }

    @Test
    public void checkPasswordTest() {
        boolean actual = formValidator.checkPassword(password);
        assertThat(actual).isTrue();
    }

    @Test
    public void checkFirstNameTest() {
        boolean actual = formValidator.checkFirstName(firstName);
        assertThat(actual).isTrue();
    }

    @Test
    public void checkLastNameTest() {
        boolean actual = formValidator.checkLastName(lastName);
        assertThat(actual).isTrue();
    }

    @Test
    public void checkEmailTest() {
        boolean actual = formValidator.checkEmail(email);
        assertThat(actual).isTrue();
    }

    @Test
    public void checkMoneyTest() {
        boolean actual = formValidator.checkMoney(money);
        assertThat(actual).isTrue();
    }

    @Test
    public void checkRechargedMoneyTest() {
        boolean actual = formValidator.checkRechargedMoney(toRecharge);
        assertThat(actual).isTrue();
    }

    @Test
    public void checkImageUrlTest() {
        boolean actual = formValidator.checkImageUrl(imageUrl);
        assertThat(actual).isTrue();
    }

    @Test
    public void checkOnMaxLengthTest() {
        boolean actual = formValidator.checkOnMaxLength(description);
        assertThat(actual).isTrue();
    }
}