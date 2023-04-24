package com.svalero.gamesdeals.api.task;

import com.svalero.gamesdeals.api.service.GamesService;
import io.reactivex.functions.Consumer;
import javafx.concurrent.Task;

public class GameTitleTask extends Task<Integer> {

    private String requestedGame;
    private Consumer<String> user;

    public GameTitleTask(String requestedGame, Consumer<String> user) {
        this.requestedGame = requestedGame;
        this.user = user;
    }
    @Override
    protected Integer call() throws Exception {
        GamesService gamesService = new GamesService();
        gamesService.getGameTitle(requestedGame).subscribe(user);


        return null;
    }
}
