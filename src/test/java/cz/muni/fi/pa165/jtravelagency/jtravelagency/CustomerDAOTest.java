/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.jtravelagency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
       //super.setUp();
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
        customerDAOImpl.createCustomer(customer);
        assertNotNull(customerDAOImpl.getCustomer(customer.getId()));
        assertEquals("Meno1", customerDAOImpl.getCustomer(customer.getId()).getFirstName());
        assertEquals("Priezvisko1", customerDAOImpl.getCustomer(customer.getId()).getLastName());
        //doriesit tuna este tu kolekciu, alebo spravit nato svoju metodu s deepEquals ...
    }

    /**
     * Test of getCustomer method, of class CustomerDAO.
     */
    public void testGetCustomer() {
        assertNull(customerDAOImpl.getCustomer(1l));
        //nejak ceknut ci je ta databaza predtym prazdna
        Customer customer=newCustomer("Meno1", "Priezvisko1");
        //este to nejako poriesit s tou kolekciou
        customerDAOImpl.createCustomer(customer);
        Long customerId = customer.getId();
        Customer result = customerDAOImpl.getCustomer(customerId);
        assertEquals(customer, result);
        assertEquals(customer.getFirstName(), result.getFirstName());
        assertEquals(customer.getLastName(), result.getLastName());
        //tuna overit este tu rovnost toho kontajnera
        
        

    }
    
    /**
     * 
     * aby to hadzalo este nejake vynimky, ta trieda customer
    public void testGetCustomerWrong(){
 
            try {
            customerManager.getCustomerById(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }
        customerManager.deleteCustomer(customer);
    }
    */

    /**
     * Test of updateCustomer method, of class CustomerDAO.
     */
    public void testUpdateCustomer() {
        System.out.println("updateCustomer");
                System.out.println("updateCustomer");
        Customer customer = newCustomer("abc", "def", "ab12trtztr");
        
        customerManager.createCustomer(customer);
        Long customerId = customer.getId();

        customer = customerManager.getCustomerById(customer.getId());
        customer.setFirstName("cba");
        customerManager.updateCustomer(customer);
        assertEquals("cba", customer.getFirstName());
        assertEquals("def", customer.getSurName());
        assertEquals("ab12trtztr", customer.getNumberOfIdCard());

        customer = customerManager.getCustomerById(customer.getId());
        customer.setSurName("fed");
        customerManager.updateCustomer(customer);
        assertEquals("cba", customer.getFirstName());
        assertEquals("fed", customer.getSurName());
        assertEquals("ab12trtztr", customer.getNumberOfIdCard());

        customer = customerManager.getCustomerById(customer.getId());
        customer.setNumberOfIdCard("21ba");
        customerManager.updateCustomer(customer);
        assertEquals("cba", customer.getFirstName());
        assertEquals("fed", customer.getSurName());
        assertEquals("21ba", customer.getNumberOfIdCard());
        
        
        
        customer.setNumberOfIdCard("12av15");
        customerManager.updateCustomer(customer);
        customerManager.deleteCustomer(customer);
    }

    /**
     * Test of deleteCustomer method, of class CustomerDAO.
     */
    public void testDeleteCustomer() {
        System.out.println("deleteCustomer");

        fail("The test case is a prototype.");
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
        
        List<DVD> expected = Arrays.asList(dvd1, dvd2);
        List<DVD> result = manager.findAllDVDs();
        
        Collections.sort(expected, idComparator);
        Collections.sort(result, idComparator);
        
        assertEquals(expected, result);
        assertDeepEquals(expected, result);
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
        reservations.add(new Reservation());
        reservations.add(new Reservation());
        return customer;
    }
    
    private Reservation newReservation(){
       //naplnit realnymi datami
        return null;
    }


}
