package com.between.ecommerce.service;

import com.between.ecommerce.dao.PriceDao;
import com.between.ecommerce.entity.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;


@Service
public class PriceServiceImpl implements IPriceService {

    @Autowired
    private PriceDao priceDao;
    @Override
    public Price findLastPrice(OffsetDateTime applicationDate, Long productId, Long brandId) {

        return priceDao.findLastPrice(applicationDate,productId,brandId);
    }
}
