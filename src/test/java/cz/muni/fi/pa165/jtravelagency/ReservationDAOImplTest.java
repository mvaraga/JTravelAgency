/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency;

import cz.muni.fi.pa165.jtravelagency.entity.Customer;
import cz.muni.fi.pa165.jtravelagency.entity.Trip;
import cz.muni.fi.pa165.jtravelagency.entity.Excursion;
import cz.muni.fi.pa165.jtravelagency.entity.Reservation;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;

/**
 *
 * @author Peter Petrinec
 */
public class ReservationDAOImplTest extends TestCase {
    private EntityManagerFactory emf;
    private EntityManager em;
    private cz.muni.fi.pa165.jtravelagency.dao.ReservationDAO instance;

    public ReservationDAOImplTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory("TestPU");
        em = emf.createEntityManager();
        instance = new cz.muni.fi.pa165.jtravelagency.dao.ReservationDAOImpl(em);

    }
    
    @Override
    protected void tearDown() throws Exception {
        //super.tearDown();
        em.close();
        emf.close();
    }

    /**
     * Test of createReservation method, of class ReservationDAOImpl.
     */
    public void testCreateReservation() {
        System.out.println("createReservation");
        Reservation reservation = newReservation();
        em.getTransaction().begin();
        instance.createReservation(reservation);
        Reservation result = instance.getReservation(reservation.getId());
        em.getTransaction().commit();
        assertDeepEquals(reservation, result);
    }
    
    /**
     * Test of createReservation method, of class ReservationDAOImpl
     * with illegal argument.
     */
    public void testCreateReservationException() {
        System.out.println("createReservationException");
        Reservation reservation = newReservation();
        reservation.setId(Long.valueOf("0"));
        em.getTransaction().begin();
        try {
            instance.createReservation(reservation);
            fail();
        } catch(IllegalArgumentException ex) {
            //SUCCESS
        }
    }
    
    /**
     * Test of getReservation method, of class ReservationDAOImpl.
     */
    public void testGetReservation() {
        System.out.println("getReservation");
        Reservation reservation = newReservation();
        em.getTransaction().begin();
        instance.createReservation(reservation);  
        Reservation result = instance.getReservation(reservation.getId());
        em.getTransaction().commit();
        assertDeepEquals(reservation, result);
    }
    
    /**
     * Test of updateReservation method, of class ReservationDAOImpl.
     */
    public void testUpdateReservation() {
        System.out.println("updateReservation");
        Reservation reservation = newReservation();
        em.getTransaction().begin();
        instance.createReservation(reservation);
        reservation.getExcursions().add(new Excursion());
        instance.updateReservation(reservation);
        Reservation res = instance.getReservation(reservation.getId());
        em.getTransaction().commit();
        assertDeepEquals(reservation, res);
    }

    

    /**
     * Test of deleteReservation method, of class ReservationDAOImpl.
     */
    public void testDeleteReservation() {
        System.out.println("deleteReservation");
        Reservation reservation = newReservation();
        em.getTransaction().begin();
        instance.createReservation(reservation);
        Reservation res = instance.getReservation(reservation.getId());
        instance.deleteReservation(res);
        res = instance.getReservation(reservation.getId());
        em.getTransaction().commit();
        assertEquals(null, res);
    }

    /**
     * Test of getAllReservations method, of class ReservationDAOImpl.
     */
    public void testGetAllReservations() {
        System.out.println("getAllReservations");
        Reservation reservation1 = newReservation();
        Reservation reservation2 = newReservation();
        List<Reservation> expected = new ArrayList<Reservation>();
        expected.add(reservation1);
        expected.add(reservation2);
        em.getTransaction().begin();
        instance.createReservation(reservation1);  
        instance.createReservation(reservation2);  
        List<Reservation> result = instance.getAllReservations();
        em.getTransaction().commit();
        assertTrue(result.contains(reservation1));
        assertTrue(result.contains(reservation2));
        assertDeepEquals(expected, result);
    }

    /**
     * Test of getReservationByTrip method, of class ReservationDAOImpl.
     */
    public void testGetReservationByTrip() {
        System.out.println("getReservationByTrip");
        Trip trip = new Trip();
        trip.setDestination("North Pole");
        Reservation reservation1 = newReservation();
        Reservation reservation2 = newReservation();
        Reservation reservation3 = newReservation();        
        reservation2.setTrip(trip);
        reservation3.setTrip(trip);
        List<Reservation> expected = new ArrayList<Reservation>();
        expected.add(reservation2);
        expected.add(reservation3);
        em.getTransaction().begin();
        instance.createReservation(reservation1);
        instance.createReservation(reservation2);
        instance.createReservation(reservation3);
        List<Reservation> result = instance.getReservationByTrip(trip);
        em.getTransaction().commit();
        assertDeepEquals(expected, result);
    }

    /**
     * Test of getReservationByCustomer method, of class ReservationDAOImpl.
     */
    public void testGetReservationByCustomer() {
        System.out.println("getReservationByCustomer");
        Customer customer = new Customer();
        customer.setFirstName("Peter");
        Reservation reservation1 = newReservation();
        Reservation reservation2 = newReservation();
        Reservation reservation3 = newReservation();        
        reservation1.setCustomer(customer);
        reservation3.setCustomer(customer);
        List<Reservation> expected = new ArrayList<Reservation>();
        List<Reservation> notExpected = new ArrayList<Reservation>();
        expected.add(reservation1);
        expected.add(reservation3);
        notExpected.add(reservation2);
        em.getTransaction().begin();
        instance.createReservation(reservation1);
        instance.createReservation(reservation2);
        instance.createReservation(reservation3);
        List<Reservation> result = instance.getReservationByCustomer(customer);
        em.getTransaction().commit();
        Customer c1 = result.get(0).getCustomer();
        assertEquals(2, result.size());
        assertEquals("Peter", c1.getFirstName());
        assertDeepEquals(expected, result);
        //assertDeepEquals(notExpected, result);
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
    
    private void assertDeepEquals(Reservation expected, Reservation actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getCustomer(), actual.getCustomer());
        assertEquals(expected.getTrip(), actual.getTrip());
        assertEquals(expected.getExcursions().size(), actual.getExcursions().size());
    }

    private void assertDeepEquals(List<Reservation> expectedList, List<Reservation> actualList) {
        for (int i = 0; i < expectedList.size(); i++) {
            Reservation expected = expectedList.get(i);
            Reservation actual = actualList.get(i);
            assertDeepEquals(expected, actual);
        }
    }
}
