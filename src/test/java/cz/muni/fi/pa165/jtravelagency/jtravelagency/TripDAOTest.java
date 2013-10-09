/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.jtravelagency;

import cz.muni.fi.pa165.jtravelagency.jtravelagency.TripDAOImpl;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.TestCase;

/**
 *
 * @author jakub
 */
public class TripDAOTest extends TestCase {
    
    private EntityManagerFactory emf;
    
    private EntityManager em;
    
    private  cz.muni.fi.pa165.jtravelagency.jtravelagency.TripDAOImpl tripDAOImpl;
    
    
    public TripDAOTest(String testName) {
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
        System.out.println("createTrip");
        em.getTransaction().begin();
        Trip trip = new Trip();
        trip.setAvailableTrips(5);
        em.getTransaction().commit();
        assertTrue(trip.getId() == 1);
    }

    /**
     * Test of getTrip method, of class TripDAO.
     *
    public void testGetTrip() {
        System.out.println("getTrip");
        Long id = null;
        TripDAO instance = new TripDAOImpl();
        Trip expResult = null;
        Trip result = instance.getTrip(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateTrip method, of class TripDAO.
     *
    public void testUpdateTrip() {
        System.out.println("updateTrip");
        Trip trip = null;
        TripDAO instance = new TripDAOImpl();
        instance.updateTrip(trip);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteTrip method, of class TripDAO.
     *
    public void testDeleteTrip() {
        System.out.println("deleteTrip");
        Trip trip = null;
        TripDAO instance = new TripDAOImpl();
        instance.deleteTrip(trip);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllTrips method, of class TripDAO.
     *
    public void testGetAllTrips() {
        System.out.println("getAllTrips");
        TripDAO instance = new TripDAOImpl();
        List expResult = null;
        List result = instance.getAllTrips();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findTripsByDateRange method, of class TripDAO.
     *
    public void testFindTripsByDateRange() {
        System.out.println("findTripsByDateRange");
        Date from = null;
        Date to = null;
        TripDAO instance = new TripDAOImpl();
        List expResult = null;
        List result = instance.findTripsByDateRange(from, to);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findTripsByDestination method, of class TripDAO.
     *
    public void testFindTripsByDestination() {
        System.out.println("findTripsByDestination");
        String destination = "";
        TripDAO instance = new TripDAOImpl();
        List expResult = null;
        List result = instance.findTripsByDestination(destination);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findTripsByPrice method, of class TripDAO.
     *
    public void testFindTripsByPrice() {
        System.out.println("findTripsByPrice");
        BigDecimal price = null;
        TripDAO instance = new TripDAOImpl();
        List expResult = null;
        List result = instance.findTripsByPrice(price);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    } */
}
