/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.service;

import cz.muni.fi.pa165.jtravelagency.dao.TripDAO;
import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import java.util.List;
import junit.framework.TestCase;
import org.joda.time.LocalDate;

/**
 *
 * @author Radka
 */
public class TripServiceImplTest extends TestCase {
    
    public TripServiceImplTest(String testName) {
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
     * Test of setTripDAO method, of class TripServiceImpl.
     */
    public void testSetTripDAO() {
        System.out.println("setTripDAO");
        TripDAO tripDAO = null;
        TripServiceImpl instance = new TripServiceImpl();
        instance.setTripDAO(tripDAO);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class TripServiceImpl.
     */
    public void testCreate() {
        System.out.println("create");
        TripDTO tripDTO = null;
        TripServiceImpl instance = new TripServiceImpl();
        instance.create(tripDTO);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class TripServiceImpl.
     */
    public void testGet() {
        System.out.println("get");
        Long id = null;
        TripServiceImpl instance = new TripServiceImpl();
        TripDTO expResult = null;
        TripDTO result = instance.get(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class TripServiceImpl.
     */
    public void testUpdate() {
        System.out.println("update");
        TripDTO trip = null;
        TripServiceImpl instance = new TripServiceImpl();
        instance.update(trip);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class TripServiceImpl.
     */
    public void testDelete() {
        System.out.println("delete");
        TripDTO trip = null;
        TripServiceImpl instance = new TripServiceImpl();
        instance.delete(trip);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class TripServiceImpl.
     */
    public void testGetAll() {
        System.out.println("getAll");
        TripServiceImpl instance = new TripServiceImpl();
        List expResult = null;
        List result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllByDateRange method, of class TripServiceImpl.
     */
    public void testFindAllByDateRange() {
        System.out.println("findAllByDateRange");
        LocalDate from = null;
        LocalDate to = null;
        TripServiceImpl instance = new TripServiceImpl();
        List expResult = null;
        List result = instance.findAllByDateRange(from, to);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllByDestination method, of class TripServiceImpl.
     */
    public void testFindAllByDestination() {
        System.out.println("findAllByDestination");
        String destination = "";
        TripServiceImpl instance = new TripServiceImpl();
        List expResult = null;
        List result = instance.findAllByDestination(destination);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
