package com.svalero.gamesdeals.api.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamesInformationList {

    @SerializedName("internalName")
    private String internalName;
    @SerializedName("title")
    private String title;
    @SerializedName("gameID")
    private String gameID;
    @SerializedName("steamAppID")
    private String steamAppID;
    @SerializedName("cheapest")
    private String cheapest;
    @SerializedName("cheapestDealID")
    private String cheapestDealID;
    @SerializedName("external")
    private String external;
    @SerializedName("thumb")
    private String thumb;


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Game ID: ").append(gameID).append("\n");
        builder.append("Titulo: ").append(external).append("\n");
        builder.append("Mejor precio: ").append(cheapest).append(" €").append("\n");
        return builder.toString();
    }



}
