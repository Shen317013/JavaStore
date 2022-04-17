package com.shen.store.dao;

import com.shen.store.domain.Goods;

import java.util.List;

public interface GoodsDao {

    Goods findByPk(long pk);

    List<Goods> findAll();

    //提供分頁查詢 start開始索引從0開始 end結束索引 return商品列表
    List<Goods> findStartEnd(int start, int end);

    void create(Goods goods);

    void modify(Goods goods);

    void remove(long pk);
}
