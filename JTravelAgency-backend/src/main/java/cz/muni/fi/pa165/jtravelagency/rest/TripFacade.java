/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.rest;

import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import cz.muni.fi.pa165.jtravelagency.facade.ServiceFacade;
import java.net.URI;
import java.util.List;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author xvaraga
 */
@Path("trips")
@Singleton
public class TripFacade {

    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    private ServiceFacade facade = applicationContext.getBean("facade", ServiceFacade.class);
    //private static List<TripDTO> trips = new ArrayList<>();
    private String _corsHeaders;

    private Response makeCORS(ResponseBuilder req, String returnMethod) {
        ResponseBuilder rb = req.header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS");

        if (!"".equals(returnMethod)) {
            rb.header("Access-Control-Allow-Headers", returnMethod);
        }

        return rb.build();
    }

    private Response makeCORS(ResponseBuilder req) {
        return makeCORS(req, _corsHeaders);
    }
    @Context
    private UriInfo context;

    public TripFacade() {
        //trips.clear();
//        List<TripDTO> trips = facade.getAllTrips();
//        this.trips.addAll(trips);
    }
//
    @GET
    @Produces("text/plain")
    public String getPlain() {
        StringBuilder returnString = new StringBuilder();
        List<TripDTO> trips = facade.getAllTrips();
        for (TripDTO trip : trips) {
            returnString.append(trip.getDestination());
            returnString.append(" ");
            returnString.append(trip.getPrice());
            returnString.append("\n");
        }

        return returnString.toString();
    }
    
    @GET
    @Path("j")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TripDTO> getJsonTrips() {
        return facade.getAllTrips();
    }

    @Path("{id}")
    public TripDTO getTripResource(@PathParam("id") Long id) {
        return facade.getTrip(id);
    }

    @GET
    @Path("json/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TripDTO getJson(@PathParam("id") Long id) {
        if (facade.getTrip(id) == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return facade.getTrip(id);
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        List<TripDTO> trips = facade.getAllTrips();
        return String.valueOf(trips.size());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response postJson(TripDTO tripResource) {
        facade.createTrip(tripResource);
        System.out.println("Created trip " + tripResource.getId());
        return Response.created(URI.create(context.getAbsolutePath() + "/" + tripResource.getId())).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, OPTIONS").build();
    }

    @OPTIONS
    public Response corsMyResource(@HeaderParam("Access-Control-Request-Headers") String requestH) {
        _corsHeaders = requestH;
        return makeCORS(Response.ok(), requestH);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        System.out.println("---- Deleting item nr. " + id);

        if (facade.getTrip(id) == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        facade.deleteTrip(facade.getTrip(id));
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putJson(@PathParam("id") Long id, TripDTO tripResource) {

        System.out.println("----  putting item ");

        URI uri = context.getAbsolutePath();
        System.out.println(context.getAbsolutePath());

        TripDTO trip = facade.getTrip(id);
        
        Response response;
        if (trip!=null) {
            response = Response.created(uri).build();
        } else {
            response = Response.noContent().build();
        }

        facade.updateTrip(tripResource);

        return response;
    }
}
