/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency;

import cz.muni.fi.pa165.jtravelagency.dto.CustomerDTO;
import cz.muni.fi.pa165.jtravelagency.dto.CustomerStatus;
import cz.muni.fi.pa165.jtravelagency.dto.ReservationDTO;
import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import cz.muni.fi.pa165.jtravelagency.facade.ServiceFacade;
import cz.muni.fi.pa165.jtravelagency.service.CustomerService;
import cz.muni.fi.pa165.jtravelagency.entity.Customer;
import cz.muni.fi.pa165.jtravelagency.entity.Excursion;
import cz.muni.fi.pa165.jtravelagency.entity.Reservation;
import cz.muni.fi.pa165.jtravelagency.entity.Trip;

import cz.muni.fi.pa165.jtravelagency.util.DTOAndEntityMapper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/**
 *
 * @author majo
 */
@Service
public class App {

    @Autowired
    private static ServiceFacade facade;
    private static CustomerService service;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        facade = context.getBean("facade", ServiceFacade.class);
       // service = context.getBean("customerService", CustomerService.class);

        CustomerDTO customerDto = new CustomerDTO();
        customerDto.setFirstName("first name");
        customerDto.setLastName("last name");
        customerDto.setReservations(new ArrayList<ReservationDTO>());
        customerDto.setStatus(CustomerStatus.REGULAR);

        TripDTO tripDto = DTOAndEntityMapper.entityToDto(prepareTrip(), TripDTO.class);

        facade.createCustomer(customerDto);
        facade.createTrip(tripDto);
        ReservationDTO reservationDto = facade.makeReservation(customerDto, tripDto, null);

        Long id = customerDto.getId();
        List<CustomerDTO> list = facade.getAllCustomers();
        System.out.println(facade.getCustomer(customerDto.getId()));
        System.out.println(facade.getTrip(tripDto.getId()));
        System.out.println(facade.getAllCustomers().size() + " " + facade.getAllTrips().size() + " " + facade.getAllReservations().size());

        System.out.println(reservationDto);

    }

    private static Reservation newReservation() {
        Reservation reservation = new Reservation();
        reservation.setCustomer(new Customer());
        List<Excursion> excursions = new ArrayList<Excursion>();
        Excursion excursion = new Excursion();
        excursions.add(excursion);
        reservation.setExcursions(excursions);
        reservation.setTrip(new Trip());
        return reservation;
    }

    private static Trip prepareTrip() {
        Trip preparedTrip = new Trip();
        //preparedTrip.setDateFrom(new DateTime(2013, 11, 23));
        //preparedTrip.setDateTo(new DateTime(2013, 1, 30));
        preparedTrip.setDestination("Spain");
        preparedTrip.setAvailableTrips(10);
        preparedTrip.setPrice(new BigDecimal(15200.25));

        return preparedTrip;
    }
}
