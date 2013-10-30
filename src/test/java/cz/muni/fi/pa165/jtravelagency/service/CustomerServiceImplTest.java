/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.service;

import cz.muni.fi.pa165.jtravelagency.CustomerStatus;
import cz.muni.fi.pa165.jtravelagency.dao.CustomerDAO;
import cz.muni.fi.pa165.jtravelagency.dao.CustomerDAOImpl;
import cz.muni.fi.pa165.jtravelagency.dto.CustomerDTO;
import cz.muni.fi.pa165.jtravelagency.dto.ReservationDTO;
import cz.muni.fi.pa165.jtravelagency.entity.Customer;
import cz.muni.fi.pa165.jtravelagency.entity.Reservation;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Peter
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest extends TestCase {
    @Mock
    private CustomerDAOImpl customerDAO;
    
    @InjectMocks
    private CustomerServiceImpl customerService;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testCreate() {
        CustomerDTO customerDTO = newCustomerDTO("John", "Doe");
        Customer customer = newCustomer("John", "Doe");
        customerService.create(customerDTO);
        verify(customerDAO).createCustomer(customer);
        
//        CustomerDTO customerDTO = prepareCustomerDTO();
//        Customer customer = prepareCustomerEntity();
//        customerCRUDService.create(customerDTO);
//
//        verify(customerDAO).create(customer);
//        verify(customerDAO, times(0)).get(any(Long.class));
//        verify(customerDAO, times(0)).update(any(Customer.class));
//        verify(customerDAO, times(0)).delete(any(Customer.class));
//        verify(customerDAO, times(0)).getAll();
    }

    /**
     * Test of get method, of class CustomerServiceImpl.
     */
//    public void testGet() {
//        System.out.println("get");
//        Long id = null;
//        CustomerServiceImpl instance = new CustomerServiceImpl();
//        CustomerDTO expResult = null;
//        CustomerDTO result = instance.get(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of update method, of class CustomerServiceImpl.
//     */
//    public void testUpdate() {
//        System.out.println("update");
//        CustomerDTO customer = null;
//        CustomerServiceImpl instance = new CustomerServiceImpl();
//        instance.update(customer);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of delete method, of class CustomerServiceImpl.
//     */
//    public void testDelete() {
//        System.out.println("delete");
//        CustomerDTO customer = null;
//        CustomerServiceImpl instance = new CustomerServiceImpl();
//        instance.delete(customer);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAll method, of class CustomerServiceImpl.
//     */
//    public void testGetAll() {
//        System.out.println("getAll");
//        CustomerServiceImpl instance = new CustomerServiceImpl();
//        List expResult = null;
//        List result = instance.getAll();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setDeletedStatus method, of class CustomerServiceImpl.
//     */
//    public void testSetDeletedStatus() {
//        System.out.println("setDeletedStatus");
//        CustomerDTO customer = null;
//        CustomerServiceImpl instance = new CustomerServiceImpl();
//        instance.setDeletedStatus(customer);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
    private CustomerDTO newCustomerDTO(String firstName, String lastName){
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setStatus(CustomerStatus.REGULAR);
        List<ReservationDTO> reservations = new ArrayList<ReservationDTO>();
        reservations.add(new ReservationDTO());
        reservations.add(new ReservationDTO());
        customer.setReservations(reservations);
        return customer;
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
}
