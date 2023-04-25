package com.svalero.gamesdeals.api.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import com.svalero.gamesdeals.api.model.DealGameInfo;
import com.svalero.gamesdeals.api.model.DealResponse;
import com.svalero.gamesdeals.api.model.GamesInformationList;
import com.svalero.gamesdeals.api.model.GamesListResponse;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class GamesService {

    static final String BASE_URL = "https://www.cheapshark.com";
    private GamesDealsAPI gamesDealsAPI;

    public GamesService() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gsonParser = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gsonParser))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.gamesDealsAPI = retrofit.create(GamesDealsAPI.class);
    }
    public Observable<String> getGameTitle(String gameID) {
        return this.gamesDealsAPI.getGame(gameID)
                .filter(gameResponse -> gameResponse != null) // Filtrar valores null
                .map(gameResponse -> "Titulo -> " + gameResponse.getGameInformation().getTitle() + " (" +
                        gameResponse.getGameInformation().getSteamAppID() + ")" +
                        "\nPrecio más barato: " + gameResponse.getCheapestPrice().getPrice()); // Agregar espacio y título adicional
    }




    public Observable<List<GamesInformationList>> getGamesTitles(String title) {
        return this.gamesDealsAPI.getGames(title, 10)
                .map(gamesInformationLists -> gamesInformationLists)
                .map(gamesListResponse -> gamesListResponse);
    }

    public Observable<DealGameInfo> getDeal(String dealID) {
        return this.gamesDealsAPI.getDeal(dealID)
                .map(dealResponse -> dealResponse.getDealGameInfo())
                .map(dealGameInfo -> dealGameInfo);
    }


}
