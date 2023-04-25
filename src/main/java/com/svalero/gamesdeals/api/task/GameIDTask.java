package com.svalero.gamesdeals.api.task;

import com.svalero.gamesdeals.api.service.GamesService;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class GameIDTask extends Task<Integer> {

    private String requestedGame;
    private ObservableList<String> results;
    private int counter;


    public GameIDTask(String requestedGame, ObservableList<String> results) {
        this.requestedGame = requestedGame;
        this.results = results;
        this.counter = 0;
    }
    @Override
    protected Integer call() throws Exception {
        GamesService gamesService = new GamesService();

        Consumer<String> userGameInfo = (gameInformation) -> {
            this.counter++;
            Thread.sleep(1000);
            updateMessage(String.valueOf(this.counter) + " Juego encontrado!");
            Platform.runLater(() -> this.results.add(gameInformation));
        };


        gamesService.getGameTitle(requestedGame).subscribe(userGameInfo);


        return null;
    }
}
