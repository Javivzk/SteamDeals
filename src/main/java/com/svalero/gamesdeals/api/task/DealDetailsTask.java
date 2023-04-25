package com.svalero.gamesdeals.api.task;

import com.svalero.gamesdeals.api.model.DealGameInfo;
import com.svalero.gamesdeals.api.model.DealResponse;
import com.svalero.gamesdeals.api.service.GamesService;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class DealDetailsTask extends Task<Integer> {

    private String requestedDeal;
    private ObservableList<DealGameInfo> results;
    private int counter;

    public DealDetailsTask(String requestedDeal, ObservableList<DealGameInfo> results) {
        this.requestedDeal = requestedDeal;
        this.results = results;
        this.counter = 0;
    }

    @Override
    protected Integer call() throws Exception {
        GamesService gamesService = new GamesService();

        Consumer<DealGameInfo> userDealsInfo = (dealGameInfo) -> {
            this.counter++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateMessage(String.valueOf(this.counter) + " Oferta encontrada!");
            Platform.runLater(() -> this.results.add(dealGameInfo));
        };

        gamesService.getDeal(requestedDeal).subscribe(userDealsInfo);

        return null;
    }
}
