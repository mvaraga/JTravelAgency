/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.jtravelagency;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;

/**
 *
 * @author Radka
 */
public class CustomerDAOTest extends TestCase {
    
    private EntityManagerFactory emf;
    
    private EntityManager em;
    
    private  cz.muni.fi.pa165.jtravelagency.jtravelagency.CustomerDAO customerDAOImpl;
    
    public CustomerDAOTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
       
       emf = Persistence.createEntityManagerFactory("TestPU");
       em = emf.createEntityManager();
       customerDAOImpl = new cz.muni.fi.pa165.jtravelagency.jtravelagency.CustomerDAOImpl(em);
    }
    
    @Override
    protected void tearDown() throws Exception {
        //super.tearDown();
        em.close();
        emf.close();
        // by mal maznut data v databaze ... robi to?
    }

    /**
     * Test of createCustomer method, of class CustomerDAO.
     */
    public void testCreateCustomer() {
        System.out.println("createCustomer");
        Customer customer=newCustomer("Meno1", "Priezvisko1");
        
        //nejak nastavit tie rezervacie, dolu su na to pomocne metody
        
        customerDAOImpl.createCustomer(customer);
        assertNotNull(customerDAOImpl.getCustomer(customer.getId()));
        assertEquals("Meno1", customerDAOImpl.getCustomer(customer.getId()).getFirstName());
        assertEquals("Priezvisko1", customerDAOImpl.getCustomer(customer.getId()).getLastName());
        //doriesit tuna kontrolu tej nastavenej kolekcie rezervacii
    }

    /**
     * Test of getCustomer method, of class CustomerDAO.
     */
    public void testGetCustomer() {
        //nejak ceknut ci je ta databaza predtym prazdna, asi staci toto
        assertNull(customerDAOImpl.getCustomer(1l));
        Customer customer=newCustomer("Meno1", "Priezvisko1");
        //este to nejako poriesit s tou kolekciou, zase nejak nastavit tu kolekciu k tomu
        customerDAOImpl.createCustomer(customer);
        Long customerId = customer.getId();
        Customer result = customerDAOImpl.getCustomer(customerId);
        assertEquals(customer, result);
        assertEquals(customer.getFirstName(), result.getFirstName());
        assertEquals(customer.getLastName(), result.getLastName());
        //tuna overit este tu rovnost toho kontajnera
        
    }
    

    public void testGetCustomerWrongInput(){
 
        try {
        customerDAOImpl.getCustomer(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }
    }
    

    /**
     * Test of updateCustomer method, of class CustomerDAO.
     */
    public void testUpdateCustomer() {
        System.out.println("updateCustomer");
        Customer customer = newCustomer("Meno1", "Priezvisko1");
        //este tie rezervacie nejak nastavit
        customerDAOImpl.createCustomer(customer);
        Long customerId = customer.getId();
        customer = customerDAOImpl.getCustomer(customer.getId());
        
        customer.setFirstName("Menonove");
        customerDAOImpl.updateCustomer(customer);
        assertEquals("Menonove", customer.getFirstName());
        assertEquals("Priezvisko1", customer.getLastName());
       //ocekovat ci zostal ten kontajner rezervacii taky isty

        customer = customerDAOImpl.getCustomer(customer.getId());
        customer.setLastName("Priezviskonove");
        customerDAOImpl.updateCustomer(customer);
        assertEquals("Menonove", customer.getFirstName());
        assertEquals("Priezviskonove", customer.getLastName());
        //ocekovat ten kontajner zase

        customer = customerDAOImpl.getCustomer(customer.getId());
       //skusit nastavit iny kontajner, updatnut a ceknut ci sa to 
        //nerozbilo
        
    }

    /**
     * Test of deleteCustomer method, of class CustomerDAO.
     */
    public void testDeleteCustomer() {
        /**
        System.out.println("deleteCustomer");

        Customer customer = newCustomer("Meno1", "Priezvisko1");
        //nejak donastavit ten kontajner zase
        Customer customer2=newCustomer("Meno2","Priezvisko2");
        //zase asi ten kontajner
        customerDAOImpl.createCustomer(customer);
        customerDAOImpl.createCustomer(customer2);
        
        assertEquals(2,customerDAOImpl.getAllCustomers().size());
        assertNotNull(customerDAOImpl.getCustomer(customer.getId()));
        assertNotNull(customerDAOImpl.getCustomer(customer.getId()));
        
        customerDAOImpl.deleteCustomer(customer);
        assertEquals(1,customerDAOImpl.getAllCustomers().size());
         assertNull(customerDAOImpl.getCustomer(customer.getId()));
        assertNotNull(customerDAOImpl.getCustomer(customer2.getId()));
        
         customerDAOImpl.deleteCustomer(customer2);
        assertEquals(0,customerDAOImpl.getAllCustomers().size());
         assertNull(customerDAOImpl.getCustomer(customer.getId()));
        assertNull(customerDAOImpl.getCustomer(customer2.getId()));
        
               
       
        */
    }
    
    public void testDeleteCustomersWrongDate(){
         try {
            customerDAOImpl.deleteCustomer(null);
            fail();
        
        } catch (IllegalArgumentException ex) {            
           
        }
    }

    /**
     * Test of getAllCustomers method, of class CustomerDAO.
     */
    public void testGetAllCustomers() {
        System.out.println("getAllCustomers");
        assertTrue(customerDAOImpl.getAllCustomers().isEmpty());
        Customer customer1 = newCustomer("Meno1", "Priezvisko1");
        
        Customer customer2 = newCustomer ("Meno2", "Priezvisko2");
        //aj tie kolekcie asi ponastavovat
        customerDAOImpl.createCustomer(customer1);
        customerDAOImpl.createCustomer(customer2);
        
       
        List<Customer> result = customerDAOImpl.getAllCustomers();
                
        assertEquals(2, result.size());
        assertTrue(result.contains(customer1));
        assertTrue(result.contains(customer2));
    }

    /**
     * Test of setDeletedStatus method, of class CustomerDAO.
     */
    public void testSetDeletedStatus() {
        System.out.println("setDeletedStatus");

        fail("The test case is a prototype.");
    }
    
    private Customer newCustomer(String firstName, String lastName){
        Customer customer=new Customer();
        customer.setFirstName(firstName);
        customer.setStatus(CustomerStatus.REGULAR);
        customer.setLastName(lastName);
        
        List<Reservation> reservations=new ArrayList<Reservation>();
        /**
         * dorobit pripadne ponastavovanie tych jednotlivych atributov
         * Excursion ex=new Excursion("asdf",....);
         * ex.setTrip(trip);
         * ???????????????? nejak inak vymysliet
         */
        Reservation reserv1=new Reservation();
        Reservation reserv2=new Reservation();
        
        reservations.add(reserv1);
        reservations.add(reserv2);
        customer.setReservations(reservations);
        return customer;
    }
    
    private Trip newTrip(Date dateFrom, Date dateTo, int availableTrips, BigDecimal price, String destination){
        Trip trip=new Trip();
        trip.setAvailableTrips(availableTrips);
        trip.setDateFrom(dateFrom);
        trip.setDateTo(dateTo);
        trip.setDestination(destination);
        trip.setPrice(price);
        return trip;
    }
    
    private Excursion newExcursion(Date excursionDate, String description, BigDecimal price){
        Excursion excursion=new Excursion();
        excursion.setDescription(description);
        excursion.setExcursionDate(excursionDate);
        excursion.setPrice(price);
        return excursion;
    }


}
