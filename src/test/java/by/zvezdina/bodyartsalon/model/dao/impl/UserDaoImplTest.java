package by.zvezdina.bodyartsalon.model.dao.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.dao.UserDao;
import by.zvezdina.bodyartsalon.model.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class UserDaoImplTest {

    private static final long USER_ID = 5;
    private static final int DEFINED_PAGE = 1;
    private static final String LOGIN_TO_FIND_USER = "bobbie";
    private static final String EMAIL_TO_FIND_USER = "email5@gmail.com";
    private static final String PASSWORD_TO_UPDATE = "SD00ERkik90nk";
    private static final int EXPECTED_ROWS_UPDATED = 1;

    @Mock
    private UserDao userDaoMock;
    private List<User> expectedUsers;
    private User expectedUser;
    private User firstElementOfExpectedList;
    private User secondElementOfExpectedList;
    private User adminToCreate;
    private User toUpdate;
    private User expectedUpdated;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        expectedUser = new User.Builder()
                .userId(5)
                .email("email5@gmail.com")
                .firstName("Bob")
                .lastName("Bobson")
                .login("bobbie")
                .password("12345678Qwe")
                .role(Role.ADMIN)
                .userStatus(UserStatus.ACTIVE)
                .isVerified(true)
                .build();

        firstElementOfExpectedList = new User.Builder()
                .userId(1)
                .email("email1@gmail.com")
                .firstName("Bob")
                .lastName("Bobson")
                .login("bobbobson")
                .password("12345678Qwe")
                .role(Role.CLIENT)
                .userStatus(UserStatus.ACTIVE)
                .isVerified(true)
                .build();

        secondElementOfExpectedList = new User.Builder()
                .userId(2)
                .email("email2@gmail.com")
                .firstName("Bob")
                .lastName("Bobson")
                .login("bobbobson")
                .password("12345678Qwe")
                .role(Role.PIERCER)
                .userStatus(UserStatus.ACTIVE)
                .isVerified(true)
                .build();

        adminToCreate = new User.Builder()
                .email("email5@gmail.com")
                .firstName("Bob")
                .lastName("Bobson")
                .login("bobbie")
                .password("12345678Qwe")
                .role(Role.ADMIN)
                .userStatus(UserStatus.ACTIVE)
                .isVerified(true)
                .build();

        toUpdate = new User.Builder()
                .userId(7)
                .email("someNewEmail@gmail.com")
                .firstName("Tom")
                .lastName("Tomson")
                .login("tommie")
                .password("12345678Asd")
                .role(Role.CLIENT)
                .userStatus(UserStatus.ACTIVE)
                .isVerified(true)
                .build();

        expectedUpdated = toUpdate;
        expectedUsers = List.of(firstElementOfExpectedList, secondElementOfExpectedList);
    }

    @Test
    public void findByIdTest() throws DaoException {
        when(userDaoMock.findById(USER_ID)).thenReturn(expectedUser);
        User actual = userDaoMock.findById(USER_ID);
        assertThat(actual).isEqualTo(expectedUser);
    }

    @Test
    public void findAllTest() throws DaoException {
        when(userDaoMock.findAll()).thenReturn(expectedUsers);
        List<User> actual = userDaoMock.findAll();
        assertThat(actual).containsExactly(firstElementOfExpectedList, secondElementOfExpectedList);
    }

    @Test
    public void findAllOnPageTest() throws DaoException {
        when(userDaoMock.findAll(DEFINED_PAGE)).thenReturn(expectedUsers);
        List<User> actual = userDaoMock.findAll(DEFINED_PAGE);
        assertThat(actual).containsExactly(firstElementOfExpectedList, secondElementOfExpectedList);
    }

    @Test
    public void createAdminTest() throws DaoException {
        when(userDaoMock.createAdmin(adminToCreate)).thenReturn(expectedUser);
        User actual = userDaoMock.createAdmin(adminToCreate);
        assertThat(actual).isEqualTo(expectedUser);
    }

    @Test
    public void updateTest() throws DaoException {
        when(userDaoMock.update(toUpdate)).thenReturn(expectedUpdated);
        User actual = userDaoMock.update(toUpdate);
        assertThat(actual).isEqualTo(expectedUpdated);
    }

    @Test
    public void deleteByIdTest() throws DaoException {
        when(userDaoMock.deleteById(USER_ID))
                .thenReturn(EXPECTED_ROWS_UPDATED);
        int actual = userDaoMock.deleteById(USER_ID);
        assertThat(actual).isEqualTo(EXPECTED_ROWS_UPDATED);
    }

    @Test
    public void restoreByIdTest() throws DaoException {
        when(userDaoMock.restoreById(USER_ID))
                .thenReturn(EXPECTED_ROWS_UPDATED);
        int actual = userDaoMock.restoreById(USER_ID);
        assertThat(actual).isEqualTo(EXPECTED_ROWS_UPDATED);
    }

    @Test
    public void findByLoginTest() throws DaoException {
        when(userDaoMock.findByLogin(LOGIN_TO_FIND_USER)).thenReturn(expectedUser);
        User actual = userDaoMock.findByLogin(LOGIN_TO_FIND_USER);
        assertThat(actual).isEqualTo(expectedUser);
    }

    @Test
    public void findByEmailTest() throws DaoException {
        when(userDaoMock.findByEmail(EMAIL_TO_FIND_USER)).thenReturn(expectedUser);
        User actual = userDaoMock.findByEmail(EMAIL_TO_FIND_USER);
        assertThat(actual).isEqualTo(expectedUser);
    }

    @Test
    public void updatePasswordTest() throws DaoException {
        when(userDaoMock.updatePassword(USER_ID, PASSWORD_TO_UPDATE))
                .thenReturn(EXPECTED_ROWS_UPDATED);
        int actual = userDaoMock.updatePassword(USER_ID, PASSWORD_TO_UPDATE);
        assertThat(actual).isEqualTo(EXPECTED_ROWS_UPDATED);
    }
}