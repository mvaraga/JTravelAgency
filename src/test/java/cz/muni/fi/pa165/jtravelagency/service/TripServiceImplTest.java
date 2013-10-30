/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.service;

import cz.muni.fi.pa165.jtravelagency.dao.TripDAO;
import cz.muni.fi.pa165.jtravelagency.dao.TripDAOImpl;
import cz.muni.fi.pa165.jtravelagency.dto.ExcursionDTO;
import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import cz.muni.fi.pa165.jtravelagency.entity.Trip;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.joda.time.LocalDate;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Radka
 */
@RunWith(MockitoJUnitRunner.class)
public class TripServiceImplTest extends TestCase {
    
    @InjectMocks
    private TripServiceImpl tripService;
    
    @Mock
    private TripDAOImpl tripDao;
    
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
           TripDTO tripDTO=newTripDTO();

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
    
        private TripDTO newTripDTO() {
        TripDTO tripDTO = new TripDTO();
        tripDTO.setDateFrom(new LocalDate(2013, 11, 23));
        tripDTO.setDateTo(new LocalDate(2013, 1, 30));
        tripDTO.setDestination("Spain");
        tripDTO.setAvailableTrips(10);
        tripDTO.setPrice(new BigDecimal(15200.25));
        
        //List<ExcursionDTO> excursions=new ArrayList<ExcursionDTO>();
        //excursions.add(new ExcursionDTO());
        //excursions.add(new ExcursionDTO());
        return tripDTO;
}

}
