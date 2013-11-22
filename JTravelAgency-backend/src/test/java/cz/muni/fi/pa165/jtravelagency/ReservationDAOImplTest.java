/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency;

import cz.muni.fi.pa165.jtravelagency.dao.CustomerDAO;
import cz.muni.fi.pa165.jtravelagency.dao.CustomerDAOImpl;
import cz.muni.fi.pa165.jtravelagency.dao.TripDAO;
import cz.muni.fi.pa165.jtravelagency.dao.TripDAOImpl;
import cz.muni.fi.pa165.jtravelagency.dto.CustomerStatus;
import cz.muni.fi.pa165.jtravelagency.entity.Customer;
import cz.muni.fi.pa165.jtravelagency.entity.Trip;
import cz.muni.fi.pa165.jtravelagency.entity.Excursion;
import cz.muni.fi.pa165.jtravelagency.entity.Reservation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 *
 * @author Peter Petrinec
 */
public class ReservationDAOImplTest extends TestCase {
    private EntityManagerFactory emf;
    private EntityManager em1, em2, em3;
    private cz.muni.fi.pa165.jtravelagency.dao.ReservationDAO instance;
    private CustomerDAO customerDaoImp;
    private TripDAO tripDaoImpl;

    public ReservationDAOImplTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory("TestPU");
        em1 = emf.createEntityManager();
        em2 = emf.createEntityManager();
        em3 = emf.createEntityManager();
        instance = new cz.muni.fi.pa165.jtravelagency.dao.ReservationDAOImpl(em1);
        customerDaoImp = new CustomerDAOImpl(em2);
        tripDaoImpl = new TripDAOImpl(em3);
    }
    
    @Override
    protected void tearDown() throws Exception {
        //super.tearDown();
        em1.close();
        em2.close();
        em3.close();
        emf.close();
    }

    /**
     * Test of createReservation method, of class ReservationDAOImpl.
     */
    public void testCreateReservation() {
        System.out.println("createReservation");
        
        em2.getTransaction().begin();
        Customer customer = newCustomer("Jan", "Novak");
        customerDaoImp.createCustomer(customer);
        em2.getTransaction().commit();
        
        em3.getTransaction().begin();
        Trip trip = prepareTrip();
        tripDaoImpl.createTrip(trip);
        em3.getTransaction().commit();
        
        em1.getTransaction().begin();
        Reservation reservation = newReservation(customer, trip);
        instance.createReservation(reservation);
        Reservation result = instance.getReservation(reservation.getId());
        em1.getTransaction().commit();
        
        assertDeepEquals(reservation, result);
    }
    
    /**
     * Test of createReservation method, of class ReservationDAOImpl
     * with illegal argument.
     */
    public void testCreateReservationException() {
        System.out.println("createReservationException");
        
        em2.getTransaction().begin();
        Customer customer = newCustomer("Jan", "Novak");
        customerDaoImp.createCustomer(customer);
        em2.getTransaction().commit();
        
        em3.getTransaction().begin();
        Trip trip = prepareTrip();
        tripDaoImpl.createTrip(trip);
        em3.getTransaction().commit();
        
        Reservation reservation = newReservation(customer, trip);
        reservation.setId(Long.valueOf("0"));
        em1.getTransaction().begin();
        try {
            instance.createReservation(reservation);
            fail();
        } catch(IllegalArgumentException ex) {
            //SUCCESS
        }
        em1.getTransaction().commit();
    }
    
    /**
     * Test of getReservation method, of class ReservationDAOImpl.
     */
    public void testGetReservation() {
        System.out.println("getReservation");
        
        em2.getTransaction().begin();
        Customer customer = newCustomer("Jan", "Novak");
        customerDaoImp.createCustomer(customer);
        em2.getTransaction().commit();
        
        em3.getTransaction().begin();
        Trip trip = prepareTrip();
        tripDaoImpl.createTrip(trip);
        em3.getTransaction().commit();
        
        Reservation reservation = newReservation(customer, trip);
        em1.getTransaction().begin();
        instance.createReservation(reservation);  
        Reservation result = instance.getReservation(reservation.getId());
        em1.getTransaction().commit();
        assertDeepEquals(reservation, result);
    }
    
    /**
     * Test of updateReservation method, of class ReservationDAOImpl.
     */
    public void testUpdateReservation() {
        System.out.println("updateReservation");
        
        em2.getTransaction().begin();
        Customer customer = newCustomer("Jan", "Novak");
        customerDaoImp.createCustomer(customer);
        em2.getTransaction().commit();
        
        em3.getTransaction().begin();
        Trip trip = prepareTrip();
        tripDaoImpl.createTrip(trip);
        em3.getTransaction().commit();
        
        Reservation reservation = newReservation(customer, trip);
        em1.getTransaction().begin();
        instance.createReservation(reservation);
        reservation.getExcursions().add(new Excursion());
        instance.updateReservation(reservation);
        Reservation res = instance.getReservation(reservation.getId());
        em1.getTransaction().commit();
        assertDeepEquals(reservation, res);
    }  

    /**
     * Test of deleteReservation method, of class ReservationDAOImpl.
     */
    public void testDeleteReservation() {
        System.out.println("deleteReservation");
        
        em2.getTransaction().begin();
        Customer customer = newCustomer("Jan", "Novak");
        customerDaoImp.createCustomer(customer);
        em2.getTransaction().commit();
        
        em3.getTransaction().begin();
        Trip trip = prepareTrip();
        tripDaoImpl.createTrip(trip);
        em3.getTransaction().commit();
        
        Reservation reservation = newReservation(customer, trip);
        em1.getTransaction().begin();
        instance.createReservation(reservation);
        Reservation res = instance.getReservation(reservation.getId());
        instance.deleteReservation(res);
        res = instance.getReservation(reservation.getId());
        em1.getTransaction().commit();
        assertEquals(null, res);
    }

    /**
     * Test of getAllReservations method, of class ReservationDAOImpl.
     */
    public void testGetAllReservations() {
        System.out.println("getAllReservations");
        
        em2.getTransaction().begin();
        Customer customer = newCustomer("Jan", "Novak");
        customerDaoImp.createCustomer(customer);
        em2.getTransaction().commit();
        
        em3.getTransaction().begin();
        Trip trip = prepareTrip();
        tripDaoImpl.createTrip(trip);
        em3.getTransaction().commit();
        
        Reservation reservation1 = newReservation(customer, trip);
        Reservation reservation2 = newReservation(customer, trip);
        List<Reservation> expected = new ArrayList<Reservation>();
        expected.add(reservation1);
        expected.add(reservation2);
        em1.getTransaction().begin();
        instance.createReservation(reservation1);  
        instance.createReservation(reservation2);  
        List<Reservation> result = instance.getAllReservations();
        em1.getTransaction().commit();
        assertTrue(result.contains(reservation1));
        assertTrue(result.contains(reservation2));
        assertDeepEquals(expected, result);
    }

    /**
     * Test of getReservationByTrip method, of class ReservationDAOImpl.
     */
    public void testGetReservationByTrip() {
        System.out.println("getReservationByTrip");
        
        em2.getTransaction().begin();
        Customer customer = newCustomer("Jan", "Novak");
        customerDaoImp.createCustomer(customer);
        em2.getTransaction().commit();
        
        em3.getTransaction().begin();
        Trip trip1 = prepareTrip();
        Trip trip2 = prepareTrip();
        trip2.setDestination("North Pole");
        tripDaoImpl.createTrip(trip1);
        tripDaoImpl.createTrip(trip2);
        em3.getTransaction().commit();
        
        Reservation reservation1 = newReservation(customer, trip1);
        Reservation reservation2 = newReservation(customer, trip2);
        Reservation reservation3 = newReservation(customer, trip2);        
        List<Reservation> expected = new ArrayList<Reservation>();
        expected.add(reservation2);
        expected.add(reservation3);
        em1.getTransaction().begin();
        instance.createReservation(reservation1);
        instance.createReservation(reservation2);
        instance.createReservation(reservation3);
        List<Reservation> result = instance.getReservationByTrip(trip2);
        em1.getTransaction().commit();
        assertDeepEquals(expected, result);
    }

    /**
     * Test of getReservationByCustomer method, of class ReservationDAOImpl.
     */
    public void testGetReservationByCustomer() {
        System.out.println("getReservationByCustomer");
        
        em2.getTransaction().begin();
        Customer customer1 = newCustomer("Jan", "Novak");
        Customer customer2 = newCustomer("Peter", "Novak");
        customerDaoImp.createCustomer(customer1);
        customerDaoImp.createCustomer(customer2);
        em2.getTransaction().commit();
        
        em3.getTransaction().begin();
        Trip trip = prepareTrip();
        tripDaoImpl.createTrip(trip);
        em3.getTransaction().commit();
        
        Reservation reservation1 = newReservation(customer1, trip);
        Reservation reservation2 = newReservation(customer2, trip);
        Reservation reservation3 = newReservation(customer1, trip);        
        List<Reservation> expected = new ArrayList<Reservation>();
        List<Reservation> notExpected = new ArrayList<Reservation>();
        expected.add(reservation1);
        expected.add(reservation3);
        notExpected.add(reservation2);
        em1.getTransaction().begin();
        instance.createReservation(reservation1);
        instance.createReservation(reservation2);
        instance.createReservation(reservation3);
        List<Reservation> result = instance.getReservationByCustomer(customer1);
        em1.getTransaction().commit();
        Customer c1 = result.get(0).getCustomer();
        assertEquals(2, result.size());
        assertEquals("Jan", c1.getFirstName());
        assertDeepEquals(expected, result);
        //assertDeepEquals(notExpected, result);
    }

    private Reservation newReservation(Customer customer, Trip trip) {
        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        List<Excursion> excursions = new ArrayList<Excursion>();
        Excursion excursion = new Excursion();
        excursions.add(excursion);
        reservation.setExcursions(excursions);
        reservation.setTrip(trip);
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
    
    private Customer newCustomer(String firstName, String lastName){
        Customer customer=new Customer();
        customer.setFirstName(firstName);
        customer.setStatus(CustomerStatus.REGULAR);
        customer.setLastName(lastName);
        List<Reservation> reservations=new ArrayList<Reservation>();
        reservations.add(new Reservation());
        reservations.add(new Reservation());
        customer.setReservations(reservations);
        return customer;
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