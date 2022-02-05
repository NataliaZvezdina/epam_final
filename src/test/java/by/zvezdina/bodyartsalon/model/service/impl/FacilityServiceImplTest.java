package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Facility;
import by.zvezdina.bodyartsalon.model.service.FacilityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class FacilityServiceImplTest {

    private static final int PAGE = 1;
    private static final long FACILITY_ID = 1;
    private static final long FACILITY_ID_TO_DELETE = 5;
    private static final long FACILITY_ID_TO_RESTORE = 6;
    private static final boolean EXPECTED = true;
    private final Map<String, String> formDataToCheck =
            Map.of("name", "facility name to validate");

    @Mock
    private FacilityService facilityServiceMock;
    private List<Facility> expectedOnPage;
    private Facility firstElement;
    private Facility secondElement;
    private Facility toCreate;
    private Facility expectedCreated;
    private Facility toUpdate;
    private Facility expectedUpdated;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        firstElement = new Facility.Builder()
                .facilityId(1)
                .name("name")
                .description("some description")
                .price(BigDecimal.valueOf(99.99))
                .accessible(true)
                .build();

        secondElement = new Facility.Builder()
                .facilityId(2)
                .name("name")
                .description("some description")
                .price(BigDecimal.valueOf(69.99))
                .accessible(true)
                .build();

        toCreate = new Facility.Builder()
                .name("name")
                .description("some description")
                .price(BigDecimal.valueOf(9.99))
                .accessible(true)
                .build();

        expectedCreated = new Facility.Builder()
                .facilityId(3)
                .name("name")
                .description("some description")
                .price(BigDecimal.valueOf(9.99))
                .accessible(true)
                .build();

        toUpdate = new Facility.Builder()
                .facilityId(4)
                .name("name")
                .description("some description")
                .price(BigDecimal.valueOf(19.99))
                .accessible(true)
                .build();

        expectedUpdated = toUpdate;

        expectedOnPage = List.of(firstElement, secondElement);
    }


    @Test
    public void findAllTest() throws ServiceException {
        when(facilityServiceMock.findAll(PAGE)).thenReturn(expectedOnPage);
        List<Facility> actual = facilityServiceMock.findAll(PAGE);
        assertThat(actual).containsExactly(firstElement, secondElement);
    }

    @Test
    public void findByIdTest() throws ServiceException {
        when(facilityServiceMock.findById(FACILITY_ID)).thenReturn(firstElement);
        Facility actual = facilityServiceMock.findById(FACILITY_ID);
        assertThat(actual).isEqualTo(firstElement);
    }

    @Test
    public void createTest() throws ServiceException {
        when(facilityServiceMock.create(toCreate)).thenReturn(expectedCreated);
        Facility actual = facilityServiceMock.create(toCreate);
        assertThat(actual).isEqualTo(expectedCreated);
    }

    @Test
    public void updateTest() throws ServiceException {
        when(facilityServiceMock.update(toUpdate)).thenReturn(expectedUpdated);
        Facility actual = facilityServiceMock.update(toUpdate);
        assertThat(actual).isEqualTo(expectedUpdated);
    }

    @Test
    public void deleteByIdTest() throws ServiceException {
        when(facilityServiceMock.deleteById(FACILITY_ID_TO_DELETE)).thenReturn(EXPECTED);
        boolean actual = facilityServiceMock.deleteById(FACILITY_ID_TO_DELETE);
        assertThat(actual).isEqualTo(EXPECTED);
    }

    @Test
    public void restoreByIdTest() throws ServiceException {
        when(facilityServiceMock.restoreById(FACILITY_ID_TO_RESTORE)).thenReturn(EXPECTED);
        boolean actual = facilityServiceMock.restoreById(FACILITY_ID_TO_RESTORE);
        assertThat(actual).isEqualTo(EXPECTED);
    }

    @Test
    public void validateInputDataTest() {
        when(facilityServiceMock.validateInputData(formDataToCheck))
                .thenReturn(EXPECTED);
        boolean actual = facilityServiceMock.validateInputData(formDataToCheck);
        assertThat(actual).isEqualTo(EXPECTED);
    }
}