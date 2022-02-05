package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Piercer;
import by.zvezdina.bodyartsalon.model.entity.Role;
import by.zvezdina.bodyartsalon.model.entity.UserStatus;
import by.zvezdina.bodyartsalon.model.service.PiercerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class PiercerServiceImplTest {

    private static final long PIERCER_ID_TO_FIND = 1;
    private static final boolean EXPECTED = true;
    private final Map<String, String> formDataToCheck =
            Map.of("firstName", "Bob", "lastName", "Bobson", "email", "email@gamil.com");

    @Mock
    private PiercerService piercerServiceMock;
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
    public void findByIdTest() throws ServiceException {
        when(piercerServiceMock.findById(PIERCER_ID_TO_FIND)).thenReturn(firstElementOfExpectedList);
        Piercer actual = piercerServiceMock.findById(PIERCER_ID_TO_FIND);
        assertThat(actual).isEqualTo(firstElementOfExpectedList);
    }

    @Test
    public void findAllActiveTest() throws ServiceException {
        when(piercerServiceMock.findAllActive()).thenReturn(expectedActive);
        List<Piercer> actual = piercerServiceMock.findAllActive();
        assertThat(actual).containsExactly(firstElementOfExpectedList, secondElementOfExpectedList);
    }

    @Test
    public void createTest() throws ServiceException {
        when(piercerServiceMock.create(toCreate)).thenReturn(expectedCreated);
        Piercer actual = piercerServiceMock.create(toCreate);
        assertThat(actual).isEqualTo(expectedCreated);
    }

    @Test
    public void validateWorkingDataTest() {
        when(piercerServiceMock.validateWorkingData(formDataToCheck)).thenReturn(EXPECTED);
        boolean actual = piercerServiceMock.validateWorkingData(formDataToCheck);
        assertThat(actual).isEqualTo(EXPECTED);
    }

    @Test
    public void updateWorkingInfoTest() throws ServiceException {
        when(piercerServiceMock.updateWorkingInfo(toUpdate)).thenReturn(EXPECTED);
        boolean actual = piercerServiceMock.updateWorkingInfo(toUpdate);
        assertThat(actual).isEqualTo(EXPECTED);
    }
}