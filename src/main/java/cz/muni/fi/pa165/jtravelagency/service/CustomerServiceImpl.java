/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.service;

import cz.muni.fi.pa165.jtravelagency.dao.CustomerDAO;
import cz.muni.fi.pa165.jtravelagency.dto.CustomerDTO;
import cz.muni.fi.pa165.jtravelagency.entity.Customer;
import cz.muni.fi.pa165.jtravelagency.util.DTOAndEntityMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mvaraga
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDao;

    public void create(CustomerDTO customerDto) {
        Customer customer = DTOAndEntityMapper.dtoToEntity(customerDto, Customer.class);
        customerDao.createCustomer(customer);
        customerDto.setId(customer.getId());
    }

    public CustomerDTO get(Long id) {
        Customer customer = customerDao.getCustomer(id);
        CustomerDTO customerDto = DTOAndEntityMapper.entityToDto(customer, CustomerDTO.class);
        return customerDto;
    }

    public void update(CustomerDTO customerDto) {
        Customer customer = DTOAndEntityMapper.dtoToEntity(customerDto, Customer.class);
        customerDao.updateCustomer(customer);
    }

    public void delete(CustomerDTO customerDto) {
        Customer customer = DTOAndEntityMapper.dtoToEntity(customerDto, Customer.class);
        customerDao.deleteCustomer(customer);
    }

    public List<CustomerDTO> getAll() {
        List<CustomerDTO> list = new ArrayList<CustomerDTO>();
        for (Customer customer : customerDao.getAllCustomers()) {
            list.add(DTOAndEntityMapper.entityToDto(customer, CustomerDTO.class));
        }
        return list;
    }

    public void setDeletedStatus(CustomerDTO customerDto) {
        Customer customer = DTOAndEntityMapper.dtoToEntity(customerDto, Customer.class);
        customerDao.setDeletedStatus(customer);
    }

}
