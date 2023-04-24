package com.svalero.gamesdeals.api.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameResponse {
    @SerializedName("info")
    private GameInformation gameInformation;

    @SerializedName("cheapestPriceEver")
    private CheapestPrice cheapestPrice;

    @SerializedName("deals")
    private List<Deal> deals;

}
