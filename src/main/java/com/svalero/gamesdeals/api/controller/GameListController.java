package com.svalero.gamesdeals.api.controller;

import com.opencsv.CSVWriter;
import com.svalero.gamesdeals.api.task.GamesListTask;
import com.svalero.gamesdeals.api.util.ZipFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameListController {
    @FXML
    private Button btDelete;
    @FXML
    private Button btExport;
    @FXML
    private ListView<String> resultsListView;
    @FXML
    private Label lbStatus;
    @FXML
    private TextField deleteInput;
    @FXML
    private ProgressBar progressBar;
    private List<String> gamesList;
    private GamesListTask gamesListTask;
    private ObservableList<String> results;
    private String requestedGame;

    public GameListController(String requestedGame) {
        this.requestedGame = requestedGame;
        this.results = FXCollections.observableArrayList();
    }

    public void initialize() {
        this.results.clear();
        this.resultsListView.setItems(this.results); // establece el adaptador de lista
        GamesListTask gamesListTask = new GamesListTask(requestedGame, this.results,progressBar);
        gamesListTask.messageProperty().addListener((observableValue, oldValue, newValue) -> this.lbStatus.setText(newValue));
        new Thread(gamesListTask).start();
    }

    @FXML
    public void deleteEntry(ActionEvent event) {
        int gameListIndex = Integer.parseInt(deleteInput.getText());
        this.results.remove(gameListIndex);
    }

    @FXML
    public void exportCSV(ActionEvent event) {
        String outputFileName = System.getProperty("user.dir") + System.getProperty("file.separator")
                + this.requestedGame + "gameslist.csv";

        File outputFile = new File(outputFileName);
        try {
            FileWriter writer = new FileWriter(outputFile);
            CSVWriter csvWriter = new CSVWriter(writer);
            List<String[]> data = new ArrayList<String[]>();
            for (String gamesInformationList : this.results){
                data.add(new String[] {gamesInformationList});
            }
            csvWriter.writeAll(data);
            csvWriter.close();
            ZipFile.createZipFile(outputFileName);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
