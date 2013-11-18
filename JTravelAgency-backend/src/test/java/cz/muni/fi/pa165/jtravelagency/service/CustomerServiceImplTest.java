/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.service;

import cz.muni.fi.pa165.jtravelagency.dto.CustomerStatus;
import cz.muni.fi.pa165.jtravelagency.dao.CustomerDAOImpl;
import cz.muni.fi.pa165.jtravelagency.dto.CustomerDTO;
import cz.muni.fi.pa165.jtravelagency.dto.ReservationDTO;
import cz.muni.fi.pa165.jtravelagency.entity.Customer;
import cz.muni.fi.pa165.jtravelagency.entity.Reservation;
import cz.muni.fi.pa165.jtravelagency.util.DTOAndEntityMapper;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Peter Petrinec
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest extends TestCase {

    @Mock
    private CustomerDAOImpl customerDAO;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void testCreate() {
        CustomerDTO customerDTO = newCustomerDTO();
        Customer customer = newCustomer();
        customerService.create(customerDTO);

        verify(customerDAO).createCustomer(customer);
        verify(customerDAO, times(0)).getCustomer(any(Long.class));
        verify(customerDAO, times(0)).updateCustomer(any(Customer.class));
        verify(customerDAO, times(0)).deleteCustomer(any(Customer.class));
        verify(customerDAO, times(0)).getAllCustomers();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateException() {
        CustomerDTO customerDTO = newCustomerDTO();
        customerDTO.setId(1l);
        try {
            customerService.create(customerDTO);
            //fail();
        } finally {
            verify(customerDAO, never()).createCustomer(newCustomer());
        }
    }

    /**
     * Test of get method, of class CustomerServiceImpl.
     */
    @Test
    public void testGet() {
        CustomerDTO expected = newCustomerDTO();
        expected.setId(1l);
        when(customerDAO.getCustomer(1l)).thenReturn(DTOAndEntityMapper.dtoToEntity(expected, Customer.class));
        CustomerDTO returned = customerService.get(1l);

        verify(customerDAO).getCustomer(1l);
        verify(customerDAO, times(0)).createCustomer(any(Customer.class));
        verify(customerDAO, times(0)).updateCustomer(any(Customer.class));
        verify(customerDAO, times(0)).deleteCustomer(any(Customer.class));
        verify(customerDAO, times(0)).getAllCustomers();

        assertDeepEquals(expected, returned);
    }

    /**
     * Test of update method, of class CustomerServiceImpl.
     */
    @Test
    public void testUpdate() {
        CustomerDTO customerDTO = newCustomerDTO();
        customerDTO.setId(1l);
        customerService.update(customerDTO);

        verify(customerDAO).updateCustomer(any(Customer.class));
        verifyNoMoreInteractions(customerDAO);
    }

    /**
     * Test of delete method, of class CustomerServiceImpl.
     */
    @Test
    public void testDelete() {
        CustomerDTO customerDTO = newCustomerDTO();
        customerDTO.setId(1l);
        customerService.delete(customerDTO);

        verify(customerDAO).deleteCustomer(any(Customer.class));
        verifyNoMoreInteractions(customerDAO);
    }

    /**
     * Test of getAll method, of class CustomerServiceImpl.
     */
    @Test
    public void testGetAll() {
        List<Customer> expected = new ArrayList<Customer>();
        expected.add(newCustomer());
        when(customerDAO.getAllCustomers()).thenReturn(expected);
        List<CustomerDTO> returned = customerService.getAll();
        
        verify(customerDAO).getAllCustomers();
        verifyNoMoreInteractions(customerDAO);
        
        assertDeepEquals(EntityToDTOList(expected), returned);
    }

    /**
     * Test of setDeletedStatus method, of class CustomerServiceImpl.
     */
    @Test
    public void testSetDeletedStatus() {
        CustomerDTO customerDTO = newCustomerDTO();
        customerService.setDeletedStatus(customerDTO);
        
        verify(customerDAO).setDeletedStatus(DTOAndEntityMapper.dtoToEntity(customerDTO, Customer.class));
        verifyNoMoreInteractions(customerDAO);
    }

    private CustomerDTO newCustomerDTO() {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setStatus(CustomerStatus.REGULAR);
        List<ReservationDTO> reservations = new ArrayList<ReservationDTO>();
        reservations.add(new ReservationDTO());
        reservations.add(new ReservationDTO());
        customer.setReservations(reservations);
        return customer;
    }

    private Customer newCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Deo");
        customer.setStatus(CustomerStatus.REGULAR);
        List<Reservation> reservations = new ArrayList<Reservation>();
        reservations.add(new Reservation());
        reservations.add(new Reservation());
        customer.setReservations(reservations);
        return customer;
    }

    private void assertDeepEquals(CustomerDTO expected, CustomerDTO actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getReservations().size(), actual.getReservations().size());
    }
    
    private void assertDeepEquals(List<CustomerDTO> expectedList, List<CustomerDTO> actualList) {
        for (int i = 0; i < expectedList.size(); i++) {
            assertDeepEquals(expectedList.get(i), actualList.get(i));
        }
    }
    
    private List<CustomerDTO> EntityToDTOList(List<Customer> list) {
        List<CustomerDTO> result = new ArrayList<CustomerDTO>();
        for(Customer c : list) {
            result.add(DTOAndEntityMapper.entityToDto(c, CustomerDTO.class));
        }
        return result;
    }
}
