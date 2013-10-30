/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.service;

import cz.muni.fi.pa165.jtravelagency.dao.ReservationDAO;
import cz.muni.fi.pa165.jtravelagency.dao.ReservationDAOImpl;
import cz.muni.fi.pa165.jtravelagency.dto.CustomerDTO;
import cz.muni.fi.pa165.jtravelagency.dto.ReservationDTO;
import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author jakub
 */
public class ReservationServiceImplTest extends TestCase {
    
    
    private ReservationServiceImpl service;
    
    private ReservationDAO reservationDAO;
    
    
    public ReservationServiceImplTest(String testName) {
        super(testName);
        service = new ReservationServiceImpl();
        //reservationDAO = mock(ReservationDAOImpl.class);
        //service.setReservationDAO(reservationDAO);
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
     * Test of create method, of class ReservationServiceImpl.
     */
    public void testCreate() {
        System.out.println("create");
        ReservationDTO reservationDTO = null;
        ReservationServiceImpl instance = new ReservationServiceImpl();
        instance.create(reservationDTO);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class ReservationServiceImpl.
     */
    public void testDelete() {
        System.out.println("delete");
        ReservationDTO reservationDTO = null;
        ReservationServiceImpl instance = new ReservationServiceImpl();
        instance.delete(reservationDTO);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class ReservationServiceImpl.
     */
    public void testUpdate() {
        System.out.println("update");
        ReservationDTO reservationDTO = null;
        ReservationServiceImpl instance = new ReservationServiceImpl();
        instance.update(reservationDTO);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class ReservationServiceImpl.
     */
    public void testGet() {
        System.out.println("get");
        Long id = null;
        ReservationServiceImpl instance = new ReservationServiceImpl();
        ReservationDTO expResult = null;
        ReservationDTO result = instance.get(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class ReservationServiceImpl.
     */
    public void testGetAll() {
        System.out.println("getAll");
        ReservationServiceImpl instance = new ReservationServiceImpl();
        List expResult = null;
        List result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getByTrip method, of class ReservationServiceImpl.
     */
    public void testGetByTrip() {
        System.out.println("getByTrip");
        TripDTO trip = null;
        ReservationServiceImpl instance = new ReservationServiceImpl();
        List expResult = null;
        List result = instance.getByTrip(trip);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getByCustomer method, of class ReservationServiceImpl.
     */
    public void testGetByCustomer() {
        System.out.println("getByCustomer");
        CustomerDTO customer = null;
        ReservationServiceImpl instance = new ReservationServiceImpl();
        List expResult = null;
        List result = instance.getByCustomer(customer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
