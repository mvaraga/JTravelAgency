/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency;

import cz.muni.fi.pa165.jtravelagency.dao.TripDAO;
import cz.muni.fi.pa165.jtravelagency.dao.TripDAOImpl;
import cz.muni.fi.pa165.jtravelagency.entity.Trip;
import cz.muni.fi.pa165.jtravelagency.entity.Excursion;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 *
 * @author Marian Varaga
 */
public class ExcursionDAOImplTest extends TestCase {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityManager em2;
    private cz.muni.fi.pa165.jtravelagency.dao.ExcursionDAO instance;
    private TripDAO tripDAO;

    public ExcursionDAOImplTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory("TestPU");
        em = emf.createEntityManager();
        em2 = emf.createEntityManager();
        instance = new cz.muni.fi.pa165.jtravelagency.dao.ExcursionDAOImpl(em);
        tripDAO = new TripDAOImpl(em2);
    }
    
    @Override
    protected void tearDown() throws Exception {
        //super.tearDown();
        em2.close();
        em.close();
        emf.close();
        instance = null;
    }

    /**
     * Test of createExcurtion method, of class ExcursionDAOImpl.
     */
    public void testCreateExcurtion() {
        System.out.println("createExcurtion");
        
        Trip trip = prepareTrip();
        Excursion excursion = newExcursion();
        excursion.setTrip(trip);
        
        em2.getTransaction().begin();
        tripDAO.createTrip(trip);
        em2.getTransaction().commit();
        
        em.getTransaction().begin();
        instance.createExcursion(excursion);
        Excursion result = instance.getExcursion(excursion.getId());
        em.getTransaction().commit();
        
        assertDeepEquals(excursion, result);
    }

    /**
     * Test of getExcursion method, of class ExcursionDAOImpl.
     */
    public void testGetExcursion() {
        System.out.println("getExcursion");
        
        Trip trip = prepareTrip();
        Excursion excursion = newExcursion();
        excursion.setTrip(trip);
        
        em2.getTransaction().begin();
        tripDAO.createTrip(trip);
        em2.getTransaction().commit();
             
        em.getTransaction().begin();
        instance.createExcursion(excursion);
        Excursion result = instance.getExcursion(excursion.getId());
        em.getTransaction().commit();
        
        assertDeepEquals(excursion, result);
    }
    
    /**
     * Test of getExcursion method, of class ExcursionDAOImpl.
     */
    public void testGetExcursionWrongInput(){
        try {
        instance.getExcursion(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }
    }

    /**
     * Test of updateExcursion method, of class ExcursionDAOImpl.
     */
    public void testUpdateExcursion() {
        System.out.println("updateExcursion");
        
        Trip trip = prepareTrip();
        Excursion excursion = newExcursion();
        excursion.setTrip(trip);
        
        em2.getTransaction().begin();
        tripDAO.createTrip(trip);
        em2.getTransaction().commit();
        
        DateTime date = new DateTime(2013, 6, 12, 12, 0);
        
        em.getTransaction().begin();
        instance.createExcursion(excursion);
        excursion.setDescription("new description");
        excursion.setExcursionDate(date);
        excursion.setPrice(BigDecimal.ONE.setScale(2));
        
        instance.updateExcursion(excursion);
        Excursion result = instance.getExcursion(excursion.getId());
        em.getTransaction().commit();
        
        assertDeepEquals(excursion, result);
    }

    /**
     * Test of deleteExcursion method, of class ExcursionDAOImpl.
     */
    public void testDeleteExcursion() {
        System.out.println("deleteExcursion");
        
        Trip trip = prepareTrip();
        Excursion excursion = newExcursion();
        excursion.setTrip(trip);
        
        em2.getTransaction().begin();
        tripDAO.createTrip(trip);
        em2.getTransaction().commit();

        em.getTransaction().begin();
        instance.createExcursion(excursion);
        Excursion result = instance.getExcursion(excursion.getId());
        assertNotNull(result);
        instance.deleteExcursion(result);
        result = instance.getExcursion(excursion.getId());
        em.getTransaction().commit();
        
        assertNull(result);
    }

    /**
     * Test of getAllExcursions method, of class ExcursionDAOImpl.
     */
    public void testGetAllExcursions() {
        System.out.println("getAllExcursions");
         
        Trip trip = prepareTrip();
        
        em2.getTransaction().begin();
        tripDAO.createTrip(trip);
        em2.getTransaction().commit();
        
        Excursion e1 = newExcursion();
        Excursion e2 = newExcursion();
        Excursion e3 = newExcursion();
        e1.setTrip(trip);
        e2.setTrip(trip);
        e3.setTrip(trip);
        
        List<Excursion> list = new ArrayList<Excursion>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        
        em.getTransaction().begin();
        instance.createExcursion(e1);
        instance.createExcursion(e2);
        instance.createExcursion(e3);
        List<Excursion> result = instance.getAllExcursions();
        em.getTransaction().commit();
       
        assertTrue(result.contains(e1));
        assertTrue(result.contains(e2));
        assertTrue(result.contains(e3));
        
        for (int i = 0; i < list.size(); i++) {
            Excursion expected = list.get(i);
            Excursion actual = result.get(i);
            assertDeepEquals(expected, actual);
        }
    }

    /**
     * Test of getTrip method, of class ExcursionDAOImpl.
     */
    public void testGetTrip() {
        System.out.println("getTrip");
        
        Trip trip = prepareTrip();
        
        em2.getTransaction().begin();
        tripDAO.createTrip(trip);
        em2.getTransaction().commit();
        
        Excursion excursion = newExcursion();
        excursion.setTrip(trip);
             
        em.getTransaction().begin();
        instance.createExcursion(excursion);
        Trip result = instance.getTrip(excursion);
        em.getTransaction().commit();

        assertEquals(trip, result);
    }

    private void assertDeepEquals(Excursion first, Excursion second) {
        assertNotNull(second.getId());
        assertEquals(first.getId(), second.getId());
        assertEquals(first.getDescription(), second.getDescription());
        assertEquals(first.getPrice(), second.getPrice());
        assertEquals(first.getTrip(), second.getTrip());
        assertEquals(first.getExcursionDate(), second.getExcursionDate());
    }
    
    
    private Excursion newExcursion() {
        DateTime date = new DateTime(2013, 5, 12, 12, 0);
        Excursion excursion = new Excursion();
        excursion.setDescription("description");
        excursion.setExcursionDate(date);
        excursion.setPrice(BigDecimal.ZERO.setScale(2));
        Trip trip = prepareTrip();
        excursion.setTrip(trip);
        return excursion;
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
}
