package cz.muni.fi.pa165.jtravelagency;

import cz.muni.fi.pa165.jtravelagency.entity.Trip;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import junit.framework.TestCase;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;


public class TripDAOImplTest extends TestCase {
    
    private EntityManagerFactory emf;
    
    private EntityManager em;
    
    private  cz.muni.fi.pa165.jtravelagency.dao.TripDAO tripDAOImpl;
    
    
    public TripDAOImplTest(String testName) {
        super(testName);
    }
    
    
    @Override
    protected void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory("TestPU");
        em = emf.createEntityManager();
        tripDAOImpl = new cz.muni.fi.pa165.jtravelagency.dao.TripDAOImpl(em);
    }
    
    @Override
    protected void tearDown() throws Exception {
        em.close();
        emf.close();
    }

    
  
    public void testCreateTrip() {
        Trip trip = prepareTrip();
        em.getTransaction().begin();
        tripDAOImpl.createTrip(trip);
        Trip result = tripDAOImpl.getTrip(trip.getId());
        em.getTransaction().commit();
        assertEquals(trip, result);
        assertTripDeepEquals(trip, result);
    }
    
   
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

   
    public void testUpdateTrip() {
            em.getTransaction().begin();
            Trip trip = prepareTrip();
            tripDAOImpl.createTrip(trip);
            Long tripId = trip.getId();
            em.getTransaction().commit();
            
            // test updated dateFrom
            em.getTransaction().begin();
            trip.setDateFrom(new DateTime(2013, 12, 12, 10, 00));
            tripDAOImpl.updateTrip(trip);
            Trip result = tripDAOImpl.getTrip(tripId);
            em.getTransaction().commit();
            assertEquals(trip, result);
            assertTripDeepEquals(trip, result);
            
            // test updated dateTo
            em.getTransaction().begin();
            trip.setDateTo(new DateTime(2013, 12, 22, 10, 00));
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
    }

  
    public void testDeleteTrip() {
        em.getTransaction().begin();
        Trip trip = prepareTrip();
        tripDAOImpl.createTrip(trip);
        Long tripId = trip.getId();
        assertNotNull(tripDAOImpl.getTrip(tripId));
        tripDAOImpl.deleteTrip(trip);
        em.getTransaction().commit();
        assertNull(tripDAOImpl.getTrip(tripId));
    }
    
  
    public void testGetAllTrips() {
        em.getTransaction().begin();
        Trip trip1 = prepareTrip();
        Trip trip2 = prepareTrip();        
        tripDAOImpl.createTrip(trip1);
        tripDAOImpl.createTrip(trip2);
        em.getTransaction().commit();
        assertEquals(2, tripDAOImpl.getAllTrips().size());
    }

    
    public void testFindTripsByDateRange() {
        em.getTransaction().begin();
        Trip trip1 = prepareTrip();
        trip1.setDateFrom(new DateTime(2013, 1, 23, 10, 00));
        trip1.setDateTo(new DateTime(2013, 1, 25, 10, 00));
        Trip trip2 = prepareTrip();
        trip2.setDateFrom(new DateTime(2013, 1, 27, 10, 00));
        trip2.setDateTo(new DateTime(2013, 1, 28, 10, 00));
        tripDAOImpl.createTrip(trip1);
        tripDAOImpl.createTrip(trip2);
        em.getTransaction().commit();
        List<Trip> trips = tripDAOImpl.findTripsByDateRange(new DateTime(2013, 1, 18, 10, 00),
                new DateTime(2013, 1, 22, 10, 00));
        //assertEquals(0, trips.size());
        //trips = tripDAOImpl.findTripsByDateRange(new DateTime(2013, 1, 22, 10, 00),
        //        new DateTime(2013, 1, 29, 10, 00));
        //assertEquals(2, trips.size());
        //trips = tripDAOImpl.findTripsByDateRange(new DateTime(2013, 2, 1, 10, 00),
        //        new DateTime(2013, 2, 15, 10, 00));
        //assertEquals(0, trips.size());
    }

   
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
        preparedTrip.setDateFrom(new DateTime(2013, 11, 23, 10, 00));
        preparedTrip.setDateTo(new DateTime(2013, 1, 30, 10, 00));
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
