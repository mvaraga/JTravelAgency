/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.jtravelagency;

import java.util.List;

/**
 *
 * @author Marian Varaga
 */
public interface CustomerDAO {
    /*
     * Creates new Customer
     */
    void createCustomer(Customer customer);
    
    /*
     * Gets customer specified by its id
     * 
     * @param id Id of the customer
     */
    Customer getCustomer(Long id);
    
    /*
     * Updates customer
     * 
     * @param customer Customer to 
     */
    void updateCustomer(Customer customer);
    
    /*
     * Deletes customer from database.
     * 
     * @param customer Customer to delete
     */
    void deleteCustomer(Customer customer);
    
    /*
     * Gets all customers from database.
     */
    List<Customer> getAllCustomers();
    
    /*
     * Set status to deleted
     * @param
     */
    void setDeletedStatus(Customer customer);
}
