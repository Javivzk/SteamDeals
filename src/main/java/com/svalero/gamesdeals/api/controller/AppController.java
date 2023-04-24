package com.svalero.gamesdeals.api.controller;

import com.opencsv.CSVWriter;
import com.svalero.gamesdeals.api.model.GamesInformationList;
import com.svalero.gamesdeals.api.task.GameTitleTask;
import com.svalero.gamesdeals.api.task.GamesListTask;
import com.svalero.gamesdeals.api.util.ZipFile;
import io.reactivex.functions.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
    private ListView resultsListView;

    private ObservableList<String> results;

    private GameTitleTask gamesTask;

    private GamesListTask gamesListTask;

    private List<String> gamesList;


    private List<String> dealsList;

    private String lastSearch;


    @FXML
    public void initialize(){
        results = FXCollections.observableArrayList();
        this.resultsListView.setItems(this.results);
    }

    @FXML
    public void exportCSV(ActionEvent event) {
        String outputFileName = System.getProperty("user.dir") + System.getProperty("file.separator")
                +  this.lastSearch + "gameslist.csv";

        File outputFile = new File(outputFileName);

        try {
            FileWriter writer = new FileWriter(outputFile);
            CSVWriter csvWriter = new CSVWriter(writer);
            List<String[]> data = new ArrayList<String[]>();
            for (String gamesInformationList  :this.results) {
                data.add(new String[] {gamesInformationList});
            }
            csvWriter.writeAll(data);
            csvWriter.close();
            ZipFile.createZipFile(outputFileName);
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

        Consumer<List<GamesInformationList>> userGamesList = (gamesInformationList) -> {
            String previousText;
//            previousText = gameInformationResult.getText() + "\n";
//            Thread.sleep(10);
//            this.gameInformationResult.setText(previousText + gamesInformationList);
//            this.gamesList.add(gamesInformationList.toString());

        };

        this.gamesListTask = new GamesListTask(requestedGame, userGamesList);
        new Thread(gamesListTask).start();
    }

    @FXML
    public void deleteGameList(ActionEvent event) {
        int gameListIndex = Integer.parseInt(deleteInputList.getText());
        this.gamesList.remove(gameListIndex);
//        this.gameInformationResult.setText("");
//        for (String gamesInformationList: this.gamesList) {
//            this.gameInformationResult.setText(gameInformationResult.getText() + "\n" + gamesInformationList);
//        }
    }

    @FXML
    public void searchInformation(ActionEvent event) {
        String requestedGame = this.inputGameID.getText();
        this.lastSearch = requestedGame;
        inputGameID.clear();
        inputGameID.requestFocus();

        Consumer<String> userGameInfo = (gameInformation) -> {
            this.results.add(gameInformation);
        };

        this.gamesTask = new GameTitleTask(requestedGame,userGameInfo);
        new Thread(gamesTask).start();
    }

    @FXML
    public void deleteGame(ActionEvent event) {
//        int informationListIndex  = Integer.parseInt(deleteInputGame.getText());
//        this.gameInformation.remove(informationListIndex );
//        this.gameInformationResult.setText("");
//        for (String gameInformation: this.gameInformation) {
//            this.gameInformationResult.setText(gameInformationResult.getText());
//        }
    }

    @FXML
    public void deleteInformation(ActionEvent event) {

    }

//    @FXML
//    public void searchDeal(ActionEvent event) {
//        dealsList =  new ArrayList<String>();
//        String requestedGame = this.inputGameID.getText();
//        this.inputGameID.clear();
//        this.inputGameID.requestFocus();
//        this.gameInformationResult.setText("");
//
//        Consumer<String> userGameInfo = (gameInformation) -> {
//            this.gameInformationResult.setText(gameInformation);
//            this.gameInformation.add(gameInformation);
//        };
//
//        this.gamesTask = new GameTitleTask(requestedGame,userGameInfo);
//        new Thread(gamesTask).start();
//    }
}
