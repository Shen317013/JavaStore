package com.shen.store.service.imp;

import com.shen.store.dao.CustomerDao;
import com.shen.store.dao.imp.CustomerDaoImpJdbc;
import com.shen.store.domain.Customer;
import com.shen.store.service.CustomerService;
import com.shen.store.service.ServiceException;

public class CustomerServiceImp implements CustomerService {

    CustomerDao customerDao = new CustomerDaoImpJdbc();
    @Override
    public boolean login(Customer customer) {

        Customer dbCustomer = customerDao.findByPk(customer.getId());

        if(dbCustomer.getPassword().equals(customer.getPassword())){
            //登入成功 將數據庫的值傳回來
            customer.setPhone(dbCustomer.getPhone());
            customer.setAddress(dbCustomer.getAddress());
            customer.setName(dbCustomer.getName());
            customer.setBirthday(dbCustomer.getBirthday());
            return true;
        }

        return false;
    }

    @Override
    public void register(Customer customer) throws ServiceException {

        Customer dbCustomer = customerDao.findByPk(customer.getId());

        if(dbCustomer != null){ //客戶ID已經存在
            throw new ServiceException("客戶ID: "+customer.getId()+"已經存在!");
        }

        //註冊開始
        customerDao.create(customer);
    }
}
