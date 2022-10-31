package com.between.ecommerce.controller.v1;

import com.between.ecommerce.constants.Constants;
import com.between.ecommerce.dto.v1.PriceDTO;
import com.between.ecommerce.entity.Price;
import com.between.ecommerce.exception.PriceNotFoundException;
import com.between.ecommerce.service.IPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class PriceApiDelegateImpl implements PriceApiDelegate  {
    @Autowired
    private IPriceService priceService;

    @Override
    public ResponseEntity<PriceDTO> getLastPrice(OffsetDateTime date,
                                                 Integer productId,
                                                 Integer brandId) {
        try{
            Price priceEntity = priceService.findLastPrice(date, Long.valueOf(productId), Long.valueOf(brandId));
            PriceDTO priceDTO = convertEntityToDTO(priceEntity);
            if(priceDTO==null) {
                return (ResponseEntity<PriceDTO>) ResponseEntity.notFound();
                //throw new PriceNotFoundException();
            }
            return ResponseEntity.ok(priceDTO);
        } catch (Exception ex) {
            return (ResponseEntity<PriceDTO>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private PriceDTO convertEntityToDTO(Price price) {
        PriceDTO priceDTO = null;
        if(price!=null) {
            priceDTO = new PriceDTO();
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