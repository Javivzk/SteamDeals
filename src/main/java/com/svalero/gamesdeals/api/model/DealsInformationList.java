package com.svalero.gamesdeals.api.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealsInformationList {
    @SerializedName("internalName")
    private String internalName;
    @SerializedName("title")
    private String title;
    @SerializedName("metacriticLink")
    private String metacriticLink;
    @SerializedName("dealID")
    private String dealID;
    @SerializedName("storeID")
    private String storeID;
    @SerializedName("gameID")
    private String gameID;
    @SerializedName("salePrice")
    private String salePrice;
    @SerializedName("normalPrice")
    private String normalPrice;
    @SerializedName("isOnSale")
    private String isOnSale;
    @SerializedName("savings")
    private String savings;
    @SerializedName("metacriticScore")
    private String metacriticScore;
    @SerializedName("steamRatingText")
    private String steamRatingText;
    @SerializedName("steamRatingPercent")
    private String steamRatingPercent;
    @SerializedName("steamRatingCount")
    private String steamRatingCount;
    @SerializedName("steamAppID")
    private String steamAppID;
    @SerializedName("releaseDate")
    private String releaseDate;
    @SerializedName("lastChange")
    private String lastChange;
    @SerializedName("dealRating")
    private String dealRating;
    @SerializedName("thumb")
    private String thumb;


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Game ID: ").append(gameID).append("\n");
        builder.append("Titulo: ").append(title).append("\n");
        builder.append("Mejor precio: ").append(salePrice).append(" €").append("\n");
        return builder.toString();
    }

}
