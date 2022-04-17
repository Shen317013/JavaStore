package com.shen.store.service.imp;

import com.shen.store.dao.GoodsDao;
import com.shen.store.dao.OrderDao;
import com.shen.store.dao.OrderLineItemDao;
import com.shen.store.dao.imp.GoodsDaoImpJdbc;
import com.shen.store.dao.imp.OrderDaoImpJdbc;
import com.shen.store.dao.imp.OrderLineItemDaoImpJdbc;
import com.shen.store.domain.Goods;
import com.shen.store.domain.OrderLineItem;
import com.shen.store.domain.Orders;
import com.shen.store.service.OrdersService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrdersServiceImp implements OrdersService {

    GoodsDao goodsDao = new GoodsDaoImpJdbc();
    OrderDao orderDao = new OrderDaoImpJdbc();
    OrderLineItemDao orderLineItemDao = new OrderLineItemDaoImpJdbc();

    @Override
    public String submitOrders(List<Map<String, Object>> cart) {

        Orders orders = new Orders();
        Date date = new Date();
        String ordersid = String.valueOf(date.getTime())
                + String.valueOf((int) (Math.random() * 100));

        orders.setId(ordersid);
        orders.setOrderDate(date);
        orders.setStatus(1);
        orders.setTotal(0.0f);
        //將訂單訊息插入數據庫
        orderDao.create(orders);

        double total = 0.0;

        for (Map item : cart) {
            //item結構 [商品id , 數量]
            Long goodsid = (Long) item.get("goodsid");
            Integer quantity = (Integer) item.get("quantity");
            Goods goods = goodsDao.findByPk(goodsid);
            //小計
            double subtotal = quantity * goods.getPrice();
            total += subtotal;

            OrderLineItem lineItem = new OrderLineItem();
            lineItem.setQuantity(quantity);
            lineItem.setGoods(goods);
            lineItem.setOrders(orders);
            lineItem.setSubTotal(subtotal);

            //將訂單詳細插入到數據庫
            orderLineItemDao.create(lineItem);
        }

        orders.setTotal(total);
        //更新訂單數據庫
        orderDao.modify(orders);

        return ordersid;
    }
}
