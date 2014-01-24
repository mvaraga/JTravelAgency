/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency;

import cz.muni.fi.pa165.jtravelagency.dto.ExcursionDTO;
import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import cz.muni.fi.pa165.jtravelagency.facade.ServiceFacade;
import java.math.BigDecimal;
import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author majo
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServiceFacade facade;
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        facade = context.getBean("facade", ServiceFacade.class);
        
//        CustomerDTO c = new CustomerDTO();
//        c.setFirstName("FirstName");
//        c.setLastName("LastName");
        
        TripDTO t = new TripDTO();
        t.setAvailableTrips(10);
        t.setDestination("trip");
        t.setPrice(new BigDecimal(1000));
        t.setDateFrom(new DateTime(2013, 11, 23, 0, 0));
        t.setDateTo(new DateTime(2013, 1, 30, 0, 0));
        
        ExcursionDTO e = new ExcursionDTO();
        e.setDescription("excursion");
        e.setExcursionDate(new DateTime(2013, 5, 12, 12, 0));
        e.setPrice(BigDecimal.TEN);
        e.setTrip(t);
        
        //facade.createCustomer(c);
        facade.createTrip(t);
        facade.createExcursion(e);
        //ReservationDTO r = facade.makeReservation(c, t, new ArrayList<ExcursionDTO>());
        
        //System.out.println(facade.getCustomer(c.getId()));
        System.out.println(t);
        System.out.println(e);
        //System.out.println(r);

    }

}
