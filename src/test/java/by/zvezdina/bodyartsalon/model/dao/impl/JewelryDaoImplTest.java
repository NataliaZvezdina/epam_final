package by.zvezdina.bodyartsalon.model.dao.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.dao.JewelryDao;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class JewelryDaoImplTest {

    private static final int DEFINED_PAGE = 1;
    private static final long JEWELRY_ID_TO_FIND = 1;
    private static final long JEWELRY_ID_TO_DELETE = 3;
    private static final long JEWELRY_ID_TO_RESTORE = 4;
    private static final int EXPECTED_ROWS_UPDATED = 1;

    @Mock
    private JewelryDao jewelryDaoMock;
    private List<Jewelry> expectedJewelryList;
    private Jewelry firstElementOfExpectedList;
    private Jewelry secondElementOfExpectedList;
    private Jewelry toCreate;
    private Jewelry expectedCreated;
    private Jewelry toUpdate;
    private Jewelry expectedUpdated;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        firstElementOfExpectedList = new Jewelry.Builder()
                .jewelryId(1)
                .type("Ring")
                .imageUrl("imagePath1.jpg")
                .manufacturer("Interstellar")
                .description("perfect choice for septum")
                .price(BigDecimal.valueOf(59.99))
                .isAvailable(true)
                .build();

        secondElementOfExpectedList = new Jewelry.Builder()
                .jewelryId(2)
                .type("Nostril")
                .imageUrl("imagePath2.jpg")
                .manufacturer("Interstellar")
                .description("perfect choice for nose piercing")
                .price(BigDecimal.valueOf(29.99))
                .isAvailable(true)
                .build();

        toCreate = new Jewelry.Builder()
                .type("Nostril")
                .imageUrl("imagePath3.jpg")
                .manufacturer("Interstellar")
                .description("awesome")
                .price(BigDecimal.valueOf(19.99))
                .isAvailable(true)
                .build();

        expectedCreated = new Jewelry.Builder()
                .jewelryId(3)
                .type("Nostril")
                .imageUrl("imagePath3.jpg")
                .manufacturer("Interstellar")
                .description("awesome")
                .price(BigDecimal.valueOf(19.99))
                .isAvailable(true)
                .build();

        toUpdate = new Jewelry.Builder()
                .jewelryId(4)
                .type("custom")
                .imageUrl("imagePath4.jpg")
                .manufacturer("Implant Grade")
                .description("cool")
                .price(BigDecimal.valueOf(19.99))
                .isAvailable(true)
                .build();

        expectedUpdated = new Jewelry.Builder()
                .jewelryId(4)
                .type("custom")
                .imageUrl("imagePath4.jpg")
                .manufacturer("Implant Grade")
                .description("cool")
                .price(BigDecimal.valueOf(9.99))
                .isAvailable(true)
                .build();

        expectedJewelryList = List.of(firstElementOfExpectedList, secondElementOfExpectedList);
    }

    @Test
    public void findAllTest() throws DaoException {
        when(jewelryDaoMock.findAll()).thenReturn(expectedJewelryList);
        List<Jewelry> actual = jewelryDaoMock.findAll();
        assertThat(actual).containsExactly(firstElementOfExpectedList, secondElementOfExpectedList);
    }

    @Test
    public void findAllOnPageTest() throws DaoException {
        when(jewelryDaoMock.findAll(DEFINED_PAGE)).thenReturn(expectedJewelryList);
        List<Jewelry> actual = jewelryDaoMock.findAll(DEFINED_PAGE);
        assertThat(actual).containsExactly(firstElementOfExpectedList, secondElementOfExpectedList);
    }

    @Test
    public void findByIdTest() throws DaoException {
        when(jewelryDaoMock.findById(JEWELRY_ID_TO_FIND))
                .thenReturn(firstElementOfExpectedList);
        Jewelry actual = jewelryDaoMock.findById(JEWELRY_ID_TO_FIND);
        assertThat(actual).isEqualTo(firstElementOfExpectedList);
    }

    @Test
    public void createTest() throws DaoException {
        when(jewelryDaoMock.create(toCreate)).thenReturn(expectedCreated);
        Jewelry actual = jewelryDaoMock.create(toCreate);
        assertThat(actual).isEqualTo(expectedCreated);
    }

    @Test
    public void updateTest() throws DaoException {
        when(jewelryDaoMock.update(toUpdate)).thenReturn(expectedUpdated);
        Jewelry actual = jewelryDaoMock.update(toUpdate);
        assertThat(actual).isEqualTo(expectedUpdated);
    }

    @Test
    public void deleteByIdTest() throws DaoException {
        when(jewelryDaoMock.deleteById(JEWELRY_ID_TO_DELETE))
                .thenReturn(EXPECTED_ROWS_UPDATED);
        int actual = jewelryDaoMock.deleteById(JEWELRY_ID_TO_DELETE);
        assertThat(actual).isEqualTo(EXPECTED_ROWS_UPDATED);
    }

    @Test
    public void restoreByIdTest() throws DaoException {
        when(jewelryDaoMock.restoreById(JEWELRY_ID_TO_RESTORE))
                .thenReturn(EXPECTED_ROWS_UPDATED);
        int actual = jewelryDaoMock.restoreById(JEWELRY_ID_TO_RESTORE);
        assertThat(actual).isEqualTo(EXPECTED_ROWS_UPDATED);
    }
}