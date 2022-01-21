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

    private static final Appointment APPOINTMENT_TO_CREATE =
            new Appointment.Builder()
                    .clientId(5)
                    .facilityId(3)
                    .notes("some notes")
                    .piercerId(7)
                    .datetime(LocalDateTime.of(2021, 3, 15, 16, 0))
                    .build();

    private static final Appointment EXPECTED_APPOINTMENT =
            new Appointment.Builder()
                    .appointmentId(10)
                    .clientId(5)
                    .facilityId(3)
                    .notes("some notes")
                    .piercerId(7)
                    .datetime(LocalDateTime.of(2021, 3, 15, 16, 0))
                    .build();

    private static final long APPOINTMENT_ID_TO_FIND = 10;
    private static final int EXPECTED_ROWS_UPDATED = 1;
    private static final long PIERCER_ID = 5;
    private static final long CLIENT_ID = 4;
    private static final Appointment FIRST_ELEMENT =
            new Appointment.Builder()
                    .appointmentId(6)
                    .clientId(4)
                    .facilityId(2)
                    .notes("some another notes")
                    .piercerId(5)
                    .datetime(LocalDateTime.of(2021, 3, 22, 14, 0))
                    .build();

    private static final Appointment SECOND_ELEMENT =
            new Appointment.Builder()
                    .appointmentId(7)
                    .clientId(5)
                    .facilityId(2)
                    .notes("some another notes")
                    .piercerId(5)
                    .datetime(LocalDateTime.of(2021, 3, 25, 14, 0))
                    .build();
    private static final List<Appointment> EXPECTED_RELEVANT_APPOINTMENTS_BY_PIERCER =
            List.of(FIRST_ELEMENT, SECOND_ELEMENT);

    private static final List<Appointment> EXPECTED_RELEVANT_APPOINTMENTS_BY_CLIENT =
            List.of(FIRST_ELEMENT);

    private static final List<Appointment> EXPECTED_APPOINTMENTS_FOR_CURRENT_DAY_BY_PIERCER =
            List.of(SECOND_ELEMENT);

    private static final long APPOINTMENT_ID_TO_DELETE = 9;

    @Mock
    private AppointmentDaoImpl appointmentDaoMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByIdTest() throws DaoException {
        when(appointmentDaoMock.findById(APPOINTMENT_ID_TO_FIND)).thenReturn(EXPECTED_APPOINTMENT);
        Appointment actual = appointmentDaoMock.findById(EXPECTED_APPOINTMENT.getAppointmentId());
        assertThat(actual).isEqualTo(EXPECTED_APPOINTMENT);
    }

    @Test
    public void createTest() throws DaoException {
        when(appointmentDaoMock.create(APPOINTMENT_TO_CREATE)).thenReturn(EXPECTED_APPOINTMENT);
        Appointment actual = appointmentDaoMock.create(APPOINTMENT_TO_CREATE);
        assertThat(actual).isEqualTo(EXPECTED_APPOINTMENT);
    }

    @Test
    public void findAllRelevantByPiercerIdTest() throws DaoException {
        when(appointmentDaoMock.findAllRelevantByPiercerId(PIERCER_ID))
                .thenReturn(EXPECTED_RELEVANT_APPOINTMENTS_BY_PIERCER);
        List<Appointment> actual = appointmentDaoMock.findAllRelevantByPiercerId(PIERCER_ID);
        assertThat(actual).containsExactly(FIRST_ELEMENT, SECOND_ELEMENT);
    }

    @Test
    public void findAllRelevantByClientIdTest() throws DaoException {
        when(appointmentDaoMock.findAllRelevantByClientId(CLIENT_ID))
                .thenReturn(EXPECTED_RELEVANT_APPOINTMENTS_BY_CLIENT);
        List<Appointment> actual = appointmentDaoMock.findAllRelevantByClientId(CLIENT_ID);
        assertThat(actual).containsExactly(FIRST_ELEMENT);
    }

    @Test
    public void findAllByPiercerIdForCurrentDateTest() throws DaoException {
        when(appointmentDaoMock.findAllByPiercerIdForCurrentDate(PIERCER_ID))
                .thenReturn(EXPECTED_APPOINTMENTS_FOR_CURRENT_DAY_BY_PIERCER);
        List<Appointment> actual = appointmentDaoMock.findAllByPiercerIdForCurrentDate(PIERCER_ID);
        assertThat(actual).containsExactly(SECOND_ELEMENT);
    }

    @Test
    public void deleteByIdTest() throws DaoException {
        when(appointmentDaoMock.deleteById(APPOINTMENT_ID_TO_DELETE))
                .thenReturn(EXPECTED_ROWS_UPDATED);
        int actual = appointmentDaoMock.deleteById(APPOINTMENT_ID_TO_DELETE);
        assertThat(actual).isEqualTo(EXPECTED_ROWS_UPDATED);
    }
}