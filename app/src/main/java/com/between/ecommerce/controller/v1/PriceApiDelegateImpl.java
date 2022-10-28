package com.between.ecommerce.controller.v1;

import com.between.ecommerce.dto.v1.PriceDTO;
import com.between.ecommerce.entity.Price;
import com.between.ecommerce.service.IPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Date;
import java.util.List;

@Service
public class PriceApiDelegateImpl implements PriceApiDelegate {

    @Autowired
    private IPriceService priceService;

    @Override
    public ResponseEntity<PriceDTO> getLastPrice(OffsetDateTime date,
                                                 Integer productId,
                                                 Integer brandId) {
        Price priceEntity = priceService.findLastPrice(date, Long.valueOf(productId), Long.valueOf(brandId));
        return ResponseEntity.ok(convertEntityToDTO(priceEntity));
    }

    private PriceDTO convertEntityToDTO(Price price) {
        PriceDTO priceDTO = new PriceDTO();
        if(price!=null) {
            priceDTO.setProductId((int) price.getProductId());
            priceDTO.setBrandId((int) price.getBrandId());
            priceDTO.setPriceList(price.getPriceList());
            priceDTO.startDate(price.getStartDate().toInstant().atOffset(ZoneOffset.UTC));
            priceDTO.setEndDate(price.getEndDate().toInstant().atOffset(ZoneOffset.UTC));
            priceDTO.setFinalPrice(price.getPrice());
        }
        return priceDTO;
    }
}