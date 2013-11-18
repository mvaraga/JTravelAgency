package cz.muni.fi.pa165.jtravelagency;

import cz.muni.fi.pa165.jtravelagency.dto.CustomerStatus;
import cz.muni.fi.pa165.jtravelagency.entity.Customer;
import cz.muni.fi.pa165.jtravelagency.entity.Reservation;
import java.util.ArrayList;
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
public class CustomerDAOImplTest extends TestCase {
    
    private EntityManagerFactory emf;
    
    private EntityManager em;
    
    private  cz.muni.fi.pa165.jtravelagency.dao.CustomerDAO customerDAOImpl;
    
    public CustomerDAOImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
       emf = Persistence.createEntityManagerFactory("TestPU");
       em = emf.createEntityManager();
       customerDAOImpl = new cz.muni.fi.pa165.jtravelagency.dao.CustomerDAOImpl(em);
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
        
        em.getTransaction().begin();
        customerDAOImpl.createCustomer(customer);
      em.getTransaction().commit();
      
        customer = customerDAOImpl.getCustomer(customer.getId());
        
        customer.setFirstName("Menonove");
        
        em.getTransaction().begin();
        customerDAOImpl.updateCustomer(customer);
        em.getTransaction().commit();
        assertEquals("Menonove", customer.getFirstName());
        assertEquals("Priezvisko1", customer.getLastName());
      assertEquals(customer.getStatus(), customerDAOImpl.getCustomer(customer.getId()).getStatus());
//      assertDeepEquals(customer.getReservations(), customerDAOImpl.getCustomer(customer.getId()).getReservations());
        
      customer = customerDAOImpl.getCustomer(customer.getId());
        customer.setLastName("Priezviskonove");
        
        em.getTransaction().begin();
        customerDAOImpl.updateCustomer(customer);
        em.getTransaction().commit();
        assertEquals("Menonove", customer.getFirstName());
        assertEquals("Priezviskonove", customer.getLastName());
       // assertDeepEquals(customer.getReservations(), customerDAOImpl.getCustomer(customer.getId()).getReservations());       
    }

    /**
     * Test of deleteCustomer method, of class CustomerDAO.
     */
    
    public void testDeleteCustomer() {
        System.out.println("deleteCustomer");
        Customer customer = newCustomer("Meno1", "Priezvisko1");
       
        Customer customer2=newCustomer("Meno2","Priezvisko2");
      
        em.getTransaction().begin();
        customerDAOImpl.createCustomer(customer);
        em.getTransaction().commit();
         em.getTransaction().begin();
        customerDAOImpl.createCustomer(customer2);
         em.getTransaction().commit();
        assertEquals(2,customerDAOImpl.getAllCustomers().size());
        assertNotNull(customerDAOImpl.getCustomer(customer.getId()));
        assertNotNull(customerDAOImpl.getCustomer(customer.getId()));
        
        em.getTransaction().begin();
        customerDAOImpl.deleteCustomer(customer);
        em.getTransaction().commit();
         
        assertEquals(1,customerDAOImpl.getAllCustomers().size());
         assertNull(customerDAOImpl.getCustomer(customer.getId()));
        assertNotNull(customerDAOImpl.getCustomer(customer2.getId()));
        
       em.getTransaction().begin();
         customerDAOImpl.deleteCustomer(customer2);
         em.getTransaction().commit();
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
        
        em.getTransaction().begin();
        customerDAOImpl.createCustomer(customer1);
        customerDAOImpl.createCustomer(customer2);
        em.getTransaction().commit();
        
       
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
        
        em.getTransaction().begin();
        customerDAOImpl.createCustomer(customer);
        customerDAOImpl.setDeletedStatus(customer);
        em.getTransaction().commit();
        
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
