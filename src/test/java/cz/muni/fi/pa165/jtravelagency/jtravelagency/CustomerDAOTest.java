/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.jtravelagency;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import junit.framework.TestCase;

/**
 *
 * @author Radka
 */
public class CustomerDAOTest extends TestCase {
    
    private EntityManagerFactory emf;
    
    private EntityManager em;
    
    private  cz.muni.fi.pa165.jtravelagency.jtravelagency.TripDAOImpl customerDAOImpl;
    
    public CustomerDAOTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of createCustomer method, of class CustomerDAO.
     */
    public void testCreateCustomer() {
        System.out.println("createCustomer");
        Customer customer = null;
        CustomerDAO instance = new CustomerDAOImpl();
        instance.createCustomer(customer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCustomer method, of class CustomerDAO.
     */
    public void testGetCustomer() {
        System.out.println("getCustomer");
        Long id = null;
        CustomerDAO instance = new CustomerDAOImpl();
        Customer expResult = null;
        Customer result = instance.getCustomer(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateCustomer method, of class CustomerDAO.
     */
    public void testUpdateCustomer() {
        System.out.println("updateCustomer");
        Customer customer = null;
        CustomerDAO instance = new CustomerDAOImpl();
        instance.updateCustomer(customer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCustomer method, of class CustomerDAO.
     */
    public void testDeleteCustomer() {
        System.out.println("deleteCustomer");
        Customer customer = null;
        CustomerDAO instance = new CustomerDAOImpl();
        instance.deleteCustomer(customer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllCustomers method, of class CustomerDAO.
     */
    public void testGetAllCustomers() {
        System.out.println("getAllCustomers");
        CustomerDAO instance = new CustomerDAOImpl();
        List expResult = null;
        List result = instance.getAllCustomers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDeletedStatus method, of class CustomerDAO.
     */
    public void testSetDeletedStatus() {
        System.out.println("setDeletedStatus");
        Customer customer = null;
        CustomerDAO instance = new CustomerDAOImpl();
        instance.setDeletedStatus(customer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


}
