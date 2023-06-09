package com.svalero.gamesdeals.api.controller;

import com.svalero.gamesdeals.api.util.R;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.List;

public class AppController {

    @FXML
    private TextField inputRequested;
    @FXML
    private Button btSearch;
    @FXML
    private Button btSearchTitle;
    @FXML
    private Button btSearchDeal;
    @FXML
    private Button btSearchDealsList;
    @FXML
    private TabPane tpGames;

    @FXML
    public void searchInformation(ActionEvent event) {
        String requestedGame = inputRequested.getText();
        inputRequested.requestFocus();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("results.fxml"));
        GameController gameController = new GameController(requestedGame);
        loader.setController(gameController);
        try {
            VBox gameBox = loader.load();
            tpGames.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
            tpGames.getTabs().add(new Tab(requestedGame, gameBox));
        }catch (IOException ioe) {
            ioe.printStackTrace();

        }
    }

    @FXML
    public void searchGameList(ActionEvent event) {
        String requestedGame = inputRequested.getText();
        inputRequested.requestFocus();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("results.fxml"));
        GameListController gameListController = new GameListController(requestedGame);
        loader.setController(gameListController);
        try {
            VBox gameListBox = loader.load();
            tpGames.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
            tpGames.getTabs().add(new Tab(requestedGame, gameListBox));
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void searchDeal(ActionEvent event) {
        String requestedDeal = inputRequested.getText();
        inputRequested.requestFocus();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("results.fxml"));
        DealsController dealsController = new DealsController(requestedDeal);
        loader.setController(dealsController);
        try {
            VBox dealBox = loader.load();
            tpGames.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
            tpGames.getTabs().add(new Tab(requestedDeal, dealBox));
        } catch (IOException ioe) {
           ioe.printStackTrace();
        }

    }

    @FXML
    public void searchDealsList(ActionEvent event) {
        String requestedDeal = inputRequested.getText();
        inputRequested.requestFocus();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("results.fxml"));
        DealListController dealListController = new DealListController(requestedDeal);
        loader.setController(dealListController);
        try {
            VBox dealListBox = loader.load();
            tpGames.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
            tpGames.getTabs().add(new Tab(requestedDeal, dealListBox));
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
