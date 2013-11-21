/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.web;

import cz.muni.fi.pa165.jtravelagency.dto.CustomerDTO;
import cz.muni.fi.pa165.jtravelagency.dto.ExcursionDTO;
import cz.muni.fi.pa165.jtravelagency.dto.ReservationDTO;
import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import cz.muni.fi.pa165.jtravelagency.facade.ServiceFacade;
import java.util.ArrayList;

import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Radka
 */
@UrlBinding("/reservations/{$event}/{reservation.id}")
public class ReservationsActionBean extends BaseActionBean {
    
    final static Logger log = LoggerFactory.getLogger(ReservationsActionBean.class);
    @SpringBean
    private ServiceFacade facade;

    public List<TripDTO> getTrips() {
        return trips;
    }

    public void setTrips(List<TripDTO> trips) {
        this.trips = trips;
    }

    
    private ReservationDTO reservation;
    private CustomerDTO customer;
    private TripDTO trip;
    private List<ExcursionDTO> excursions;
    private List<ReservationDTO> reservations;
    private List<CustomerDTO> customers;
    private List<TripDTO> trips;
    
    private Long customerId;
    private Long tripId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public ServiceFacade getFacade() {
        return facade;
    }

    public void setFacade(ServiceFacade facade) {
        this.facade = facade;
    }

    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    public List<ExcursionDTO> getExcursions() {
        return excursions;
    }



    public List<CustomerDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerDTO> customers) {
        this.customers = customers;
    }
    
    

    public ReservationDTO getReservation() {
        return reservation;
    }

    public void setReservation(ReservationDTO reservation) {
        this.reservation = reservation;
    }



    public List<ReservationDTO> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationDTO> reservations) {
        this.reservations = reservations;
    }
    
     @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        customers=facade.getAllCustomers();
        reservations = facade.getAllReservations();
        trips=facade.getAllTrips();
        
        excursions=facade.getAllExcursions();
        return new ForwardResolution("/reservation/list.jsp");
    }
     


    

    //--- part for adding a book ----

//    @ValidateNestedProperties(value = {
//            @Validate(on = {"add", "save"}, field = "author", required = true),
//            @Validate(on = {"add", "save"}, field = "title", required = true),
//            @Validate(on = {"add", "save"}, field = "year", required = true, minvalue = 800)
//    })
   

    public Resolution add() {
        log.debug("add() reservation={}", reservation);
        facade.makeReservation(facade.getCustomer(customerId), facade.getTrip(tripId), excursions );
        //getContext().getMessages().add(new LocalizableMessage("reservation.add.message",escapeHTML(customer.getFirstName()),escapeHTML(customer.getLastName())));
        return new RedirectResolution(this.getClass(), "list");
    }

    
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
 
        return null;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    //--- part for deleting a book ----

    public Resolution delete() {
        log.debug("delete({})", customer.getId());
        //only id is filled by the form
        customer = facade.getCustomer(customer.getId());
        facade.deleteCustomer(customer);
        getContext().getMessages().add(new LocalizableMessage("customer.delete.message",escapeHTML(customer.getFirstName()),escapeHTML(customer.getLastName())));
        return new RedirectResolution(this.getClass(), "list");
    }

    //--- part for editing a book ----

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadReservationFromDatabase() {
        String ids = getContext().getRequest().getParameter("reservation.id");
        if (ids == null) return;
        reservation = facade.getReservation(Long.parseLong(ids));
        customers = facade.getAllCustomers();
        customerId = reservation.getCustomer().getId();
        tripId=reservation.getTrip().getId();
    }

    public Resolution edit() {
        log.debug("edit() reservation={}", reservation);
        return new ForwardResolution("/customer/edit.jsp");
    }

    public Resolution save() {
        log.debug("save() reservation={}", reservation);
        facade.updateReservation(reservation);
        return new RedirectResolution(this.getClass(), "list");
    }
}
