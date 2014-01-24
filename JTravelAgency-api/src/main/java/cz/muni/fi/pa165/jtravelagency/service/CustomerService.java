/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.service;

import cz.muni.fi.pa165.jtravelagency.dto.CustomerDTO;
import java.util.List;

/**
 *
 * @author mvaraga
 */
public interface CustomerService {
    
    void create(CustomerDTO customer);
    
    /*
     * Gets customer specified by its id
     * 
     * @param id Id of the customer
     */
    CustomerDTO get(Long id);
    
    /*
     * Updates customer
     * 
     * @param customer Customer to delete
     */
    void update(CustomerDTO customer);
    
    /*
     * Deletes customer from database.
     * 
     * @param customer Customer to delete
     */
    void delete(CustomerDTO customer);
    
    /*
     * Gets all customers from database.
     */
    List<CustomerDTO> getAll();
    
    CustomerDTO getCustomerByUsername(String username);
}
