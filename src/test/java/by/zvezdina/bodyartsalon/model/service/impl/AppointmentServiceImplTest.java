package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Appointment;
import by.zvezdina.bodyartsalon.model.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class AppointmentServiceImplTest {

    private static final long APPOINTMENT_ID_TO_FIND = 8;
    private static final long APPOINTMENT_ID_TO_DELETE = 5;
    private static final boolean EXPECTED = true;
    private static final long PIERCER_ID = 6;
    private static final long CLIENT_ID = 5;
    private static final int DEFINED_PAGE = 1;
    private final LocalDateTime timeToCheck =
            LocalDateTime.of(2021, 4, 12, 15, 0);
    private final Map<String, String> formDataToCheck =
            Map.of("notes", "some notes regarding appointment");

    @Mock
    private AppointmentService appointmentServiceMock;
    private Appointment toCreate;
    private Appointment expected;
    private List<Appointment> expectedAppointments;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        toCreate = new Appointment.Builder()
                .clientId(5)
                .facilityId(2)
                .notes("some notes")
                .piercerId(6)
                .datetime(LocalDateTime.of(2021, 5, 11, 14, 0))
                .build();

        expected = new Appointment.Builder()
                .appointmentId(8)
                .clientId(5)
                .facilityId(2)
                .notes("some notes")
                .piercerId(6)
                .datetime(LocalDateTime.of(2021, 5, 11, 14, 0))
                .build();

        expectedAppointments = List.of(expected);
    }

    @Test
    public void createTest() throws ServiceException {
        when(appointmentServiceMock.create(toCreate)).thenReturn(expected);
        Appointment actual = appointmentServiceMock.create(toCreate);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findByIdTest() throws ServiceException {
        when(appointmentServiceMock.findById(APPOINTMENT_ID_TO_FIND)).thenReturn(expected);
        Appointment actual = appointmentServiceMock.findById(APPOINTMENT_ID_TO_FIND);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findAllTest() throws ServiceException {
        when(appointmentServiceMock.findAll(DEFINED_PAGE)).thenReturn(expectedAppointments);
        List<Appointment> actual = appointmentServiceMock.findAll(DEFINED_PAGE);
        assertThat(actual).containsExactly(expected);
    }

    @Test
    public void findAllRelevantByPiercerIdTest() throws ServiceException {
        when(appointmentServiceMock.findAllRelevantByPiercerId(PIERCER_ID))
                .thenReturn(expectedAppointments);
        List<Appointment> actual = appointmentServiceMock.findAllRelevantByPiercerId(PIERCER_ID);
        assertThat(actual).containsExactly(expected);
    }

    @Test
    public void findAllByPiercerIdForCurrentDateTest() throws ServiceException {
        when(appointmentServiceMock.findAllByPiercerIdForCurrentDate(PIERCER_ID))
                .thenReturn(expectedAppointments);
        List<Appointment> actual = appointmentServiceMock.findAllByPiercerIdForCurrentDate(PIERCER_ID);
        assertThat(actual).containsExactly(expected);
    }

    @Test
    public void findAllRelevantByClientIdTest() throws ServiceException {
        when(appointmentServiceMock.findAllRelevantByClientId(CLIENT_ID))
                .thenReturn(expectedAppointments);
        List<Appointment> actual = appointmentServiceMock.findAllRelevantByClientId(CLIENT_ID);
        assertThat(actual).containsExactly(expected);
    }

    @Test
    public void validateInputDataTest() {
        when(appointmentServiceMock.validateInputData(formDataToCheck))
                .thenReturn(EXPECTED);
        boolean actual = appointmentServiceMock.validateInputData(formDataToCheck);
        assertThat(actual).isEqualTo(EXPECTED);

    }

    @Test
    public void checkIfOccupiedTest() throws ServiceException {
        when(appointmentServiceMock.checkIfOccupied(PIERCER_ID, timeToCheck))
                .thenReturn(EXPECTED);
        boolean actual = appointmentServiceMock.checkIfOccupied(PIERCER_ID, timeToCheck);
        assertThat(actual).isEqualTo(EXPECTED);
    }

    @Test
    public void deleteByIdTest() throws ServiceException {
        when(appointmentServiceMock.deleteById(APPOINTMENT_ID_TO_DELETE))
                .thenReturn(EXPECTED);
        boolean actual = appointmentServiceMock.deleteById(APPOINTMENT_ID_TO_DELETE);
        assertThat(actual).isEqualTo(EXPECTED);
    }
}