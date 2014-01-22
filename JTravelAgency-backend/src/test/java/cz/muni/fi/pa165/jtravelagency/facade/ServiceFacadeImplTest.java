/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.facade;

import cz.muni.fi.pa165.jtravelagency.dto.CustomerDTO;
import cz.muni.fi.pa165.jtravelagency.dao.CustomerDAOImpl;
import cz.muni.fi.pa165.jtravelagency.dao.ExcursionDAOImpl;
import cz.muni.fi.pa165.jtravelagency.dao.ReservationDAOImpl;
import cz.muni.fi.pa165.jtravelagency.dao.TripDAOImpl;
import cz.muni.fi.pa165.jtravelagency.dto.ExcursionDTO;
import cz.muni.fi.pa165.jtravelagency.dto.ReservationDTO;
import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import cz.muni.fi.pa165.jtravelagency.entity.Customer;
import cz.muni.fi.pa165.jtravelagency.entity.Excursion;
import cz.muni.fi.pa165.jtravelagency.entity.Reservation;
import cz.muni.fi.pa165.jtravelagency.entity.Trip;
import cz.muni.fi.pa165.jtravelagency.service.CustomerServiceImpl;
import cz.muni.fi.pa165.jtravelagency.service.ExcursionServiceImpl;
import cz.muni.fi.pa165.jtravelagency.service.ReservationServiceImpl;
import cz.muni.fi.pa165.jtravelagency.service.TripServiceImpl;
import cz.muni.fi.pa165.jtravelagency.util.DTOAndEntityMapper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.fail;
import org.joda.time.DateTime;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author jakub
 */
@RunWith(MockitoJUnitRunner.class)
public class ServiceFacadeImplTest {
    
    
    @InjectMocks
    private ExcursionServiceImpl excursionService;
    
    @InjectMocks
    private ReservationServiceImpl reservationService;
    
    @InjectMocks
    private TripServiceImpl tripService;
    
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private ExcursionDAOImpl excursionDAO;
    
    @Mock
    private ReservationDAOImpl reservationDAO;
    
    @Mock
    private TripDAOImpl tripDAO;
    
    @Mock
    private CustomerDAOImpl customerDAO;


  
    /**
     * Test of createCustomer method, of class ServiceFacadeImpl.
     */
    @Test
    public void testCreateCustomer() {
        doThrow(new IllegalArgumentException()).when(customerDAO).createCustomer(null);

        try {
            tripService.create(null);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }
        
        CustomerDTO customerDTO = newCustomerDTO();
        Customer customer = newCustomer();
        customerService.create(customerDTO);

        verify(customerDAO).createCustomer(customer);
        verify(customerDAO, times(0)).getCustomer(any(Long.class));
        verify(customerDAO, times(0)).updateCustomer(any(Customer.class));
        verify(customerDAO, times(0)).deleteCustomer(any(Customer.class));
        verify(customerDAO, times(0)).getAllCustomers();
    }

    /**
     * Test of createTrip method, of class ServiceFacadeImpl.
     */
    @Test
    public void testCreateTrip() {
        doThrow(new IllegalArgumentException()).when(tripDAO).createTrip(null);

        try {
            tripService.create(null);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }

        Trip trip = prepareTrip();
        //TripDTO tripDTO = DTOAndEntityMapper.entityToDto(trip);
        TripDTO tripDTO = DTOAndEntityMapper.entityToDto(trip, TripDTO.class);

        tripService.create(tripDTO);

        verify(tripDAO).createTrip(trip);
        verify(tripDAO, times(0)).getTrip(any(Long.class));
        verify(tripDAO, times(0)).updateTrip(any(Trip.class));
        verify(tripDAO, times(0)).deleteTrip(any(Trip.class));
        verify(tripDAO, times(0)).getAllTrips();
    }

    /**
     * Test of createExcursion method, of class ServiceFacadeImpl.
     */
    @Test
    public void testCreateExcursion() {
        doThrow(new IllegalArgumentException()).when(excursionDAO).createExcursion(null);

        try {
            excursionService.create(null);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }


        ExcursionDTO excursionDto = newExcursionDto();   
        Excursion excursion = DTOAndEntityMapper.dtoToEntity(excursionDto, Excursion.class);//newExcursion();
        excursionService.create(excursionDto);
        verify(excursionDAO).createExcursion(excursion);

        verify(excursionDAO, never()).deleteExcursion(excursion);
        verify(excursionDAO, never()).updateExcursion(null);

    }

    /**
     * Test of getCustomer method, of class ServiceFacadeImpl.
     */
    @Test
    public void testGetCustomer() {
        CustomerDTO expected = newCustomerDTO();
        expected.setId(1l);
        when(customerDAO.getCustomer(1l)).thenReturn(DTOAndEntityMapper.dtoToEntity(expected, Customer.class));
        CustomerDTO returned = customerService.get(1l);

        verify(customerDAO).getCustomer(1l);
        verify(customerDAO, times(0)).createCustomer(any(Customer.class));
        verify(customerDAO, times(0)).updateCustomer(any(Customer.class));
        verify(customerDAO, times(0)).deleteCustomer(any(Customer.class));
        verify(customerDAO, times(0)).getAllCustomers();

        assertDeepEquals(expected, returned);
    }

    /**
     * Test of getTrip method, of class ServiceFacadeImpl.
     */
    @Test
    public void testGetTrip() {
        try {
            tripService.get(null);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        Trip trip = prepareTrip();

        TripDTO expected = DTOAndEntityMapper.entityToDto(trip, TripDTO.class);
        expected.setId(1l);
        when(tripDAO.getTrip(1l)).thenReturn(DTOAndEntityMapper.dtoToEntity(expected, Trip.class));
        TripDTO returned = tripService.get(1l);

        verify(tripDAO).getTrip(1l);
        verify(tripDAO, times(0)).createTrip(any(Trip.class));
        verify(tripDAO, times(0)).updateTrip(any(Trip.class));
        verify(tripDAO, times(0)).deleteTrip(any(Trip.class));
        verify(tripDAO, times(0)).getAllTrips();
    }

    /**
     * Test of getExcursion method, of class ServiceFacadeImpl.
     */
    @Test
    public void testGetExcursion() {
        doThrow(new IllegalArgumentException()).when(excursionDAO).getExcursion(null);
        doThrow(new IllegalArgumentException()).when(excursionDAO).getExcursion(-1l);

        try {
            excursionService.get(null);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }

        try {
            excursionService.get(-1l);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }

        verify(excursionDAO, never()).createExcursion(null);
        verify(excursionDAO, never()).updateExcursion(null);
        verify(excursionDAO, times(1)).getExcursion(-1l);

        ExcursionDTO excursionDto = newExcursionDto("excursion1");
        excursionDto.setId(1l);

        when(excursionDAO.getExcursion(1l)).thenReturn(DTOAndEntityMapper.dtoToEntity(excursionDto, Excursion.class));
        assertEquals(excursionDto, excursionService.get(excursionDto.getId()));

        verify(excursionDAO, times(1)).getExcursion(1l);
        verify(excursionDAO, times(0)).createExcursion(DTOAndEntityMapper.dtoToEntity(excursionDto, Excursion.class));
        verify(excursionDAO, never()).updateExcursion(DTOAndEntityMapper.dtoToEntity(excursionDto, Excursion.class));
    }

    /**
     * Test of getReservation method, of class ServiceFacadeImpl.
     */
    @Test
    public void testGetReservation() {
        try {
            reservationService.get(null);
            fail();
        } catch(IllegalArgumentException ex){
            // OK
        }
        
        verify(reservationDAO,never()).createReservation(null);
        verify(reservationDAO,never()).updateReservation(null);
        
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(1l);

        when(reservationDAO.getReservation(1l)).thenReturn(DTOAndEntityMapper.dtoToEntity(reservationDTO, Reservation.class));
        assertEquals(reservationDTO, reservationService.get(reservationDTO.getId()));

        verify(reservationDAO, times(1)).getReservation(1l);
        verify(reservationDAO, times(0)).createReservation(DTOAndEntityMapper.dtoToEntity(reservationDTO, Reservation.class));
        verify(reservationDAO, never()).updateReservation(DTOAndEntityMapper.dtoToEntity(reservationDTO, Reservation.class));
    }

    /**
     * Test of getReservationsByCustomer method, of class ServiceFacadeImpl.
     */
    @Test
    public void testGetReservationsByCustomer() {
        try {
            reservationService.getByCustomer(null);
            fail();
        } catch(IllegalArgumentException ex){
            // OK
        }
        
        Customer customer = new Customer();
        customer.setId(1l);
        
        when(reservationDAO.getReservationByCustomer(customer)).thenReturn(new ArrayList<Reservation>());
        ArrayList<ReservationDTO> dtoList = new ArrayList<ReservationDTO>();
        assertEquals(new ArrayList<Reservation>(), reservationDAO.getReservationByCustomer(customer));

        verify(reservationDAO, times(1)).getReservationByCustomer(customer);
        
        ReservationDTO resevationDTO1 = new ReservationDTO();
        ReservationDTO resevationDTO2 = new ReservationDTO();
        ReservationDTO resevationDTO3 = new ReservationDTO();

        resevationDTO1.setId(1l);
        resevationDTO2.setId(2l);
        resevationDTO3.setId(3l);

        dtoList.add(resevationDTO1);
        dtoList.add(resevationDTO2);
        dtoList.add(resevationDTO3);

        List<Reservation> entityList = new ArrayList<>();
        entityList.add(DTOAndEntityMapper.dtoToEntity(resevationDTO1, Reservation.class));
        entityList.add(DTOAndEntityMapper.dtoToEntity(resevationDTO2, Reservation.class));
        entityList.add(DTOAndEntityMapper.dtoToEntity(resevationDTO3, Reservation.class));
        
        when(reservationDAO.getReservationByCustomer(customer)).thenReturn(entityList);
        assertEquals(dtoList, reservationService.getByCustomer(DTOAndEntityMapper.entityToDto(customer, CustomerDTO.class)));
        verify(reservationDAO, times(2)).getReservationByCustomer(customer);
    }

    /**
     * Test of getReservationByTrip method, of class ServiceFacadeImpl.
     */
    @Test
    public void testGetReservationByTrip() {
 try {
            reservationService.getByTrip(null);
            fail();
        } catch(IllegalArgumentException ex){
            // OK
        }
        
        Trip trip = new Trip();
        trip.setId(1l);
        
        when(reservationDAO.getReservationByTrip(trip)).thenReturn(new ArrayList<Reservation>());
        ArrayList<ReservationDTO> dtoList = new ArrayList<>();
        assertEquals(new ArrayList<Reservation>(), reservationDAO.getReservationByTrip(trip));

        verify(reservationDAO, times(1)).getReservationByTrip(trip);
        
        ReservationDTO resevationDTO1 = new ReservationDTO();
        ReservationDTO resevationDTO2 = new ReservationDTO();
        ReservationDTO resevationDTO3 = new ReservationDTO();

        resevationDTO1.setId(1l);
        resevationDTO2.setId(2l);
        resevationDTO3.setId(3l);

        dtoList.add(resevationDTO1);
        dtoList.add(resevationDTO2);
        dtoList.add(resevationDTO3);

        List<Reservation> entityList = new ArrayList<>();
        entityList.add(DTOAndEntityMapper.dtoToEntity(resevationDTO1, Reservation.class));
        entityList.add(DTOAndEntityMapper.dtoToEntity(resevationDTO2, Reservation.class));
        entityList.add(DTOAndEntityMapper.dtoToEntity(resevationDTO3, Reservation.class));
        
        when(reservationDAO.getReservationByTrip(trip)).thenReturn(entityList);
        assertEquals(dtoList, reservationService.getByTrip(DTOAndEntityMapper.entityToDto(trip, TripDTO.class)));
        verify(reservationDAO, times(2)).getReservationByTrip(trip);
    }

    /**
     * Test of getAllCustomers method, of class ServiceFacadeImpl.
     */
    @Test
    public void testGetAllCustomers() {
        List<Customer> expected = new ArrayList<>();
        expected.add(newCustomer());
        when(customerDAO.getAllCustomers()).thenReturn(expected);
        List<CustomerDTO> returned = customerService.getAll();
        
        verify(customerDAO).getAllCustomers();
        verifyNoMoreInteractions(customerDAO);
        
        assertDeepEquals(EntityToDTOList(expected), returned);
    }

    /**
     * Test of getAllTrips method, of class ServiceFacadeImpl.
     */
    @Test
    public void testGetAllTrips() {
        List<Trip> expected = new ArrayList<Trip>();
        expected.add(prepareTrip());
        when(tripDAO.getAllTrips()).thenReturn(expected);
        List<TripDTO> returned = tripService.getAll();

        verify(tripDAO).getAllTrips();
        verifyNoMoreInteractions(tripDAO);

        for (int i = 0; i < expected.size(); i++) {
            assertTripDeepEquals(DTOAndEntityMapper.entityToDto(expected.get(i), TripDTO.class), returned.get(i));
        }
    }

    /**
     * Test of getAllExcursions method, of class ServiceFacadeImpl.
     */
    @Test
    public void testGetAllExcursions() {
        when(excursionDAO.getAllExcursions()).thenReturn(new ArrayList<Excursion>());
        ArrayList<ExcursionDTO> dtoList = new ArrayList<ExcursionDTO>();
        assertEquals(new ArrayList<Excursion>(), excursionDAO.getAllExcursions());

        ExcursionDTO excursionDto1 = newExcursionDto("excursion1");
        ExcursionDTO excursionDto2 = newExcursionDto("excursion2");
        ExcursionDTO excursionDto3 = newExcursionDto("excursion3");

        excursionDto1.setId(1l);
        excursionDto2.setId(2l);
        excursionDto3.setId(3l);

        dtoList.add(excursionDto1);
        dtoList.add(excursionDto2);
        dtoList.add(excursionDto3);

        List<Excursion> entityList = new ArrayList<Excursion>();
        entityList.add(DTOAndEntityMapper.dtoToEntity(excursionDto1, Excursion.class));
        entityList.add(DTOAndEntityMapper.dtoToEntity(excursionDto2, Excursion.class));
        entityList.add(DTOAndEntityMapper.dtoToEntity(excursionDto3, Excursion.class));

        when(excursionDAO.getAllExcursions()).thenReturn(entityList);
        assertEquals(dtoList, excursionService.getAll());
        verify(excursionDAO, times(2)).getAllExcursions();

    }

    /**
     * Test of getAllReservations method, of class ServiceFacadeImpl.
     */
    @Test
    public void testGetAllReservations() {
        when(reservationDAO.getAllReservations()).thenReturn(new ArrayList<Reservation>());
        ArrayList<ReservationDTO> dtoList = new ArrayList<>();
        assertEquals(new ArrayList<Reservation>(), reservationDAO.getAllReservations());

        verify(reservationDAO, times(1)).getAllReservations();
        
        ReservationDTO resevationDTO1 = new ReservationDTO();
        ReservationDTO resevationDTO2 = new ReservationDTO();
        ReservationDTO resevationDTO3 = new ReservationDTO();

        resevationDTO1.setId(1l);
        resevationDTO2.setId(2l);
        resevationDTO3.setId(3l);

        dtoList.add(resevationDTO1);
        dtoList.add(resevationDTO2);
        dtoList.add(resevationDTO3);

        List<Reservation> entityList = new ArrayList<Reservation>();
        entityList.add(DTOAndEntityMapper.dtoToEntity(resevationDTO1, Reservation.class));
        entityList.add(DTOAndEntityMapper.dtoToEntity(resevationDTO2, Reservation.class));
        entityList.add(DTOAndEntityMapper.dtoToEntity(resevationDTO3, Reservation.class));

        when(reservationDAO.getAllReservations()).thenReturn(entityList);
        assertEquals(dtoList, reservationService.getAll());
        verify(reservationDAO, times(2)).getAllReservations();

    }

    /**
     * Test of updateCustomer method, of class ServiceFacadeImpl.
     */
    @Test
    public void testUpdateCustomer() {
        CustomerDTO customerDTO = newCustomerDTO();
        customerDTO.setId(1l);
        customerService.update(customerDTO);

        verify(customerDAO).updateCustomer(any(Customer.class));
        verifyNoMoreInteractions(customerDAO);
    }

    /**
     * Test of updateTrip method, of class ServiceFacadeImpl.
     */
    @Test
    public void testUpdateTrip() {
        doThrow(new IllegalArgumentException()).when(tripDAO).updateTrip(null);

        try {
            tripService.update(null);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        Trip trip = prepareTrip();
        TripDTO tripDTO = DTOAndEntityMapper.entityToDto(trip, TripDTO.class);
        //TripDTO tripDTO = DTOAndEntityMapper.entityToDto(trip);
        tripDTO.setId(1l);
        tripService.update(tripDTO);

        verify(tripDAO).updateTrip(any(Trip.class));
        verifyNoMoreInteractions(tripDAO);
    }

    /**
     * Test of updateExcursion method, of class ServiceFacadeImpl.
     */
    @Test
    public void testUpdateExcursion() {
        doThrow(new IllegalArgumentException()).when(excursionDAO).updateExcursion(null);

        try {
            excursionService.update(null);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }

        ExcursionDTO excursionDTO = newExcursionDto();
        excursionDTO.setTrip(new TripDTO());
        excursionDTO.setId(1L);
        excursionService.update(excursionDTO);

        verify(excursionDAO,never()).createExcursion(null);
        verify(excursionDAO,times(1)).updateExcursion(DTOAndEntityMapper.dtoToEntity(excursionDTO, Excursion.class));
    }

    /**
     * Test of updateReservation method, of class ServiceFacadeImpl.
     */
    @Test
    public void testUpdateReservation() {
        try {
            reservationService.update(null);
            fail();
        } catch(IllegalArgumentException ex){
            // OK
        }
         
        ReservationDTO reservationDTO = newReservationDTO();        
        reservationDTO.setTrip(new TripDTO());
        reservationDTO.setId(1L);
        reservationService.update(reservationDTO);
        
        verify(reservationDAO,never()).createReservation(null);
        verify(reservationDAO,times(1)).updateReservation(DTOAndEntityMapper.dtoToEntity(reservationDTO, Reservation.class));
    }

    /**
     * Test of findTripsByDateRange method, of class ServiceFacadeImpl.
     */
    @Test
    public void testFindTripsByDateRange() {
        List<Trip> expected = new ArrayList<Trip>();
        expected.add(prepareTrip());
        when(tripDAO.getAllTrips()).thenReturn(expected);
        List<TripDTO> returned = tripService.getAll();

        verify(tripDAO).getAllTrips();
        verifyNoMoreInteractions(tripDAO);

        for (int i = 0; i < expected.size(); i++) {
            assertTripDeepEquals(DTOAndEntityMapper.entityToDto(expected.get(i), TripDTO.class), returned.get(i));
        }
    }

    /**
     * Test of findTripsByDestination method, of class ServiceFacadeImpl.
     */
    @Test
    public void testFindTripsByDestination() {
        List<Trip> expected = new ArrayList<Trip>();
        expected.add(prepareTrip());
        when(tripDAO.getAllTrips()).thenReturn(expected);
        List<TripDTO> returned = tripService.getAll();

        verify(tripDAO).getAllTrips();
        verifyNoMoreInteractions(tripDAO);

        for (int i = 0; i < expected.size(); i++) {
            assertTripDeepEquals(DTOAndEntityMapper.entityToDto(expected.get(i), TripDTO.class), returned.get(i));
        }
    }
    
    
    private CustomerDTO newCustomerDTO() {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("John");
        customer.setLastName("Doe");
//        List<ReservationDTO> reservations = new ArrayList<ReservationDTO>();
//        reservations.add(new ReservationDTO());
//        reservations.add(new ReservationDTO());
//        customer.setReservations(reservations);
        return customer;
    }
    
    private Customer newCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Deo");
//        List<Reservation> reservations = new ArrayList<Reservation>();
//        reservations.add(new Reservation());
//        reservations.add(new Reservation());
//        customer.setReservations(reservations);
        return customer;
    }
    
    private List<CustomerDTO> EntityToDTOList(List<Customer> list) {
        List<CustomerDTO> result = new ArrayList<CustomerDTO>();
        for(Customer c : list) {
            result.add(DTOAndEntityMapper.entityToDto(c, CustomerDTO.class));
        }
        return result;
    }

    private void assertDeepEquals(CustomerDTO expected, CustomerDTO actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        //assertEquals(expected.getReservations().size(), actual.getReservations().size());
    }
    
    private void assertDeepEquals(List<CustomerDTO> expectedList, List<CustomerDTO> actualList) {
        for (int i = 0; i < expectedList.size(); i++) {
            assertDeepEquals(expectedList.get(i), actualList.get(i));
        }
    }
    
    private Trip prepareTrip() {
        Trip preparedTrip = new Trip();
        preparedTrip.setDateFrom(new DateTime(2013, 11, 23, 10, 00));
        preparedTrip.setDateTo(new DateTime(2013, 1, 30, 10, 00));
        preparedTrip.setDestination("Spain");
        preparedTrip.setAvailableTrips(10);
        preparedTrip.setPrice(new BigDecimal(15200.25));

        return preparedTrip;
    }

    private static void assertTripDeepEquals(TripDTO expected, TripDTO actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getDateFrom(), actual.getDateFrom());
        assertEquals(expected.getDateTo(), actual.getDateTo());
        assertEquals(expected.getDestination(), actual.getDestination());
        assertEquals(expected.getAvailableTrips(), actual.getAvailableTrips());
        assertEquals(expected.getPrice(), actual.getPrice());
    }
    
    private ExcursionDTO newExcursionDto(String description) {
        ExcursionDTO excursionDto = new ExcursionDTO();
        excursionDto.setDescription(description);
        excursionDto.setExcursionDate(new DateTime(2013, 10, 12, 10, 00));
        excursionDto.setPrice(new BigDecimal(10));
        excursionDto.setTrip(new TripDTO());
        return excursionDto;
    }

    private ExcursionDTO newExcursionDto() {
        return newExcursionDto("Description");
    }
    
    private ReservationDTO newReservationDTO() {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setCustomer(new CustomerDTO());
        List<ExcursionDTO> excursions = new ArrayList<ExcursionDTO>();
        ExcursionDTO excursion = new ExcursionDTO();
        excursions.add(excursion);
        reservationDTO.setExcursions(excursions);
        reservationDTO.setTrip(new TripDTO());
        return reservationDTO;
    }
}