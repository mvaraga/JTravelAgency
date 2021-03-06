/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.web;

import cz.muni.fi.pa165.jtravelagency.dto.CustomerDTO;
import cz.muni.fi.pa165.jtravelagency.dto.ExcursionDTO;
import cz.muni.fi.pa165.jtravelagency.dto.ReservationDTO;
import cz.muni.fi.pa165.jtravelagency.facade.ServiceFacade;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author majo
 */
@UrlBinding("/customer_reservations/{$event}/")
public class CustomerReservationsActionBean extends BaseActionBean {

    final static Logger log = LoggerFactory.getLogger(CustomerReservationsActionBean.class);

    @SpringBean
    private ServiceFacade facade;

    private List<ReservationDTO> reservations;
    private CustomerDTO customer;
    private ReservationDTO reservation;
    private Long reservationId;
    private Integer reservationsCount;

    public Integer getReservationsCount() {
        return reservationsCount;
    }

    public void setReservationsCount(Integer reservationsCount) {
        this.reservationsCount = reservationsCount;
    }
    
    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public ServiceFacade getFacade() {
        return facade;
    }

    public void setFacade(ServiceFacade facade) {
        this.facade = facade;
    }

    public List<ReservationDTO> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationDTO> reservations) {
        this.reservations = reservations;
    }

    public ReservationDTO getReservation() {
        return reservation;
    }

    public void setReservation(ReservationDTO reservation) {
        this.reservation = reservation;
    }

    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        customer = facade.getCustomerByUsername(username);
       
        reservationsCount = facade.getReservationsByCustomer(facade.getCustomerByUsername(username)).size();
        reservations = facade.getReservationsByCustomer(customer);
        return new ForwardResolution("/customer_reservations/list.jsp");
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public Resolution delete() {
        log.debug("delete()");
        //only id is filled by the form
        reservation = facade.getReservation(reservationId);
        facade.deleteReservation(reservation);

        getContext().getMessages().add(new LocalizableMessage("reservation.delete.message", escapeHTML(reservation.getId().toString())));
        return new RedirectResolution(this.getClass(), "list");
    }
}
