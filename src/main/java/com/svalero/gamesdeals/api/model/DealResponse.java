package com.svalero.gamesdeals.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealResponse {

    private DealGameInfo dealGameInfo;

    private List<CheaperStores> cheaperStores;

    private CheapestPrice cheapestPrice;
}
