package com.svalero.gamesdeals.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealGameInfo {

    private String storeID;
    private String gameID;
    private String name;
    private String steamAppID;
    private String salePrice;
    private String retailPrice;
    private String steamRatingText;
    private String steamRatingPercent;
    private String steamRatingCount;
    private String metacriticScore;
    private String metacriticLink;
    private String releaseDate;
    private String publisher;
    private String steamworks;
    private String thumb;

}
