package by.zvezdina.bodyartsalon.model.dao.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.dao.PiercerDao;
import by.zvezdina.bodyartsalon.model.entity.Piercer;
import by.zvezdina.bodyartsalon.model.entity.Role;
import by.zvezdina.bodyartsalon.model.entity.UserStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class PiercerDaoImplTest {

    private static final long PIERCER_ID_TO_FIND = 1;
    private static final int EXPECTED_ROWS_UPDATED = 1;

    @Mock
    private PiercerDao piercerDaoMock;
    private List<Piercer> expectedActive;
    private Piercer firstElementOfExpectedList;
    private Piercer secondElementOfExpectedList;
    private Piercer toCreate;
    private Piercer expectedCreated;
    private Piercer toUpdate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        firstElementOfExpectedList = new Piercer.Builder()
                .piercerId(1)
                .email("email@gmail.com")
                .firstName("Bob")
                .lastName("Bobson")
                .login("bobbie")
                .password("12345678Qwe")
                .role(Role.PIERCER)
                .userStatus(UserStatus.ACTIVE)
                .isVerified(true)
                .photoUrl("photo.jpg")
                .infoAbout("info")
                .build();

        secondElementOfExpectedList = new Piercer.Builder()
                .piercerId(2)
                .email("email2@gmail.com")
                .firstName("Sam")
                .lastName("Bobson")
                .login("samson")
                .password("12345678Qwe")
                .role(Role.PIERCER)
                .userStatus(UserStatus.ACTIVE)
                .isVerified(true)
                .photoUrl("photo2.jpg")
                .infoAbout("info")
                .build();

        toCreate = new Piercer.Builder()
                .email("email3@gmail.com")
                .firstName("Tom")
                .lastName("Bobson")
                .login("tomtom")
                .password("12345678Qwe")
                .role(Role.PIERCER)
                .userStatus(UserStatus.ACTIVE)
                .isVerified(true)
                .photoUrl("photo3.jpg")
                .infoAbout("info")
                .build();

        expectedCreated = new Piercer.Builder()
                .piercerId(3)
                .email("email3@gmail.com")
                .firstName("Tom")
                .lastName("Bobson")
                .login("tomtom")
                .password("12345678Qwe")
                .role(Role.PIERCER)
                .userStatus(UserStatus.ACTIVE)
                .isVerified(true)
                .photoUrl("photo3.jpg")
                .infoAbout("info")
                .build();

        toUpdate = new Piercer.Builder()
                .piercerId(4)
                .email("email4@gmail.com")
                .firstName("John")
                .lastName("Bobson")
                .login("johnie")
                .password("12345678Qwe")
                .role(Role.PIERCER)
                .userStatus(UserStatus.ACTIVE)
                .isVerified(true)
                .photoUrl("photo4.jpg")
                .infoAbout("info")
                .build();

        expectedActive = List.of(firstElementOfExpectedList, secondElementOfExpectedList);
    }

    @Test
    public void findByIdTest() throws DaoException {
        when(piercerDaoMock.findById(PIERCER_ID_TO_FIND)).thenReturn(firstElementOfExpectedList);
        Piercer actual = piercerDaoMock.findById(PIERCER_ID_TO_FIND);
        assertThat(actual).isEqualTo(firstElementOfExpectedList);
    }

    @Test
    public void findAllActiveTest() throws DaoException {
        when(piercerDaoMock.findAllActive()).thenReturn(expectedActive);
        List<Piercer> actual = piercerDaoMock.findAllActive();
        assertThat(actual).containsExactly(firstElementOfExpectedList, secondElementOfExpectedList);
    }

    @Test
    public void createTest() throws DaoException {
        when(piercerDaoMock.create(toCreate)).thenReturn(expectedCreated);
        Piercer actual = piercerDaoMock.create(toCreate);
        assertThat(actual).isEqualTo(expectedCreated);
    }

    @Test
    public void updateWorkingInfoTest() throws DaoException {
        when(piercerDaoMock.updateWorkingInfo(toUpdate)).thenReturn(EXPECTED_ROWS_UPDATED);
        int actual = piercerDaoMock.updateWorkingInfo(toUpdate);
        assertThat(actual).isEqualTo(EXPECTED_ROWS_UPDATED);
    }
}