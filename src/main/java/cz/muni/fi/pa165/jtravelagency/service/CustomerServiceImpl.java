/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.service;

import cz.muni.fi.pa165.jtravelagency.dao.CustomerDAO;
import cz.muni.fi.pa165.jtravelagency.dto.CustomerDTO;
import cz.muni.fi.pa165.jtravelagency.entity.Customer;
import static cz.muni.fi.pa165.jtravelagency.util.DTOAndDAOMapper.dtoToEntity;
import static cz.muni.fi.pa165.jtravelagency.util.DTOAndDAOMapper.entityToDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author mvaraga
 */
public class CustomerServiceImpl implements CustomerService{
    
    @Autowired
    private CustomerDAO customerDao;

    public void create(CustomerDTO customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public CustomerDTO get(Long id) {
        return entityToDto(customerDao.getCustomer(id));
    }

    public void update(CustomerDTO customer) {
        customerDao.updateCustomer(dtoToEntity(customer));
    }

    public void delete(CustomerDTO customer) {
        customerDao.deleteCustomer(dtoToEntity(customer));
    }

    public List<CustomerDTO> getAll() {
        List<CustomerDTO> list = new ArrayList<CustomerDTO>();
        for (Customer customer : customerDao.getAllCustomers()) {
            list.add(entityToDto(customer));
        }
        return list;
    }

    public void setDeletedStatus(CustomerDTO customer) {
        customerDao.setDeletedStatus(dtoToEntity(customer));
    }
    
    
}
