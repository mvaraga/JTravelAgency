/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.jtravelagency;

import cz.muni.fi.pa165.jtravelagency.jtravelagency.TripDAOImpl;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import junit.framework.TestCase;

/**
 *
 * @author Jakub Marecek (404364)
 */
public class TripDAOImplTest extends TestCase {
    
    private EntityManagerFactory emf;
    
    private EntityManager em;
    
    private  cz.muni.fi.pa165.jtravelagency.jtravelagency.TripDAO tripDAOImpl;
    
    private SimpleDateFormat sdf =  new SimpleDateFormat("dd. MM. yyyy");
    
    
    public TripDAOImplTest(String testName) {
        super(testName);
    }
    
    
    @Override
    protected void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory("TestPU");
        em = emf.createEntityManager();
        tripDAOImpl = new cz.muni.fi.pa165.jtravelagency.jtravelagency.TripDAOImpl(em);
    }
    
    @Override
    protected void tearDown() throws Exception {
        em.close();
        emf.close();
    }

    
    /**
     * Test of createTrip method, of class TripDAO.
     */
    public void testCreateTrip() {
        Trip trip = prepareTrip();
        em.getTransaction().begin();
        tripDAOImpl.createTrip(trip);
        Trip result = tripDAOImpl.getTrip(trip.getId());
        em.getTransaction().commit();
        assertEquals(trip, result);
        assertTripDeepEquals(trip, result);
    }
    
    /**
     * Test of createTrip method with wrong attributes, of class TripDAO.
     */
    public void testCreateTripWithWrongAttributes() {
        // fail with null
        try {
            tripDAOImpl.createTrip(null);
            fail();
        } catch (IllegalArgumentException ex) {
            // OK
        }
        
        // fail with set ID
        Trip trip = prepareTrip();
        trip.setId(Long.MIN_VALUE);
        try {
            tripDAOImpl.createTrip(trip);
            fail();
        } catch (IllegalArgumentException ex) {
            // OK
        }
        
        // fail with null dateFrom
        trip = prepareTrip();
        trip.setDateFrom(null);
        try {
            tripDAOImpl.createTrip(trip);
            fail();
        } catch (IllegalArgumentException ex) {
            // OK
        }

        // fail with null dateTo
        trip = prepareTrip();
        trip.setDateTo(null);
        try {
            tripDAOImpl.createTrip(trip);
            fail();
        } catch (IllegalArgumentException ex) {
            // OK
        }
        
        // fail with null destination
        trip = prepareTrip();
        trip.setDestination(null);
        try {
            tripDAOImpl.createTrip(trip);
            fail();
        } catch (IllegalArgumentException ex) {
            // OK
        }
    }

    /**
     * Test of getTrip method, of class TripDAO.
     */
    public void testGetTrip() {
        assertNull(tripDAOImpl.getTrip(Long.MIN_VALUE));
        em.getTransaction().begin();
        Trip trip = prepareTrip();
        tripDAOImpl.createTrip(trip);
        Long tripId = trip.getId();
        Trip result = tripDAOImpl.getTrip(tripId);
        em.getTransaction().commit();
        assertEquals(trip, result);
        assertTripDeepEquals(trip, result);
    }

    /**
     * Test of updateTrip method, of class TripDAO.
     */
    public void testUpdateTrip() {
        try {
            em.getTransaction().begin();
            Trip trip = prepareTrip();
            tripDAOImpl.createTrip(trip);
            Long tripId = trip.getId();
            em.getTransaction().commit();
            
            // test updated dateFrom
            em.getTransaction().begin();
            trip.setDateFrom(sdf.parse("12. 12. 2013"));
            tripDAOImpl.updateTrip(trip);
            Trip result = tripDAOImpl.getTrip(tripId);
            em.getTransaction().commit();
            assertEquals(trip, result);
            assertTripDeepEquals(trip, result);
            
            // test updated dateTo
            em.getTransaction().begin();
            trip.setDateTo(sdf.parse("22. 12. 2013"));
            tripDAOImpl.updateTrip(trip);
            result = tripDAOImpl.getTrip(tripId);
            em.getTransaction().commit();
            assertEquals(trip, result);
            assertTripDeepEquals(trip, result);
            
            // test updated destination
            em.getTransaction().begin();
            trip.setDestination("Another Destination");
            tripDAOImpl.updateTrip(trip);
            result = tripDAOImpl.getTrip(tripId);
            em.getTransaction().commit();
            assertEquals(trip, result);
            assertTripDeepEquals(trip, result);
            
            // test updated availableTrips
            em.getTransaction().begin();
            trip.setAvailableTrips(20);
            tripDAOImpl.updateTrip(trip);
            result = tripDAOImpl.getTrip(tripId);
            em.getTransaction().commit();
            assertEquals(trip, result);
            assertTripDeepEquals(trip, result);
            
            // test updated price
            em.getTransaction().begin();
            trip.setPrice(BigDecimal.valueOf(18200.50));
            tripDAOImpl.updateTrip(trip);
            result = tripDAOImpl.getTrip(tripId);
            em.getTransaction().commit();
            assertEquals(trip, result);
            assertTripDeepEquals(trip, result);
        } catch (ParseException ex) {
            Logger.getLogger(TripDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deleteTrip method, of class TripDAO.
     */
    public void testDeleteTrip() {
        em.getTransaction().begin();
        Trip trip = prepareTrip();
        tripDAOImpl.createTrip(trip);
        Long tripId = trip.getId();
        assertNotNull(tripDAOImpl.getTrip(tripId));
        //tripDAOImpl.deleteTrip(trip);
        //em.getTransaction().commit();
        //assertNull(tripDAOImpl.getTrip(tripId));
    }

    /**
     * Test of getAllTrips method, of class TripDAO.
     */
    public void testGetAllTrips() {
        em.getTransaction().begin();
        Trip trip1 = prepareTrip();
        Trip trip2 = prepareTrip();        
        tripDAOImpl.createTrip(trip1);
        tripDAOImpl.createTrip(trip2);
        em.getTransaction().commit();
        assertEquals(2, tripDAOImpl.getAllTrips().size());
    }

    /**
     * Test of findTripsByDateRange method, of class TripDAO.
     */
    public void testFindTripsByDateRange() {
        try {
            em.getTransaction().begin();
            Trip trip1 = prepareTrip();
            trip1.setDateFrom(sdf.parse("23. 01. 2013"));
            trip1.setDateTo(sdf.parse("25. 01. 2013"));
            Trip trip2 = prepareTrip();
            trip2.setDateFrom(sdf.parse("27. 01. 2013"));
            trip2.setDateTo(sdf.parse("28. 01. 2013"));        
            tripDAOImpl.createTrip(trip1);
            tripDAOImpl.createTrip(trip2);
            em.getTransaction().commit();
            List<Trip> trips = tripDAOImpl.findTripsByDateRange(sdf.parse("18. 01. 2013"),
                                                                sdf.parse("22. 01. 2013"));
            assertEquals(0, trips.size());
            trips = tripDAOImpl.findTripsByDateRange(sdf.parse("22. 01. 2013"),
                                                     sdf.parse("29. 01. 2013"));
            assertEquals(2, trips.size());
            trips = tripDAOImpl.findTripsByDateRange(sdf.parse("01. 02. 2013"),
                                                     sdf.parse("15. 02. 2013"));
            assertEquals(0, trips.size());
        } catch (ParseException ex) {
            Logger.getLogger(TripDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of findTripsByDestination method, of class TripDAO.
     */
    public void testFindTripsByDestination() {
        em.getTransaction().begin();
        Trip trip1 = prepareTrip();
        trip1.setDestination("Greece");
        tripDAOImpl.createTrip(trip1);
        Trip trip2 = prepareTrip();
        trip1.setDestination("Spain");
        tripDAOImpl.createTrip(trip2);
        Trip trip3 = prepareTrip();
        trip1.setDestination("Greece");
        tripDAOImpl.createTrip(trip3);
        em.getTransaction().commit();
        List<Trip> trips = tripDAOImpl.findTripsByDestination("Portugal");
        assertEquals(0, trips.size());
        trips = tripDAOImpl.findTripsByDestination("Greece");
        assertEquals(1, trips.size());
        trips = tripDAOImpl.findTripsByDestination("Spain");
        assertEquals(2, trips.size());
    }
    
    
    private Trip prepareTrip() {
        Trip preparedTrip = new Trip();
        try {
            preparedTrip.setDateFrom(sdf.parse("23. 11. 2013"));
            preparedTrip.setDateTo(sdf.parse("30. 11. 2013"));
            preparedTrip.setDestination("Spain");
            preparedTrip.setAvailableTrips(10);
            preparedTrip.setPrice(new BigDecimal(15200.25));
        } catch (ParseException ex) {
            Logger.getLogger(TripDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return preparedTrip;
        }
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
