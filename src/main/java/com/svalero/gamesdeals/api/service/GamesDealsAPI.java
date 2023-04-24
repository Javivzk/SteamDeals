package com.svalero.gamesdeals.api.service;

import com.svalero.gamesdeals.api.model.*;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface GamesDealsAPI {

    @GET("deals")
    Observable<List<DealGameInfo>> getDeals();

    @GET("api/1.0/deals")
    Observable<DealResponse> getDeal(@Query("dealID") String dealID);

    @GET("api/1.0/games")
    Observable<List<GamesInformationList>> getGames(@Query("title") String title, @Query("limit") int limit);

    @GET("api/1.0/games")
    Observable<GameResponse> getGame(@Query("id") String gameID);


}
