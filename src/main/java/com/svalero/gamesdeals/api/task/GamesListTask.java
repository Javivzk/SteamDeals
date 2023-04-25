package com.svalero.gamesdeals.api.task;

import com.svalero.gamesdeals.api.model.GamesInformationList;
import com.svalero.gamesdeals.api.model.GamesListResponse;
import com.svalero.gamesdeals.api.service.GamesService;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.util.List;
import java.util.stream.Collectors;

public class GamesListTask extends Task<Integer> {

    private String requestedGame;
    private ObservableList<String> results;
    private int counter;

    public GamesListTask(String requestedGame, ObservableList<String> results) {
        this.requestedGame = requestedGame;
        this.results = results;
        this.counter = 0;
    }

    @Override
    protected Integer call() throws Exception {
        GamesService gamesService = new GamesService();

        Consumer<List<GamesInformationList>> userGamesList = (gamesInformationList) -> {
            this.counter++;
            Thread.sleep(100);
            updateMessage(String.valueOf(this.counter) + " Lista de resultados devuelta");
            Platform.runLater(() -> {
                String gamesListText = gamesInformationList.stream()
                        .map(GamesInformationList::toString)
                        .collect(Collectors.joining("\n"));
                this.results.add(gamesListText);
            });
        };

        gamesService.getGamesTitles(requestedGame).subscribe(userGamesList);
        return null;
    }
}
