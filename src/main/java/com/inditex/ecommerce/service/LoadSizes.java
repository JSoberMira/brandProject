package com.inditex.ecommerce.service;

import com.inditex.ecommerce.model.Size;
import com.inditex.ecommerce.repository.SizesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoadSizes {

    @Autowired
    private SizesRepository sizesRepository;

    public Map<Integer, List<Size>> getSizes(final List<Integer> products, List<Integer> sizeIds) {
        Map<Integer, List<Size>> sizes = new HashMap<>();
        List<Size> sizeList = sizesRepository.findByProductIdIn( products );
        sizeList.stream().forEach( size -> {
            if (sizes.containsKey( size.getProductId() ) ) {
                sizes.get( size.getProductId() ).add( size );
            } else {
                List< Size > sizeList1 = new ArrayList<>();
                sizeList1.add( size );
                sizes.put( size.getProductId(), sizeList1 );
            }
            sizeIds.add( size.getId() );
            }  );
        return sizes;
    }
}
