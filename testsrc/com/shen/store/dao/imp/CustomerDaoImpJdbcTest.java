package com.shen.store.dao.imp;

import com.shen.store.dao.CustomerDao;
import com.shen.store.domain.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDaoImpJdbcTest {

    CustomerDao dao;

    @BeforeEach
    void setUp() {
        dao = new CustomerDaoImpJdbc();
    }

    @AfterEach
    void tearDown() {
        dao = null;
    }

    @Test
    void findByPk() {

        //按照主鍵查詢
        Customer customer = dao.findByPk("shen");
        assertNotNull(customer);
        assertEquals("shen",customer.getId());
        assertEquals("沈琮愈",customer.getName());
        assertEquals("111",customer.getPassword());
        assertEquals("宜蘭縣",customer.getAddress());
        assertEquals("88888",customer.getPhone());
        assertEquals(11111111,customer.getBirthday().getTime());

    }

    @Test
    void findAll() {
        List<Customer> list = dao.findAll();
        assertEquals(list.size(),1);
    }

    @Test
    void create() {
        Customer customer = new Customer();
        customer.setId("tom");
        customer.setName("沈琮凱");
        customer.setPassword("123");
        customer.setAddress("羅東鎮");
        customer.setPhone("11111");
        customer.setBirthday(new Date(111111234));

        dao.create(customer);

        //按照主鍵查詢
        Customer customer1 = dao.findByPk("tom");
        assertNotNull(customer);
        assertEquals("tom",customer1.getId());
        assertEquals("沈琮凱",customer1.getName());
        assertEquals("123",customer1.getPassword());
        assertEquals("羅東鎮",customer1.getAddress());
        assertEquals("11111",customer1.getPhone());
        assertEquals(111111234,customer1.getBirthday().getTime());
    }

    @Test
    void modify() {
        Customer customer = new Customer();
        customer.setId("tom");
        customer.setName("葉伸");
        customer.setPassword("567");
        customer.setAddress("礁溪鄉");
        customer.setPhone("22222");
        customer.setBirthday(new Date(11156121));

        dao.modify(customer);

        //按照主鍵查詢
        Customer customer1 = dao.findByPk("tom");
        assertNotNull(customer);
        assertEquals("tom",customer1.getId());
        assertEquals("葉伸",customer1.getName());
        assertEquals("567",customer1.getPassword());
        assertEquals("礁溪鄉",customer1.getAddress());
        assertEquals("22222",customer1.getPhone());
        assertEquals(11156121,customer1.getBirthday().getTime());
    }

    @Test
    void remove() {
        dao.remove("tom");
        //按照主鍵查詢
        Customer customer = dao.findByPk("tom");
        assertNull(customer);
    }
}