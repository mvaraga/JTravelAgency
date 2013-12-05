/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.rest;

import cz.muni.fi.pa165.jtravelagency.dto.CustomerDTO;
import cz.muni.fi.pa165.jtravelagency.facade.ServiceFacade;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author xvaraga
 */
@Path("customers")
public class CustomerFacade {
    private static final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    private ServiceFacade facade = applicationContext.getBean("facade", ServiceFacade.class);
    //private static List<CustomerDTO> customers = new ArrayList<>();
    
    @Context
    private UriInfo context;
    
    public CustomerFacade() {
        //customers.clear();
//        List<CustomerDTO> customers = facade.getAllCustomers();
//        this.customers.addAll(customers);
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
    
    @Path("{id}")
    public String getCustomerResource(@PathParam("id") Long id) {
        return facade.getCustomer(id).getPlain();
    }
 
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        List<CustomerDTO> customers = facade.getAllCustomers();
        return String.valueOf(customers.size());
    }
}
