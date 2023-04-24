package com.svalero.gamesdeals.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheaperStores {

    private String dealID;
    private String storeID;
    private String salePrice;
    private String retailPrice;
}
