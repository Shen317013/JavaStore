package com.shen.store.service;

import com.shen.store.domain.Goods;

import java.util.List;

public interface GoodsService {

    List<Goods> queryAll();

    List<Goods> queryByStartEnd(int start , int end);

    Goods querDetail(long goodsid);

}
