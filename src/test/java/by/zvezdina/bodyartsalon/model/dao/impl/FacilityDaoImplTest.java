package by.zvezdina.bodyartsalon.model.dao.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.Facility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class FacilityDaoImplTest {

    private static final int PAGE = 1;
    private static final long FACILITY_ID = 1;

    @Mock
    private FacilityDaoImpl facilityDaoMock;
    private Facility firstElement;
    private Facility secondElement;
    private List<Facility> expectedOnDefinedPage;
    private Facility facilityToCreate;
    private Facility expectedCreated;
    private Facility facilityToUpdate;
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
                .accessible(false)
                .build();

        facilityToCreate = new Facility.Builder()
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

        facilityToUpdate = new Facility.Builder()
                .facilityId(4)
                .name("name")
                .description("some description")
                .price(BigDecimal.valueOf(19.99))
                .accessible(true)
                .build();

        expectedUpdated = new Facility.Builder()
                .facilityId(4)
                .name("name")
                .description("updated description")
                .price(BigDecimal.valueOf(9.99))
                .accessible(true)
                .build();

        expectedOnDefinedPage = List.of(firstElement, secondElement);
    }

    @Test
    public void findAllTest() throws DaoException {
        when(facilityDaoMock.findAll(PAGE)).thenReturn(expectedOnDefinedPage);
        List<Facility> actual = facilityDaoMock.findAll(PAGE);
        assertThat(actual).containsExactly(firstElement, secondElement);
    }

    @Test
    public void findByIdTest() throws DaoException {
        when(facilityDaoMock.findById(FACILITY_ID)).thenReturn(firstElement);
        Facility actual = facilityDaoMock.findById(FACILITY_ID);
        assertThat(actual).isEqualTo(firstElement);
    }

    @Test
    public void createTest() throws DaoException {
        when(facilityDaoMock.create(facilityToCreate)).thenReturn(expectedCreated);
        Facility actual = facilityDaoMock.create(facilityToCreate);
        assertThat(actual).isEqualTo(expectedCreated);
    }

    @Test
    public void updateTest() throws DaoException {
        when(facilityDaoMock.update(facilityToUpdate)).thenReturn(expectedUpdated);
        Facility actual = facilityDaoMock.update(facilityToUpdate);
        assertThat(actual).isEqualTo(expectedUpdated);
    }

    @Test
    void deleteById() {
    }

    @Test
    void restoreById() {
    }
}