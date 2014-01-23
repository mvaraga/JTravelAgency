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
import static cz.muni.fi.pa165.jtravelagency.web.BaseActionBean.escapeHTML;
import static cz.muni.fi.pa165.jtravelagency.web.ReservationsActionBean.log;
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
    private List<Long> excursionsIds=new ArrayList<Long>();

    public List<Long> getExcursionsIds() {
        return excursionsIds;
    }

    public void setExcursionsIds(List<Long> excursionsIds) {
        this.excursionsIds = excursionsIds;
    }
    
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
        reservations = facade.getAllReservations();
        customers=facade.getAllCustomers();
        for(CustomerDTO c : customers) {
            c.setLastName(c.getFirstName() + " " + c.getLastName());
        }
        trips=facade.getAllTrips();
        
        excursions=facade.getAllExcursions();
        return new ForwardResolution("/reservation/list.jsp");
    }
     

   

    public Resolution add() {
        log.debug("add() reservation={}", reservation);
        List<ExcursionDTO> pomExcursions=new ArrayList<ExcursionDTO>();
        for(int i=0;i<excursionsIds.size();i++){
            pomExcursions.add(facade.getExcursion(excursionsIds.get(i)));
        }
        reservation = facade.makeReservation(facade.getCustomer(customerId), facade.getTrip(tripId), pomExcursions );

        getContext().getMessages().add(new LocalizableMessage("reservation.add.message",escapeHTML(reservation.getId().toString())));
        return new RedirectResolution(this.getClass(), "list");
    }

    
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        reservations=facade.getAllReservations();
        customers=facade.getAllCustomers();
        trips=facade.getAllTrips();
        excursions=facade.getAllExcursions();
        return null;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }


    public Resolution delete() {
        log.debug("delete({})", reservation.getId());
        //only id is filled by the form
        reservation = facade.getReservation(reservation.getId());
        facade.deleteReservation(reservation);
        
        getContext().getMessages().add(new LocalizableMessage("reservation.delete.message",escapeHTML(reservation.getId().toString())));
        return new RedirectResolution(this.getClass(), "list");
    }


    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadReservationFromDatabase() {
        String ids = getContext().getRequest().getParameter("reservation.id");
        if (ids == null) return;
        reservation = facade.getReservation(Long.parseLong(ids));
        customers = facade.getAllCustomers();
        customerId = reservation.getCustomer().getId();
        trips=facade.getAllTrips();
        tripId=reservation.getTrip().getId();
        excursions=facade.getAllExcursions();
        for(int i=0;i<excursionsIds.size();i++){
            excursionsIds.add((reservation.getExcursions()).get(i).getId());
        }     
    }

    public Resolution edit() {
        log.debug("edit() reservation={}", reservation);
        return new ForwardResolution("/reservation/edit.jsp");
    }

    public Resolution save() {
        log.debug("save() reservation={}", reservation);
        reservation.setCustomer(facade.getCustomer(customerId));
        reservation.setTrip(facade.getTrip(tripId));
        List<ExcursionDTO> pomExcursions=new ArrayList<ExcursionDTO>();
        for(int i=0;i<excursionsIds.size();i++){
            pomExcursions.add(facade.getExcursion(excursionsIds.get(i)));
        }
        reservation.setExcursions(pomExcursions);
        facade.updateReservation(reservation);
        getContext().getMessages().add(new LocalizableMessage("reservation.update.message",escapeHTML(reservation.getId().toString())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution cancel() {
        return new RedirectResolution(this.getClass(), "list");
    }
}
