package com.svalero.gamesdeals.api.task;

import com.svalero.gamesdeals.api.model.DealGameInfo;
import com.svalero.gamesdeals.api.model.DealResponse;
import com.svalero.gamesdeals.api.model.GamesInformationList;
import com.svalero.gamesdeals.api.service.GamesService;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DealDetailsTask extends Task<Integer> {

    private String requestedDeal;
    private ObservableList<List<DealGameInfo>> results;
    private int counter;
    private ProgressBar progressBar;
    private String filterText;


    public DealDetailsTask(String requestedDeal, ObservableList<List<DealGameInfo>> results, ProgressBar progressBar) {
        this.requestedDeal = requestedDeal;
        this.results = results;
        this.progressBar = progressBar;
        this.counter = 0;
    }

    @Override
    protected Integer call() throws Exception {
        GamesService gamesService = new GamesService();

        Consumer<List<DealGameInfo>> userDealsInfo = (dealGameInfo) -> {
            this.counter++;
            updateProgress(this.counter, 100);
            Thread.sleep(100);
            updateMessage(String.valueOf(this.counter) + " Lista de resultados devuelta");
            List<DealGameInfo> filteredList = filterDeals(dealGameInfo, filterText);
            Platform.runLater(() -> {
                this.results.addAll(dealGameInfo);
            });
        };


        gamesService.getDeal(requestedDeal).subscribe(userDealsInfo);

        return null;
    }

    private List<DealGameInfo> filterDeals(List<DealGameInfo> dealGameInfo, String filterText) {
        if (filterText == null || filterText.trim().isEmpty()) {
            return dealGameInfo;
        }
        String lowerCaseFilter = filterText.toLowerCase();
        return dealGameInfo.stream()
                .filter(dealGameInfo1 -> dealGameInfo1.getName().toLowerCase().contains(lowerCaseFilter))
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
