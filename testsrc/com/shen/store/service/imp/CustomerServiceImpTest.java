package com.shen.store.service.imp;

import com.shen.store.domain.Customer;
import com.shen.store.service.CustomerService;
import com.shen.store.service.ServiceException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImpTest {

    CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImp();
    }

    @AfterEach
    void tearDown() {
        customerService = null;
    }

    @Test
    @DisplayName("登入成功")
    void login1() {

        Customer customer = new Customer();
        customer.setId("shen");
        customer.setPassword("111");

        assertTrue(customerService.login(customer));
        assertNotNull(customer);
        assertEquals("shen",customer.getId());
        assertEquals("沈琮愈",customer.getName());
        assertEquals("111",customer.getPassword());
        assertEquals("宜蘭縣",customer.getAddress());
        assertEquals("88888",customer.getPhone());
        assertEquals(11111111,customer.getBirthday().getTime());

    }

    @Test
    @DisplayName("登入失敗")
    void login2() {

        Customer customer = new Customer();
        customer.setId("shen");
        customer.setPassword("123");
        assertFalse(customerService.login(customer));
    }

    @Test
    @DisplayName("註冊成功")
    void register1() throws ServiceException {

        Customer customer = new Customer();
        customer.setId("tom");
        customer.setName("葉伸");
        customer.setPassword("567");
        customer.setAddress("礁溪鄉");
        customer.setPhone("22222");
        customer.setBirthday(new Date(11156121));

        customerService.register(customer);
        Customer customer1 = new Customer();
        customer1.setId("tom");
        customer1.setPassword("567");

        customerService.login(customer1);
        assertNotNull(customer1);
        assertEquals("tom",customer1.getId());
        assertEquals("葉伸",customer1.getName());
        assertEquals("567",customer1.getPassword());
        assertEquals("礁溪鄉",customer1.getAddress());
        assertEquals("22222",customer1.getPhone());
        assertEquals(11156121,customer1.getBirthday().getTime());

    }

    @Test
    @DisplayName("註冊失敗,客戶ID已存在")
    void register2() throws ServiceException {

        Customer customer = new Customer();
        customer.setId("tom");
        customer.setPassword("567");

        assertThrows(ServiceException.class , ()->{
            customerService.register(customer);
        });

    }
}