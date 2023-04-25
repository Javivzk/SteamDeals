package com.svalero.gamesdeals.api.task;

import com.svalero.gamesdeals.api.model.DealsInformationList;
import com.svalero.gamesdeals.api.service.GamesService;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;


import java.util.List;
import java.util.stream.Collectors;

public class DealsListTask extends Task<Integer> {

    private String requestedDeal;
    private ObservableList<String> results;
    private int counter;
    private ProgressBar progressBar;

    public DealsListTask(String requestedDeal, ObservableList<String> results, ProgressBar progressBar) {
        this.requestedDeal = requestedDeal;
        this.results = results;
        this.progressBar = progressBar;
        this.counter = 0;
    }

    @Override
    protected Integer call() throws Exception {
        GamesService gamesService = new GamesService();

        Consumer<List<DealsInformationList>> userDealsList = (dealsInformationList) -> {
            this.counter++;
            updateProgress(this.counter, 100);
            Thread.sleep(100);
            updateMessage(String.valueOf(this.counter) + " Lista de resultados devuelta");
            Platform.runLater(() -> {
                String dealsListText = dealsInformationList.stream()
                        .map(DealsInformationList::toString)
                        .collect(Collectors.joining("\n"));
                this.results.add(dealsListText);
            });
        };

        gamesService.getDealsList(requestedDeal).subscribe(userDealsList);
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
