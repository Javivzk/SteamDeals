package com.svalero.gamesdeals.api.controller;

import com.opencsv.CSVWriter;
import com.svalero.gamesdeals.api.task.GameIDTask;
import com.svalero.gamesdeals.api.util.ZipFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameController {
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
    private GameIDTask gamesTask;
    private ObservableList<String> results;
    private String requestedGame;
    public GameController(String requestedGame) {
        this.requestedGame = requestedGame;
        this.results = FXCollections.observableArrayList();

    }
    @FXML
    public void initialize(){
        this.results.clear();
        this.resultsListView.setItems(this.results);
        this.gamesTask = new GameIDTask(requestedGame, this.results);
        this.gamesTask.messageProperty().addListener((observableValue, oldValue, newValue) -> this.lbStatus.setText(newValue) ) ;
        new Thread(gamesTask).start();
    }

    @FXML
    public void exportCSV(ActionEvent event) {
        String outputFileName = System.getProperty("user.dir") + System.getProperty("file.separator")
                +  this.requestedGame + "gamebyid.csv";

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
    public void deleteEntry(ActionEvent event) {
        int informationListIndex  = Integer.parseInt(deleteInput.getText());
        this.results.remove(informationListIndex );
    }


}
