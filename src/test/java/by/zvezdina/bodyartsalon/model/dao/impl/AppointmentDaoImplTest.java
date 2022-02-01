package by.zvezdina.bodyartsalon.model.dao.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.Appointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class AppointmentDaoImplTest {

    private static final long APPOINTMENT_ID_TO_DELETE = 9;
    private static final long APPOINTMENT_ID_TO_FIND = 10;
    private static final int EXPECTED_ROWS_UPDATED = 1;
    private static final long PIERCER_ID = 5;
    private static final long CLIENT_ID = 4;

    @Mock
    private AppointmentDaoImpl appointmentDaoMock;
    private Appointment toCreate;
    private Appointment expected;
    private Appointment firstElement;
    private Appointment secondElement;
    private List<Appointment> expectedRelevantByPiercer;
    private List<Appointment> expectedRelevantByClient;
    private List<Appointment> expectedRelevantForCurrentDayByPiercer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        toCreate = new Appointment.Builder()
                .clientId(5)
                .facilityId(3)
                .notes("some notes")
                .piercerId(7)
                .datetime(LocalDateTime.of(2021, 3, 15, 16, 0))
                .build();

        expected = new Appointment.Builder()
                .appointmentId(10)
                .clientId(5)
                .facilityId(3)
                .notes("some notes")
                .piercerId(7)
                .datetime(LocalDateTime.of(2021, 3, 15, 16, 0))
                .build();

        firstElement = new Appointment.Builder()
                .appointmentId(6)
                .clientId(4)
                .facilityId(2)
                .notes("some another notes")
                .piercerId(5)
                .datetime(LocalDateTime.of(2021, 3, 22, 14, 0))
                .build();

        secondElement = new Appointment.Builder()
                .appointmentId(7)
                .clientId(5)
                .facilityId(2)
                .notes("some another notes")
                .piercerId(5)
                .datetime(LocalDateTime.of(2021, 3, 25, 14, 0))
                .build();

        expectedRelevantByPiercer = List.of(firstElement, secondElement);
        expectedRelevantByClient = List.of(firstElement);
        expectedRelevantForCurrentDayByPiercer = List.of(secondElement);
    }

    @Test
    public void findByIdTest() throws DaoException {
        when(appointmentDaoMock.findById(APPOINTMENT_ID_TO_FIND)).thenReturn(expected);
        Appointment actual = appointmentDaoMock.findById(expected.getAppointmentId());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void createTest() throws DaoException {
        when(appointmentDaoMock.create(toCreate)).thenReturn(expected);
        Appointment actual = appointmentDaoMock.create(toCreate);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findAllRelevantByPiercerIdTest() throws DaoException {
        when(appointmentDaoMock.findAllRelevantByPiercerId(PIERCER_ID))
                .thenReturn(expectedRelevantByPiercer);
        List<Appointment> actual = appointmentDaoMock.findAllRelevantByPiercerId(PIERCER_ID);
        assertThat(actual).containsExactly(firstElement, secondElement);
    }

    @Test
    public void findAllRelevantByClientIdTest() throws DaoException {
        when(appointmentDaoMock.findAllRelevantByClientId(CLIENT_ID))
                .thenReturn(expectedRelevantByClient);
        List<Appointment> actual = appointmentDaoMock.findAllRelevantByClientId(CLIENT_ID);
        assertThat(actual).containsExactly(firstElement);
    }

    @Test
    public void findAllByPiercerIdForCurrentDateTest() throws DaoException {
        when(appointmentDaoMock.findAllByPiercerIdForCurrentDate(PIERCER_ID))
                .thenReturn(expectedRelevantForCurrentDayByPiercer);
        List<Appointment> actual = appointmentDaoMock.findAllByPiercerIdForCurrentDate(PIERCER_ID);
        assertThat(actual).containsExactly(secondElement);
    }

    @Test
    public void deleteByIdTest() throws DaoException {
        when(appointmentDaoMock.deleteById(APPOINTMENT_ID_TO_DELETE))
                .thenReturn(EXPECTED_ROWS_UPDATED);
        int actual = appointmentDaoMock.deleteById(APPOINTMENT_ID_TO_DELETE);
        assertThat(actual).isEqualTo(EXPECTED_ROWS_UPDATED);
    }
}