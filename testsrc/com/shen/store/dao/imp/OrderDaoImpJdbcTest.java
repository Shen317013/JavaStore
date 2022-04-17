package com.shen.store.dao.imp;

import com.shen.store.dao.OrderDao;
import com.shen.store.domain.Orders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoImpJdbcTest {

    OrderDao dao;

    @BeforeEach
    void setUp() {
        dao = new OrderDaoImpJdbc();
    }

    @AfterEach
    void tearDown() {
        dao = null;
    }

    @Test
    void findByPk() {

        Orders orders = dao.findByPk("100");
        assertNotNull(orders);
        assertEquals("100",orders.getId());
        assertEquals(111111,orders.getOrderDate().getTime());
        assertEquals(1,orders.getStatus());
        assertEquals(9000,orders.getTotal());
    }

    @Test
    void findAll() {

        List<Orders> list = dao.findAll();
        assertEquals(2,list.size());

        Orders orders = list.get(1);
        assertNotNull(orders);
        assertEquals("200",orders.getId());
        assertEquals(124431,orders.getOrderDate().getTime());
        assertEquals(2,orders.getStatus());
        assertEquals(5000,orders.getTotal());
    }

    @Test
    void create() {

        Orders orders = new Orders();
        orders.setId("300");
        orders.setStatus(0);
        orders.setOrderDate(new Date(124431));
        orders.setTotal(3500.0);

        dao.create(orders);
        Orders orders1 = dao.findByPk("300");
        assertNotNull(orders1);
        assertEquals("300",orders1.getId());
        assertEquals(124431,orders1.getOrderDate().getTime());
        assertEquals(0,orders1.getStatus());
        assertEquals(3500,orders1.getTotal());
    }

    @Test
    void modify() {

        Orders orders = new Orders();
        orders.setId("300");
        orders.setStatus(1);
        orders.setOrderDate(new Date(1342151));
        orders.setTotal(800.5);

        dao.modify(orders);

        Orders orders1 = dao.findByPk("300");
        assertNotNull(orders1);
        assertEquals("300",orders1.getId());
        assertEquals(1342151,orders1.getOrderDate().getTime());
        assertEquals(1,orders1.getStatus());
        assertEquals(800.5,orders1.getTotal());

    }

    @Test
    void remove() {
        dao.remove("300");
        Orders orders = dao.findByPk("300");
        assertNull(orders);
    }
}