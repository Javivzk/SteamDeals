package com.svalero.gamesdeals.api.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheapestPrice {
    @SerializedName("price")
    private String price;

    @SerializedName("date")
    private long date;
}
