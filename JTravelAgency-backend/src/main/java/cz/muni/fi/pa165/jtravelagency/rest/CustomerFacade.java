/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.rest;

import cz.muni.fi.pa165.jtravelagency.dto.CustomerDTO;
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
import javax.ws.rs.core.GenericEntity;
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
@Path("customers")
@Singleton
public class CustomerFacade {

    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    private ServiceFacade facade = applicationContext.getBean("facade", ServiceFacade.class);
    //private static List<CustomerDTO> customers = new ArrayList<>();
    private String _corsHeaders;

    private Response makeCORS(ResponseBuilder req, String returnMethod) {
        ResponseBuilder rb = req.header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS, DELETE, PUT");

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

    public CustomerFacade() {
    }

    @GET
    @Produces("text/plain")
    public String getPlain() {
        StringBuilder returnString = new StringBuilder();
        List<CustomerDTO> customers = facade.getAllCustomers();
        for (CustomerDTO customer : customers) {
            returnString.append(customer.getFirstName());
            returnString.append(" ");
            returnString.append(customer.getLastName());
            returnString.append("\n");
        }

        return returnString.toString();
    }

    @GET
    @Path("j")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonCustomers() {
        List<CustomerDTO> list = facade.getAllCustomers();
        GenericEntity<List<CustomerDTO>> entity = new GenericEntity<List<CustomerDTO>>(list) {
        };

        return makeCORS(Response.ok(entity));

    }

    @Path("{id}")
    public CustomerDTO getCustomerResource(@PathParam("id") Long id) {
        return facade.getCustomer(id);
    }

    @GET
    @Path("json/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDTO getJson(@PathParam("id") Long id) {
        if (facade.getCustomer(id) == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return facade.getCustomer(id);
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        List<CustomerDTO> customers = facade.getAllCustomers();
        return String.valueOf(customers.size());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response postJson(CustomerDTO customerResource) {
        facade.createCustomer(customerResource);
        System.out.println("Created customer " + customerResource.getId());
        return makeCORS(Response.created(URI.create(context.getAbsolutePath() + "/" + customerResource.getId())));
    }

    @OPTIONS
    public Response corsMyResource(@HeaderParam("Access-Control-Request-Headers") String requestH) {
        _corsHeaders = requestH;
        return makeCORS(Response.ok(), requestH);
    }
    
    @OPTIONS
    @Path("{id}")
    public Response corsMyResource2(@HeaderParam("Access-Control-Request-Headers") String requestH) {
        _corsHeaders = requestH;
        return makeCORS(Response.ok(), requestH);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        System.out.println("---- Deleting item nr. " + id);

        if (facade.getCustomer(id) == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        facade.deleteCustomer(facade.getCustomer(id));
        return makeCORS(Response.ok());
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putJson(@PathParam("id") Long id, CustomerDTO customerResource) {

        System.out.println("----  putting item ");

        URI uri = context.getAbsolutePath();
        System.out.println(context.getAbsolutePath());

        CustomerDTO customer = facade.getCustomer(id);

        Response response;
        if (customer != null) {
            response = Response.created(uri).build();
        } else {
            response = Response.noContent().build();
        }

        facade.updateCustomer(customerResource);

        return response;
    }
}
