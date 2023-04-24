package com.svalero.gamesdeals.api.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameInformation {
    @SerializedName("title")
    private String title;

    @SerializedName("steamAppID")
    private String steamAppID;

    @SerializedName("thumb")
    private String thumb;


}
