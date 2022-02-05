package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Role;
import by.zvezdina.bodyartsalon.model.entity.User;
import by.zvezdina.bodyartsalon.model.entity.UserStatus;
import by.zvezdina.bodyartsalon.model.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    private static final String LOGIN = "Bob";
    private static final String PASSWORD = "12345678Qwe";
    private static final String EMAIL = "email5@gmail.com";
    private static final long USER_ID = 5;
    private static final String PASSWORD_TO_UPDATE = "SD00ERkik90nk";
    private static final boolean EXPECTED = true;
    private final Map<String, String> passwordsToCheck =
            Map.of("password", "12345678Qwe", "repeatPassword", "12345678Qwe");
    private final Map<String, String> userDataToCheck =
            Map.of("firstName", "Bob", "lastName", "Bobson", "email", "email@gamil.com",
                    "login", "bob", "password", "12345678Qwe");

    @Mock
    private UserService userServiceMock;
    private Optional<User> found;
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

        found = Optional.of(expectedUser);

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
    public void sighInTest() throws ServiceException {
        when(userServiceMock.sighIn(LOGIN, PASSWORD)).thenReturn(found);
        Optional<User> actual = userServiceMock.sighIn(LOGIN, PASSWORD);
        assertThat(actual).isEqualTo(found);
    }

    @Test
    public void findAllTest() throws ServiceException {
        when(userServiceMock.findAll()).thenReturn(expectedUsers);
        List<User> actual = userServiceMock.findAll();
        assertThat(actual).containsExactly(firstElementOfExpectedList, secondElementOfExpectedList);
    }

    @Test
    public void findByIdTest() throws ServiceException {
        when(userServiceMock.findById(USER_ID)).thenReturn(expectedUser);
        User actual = userServiceMock.findById(USER_ID);
        assertThat(actual).isEqualTo(expectedUser);
    }

    @Test
    public void createAdminTest() throws ServiceException {
        when(userServiceMock.createAdmin(adminToCreate)).thenReturn(expectedUser);
        User actual = userServiceMock.createAdmin(adminToCreate);
        assertThat(actual).isEqualTo(expectedUser);
    }

    @Test
    public void updateTest() throws ServiceException {
        when(userServiceMock.update(toUpdate)).thenReturn(expectedUpdated);
        User actual = userServiceMock.update(toUpdate);
        assertThat(actual).isEqualTo(expectedUpdated);
    }

    @Test
    public void deleteByIdTest() throws ServiceException {
        when(userServiceMock.deleteById(USER_ID)).thenReturn(EXPECTED);
        boolean actual = userServiceMock.deleteById(USER_ID);
        assertThat(actual).isEqualTo(EXPECTED);
    }

    @Test
    public void restoreByIdTest() throws ServiceException {
        when(userServiceMock.restoreById(USER_ID)).thenReturn(EXPECTED);
        boolean actual = userServiceMock.restoreById(USER_ID);
        assertThat(actual).isEqualTo(EXPECTED);
    }

    @Test
    public void validateInputPasswordsTest() {
        when(userServiceMock.validateInputPasswords(passwordsToCheck)).thenReturn(EXPECTED);
        boolean actual = userServiceMock.validateInputPasswords(passwordsToCheck);
        assertThat(actual).isEqualTo(EXPECTED);
    }

    @Test
    public void validateUserDataTest() {
        when(userServiceMock.validateUserData(userDataToCheck)).thenReturn(EXPECTED);
        boolean actual = userServiceMock.validateUserData(userDataToCheck);
        assertThat(actual).isEqualTo(EXPECTED);
    }

    @Test
    public void checkIfLoginExistTest() throws ServiceException {
        when(userServiceMock.checkIfLoginExist(LOGIN)).thenReturn(EXPECTED);
        boolean actual = userServiceMock.checkIfLoginExist(LOGIN);
        assertThat(actual).isEqualTo(EXPECTED);
    }

    @Test
    public void checkIfEmailExistTest() throws ServiceException {
        when(userServiceMock.checkIfEmailExist(EMAIL)).thenReturn(EXPECTED);
        boolean actual = userServiceMock.checkIfEmailExist(EMAIL);
        assertThat(actual).isEqualTo(EXPECTED);
    }

    @Test
    public void checkUserPasswordTest() throws ServiceException {
        when(userServiceMock.checkUserPassword(USER_ID, PASSWORD)).thenReturn(EXPECTED);
        boolean actual = userServiceMock.checkUserPassword(USER_ID, PASSWORD);
        assertThat(actual).isEqualTo(EXPECTED);
    }

    @Test
    public void updatePasswordTest() throws ServiceException {
        when(userServiceMock.updatePassword(USER_ID, PASSWORD_TO_UPDATE)).thenReturn(EXPECTED);
        boolean actual = userServiceMock.updatePassword(USER_ID, PASSWORD_TO_UPDATE);
        assertThat(actual).isEqualTo(EXPECTED);
    }
}