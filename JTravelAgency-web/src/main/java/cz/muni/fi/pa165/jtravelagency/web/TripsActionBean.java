/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.web;

import cz.muni.fi.pa165.jtravelagency.dto.CustomerDTO;
import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import cz.muni.fi.pa165.jtravelagency.facade.ServiceFacade;
import static cz.muni.fi.pa165.jtravelagency.web.CustomersActionBean.log;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jakub
 */
public class TripsActionBean extends BaseActionBean implements ValidationErrorHandler {

    final static Logger log = LoggerFactory.getLogger(CustomersActionBean.class);

    @SpringBean //Spring can inject even to private and protected fields
    protected ServiceFacade facade;

    //--- part for showing a list of books ----
    private List<TripDTO> trips;

    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        trips = facade.getAllTrips();
        return new ForwardResolution("/trip/list.jsp");
    }

    public List<TripDTO> getTrips() {
        return trips;
    }

    //--- part for adding a book ----

//    @ValidateNestedProperties(value = {
//            @Validate(on = {"add", "save"}, field = "author", required = true),
//            @Validate(on = {"add", "save"}, field = "title", required = true),
//            @Validate(on = {"add", "save"}, field = "year", required = true, minvalue = 800)
//    })
    private TripDTO trip;

    public Resolution add() {
        log.debug("add() trip={}", trip);
        facade.createTrip(trip);
        getContext().getMessages().add(new LocalizableMessage("trip.add.message",escapeHTML(trip.getDestination())));
        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        //fill up the data for the table if validation errors occured
        trips = facade.getAllTrips();
        //return null to let the event handling continue
        return null;
    }

    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    //--- part for deleting a book ----

    public Resolution delete() {
        log.debug("delete({})", trip.getId());
        //only id is filled by the form
        trip = facade.getTrip(trip.getId());
        facade.deleteTrip(trip);
        getContext().getMessages().add(new LocalizableMessage("trip.delete.message",escapeHTML(trip.getDestination())));
        return new RedirectResolution(this.getClass(), "list");
    }

    //--- part for editing a book ----

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadTripFromDatabase() {
        String ids = getContext().getRequest().getParameter("trip.id");
        if (ids == null) return;
        trip = facade.getTrip(Long.parseLong(ids));
    }

    public Resolution edit() {
        log.debug("edit() customer={}", trip);
        return new ForwardResolution("/trip/edit.jsp");
    }

    public Resolution save() {
        log.debug("save() customer={}", trip);
        facade.updateTrip(trip);
        return new RedirectResolution(this.getClass(), "list");
    }
    
}
