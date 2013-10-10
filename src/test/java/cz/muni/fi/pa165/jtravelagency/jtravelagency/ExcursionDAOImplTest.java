/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.jtravelagency;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import junit.framework.TestCase;

/**
 *
 * @author Marian Varaga
 */
public class ExcursionDAOImplTest extends TestCase {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    private cz.muni.fi.pa165.jtravelagency.jtravelagency.ExcursionDAO instance;

    public ExcursionDAOImplTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory("TestPU");
        em = emf.createEntityManager();
        instance = new cz.muni.fi.pa165.jtravelagency.jtravelagency.ExcursionDAOImpl(em);

    }
    
    @Override
    protected void tearDown() throws Exception {
        //super.tearDown();
        em.close();
        emf.close();
    }

    /**
     * Test of createExcurtion method, of class ExcursionDAOImpl.
     */
    public void testCreateExcurtion() {
        System.out.println("createExcurtion");
        Excursion excursion = new Excursion();
        excursion.setDescription("description");
        excursion.setExcursionDate(new Date());
        excursion.setPrice(BigDecimal.ZERO);
        excursion.setTrip(new Trip());
        
        em.getTransaction().begin();
        instance.createExcurtion(excursion);
        em.getTransaction().commit();

        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertNotNull(instance.getExcursion(excursion.getId()));
        assertEquals("description", instance.getExcursion(excursion.getId()).getDescription());
        assertEquals(BigDecimal.ZERO.setScale(2), instance.getExcursion(excursion.getId()).getPrice());
    }
//
//    /**
//     * Test of getExcursion method, of class ExcursionDAOImpl.
//     */
//    public void testGetExcursion() {
//        System.out.println("getExcursion");
//        Long id = null;
//        ExcursionDAOImpl instance = null;
//        Excursion expResult = null;
//        Excursion result = instance.getExcursion(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateExcursion method, of class ExcursionDAOImpl.
//     */
//    public void testUpdateExcursion() {
//        System.out.println("updateExcursion");
//        Excursion excursion = null;
//        ExcursionDAOImpl instance = null;
//        instance.updateExcursion(excursion);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteExcursion method, of class ExcursionDAOImpl.
//     */
//    public void testDeleteExcursion() {
//        System.out.println("deleteExcursion");
//        Excursion excursion = null;
//        ExcursionDAOImpl instance = null;
//        instance.deleteExcursion(excursion);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAllExcursions method, of class ExcursionDAOImpl.
//     */
//    public void testGetAllExcursions() {
//        System.out.println("getAllExcursions");
//        ExcursionDAOImpl instance = null;
//        List expResult = null;
//        List result = instance.getAllExcursions();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTrip method, of class ExcursionDAOImpl.
//     */
//    public void testGetTrip() {
//        System.out.println("getTrip");
//        Excursion excursion = null;
//        ExcursionDAOImpl instance = null;
//        Trip expResult = null;
//        Trip result = instance.getTrip(excursion);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
