package com.svalero.gamesdeals.api.task;

import com.svalero.gamesdeals.api.service.GamesService;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

public class GameIDTask extends Task<Integer> {

    private String requestedGame;
    private ObservableList<String> results;
    private int counter;
    private ProgressBar progressBar;



    public GameIDTask(String requestedGame, ObservableList<String> results, ProgressBar progressBar) {
        this.requestedGame = requestedGame;
        this.results = results;
        this.progressBar = progressBar;
        this.counter = 0;
    }
    @Override
    protected Integer call() throws Exception {
        GamesService gamesService = new GamesService();

        Consumer<String> userGameInfo = (gameInformation) -> {
            this.counter++;
            updateProgress(this.counter, 100);
            Thread.sleep(1000);
            updateMessage(String.valueOf(this.counter) + " Juego encontrado!");
            Platform.runLater(() -> this.results.add(gameInformation));
        };


        gamesService.getGameTitle(requestedGame).subscribe(userGameInfo);


        return null;
    }

    @Override
    protected void succeeded() {
        progressBar.setProgress(1.0);
    }

    @Override
    protected void failed() {
        progressBar.setProgress(0);
    }

    @Override
    protected void cancelled() {
        progressBar.setProgress(0);
    }
}
