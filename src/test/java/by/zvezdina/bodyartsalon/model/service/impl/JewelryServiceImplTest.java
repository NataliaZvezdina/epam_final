package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
import by.zvezdina.bodyartsalon.model.service.JewelryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class JewelryServiceImplTest {

    private static final int DEFINED_PAGE = 1;
    private static final long JEWELRY_ID_TO_FIND = 1;
    private static final long JEWELRY_ID_TO_DELETE = 3;
    private static final long JEWELRY_ID_TO_RESTORE = 4;
    private static final int DISCOUNT = 7;
    private static final boolean EXPECTED = true;
    private final Map<String, String> formDataToCheck =
            Map.of("name", "microdermal", "price", "59.99", "imageUrl", "pic.jpg");
    private final Map<Long, Integer> items = Map.of(2L, 1, 3L, 2, 6L, 1);
    private final BigDecimal expectedCost = BigDecimal.valueOf(255.5);

    @Mock
    private JewelryService jewelryServiceMock;
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
    public void findAllTest() throws ServiceException {
        when(jewelryServiceMock.findAll()).thenReturn(expectedJewelryList);
        List<Jewelry> actual = jewelryServiceMock.findAll();
        assertThat(actual).containsExactly(firstElementOfExpectedList, secondElementOfExpectedList);
    }

    @Test
    public void findAllOnPageTest() throws ServiceException {
        when(jewelryServiceMock.findAll(DEFINED_PAGE)).thenReturn(expectedJewelryList);
        List<Jewelry> actual = jewelryServiceMock.findAll(DEFINED_PAGE);
        assertThat(actual).containsExactly(firstElementOfExpectedList, secondElementOfExpectedList);
    }

    @Test
    public void findByIdTest() throws ServiceException {
        when(jewelryServiceMock.findById(JEWELRY_ID_TO_FIND))
                .thenReturn(firstElementOfExpectedList);
        Jewelry actual = jewelryServiceMock.findById(JEWELRY_ID_TO_FIND);
        assertThat(actual).isEqualTo(firstElementOfExpectedList);
    }

    @Test
    public void createTest() throws ServiceException {
        when(jewelryServiceMock.create(toCreate)).thenReturn(expectedCreated);
        Jewelry actual = jewelryServiceMock.create(toCreate);
        assertThat(actual).isEqualTo(expectedCreated);
    }

    @Test
    public void updateTest() throws ServiceException {
        when(jewelryServiceMock.update(toUpdate)).thenReturn(expectedUpdated);
        Jewelry actual = jewelryServiceMock.update(toUpdate);
        assertThat(actual).isEqualTo(expectedUpdated);
    }

    @Test
    public void deleteByIdTest() throws ServiceException {
        when(jewelryServiceMock.deleteById(JEWELRY_ID_TO_DELETE))
                .thenReturn(EXPECTED);
        boolean actual = jewelryServiceMock.deleteById(JEWELRY_ID_TO_DELETE);
        assertThat(actual).isEqualTo(EXPECTED);
    }

    @Test
    public void restoreByIdTest() throws ServiceException {
        when(jewelryServiceMock.restoreById(JEWELRY_ID_TO_RESTORE))
                .thenReturn(EXPECTED);
        boolean actual = jewelryServiceMock.restoreById(JEWELRY_ID_TO_RESTORE);
        assertThat(actual).isEqualTo(EXPECTED);
    }

    @Test
    public void validateInputDataTest() {
        when(jewelryServiceMock.validateInputData(formDataToCheck))
                .thenReturn(EXPECTED);
        boolean actual = jewelryServiceMock.validateInputData(formDataToCheck);
        assertThat(actual).isEqualTo(EXPECTED);
    }

    @Test
    public void calculateJewelrySetTest() throws ServiceException {
        when(jewelryServiceMock.calculateJewelrySet(items, DISCOUNT))
                .thenReturn(expectedCost);
        BigDecimal actual = jewelryServiceMock.calculateJewelrySet(items, DISCOUNT);
        assertThat(actual).isEqualTo(expectedCost);
    }
}