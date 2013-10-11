/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.jtravelagency;

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
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;

/**
 *
 * @author Marian Varaga
 */
public class ExcursionDAOImplTest extends TestCase {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    private cz.muni.fi.pa165.jtravelagency.jtravelagency.ExcursionDAO instance;
    private SimpleDateFormat sdf =  new SimpleDateFormat("dd. MM. yyyy");
    private SimpleDateFormat dateTime =  new SimpleDateFormat("HH:mm dd. MM. yyyy");

    public ExcursionDAOImplTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory("TestPU");
        em = emf.createEntityManager();
        instance = new cz.muni.fi.pa165.jtravelagency.jtravelagency.ExcursionDAOImpl(em);

    }
    
    @Override
    protected void tearDown() throws Exception {
        //super.tearDown();
        em.close();
        emf.close();
        instance = null;
    }

    /**
     * Test of createExcurtion method, of class ExcursionDAOImpl.
     */
    public void testCreateExcurtion() {
        System.out.println("createExcurtion");
        Excursion excursion = newExcursion();
             
        em.getTransaction().begin();
        instance.createExcurtion(excursion);
        Excursion result = instance.getExcursion(excursion.getId());
        em.getTransaction().commit();
        
        assertDeepEquals(excursion, result);
    }

    private Excursion newExcursion() {
        Date date = null;
        try {
            date = dateTime.parse("12:00 12. 5. 2013");
        } catch (ParseException ex) {
            Logger.getLogger(TripDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        Excursion excursion = new Excursion();
        excursion.setDescription("description");
        excursion.setExcursionDate(date);
        excursion.setPrice(BigDecimal.ZERO.setScale(2));
        Trip trip = prepareTrip();
        excursion.setTrip(trip);
        return excursion;
    }

    /**
     * Test of getExcursion method, of class ExcursionDAOImpl.
     */
    public void testGetExcursion() {
        System.out.println("getExcursion");
        Excursion excursion = newExcursion();
             
        em.getTransaction().begin();
        instance.createExcurtion(excursion);
        Excursion result = instance.getExcursion(excursion.getId());
        em.getTransaction().commit();
        
        assertDeepEquals(excursion, result);
    }

    /**
     * Test of updateExcursion method, of class ExcursionDAOImpl.
     */
    public void testUpdateExcursion() {
        System.out.println("updateExcursion");
        Excursion excursion = newExcursion();
        Trip trip = new Trip();
        excursion.setTrip(trip);
        Date date = null;
        try {
            date = dateTime.parse("12:00 12. 6. 2013");
        } catch (ParseException ex) {
            Logger.getLogger(TripDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        em.getTransaction().begin();
        instance.createExcurtion(excursion);
        excursion.setDescription("new description");
        excursion.setExcursionDate(date);
        excursion.setPrice(BigDecimal.ONE.setScale(2));
        excursion.setTrip(trip);
        
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
        Excursion excursion = newExcursion();

        em.getTransaction().begin();
        instance.createExcurtion(excursion);
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
        Excursion e1 = newExcursion();
        Excursion e2 = newExcursion();
        Excursion e3 = newExcursion();
        List<Excursion> list = new ArrayList<Excursion>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        
        em.getTransaction().begin();
        instance.createExcurtion(e1);
        instance.createExcurtion(e2);
        instance.createExcurtion(e3);
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
        
        Excursion excursion = newExcursion();
        Trip trip = excursion.getTrip();
             
        em.getTransaction().begin();
        instance.createExcurtion(excursion);
        Trip result = instance.getExcursion(excursion.getId()).getTrip();
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
    
    public void testGetExcursionWrongInput(){
 
        try {
        instance.getExcursion(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }
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
}
