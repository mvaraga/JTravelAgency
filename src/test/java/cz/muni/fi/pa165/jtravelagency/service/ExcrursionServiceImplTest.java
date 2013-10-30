/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.service;

import cz.muni.fi.pa165.jtravelagency.dao.ExcursionDAOImpl;
import cz.muni.fi.pa165.jtravelagency.dto.ExcursionDTO;
import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import cz.muni.fi.pa165.jtravelagency.entity.Excursion;
import cz.muni.fi.pa165.jtravelagency.entity.Trip;
import cz.muni.fi.pa165.jtravelagency.util.DTOAndDAOMapper;
import java.math.BigDecimal;
import java.util.List;
import junit.framework.TestCase;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author mvaraga
 */
@RunWith(MockitoJUnitRunner.class)
public class ExcrursionServiceImplTest extends TestCase {

    @InjectMocks
    private ExcrursionServiceImpl service;
    @Mock
    private ExcursionDAOImpl dao;

//    public ExcrursionServiceImplTest(String testName) {
//        super(testName);
//    }
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of create method, of class ExcrursionServiceImpl.
     */
    @Test
    public void testCreate() {
        doThrow(new IllegalArgumentException()).when(dao).createExcursion(null);
        
        try {
            service.create(null);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }

 
        ExcursionDTO excursionDto = newExcursionDto();
        //doNothing().when(dao).createExcursion(DTOAndDAOMapper.dtoToEntity(excursionDto));       
        Excursion excursion = DTOAndDAOMapper.dtoToEntity(excursionDto);//newExcursion();
        service.create(excursionDto);
        verify(dao).createExcursion(excursion);

        verify(dao, never()).deleteExcursion(excursion);
        verify(dao, never()).updateExcursion(null);

        // dao.createExcursion(excursion);
    }

    /**
     * Test of get method, of class ExcrursionServiceImpl.
     */
    public void testGet() {
        System.out.println("get");
        Long id = null;
        ExcrursionServiceImpl instance = new ExcrursionServiceImpl();
        ExcursionDTO expResult = null;
        ExcursionDTO result = instance.get(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class ExcrursionServiceImpl.
     */
    public void testUpdate() {
        doThrow(new IllegalArgumentException()).when(dao).updateExcursion(null);
        
        try{
            service.update(null);
            fail();
        }catch(IllegalArgumentException ex){
           //OK
        }
         
        verify(dao,never()).createExcursion(null);
        verify(dao,times(1)).updateExcursion(null);
        verifyNoMoreInteractions(dao);
                
        ExcursionDTO excursionDto = newExcursionDto();
        service.update(excursionDto);
        Excursion excursion = DTOAndDAOMapper.dtoToEntity(excursionDto);
        
        verify(dao,times(1)).updateExcursion(excursion);
        verify(dao,times(0)).createExcursion(excursion);
    }

    /**
     * Test of delete method, of class ExcrursionServiceImpl.
     */
    public void testDelete() {
        doThrow(new IllegalArgumentException()).when(dao).deleteExcursion(null);
        
        try{
            service.delete(null);
            fail();
        }catch(IllegalArgumentException ex){
            
        }
         
        verify(dao,never()).createExcursion(null);
        verify(dao,times(1)).deleteExcursion(null);
        verify(dao,never()).updateExcursion(null);
        verifyNoMoreInteractions(dao);
                
        ExcursionDTO excursionDto = newExcursionDto();
        service.delete(excursionDto);
        Excursion excursion = DTOAndDAOMapper.dtoToEntity(excursionDto);
        
        verify(dao,times(1)).deleteExcursion(excursion);
        verify(dao,times(0)).createExcursion(excursion);
        verify(dao,never()).updateExcursion(excursion);
    }

    /**
     * Test of getAll method, of class ExcrursionServiceImpl.
     */
    public void testGetAll() {
        System.out.println("getAll");
        ExcrursionServiceImpl instance = new ExcrursionServiceImpl();
        List expResult = null;
        List result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    private ExcursionDTO newExcursionDto() {
        ExcursionDTO excursionDto = new ExcursionDTO();
        excursionDto.setDescription("Description");
        excursionDto.setExcursionDate(new DateTime(2013, 10, 12, 10, 00));
        excursionDto.setPrice(new BigDecimal(10));
        excursionDto.setTrip(new TripDTO());
        return excursionDto;
    }

    private Excursion newExcursion() {
        Excursion excursion = new Excursion();
        excursion.setDescription("Description");
        excursion.setExcursionDate(new DateTime(2013, 10, 12, 10, 00));
        excursion.setPrice(new BigDecimal(10));
        excursion.setTrip(new Trip());
        return excursion;
    }
}
