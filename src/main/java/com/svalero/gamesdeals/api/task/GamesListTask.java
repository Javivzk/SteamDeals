package com.svalero.gamesdeals.api.task;

import com.svalero.gamesdeals.api.model.GameInformation;
import com.svalero.gamesdeals.api.model.GamesInformationList;
import com.svalero.gamesdeals.api.service.GamesService;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

import java.util.List;
import java.util.stream.Collectors;

public class GamesListTask extends Task<Integer> {

    private String requestedGame;
    private ObservableList<String> results;
    private int counter;
    private ProgressBar progressBar;


    public GamesListTask(String requestedGame, ObservableList<String> results, ProgressBar progressBar) {
        this.requestedGame = requestedGame;
        this.results = results;
        this.progressBar = progressBar;
        this.counter = 0;
    }

    @Override
    protected Integer call() throws Exception {
        GamesService gamesService = new GamesService();

        Consumer<List<GamesInformationList>> userGamesList = (gamesInformationList) -> {
            this.counter++;
            updateProgress(this.counter, 100);
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

    private List<GamesInformationList> filterDeals(List<GamesInformationList> dealGameInfo, String filterText) {
        if (filterText == null || filterText.trim().isEmpty()) {
            return dealGameInfo;
        }
        String lowerCaseFilter = filterText.toLowerCase();
        return dealGameInfo.stream()
                .filter(dealGameInfo1 -> dealGameInfo1.getTitle().toLowerCase().contains(lowerCaseFilter))
                .collect(Collectors.toList());
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
