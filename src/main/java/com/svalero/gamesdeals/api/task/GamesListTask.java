package com.svalero.gamesdeals.api.task;

import com.svalero.gamesdeals.api.model.GamesInformationList;
import com.svalero.gamesdeals.api.model.GamesListResponse;
import com.svalero.gamesdeals.api.service.GamesService;
import io.reactivex.functions.Consumer;
import javafx.concurrent.Task;

import java.util.List;

public class GamesListTask extends Task<Integer> {

    private String requestedGame;
    private Consumer<List<GamesInformationList>> gamesListUser;

    public GamesListTask(String requestedGame, Consumer<List<GamesInformationList>> user) {
        this.requestedGame = requestedGame;
        this.gamesListUser = user;
    }

    @Override
    protected Integer call() throws Exception {
        GamesService gamesService = new GamesService();
        gamesService.getGamesTitles(requestedGame).subscribe(gamesListUser);
        return null;
    }
}
