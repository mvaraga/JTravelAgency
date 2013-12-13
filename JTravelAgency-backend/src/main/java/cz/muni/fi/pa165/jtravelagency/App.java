/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency;

import cz.muni.fi.pa165.jtravelagency.dto.CustomerDTO;
import cz.muni.fi.pa165.jtravelagency.dto.ExcursionDTO;
import cz.muni.fi.pa165.jtravelagency.dto.ReservationDTO;
import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import cz.muni.fi.pa165.jtravelagency.entity.Customer;
import cz.muni.fi.pa165.jtravelagency.facade.ServiceFacade;
import cz.muni.fi.pa165.jtravelagency.entity.Excursion;
import cz.muni.fi.pa165.jtravelagency.entity.Reservation;
import cz.muni.fi.pa165.jtravelagency.entity.Trip;

import cz.muni.fi.pa165.jtravelagency.util.DTOAndEntityMapper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author majo
 */
@Service
public class App {

    @Autowired
    private static ServiceFacade facade;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        facade = context.getBean("facade", ServiceFacade.class);

        //vytvorenie zakaznika
        Customer customer = newCustomer();
        CustomerDTO customerDto = DTOAndEntityMapper.entityToDto(customer, CustomerDTO.class);

        //vytvorenie tripu
        Trip trip = prepareTrip();
        TripDTO tripDto = DTOAndEntityMapper.entityToDto(trip, TripDTO.class);

        //ulozenie zakaznika a tripu
        facade.createCustomer(customerDto);
        facade.createTrip(tripDto);

        //vytvorenie exkurzie k uz vytvorenemu tripu
        List<ExcursionDTO> excursions = new ArrayList<>();
        ExcursionDTO excursionDto = DTOAndEntityMapper.entityToDto(newExcursion(DTOAndEntityMapper.dtoToEntity(tripDto, Trip.class)), ExcursionDTO.class);
        excursions.add(excursionDto);

        //ulozenie exkurzie
        facade.createExcursion(excursionDto);

        //vytvorenie rezervacie
        ReservationDTO reservationDto = facade.makeReservation(customerDto, tripDto, excursions);
        
        //zmazanie zakaznika
        facade.deleteCustomer(customerDto);

    }

    private static Trip prepareTrip() {
        Trip preparedTrip = new Trip();
        preparedTrip.setDateFrom(new DateTime(2013, 11, 23, 0, 0));
        preparedTrip.setDateTo(new DateTime(2013, 1, 30, 0, 0));
        preparedTrip.setDestination("Spain");
        preparedTrip.setAvailableTrips(10);
        preparedTrip.setPrice(new BigDecimal(15200.25));

        return preparedTrip;
    }

    private static Excursion newExcursion(Trip trip) {
        DateTime date = new DateTime(2013, 5, 12, 12, 0);
        Excursion excursion = new Excursion();
        excursion.setDescription("description");
        excursion.setExcursionDate(date);
        excursion.setPrice(BigDecimal.ZERO.setScale(2));
        excursion.setTrip(trip);
        return excursion;
    }
    
    private static Customer newCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("first name");
        customer.setLastName("last name");
        customer.setReservations(new ArrayList<Reservation>());
        return customer;
    }
}
