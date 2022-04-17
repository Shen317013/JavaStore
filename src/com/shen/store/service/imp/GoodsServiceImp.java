package com.shen.store.service.imp;

import com.shen.store.dao.GoodsDao;
import com.shen.store.dao.imp.GoodsDaoImpJdbc;
import com.shen.store.domain.Goods;
import com.shen.store.service.GoodsService;

import java.util.List;

public class GoodsServiceImp implements GoodsService {

    GoodsDao goodsDao = new GoodsDaoImpJdbc();

    @Override
    public List<Goods> queryAll() {
        return goodsDao.findAll();
    }

    @Override
    public List<Goods> queryByStartEnd(int start, int end) {
        return goodsDao.findStartEnd(start, end);
    }

    @Override
    public Goods querDetail(long goodsid) {
        return goodsDao.findByPk(goodsid);
    }
}
