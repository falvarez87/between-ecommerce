package com.between.ecommerce.service;

import com.between.ecommerce.entity.Price;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

public interface IPriceService {
    Price findLastPrice(OffsetDateTime applicationDate, Long productId, Long brandId);
}
