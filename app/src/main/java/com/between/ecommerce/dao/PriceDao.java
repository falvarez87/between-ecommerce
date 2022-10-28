package com.between.ecommerce.dao;

import com.between.ecommerce.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;

public interface PriceDao extends JpaRepository<Price, Long> {

    @Query(nativeQuery = true, value = "SELECT Top 1 * FROM Price p WHERE p.product_id=:productId AND p.brand_id =:brandId AND :applicationDate BETWEEN p.start_date AND p.end_date ORDER BY p.priority DESC")
    Price findLastPrice(@Param("applicationDate") OffsetDateTime applicationDate, @Param("productId") Long productId, @Param("brandId") Long brandId);
}
