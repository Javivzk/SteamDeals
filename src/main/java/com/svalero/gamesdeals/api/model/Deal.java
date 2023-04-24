package com.svalero.gamesdeals.api.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deal {
    @SerializedName("price")
    private String price;

    @SerializedName("retailPrice")
    private String retailPrice;
}