package com.svalero.gamesdeals.api.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealGameInfo {

    @SerializedName("storeID")
    private String storeID;
    @SerializedName("gameID")
    private String gameID;
    @SerializedName("name")
    private String name;
    @SerializedName("steamAppID")
    private String steamAppID;
    @SerializedName("salePrice")
    private String salePrice;
    @SerializedName("retailPrice")
    private String retailPrice;
    @SerializedName("steamRatingText")
    private String steamRatingText;
    @SerializedName("steamRatingPercent")
    private String steamRatingPercent;
    @SerializedName("steamRatingCount")
    private String steamRatingCount;
    @SerializedName("metacriticScore")
    private String metacriticScore;
    @SerializedName("metacriticLink")
    private String metacriticLink;
    @SerializedName("releaseDate")
    private String releaseDate;
    @SerializedName("publisher")
    private String publisher;
    @SerializedName("steamworks")
    private String steamworks;
    @SerializedName("thumb")
    private String thumb;

}
