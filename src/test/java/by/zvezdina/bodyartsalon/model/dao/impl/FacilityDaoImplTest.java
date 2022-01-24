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

    private static final Facility FIRST_ELEMENT =
            new Facility.Builder()
                    .facilityId(1)
                    .name("name")
                    .description("some description")
                    .price(BigDecimal.valueOf(99.99))
                    .accessible(true)
                    .build();

    private static final Facility SECOND_ELEMENT =
            new Facility.Builder()
                    .facilityId(2)
                    .name("name")
                    .description("some description")
                    .price(BigDecimal.valueOf(69.99))
                    .accessible(false)
                    .build();

    private static final List<Facility> EXPECTED_ON_DEFINED_PAGE =
            List.of(FIRST_ELEMENT, SECOND_ELEMENT);

    private static final Facility FACILITY_TO_CREATE =
            new Facility.Builder()
                    .name("name")
                    .description("some description")
                    .price(BigDecimal.valueOf(9.99))
                    .accessible(true)
                    .build();

    private static final Facility EXPECTED_CREATED =
            new Facility.Builder()
                    .facilityId(3)
                    .name("name")
                    .description("some description")
                    .price(BigDecimal.valueOf(9.99))
                    .accessible(true)
                    .build();

    private static final Facility FACILITY_TO_UPDATE =
            new Facility.Builder()
                    .facilityId(4)
                    .name("name")
                    .description("some description")
                    .price(BigDecimal.valueOf(19.99))
                    .accessible(true)
                    .build();

    private static final Facility EXPECTED_UPDATED =
            new Facility.Builder()
                    .facilityId(4)
                    .name("name")
                    .description("updated description")
                    .price(BigDecimal.valueOf(9.99))
                    .accessible(true)
                    .build();

    @Mock
    private FacilityDaoImpl facilityDaoMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTest() throws DaoException {
        when(facilityDaoMock.findAll(PAGE)).thenReturn(EXPECTED_ON_DEFINED_PAGE);
        List<Facility> actual = facilityDaoMock.findAll(PAGE);
        assertThat(actual).containsExactly(FIRST_ELEMENT, SECOND_ELEMENT);
    }

    @Test
    public void findByIdTest() throws DaoException {
        when(facilityDaoMock.findById(FACILITY_ID)).thenReturn(FIRST_ELEMENT);
        Facility actual = facilityDaoMock.findById(FACILITY_ID);
        assertThat(actual).isEqualTo(FIRST_ELEMENT);
    }

    @Test
    public void createTest() throws DaoException {
        when(facilityDaoMock.create(FACILITY_TO_CREATE)).thenReturn(EXPECTED_CREATED);
        Facility actual = facilityDaoMock.create(FACILITY_TO_CREATE);
        assertThat(actual).isEqualTo(EXPECTED_CREATED);
    }

    @Test
    public void updateTest() throws DaoException {
        when(facilityDaoMock.update(FACILITY_TO_UPDATE)).thenReturn(EXPECTED_UPDATED);
        Facility actual = facilityDaoMock.update(FACILITY_TO_UPDATE);
        assertThat(actual).isEqualTo(EXPECTED_UPDATED);
    }

    @Test
    void deleteById() {
    }

    @Test
    void restoreById() {
    }
}