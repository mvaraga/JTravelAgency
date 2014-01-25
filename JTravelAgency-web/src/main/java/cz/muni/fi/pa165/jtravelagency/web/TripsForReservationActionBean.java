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
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Peter
 */
@UrlBinding("/trips_for_reservation/{$event}/")
public class TripsForReservationActionBean extends BaseActionBean {

    final static Logger log = LoggerFactory.getLogger(CustomerReservationsActionBean.class);
    @SpringBean
    private ServiceFacade facade;
    
    private List<TripDTO> trips;
    private List<List<ExcursionDTO>> excursions;
    private Integer reservationsCount;

    public Integer getReservationsCount() {
        return reservationsCount;
    }

    public void setReservationsCount(Integer reservationsCount) {
        this.reservationsCount = reservationsCount;
    }

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public ServiceFacade getFacade() {
        return facade;
    }

    public void setFacade(ServiceFacade facade) {
        this.facade = facade;
    }

    public List<TripDTO> getTrips() {
        return trips;
    }

    public void setTrips(List<TripDTO> trips) {
        this.trips = trips;
    }

    public List<List<ExcursionDTO>> getExcursions() {
        return excursions;
    }

    public void setExcursions(List<List<ExcursionDTO>> excursions) {
        this.excursions = excursions;
    }
    // </editor-fold>

    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        excursions = new ArrayList<>();
        trips = facade.getAllTrips();
        for (TripDTO t : trips) {
            List<ExcursionDTO> exc = facade.getExcursionsByTrip(t);
            excursions.add(exc);
        }
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        
        reservationsCount = facade.getReservationsByCustomer(facade.getCustomerByUsername(username)).size();
        return new ForwardResolution("/trips_for_reservation/list.jsp");
    }
    private Long tripId;
    private List<Long> excursionsIds;

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public List<Long> getExcursionsIds() {
        return excursionsIds;
    }

    public void setExcursionsIds(List<Long> excursionsIds) {
        this.excursionsIds = excursionsIds;
    }

    public Resolution add() {
        log.debug("add()");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        
        List<ExcursionDTO> excursionsSelectedByCustomer = new ArrayList<>();
        if (excursionsIds != null) {
            for (Long e : excursionsIds) {
                excursionsSelectedByCustomer.add(facade.getExcursion(e));
            }
        }
        
        ReservationDTO reservation = facade.makeReservation(facade.getCustomerByUsername(username), facade.getTrip(tripId), excursionsSelectedByCustomer);

        getContext().getMessages().add(new LocalizableMessage("trip.add.message",escapeHTML(reservation.getTrip().getDestination())));
        return new RedirectResolution(this.getClass(), "list");
    }
}
