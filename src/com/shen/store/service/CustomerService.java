package com.shen.store.service;

import com.shen.store.domain.Customer;

public interface CustomerService {

    //處理登入
    boolean login(Customer customer);

    //處理註冊
    void register(Customer customer) throws ServiceException;
}
