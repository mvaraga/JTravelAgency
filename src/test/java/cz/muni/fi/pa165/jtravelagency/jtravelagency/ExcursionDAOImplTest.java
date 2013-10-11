/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.jtravelagency;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
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
        instance = null;
    }

    /**
     * Test of createExcurtion method, of class ExcursionDAOImpl.
     */
    public void testCreateExcurtion() {
        System.out.println("createExcurtion");
        Excursion excursion = newExcursion();
             
        em.getTransaction().begin();
        instance.createExcurtion(excursion);
        Excursion result = instance.getExcursion(excursion.getId());
        em.getTransaction().commit();
        
        assertDeepEquals(excursion, result);
    }

        private Excursion newExcursion() {
        Excursion excursion = new Excursion();
        excursion.setDescription("description");
        excursion.setExcursionDate(new Date());
        excursion.setPrice(BigDecimal.ZERO.setScale(2));
        excursion.setTrip(new Trip());
        return excursion;
    }

    /**
     * Test of getExcursion method, of class ExcursionDAOImpl.
     */
    public void testGetExcursion() {
        System.out.println("getExcursion");
        Excursion excursion = newExcursion();
             
        em.getTransaction().begin();
        instance.createExcurtion(excursion);
        Excursion result = instance.getExcursion(excursion.getId());
        em.getTransaction().commit();
        
        assertDeepEquals(excursion, result);
    }

    /**
     * Test of updateExcursion method, of class ExcursionDAOImpl.
     */
    public void testUpdateExcursion() {
        System.out.println("updateExcursion");
        Excursion excursion = newExcursion();
             
        em.getTransaction().begin();
        instance.createExcurtion(excursion);
        excursion.setDescription("new description");
        excursion.setExcursionDate(new Date());
        excursion.setPrice(BigDecimal.ONE.setScale(2));
        excursion.setTrip(new Trip());
        instance.updateExcursion(excursion);
        Excursion result = instance.getExcursion(excursion.getId());
        em.getTransaction().commit();
        
        assertDeepEquals(excursion, result);
    }

    /**
     * Test of deleteExcursion method, of class ExcursionDAOImpl.
     */
    public void testDeleteExcursion() {
        System.out.println("deleteExcursion");
        Excursion excursion = newExcursion();

        em.getTransaction().begin();
        instance.createExcurtion(excursion);
        Excursion result = instance.getExcursion(excursion.getId());
        instance.deleteExcursion(result);
        result = instance.getExcursion(excursion.getId());
        em.getTransaction().commit();

        assertEquals(null, result);
    }

    /**
     * Test of getAllExcursions method, of class ExcursionDAOImpl.
     */
    public void testGetAllExcursions() {
        System.out.println("getAllExcursions");
        Excursion e1 = newExcursion();
        Excursion e2 = newExcursion();
        Excursion e3 = newExcursion();
        List<Excursion> list = new ArrayList<Excursion>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        
        em.getTransaction().begin();
        instance.createExcurtion(e1);
        instance.createExcurtion(e2);
        instance.createExcurtion(e3);
        List<Excursion> result = instance.getAllExcursions();
        em.getTransaction().commit();
        
        assertTrue(result.contains(e1));
        assertTrue(result.contains(e2));
        assertTrue(result.contains(e3));
        
        for (int i = 0; i < list.size(); i++) {
            Excursion expected = list.get(i);
            Excursion actual = result.get(i);
            assertDeepEquals(expected, actual);
        }
    }

//    /**
//     * Test of getTrip method, of class ExcursionDAOImpl.
//     */
//    public void testGetTrip() {
//        System.out.println("getTrip");
//        
//        Excursion excursion = newExcursion();
//             
//        em.getTransaction().begin();
//        instance.createExcurtion(excursion);
//        Excursion result = instance.getExcursion(excursion.getId());
//        em.getTransaction().commit();
//        
//        assertDeepEquals(excursion, result);
//        
//        Trip result = instance.getTrip(excursion);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    private void assertDeepEquals(Excursion first, Excursion second) {
        assertNotNull(second.getId());
        assertEquals(first.getId(), second.getId());
        assertEquals(first.getDescription(), second.getDescription());
        assertEquals(first.getPrice(), second.getPrice());
        assertEquals(first.getTrip(), second.getTrip());
        assertEquals(first.getExcursionDate(), second.getExcursionDate());
    }
    
    public void testGetCustomerWrongInput(){
 
        try {
        instance.getExcursion(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }
    }
}
