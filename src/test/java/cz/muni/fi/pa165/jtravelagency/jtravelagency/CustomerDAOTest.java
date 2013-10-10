/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.jtravelagency;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
        em.close();
        emf.close();
        customerDAOImpl=null;
    }

    /**
     * Test of createCustomer method, of class CustomerDAO.
     */
    public void testCreateCustomer() {
        System.out.println("createCustomer");
        Customer customer=newCustomer("Meno1", "Priezvisko1");
        customerDAOImpl.createCustomer(customer);
        assertNotNull(customerDAOImpl.getCustomer(customer.getId()));
        assertEquals("Meno1", customerDAOImpl.getCustomer(customer.getId()).getFirstName());
        assertEquals("Priezvisko1", customerDAOImpl.getCustomer(customer.getId()).getLastName());
        assertEquals(customer.getStatus(), customerDAOImpl.getCustomer(customer.getId()).getStatus());
        assertDeepEquals(customer.getReservations(), customerDAOImpl.getCustomer(customer.getId()).getReservations());
    }

    /**
     * Test of getCustomer method, of class CustomerDAO.
     */
    public void testGetCustomer() {
        assertNull(customerDAOImpl.getCustomer(1l));
        Customer customer=newCustomer("Meno1", "Priezvisko1");
        customerDAOImpl.createCustomer(customer);
        Long customerId = customer.getId();
        Customer result = customerDAOImpl.getCustomer(customerId);
        assertEquals(customer, result);
        assertEquals(customer.getFirstName(), result.getFirstName());
        assertEquals(customer.getLastName(), result.getLastName());
        assertEquals(customer.getStatus(), customerDAOImpl.getCustomer(customer.getId()).getStatus());
        assertDeepEquals(customer.getReservations(), customerDAOImpl.getCustomer(customer.getId()).getReservations());
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
        
        customerDAOImpl.createCustomer(customer);
      
        customer = customerDAOImpl.getCustomer(customer.getId());
        
        customer.setFirstName("Menonove");
        customerDAOImpl.updateCustomer(customer);
        assertEquals("Menonove", customer.getFirstName());
        assertEquals("Priezvisko1", customer.getLastName());
      assertEquals(customer.getStatus(), customerDAOImpl.getCustomer(customer.getId()).getStatus());
      assertDeepEquals(customer.getReservations(), customerDAOImpl.getCustomer(customer.getId()).getReservations());
        customer = customerDAOImpl.getCustomer(customer.getId());
        customer.setLastName("Priezviskonove");
        customerDAOImpl.updateCustomer(customer);
        assertEquals("Menonove", customer.getFirstName());
        assertEquals("Priezviskonove", customer.getLastName());
        assertDeepEquals(customer.getReservations(), customerDAOImpl.getCustomer(customer.getId()).getReservations());

        
    }

    /**
     * Test of deleteCustomer method, of class CustomerDAO.
     */
    public void testDeleteCustomer() {
        System.out.println("deleteCustomer");
        Customer customer = newCustomer("Meno1", "Priezvisko1");
       
        Customer customer2=newCustomer("Meno2","Priezvisko2");
      
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
        Customer customer= newCustomer("Meno1", "Priezvisko1");
        
        customerDAOImpl.createCustomer(customer);
        customerDAOImpl.setDeletedStatus(customer);
        
        assertEquals(customer.getStatus(), CustomerStatus.DELETED);
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
    


    private void assertDeepEquals(List<Reservation> expectedList, List<Reservation> actualList) {
        for (int i = 0; i < expectedList.size(); i++) {
            Reservation expected = expectedList.get(i);
            Reservation actual = actualList.get(i);
            assertEquals(expected, actual);
        }
    }

}

 
    
