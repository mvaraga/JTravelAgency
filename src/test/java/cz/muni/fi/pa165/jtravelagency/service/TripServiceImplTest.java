/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.service;

import cz.muni.fi.pa165.jtravelagency.dao.TripDAO;
import cz.muni.fi.pa165.jtravelagency.dao.TripDAOImpl;
import cz.muni.fi.pa165.jtravelagency.dto.ExcursionDTO;
import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import cz.muni.fi.pa165.jtravelagency.entity.Trip;
import cz.muni.fi.pa165.jtravelagency.util.DTOAndEntityMapper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import org.joda.time.LocalDate;
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
    private TripServiceImpl tripService;
    
    @Mock
    private TripDAOImpl tripDao;
    
    public TripServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of setTripDAO method, of class TripServiceImpl.
     */
    public void testSetTripDAO() {
    }

    /**
     * Test of create method, of class TripServiceImpl.
     */
    public void testCreate() {
        doThrow(new IllegalArgumentException()).when(tripDao).createTrip(null);
        
        try{
            tripService.create(null);
            fail();
        }catch(IllegalArgumentException ex){
            
        }
         
        verify(tripDao).createTrip(null);
        verify(tripDao,never()).updateTrip(null);
        verifyNoMoreInteractions(tripDao);
                
        Trip trip = prepareTrip();
        
        tripService.create(DTOAndEntityMapper.entityToDto(trip, TripDTO.class));
        
        verify(tripDao,times(1)).createTrip(trip);
        verify(tripDao,times(0)).updateTrip(trip);
    }

    /**
     * Test of get method, of class TripServiceImpl.
     */
    public void testGet() {
        System.out.println("get");
        Long id = null;
        TripServiceImpl instance = new TripServiceImpl();
        TripDTO expResult = null;
        TripDTO result = instance.get(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class TripServiceImpl.
     */
    public void testUpdate() {
        doThrow(new IllegalArgumentException()).when(tripDao).updateTrip(null);
        
        try{
            tripService.update(null);
            fail();
        }catch(IllegalArgumentException ex){
           
        }
         
        verify(tripDao,never()).createTrip(null);
        verify(tripDao,times(1)).updateTrip(null);
        verifyNoMoreInteractions(tripDao);
                
        Trip trip = prepareTrip();
        tripService.update(DTOAndEntityMapper.entityToDto(trip, TripDTO.class));
        
        verify(tripDao,times(1)).updateTrip(trip);
        verify(tripDao,times(0)).createTrip(trip);
    }

    /**
     * Test of delete method, of class TripServiceImpl.
     */
    public void testDelete() {
     doThrow(new IllegalArgumentException()).when(tripDao).deleteTrip(null);
        
        try{
            tripService.delete(null);
            fail();
        }catch(IllegalArgumentException ex){
            
        }
         
        verify(tripDao,never()).createTrip(null);
        verify(tripDao,times(1)).deleteTrip(null);
        verify(tripDao,never()).updateTrip(null);
        verifyNoMoreInteractions(tripDao);
                
        Trip trip = prepareTrip();
        tripService.delete(DTOAndEntityMapper.entityToDto(trip, TripDTO.class));
        
        verify(tripDao,times(1)).deleteTrip(trip);
        verify(tripDao,times(0)).createTrip(trip);
        verify(tripDao,never()).updateTrip(trip);
    }

    /**
     * Test of getAll method, of class TripServiceImpl.
     */
    public void testGetAll() {
        doThrow(new IllegalArgumentException()).when(tripDao).getTrip(null);
        doThrow(new IllegalArgumentException()).when(tripDao).getTrip(-1l);
        
        try{
            tripService.get(null);
            fail();
        }catch(IllegalArgumentException ex){
          
        }
        
        try{
            tripService.get(-1l);
            fail();
        }catch(IllegalArgumentException ex){
            
        }
         
        verify(tripDao,never()).createTrip(null);
        verify(tripDao,times(1)).getTrip(null);
        verify(tripDao,never()).updateTrip(null);
        verify(tripDao,times(1)).getTrip(-1l);
                
        Trip trip = prepareTrip();
        trip.setId(1l);
        
        when(tripDao.getTrip(1l)).thenReturn(trip); 
            
        assertEquals(trip, tripService.get(trip.getId()));
        
        verify(tripDao,times(1)).getTrip(1l);
        verify(tripDao,times(0)).createTrip(trip);
        verify(tripDao,never()).updateTrip(trip);
    }

    /**
     * Test of findAllByDateRange method, of class TripServiceImpl.
     */
    public void testFindAllByDateRange() {
        System.out.println("findAllByDateRange");
        LocalDate from = null;
        LocalDate to = null;
        TripServiceImpl instance = new TripServiceImpl();
        List expResult = null;
        List result = instance.findAllByDateRange(from, to);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllByDestination method, of class TripServiceImpl.
     */
    public void testFindAllByDestination() {
        System.out.println("findAllByDestination");
        String destination = "";
        TripServiceImpl instance = new TripServiceImpl();
        List expResult = null;
        List result = instance.findAllByDestination(destination);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
        private Trip prepareTrip() {
        Trip preparedTrip = new Trip();
        preparedTrip.setDateFrom(new LocalDate(2013, 11, 23));
        preparedTrip.setDateTo(new LocalDate(2013, 1, 30));
        preparedTrip.setDestination("Spain");
        preparedTrip.setAvailableTrips(10);
        preparedTrip.setPrice(new BigDecimal(15200.25));
        
        return preparedTrip;
    }
    
    private static void assertTripDeepEquals(Trip expected, Trip actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getDateFrom(), actual.getDateFrom());
        assertEquals(expected.getDateTo(), actual.getDateTo());
        assertEquals(expected.getDestination(), actual.getDestination());
        assertEquals(expected.getAvailableTrips(), actual.getAvailableTrips());
        assertEquals(expected.getPrice(), actual.getPrice());
    }

}
