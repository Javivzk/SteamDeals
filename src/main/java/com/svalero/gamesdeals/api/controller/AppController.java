package com.svalero.gamesdeals.api.controller;

import com.opencsv.CSVWriter;
import com.svalero.gamesdeals.api.model.GamesInformationList;
import com.svalero.gamesdeals.api.task.GameTitleTask;
import com.svalero.gamesdeals.api.task.GamesListTask;
import io.reactivex.functions.Consumer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppController {

    @FXML
    private TextField inputGameID;
    @FXML
    private TextField inputGameTitle;
    @FXML
    private Button btSearch;
    @FXML
    private Button btDeleteList;
    @FXML
    private Button btDeleteGame;
    @FXML
    private Button btExport;
    @FXML
    private TextField deleteInputList;
    @FXML
    private TextField deleteInputGame;
    @FXML
    private TextArea gameInformationResult;

    private GameTitleTask gamesTask;

    private GamesListTask gamesListTask;

    private List<String> gamesList;

    private List<String> gameInformation;

    private List<String> dealsList;

    private String lastSearch;


    @FXML
    public void exportCSV(ActionEvent event) {
        File outputFile = new File(System.getProperty("user.dir") + System.getProperty("file.separator")
                +  this.lastSearch + "gameslist.csv");

        try {
            FileWriter writer = new FileWriter(outputFile);
            CSVWriter csvWriter = new CSVWriter(writer);
            List<String[]> data = new ArrayList<String[]>();
            for (String gamesInformationList  :this.gamesList) {
                data.add(new String[] {gamesInformationList});
            }
            csvWriter.writeAll(data);
            csvWriter.close();
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    @FXML
    public void searchGameList(ActionEvent event) {
        this.gamesList = new ArrayList<String>();
        String requestedGame = this.inputGameTitle.getText();
        this.lastSearch = requestedGame;
        this.inputGameTitle.clear();
        this.inputGameTitle.requestFocus();
        this.gameInformationResult.setText("");

        Consumer<List<GamesInformationList>> userGamesList = (gamesInformationList) -> {
            String previousText;
            previousText = gameInformationResult.getText() + "\n";
            Thread.sleep(10);
            this.gameInformationResult.setText(previousText + gamesInformationList);
            this.gamesList.add(gamesInformationList.toString());

        };

        this.gamesListTask = new GamesListTask(requestedGame, userGamesList);
        new Thread(gamesListTask).start();
    }

    @FXML
    public void deleteGameList(ActionEvent event) {
        int gameListIndex = Integer.parseInt(deleteInputList.getText());
        this.gamesList.remove(gameListIndex);
        this.gameInformationResult.setText("");
        for (String gamesInformationList: this.gamesList) {
            this.gameInformationResult.setText(gameInformationResult.getText() + "\n" + gamesInformationList);
        }
    }

    @FXML
    public void searchInformation(ActionEvent event) {
        gameInformation =  new ArrayList<String>();
        String requestedGame = this.inputGameID.getText();
        this.inputGameID.clear();
        this.inputGameID.requestFocus();
        this.gameInformationResult.setText("");

        Consumer<String> userGameInfo = (gameInformation) -> {
            this.gameInformationResult.setText(gameInformation);
            this.gameInformation.add(gameInformation);
        };

        this.gamesTask = new GameTitleTask(requestedGame,userGameInfo);
        new Thread(gamesTask).start();
    }

    @FXML
    public void deleteGame(ActionEvent event) {
        int informationListIndex  = Integer.parseInt(deleteInputGame.getText());
        this.gameInformation.remove(informationListIndex );
        this.gameInformationResult.setText("");
        for (String gameInformation: this.gameInformation) {
            this.gameInformationResult.setText(gameInformationResult.getText());
        }
    }

    @FXML
    public void deleteInformation(ActionEvent event) {

    }

    @FXML
    public void searchDeal(ActionEvent event) {
        dealsList =  new ArrayList<String>();
        String requestedGame = this.inputGameID.getText();
        this.inputGameID.clear();
        this.inputGameID.requestFocus();
        this.gameInformationResult.setText("");

        Consumer<String> userGameInfo = (gameInformation) -> {
            this.gameInformationResult.setText(gameInformation);
            this.gameInformation.add(gameInformation);
        };

        this.gamesTask = new GameTitleTask(requestedGame,userGameInfo);
        new Thread(gamesTask).start();
    }
}
