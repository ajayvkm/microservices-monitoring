package com.sdsu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Integer id;
    private Integer productId;
    private Integer accountId;
    private Integer count;
    private Double price;
    private Double discountedPrice;
/*    private Date createdDate;
    private String createdBy;*/

}
