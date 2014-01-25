/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.web;

import cz.muni.fi.pa165.jtravelagency.dto.ExcursionDTO;
import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import cz.muni.fi.pa165.jtravelagency.facade.ServiceFacade;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        for(TripDTO t : trips) {
            List<ExcursionDTO> exc = facade.getExcursionsByTrip(t);
            excursions.add(exc);
        }
        return new ForwardResolution("/trips_for_reservation/list.jsp");
    }
}
