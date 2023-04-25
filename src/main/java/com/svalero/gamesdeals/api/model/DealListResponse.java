package com.svalero.gamesdeals.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealListResponse {
    private int numResults;
    private List<DealsInformationList> dealsInformationList;
}
