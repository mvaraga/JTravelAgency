/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.service;

import cz.muni.fi.pa165.jtravelagency.dao.ReservationDAO;
import cz.muni.fi.pa165.jtravelagency.dto.CustomerDTO;
import cz.muni.fi.pa165.jtravelagency.dto.ExcursionDTO;
import cz.muni.fi.pa165.jtravelagency.dto.ReservationDTO;
import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import cz.muni.fi.pa165.jtravelagency.entity.Customer;
import cz.muni.fi.pa165.jtravelagency.entity.Excursion;
import cz.muni.fi.pa165.jtravelagency.entity.Reservation;
import cz.muni.fi.pa165.jtravelagency.entity.Trip;
import cz.muni.fi.pa165.jtravelagency.util.DTOAndEntityMapper;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceImplTest extends TestCase {
    
    
    @InjectMocks
    private ReservationServiceImpl service;
    
    @Mock
    private ReservationDAO reservationDAO;
    
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }


   
    @Test
    public void testCreate() {
        try{
            service.create(null);
            fail();
        }   catch(IllegalArgumentException ex){
            // OK
        }
        
        verify(reservationDAO,never()).createReservation(null);
        
        ReservationDTO reservationDTO = newReservationDTO();        
        Reservation reservation = newReservation();
        service.create(reservationDTO);
        
        verify(reservationDAO).createReservation(reservation);
        
        //doNothing().when(reservationDAO).createReservation(any(Reservation.class));
        //service.create(DTOAndEntityMapper.entityToDto(reservation));
        //verify(reservationDAO,times(0)).createReservation(reservation);
        
    }
    

    @Test(expected = IllegalArgumentException.class)
    public void testCreateException() {
        ReservationDTO reservationDTO = newReservationDTO();    
        reservationDTO.setId(1l);
        try {
            service.create(reservationDTO);
            fail();
        }
        finally {
            verify(reservationDAO,never()).createReservation(null);
        }
    }
    
   
    @Test
    public void testDelete() {
        try{
            service.delete(null);
            fail();
        }   catch(IllegalArgumentException ex){
            // OK
        }
        
        verify(reservationDAO,never()).deleteReservation(null);
        
        ReservationDTO reservationDTO = newReservationDTO();
        Reservation reservation = newReservation();
        service.create(reservationDTO);
        
        verify(reservationDAO,times(0)).deleteReservation(reservation);
        
        //todo
        //service.delete(reservationDTO);
  
        verify(reservationDAO,times(1)).createReservation(reservation);
        //verify(reservationDAO,times(1)).deleteReservation(reservation);
    }

    @Test
    public void testUpdate() {
        try {
            service.update(null);
            fail();
        } catch(IllegalArgumentException ex){
            // OK
        }
         
        ReservationDTO reservationDTO = newReservationDTO();
        reservationDTO.setTrip(new TripDTO());
        service.update(reservationDTO);
        
        verify(reservationDAO,never()).createReservation(null);
        verify(reservationDAO,times(1)).updateReservation(DTOAndEntityMapper.dtoToEntity(reservationDTO, Reservation.class));
    }

   
    @Test
    public void testGet() {
        try {
            service.get(null);
            fail();
        } catch(IllegalArgumentException ex){
            // OK
        }
        
        verify(reservationDAO,never()).createReservation(null);
        verify(reservationDAO,never()).updateReservation(null);
        
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(1l);

        when(reservationDAO.getReservation(1l)).thenReturn(DTOAndEntityMapper.dtoToEntity(reservationDTO, Reservation.class));
        assertEquals(reservationDTO, service.get(reservationDTO.getId()));

        verify(reservationDAO, times(1)).getReservation(1l);
        verify(reservationDAO, times(0)).createReservation(DTOAndEntityMapper.dtoToEntity(reservationDTO, Reservation.class));
        verify(reservationDAO, never()).updateReservation(DTOAndEntityMapper.dtoToEntity(reservationDTO, Reservation.class));
    }

 
    @Test
    public void testGetAll() {
        when(reservationDAO.getAllReservations()).thenReturn(new ArrayList<Reservation>());
        ArrayList<ReservationDTO> dtoList = new ArrayList<ReservationDTO>();
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
        assertEquals(dtoList, service.getAll());
        verify(reservationDAO, times(2)).getAllReservations();
    }

    
    @Test
    public void testGetByTrip() {
        try {
            service.getByTrip(null);
            fail();
        } catch(IllegalArgumentException ex){
            // OK
        }
        
        Trip trip = new Trip();
        trip.setId(1l);
        
        when(reservationDAO.getReservationByTrip(trip)).thenReturn(new ArrayList<Reservation>());
        ArrayList<ReservationDTO> dtoList = new ArrayList<ReservationDTO>();
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

        List<Reservation> entityList = new ArrayList<Reservation>();
        entityList.add(DTOAndEntityMapper.dtoToEntity(resevationDTO1, Reservation.class));
        entityList.add(DTOAndEntityMapper.dtoToEntity(resevationDTO2, Reservation.class));
        entityList.add(DTOAndEntityMapper.dtoToEntity(resevationDTO3, Reservation.class));
        
        when(reservationDAO.getReservationByTrip(trip)).thenReturn(entityList);
        assertEquals(dtoList, service.getByTrip(DTOAndEntityMapper.entityToDto(trip, TripDTO.class)));
        verify(reservationDAO, times(2)).getReservationByTrip(trip);
    }


    @Test
    public void testGetByCustomer() {
        try {
            service.getByCustomer(null);
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

        List<Reservation> entityList = new ArrayList<Reservation>();
        entityList.add(DTOAndEntityMapper.dtoToEntity(resevationDTO1, Reservation.class));
        entityList.add(DTOAndEntityMapper.dtoToEntity(resevationDTO2, Reservation.class));
        entityList.add(DTOAndEntityMapper.dtoToEntity(resevationDTO3, Reservation.class));
        
        when(reservationDAO.getReservationByCustomer(customer)).thenReturn(entityList);
        assertEquals(dtoList, service.getByCustomer(DTOAndEntityMapper.entityToDto(customer, CustomerDTO.class)));
        verify(reservationDAO, times(2)).getReservationByCustomer(customer);
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
    
    private Reservation newReservation() {
        Reservation reservation = new Reservation();
        reservation.setCustomer(new Customer());
        List<Excursion> excursions = new ArrayList<Excursion>();
        Excursion excursion = new Excursion();
        excursions.add(excursion);
        reservation.setExcursions(excursions);
        reservation.setTrip(new Trip());
        return reservation;
    }
}
*/
