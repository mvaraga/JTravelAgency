/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.service;

import cz.muni.fi.pa165.jtravelagency.dao.TripDAOImpl;
import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import cz.muni.fi.pa165.jtravelagency.entity.Trip;
import cz.muni.fi.pa165.jtravelagency.util.DTOAndEntityMapper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Radka
 */
@RunWith(MockitoJUnitRunner.class)
public class TripServiceImplTest extends TestCase {
    
    @InjectMocks
    private TripServiceImpl service;
    
    @Mock
    private TripDAOImpl dao;
    
  
    
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
        doThrow(new IllegalArgumentException()).when(dao).createTrip(null);

        try {
            service.create(null);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }
        
        Trip trip =prepareTrip();
        TripDTO tripDTO = DTOAndEntityMapper.entityToDto(trip, TripDTO.class);
        service.create(tripDTO);

        verify(dao).createTrip(trip);
        verify(dao, times(0)).getTrip(any(Long.class));
        verify(dao, times(0)).updateTrip(any(Trip.class));
        verify(dao, times(0)).deleteTrip(any(Trip.class));
        verify(dao, times(0)).getAllTrips();
    }

   
    @Test
    public void testGet() {
        
                try{
            service.get(null);
            fail();
        }catch(IllegalArgumentException ex){
          
        }
        
        Trip trip=prepareTrip();
        
        TripDTO expected =DTOAndEntityMapper.entityToDto(trip, TripDTO.class);
        expected.setId(1l);
        //asi by sa to malo este do databazy poslat 
        when(dao.getTrip(1l)).thenReturn(DTOAndEntityMapper.dtoToEntity(expected, Trip.class));
        TripDTO returned = service.get(1l);

        verify(dao).getTrip(1l);
        verify(dao, times(0)).createTrip(any(Trip.class));
        verify(dao, times(0)).updateTrip(any(Trip.class));
        verify(dao, times(0)).deleteTrip(any(Trip.class));
        verify(dao, times(0)).getAllTrips();

       assertTripDeepEquals(expected, returned);
       
    }

 
    @Test
    public void testUpdate() {
        
                
        try{
            service.update(null);
            fail();
        }catch(IllegalArgumentException ex){
           
        }
        
        Trip trip = prepareTrip();
        TripDTO tripDTO=DTOAndEntityMapper.entityToDto(trip, TripDTO.class);
        tripDTO.setId(1l);
        service.update(tripDTO);

        verify(dao).updateTrip(any(Trip.class));
        verifyNoMoreInteractions(dao);
    }

    
    @Test
    public void testDelete() {
        
                try{
            service.delete(null);
            fail();
        }catch(IllegalArgumentException ex){
            
        }
        Trip trip = prepareTrip();
        TripDTO tripDTO=DTOAndEntityMapper.entityToDto(trip, TripDTO.class);
        tripDTO.setId(1l);
        service.delete(tripDTO);

        verify(dao).deleteTrip(any(Trip.class));
        verifyNoMoreInteractions(dao);
    }

    @Test
    public void testGetAll() {
        List<Trip> expected = new ArrayList<Trip>();
        expected.add(prepareTrip());
        when(dao.getAllTrips()).thenReturn(expected);
        List<TripDTO> returned = service.getAll();
        
        verify(dao).getAllTrips();
        verifyNoMoreInteractions(dao);
        
       for(int i=0;i<expected.size();i++){
       assertTripDeepEquals(DTOAndEntityMapper.entityToDto(expected.get(i), TripDTO.class), returned.get(i));
       }
    }

    
    @Test
    public void testFindAllByDateRange() {

                List<Trip> expected = new ArrayList<Trip>();
        expected.add(prepareTrip());
        when(dao.getAllTrips()).thenReturn(expected);
        List<TripDTO> returned = service.getAll();
        
        verify(dao).getAllTrips();
        verifyNoMoreInteractions(dao);
        
       for(int i=0;i<expected.size();i++){
       assertTripDeepEquals(DTOAndEntityMapper.entityToDto(expected.get(i), TripDTO.class), returned.get(i));
    }
    
    }

    @Test
    public void testFindAllByDestination() {

                List<Trip> expected = new ArrayList<Trip>();
        expected.add(prepareTrip());
        when(dao.getAllTrips()).thenReturn(expected);
        List<TripDTO> returned = service.getAll();
        
        verify(dao).getAllTrips();
        verifyNoMoreInteractions(dao);
        
       for(int i=0;i<expected.size();i++){
       assertTripDeepEquals(DTOAndEntityMapper.entityToDto(expected.get(i), TripDTO.class), returned.get(i));
    }}
    
        private Trip prepareTrip() {
        Trip preparedTrip = new Trip();
        preparedTrip.setDateFrom(new LocalDate(2013, 11, 23));
        preparedTrip.setDateTo(new LocalDate(2013, 1, 30));
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
   

}
