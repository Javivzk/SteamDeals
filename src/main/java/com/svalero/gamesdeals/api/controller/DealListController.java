package com.svalero.gamesdeals.api.controller;

import com.opencsv.CSVWriter;
import com.svalero.gamesdeals.api.model.DealGameInfo;
import com.svalero.gamesdeals.api.model.DealsInformationList;
import com.svalero.gamesdeals.api.task.DealsListTask;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DealListController {

    @FXML
    private Button btDelete;
    @FXML
    private Button btExport;
    @FXML
    private Button btFilter;
    @FXML
    private ListView<String> resultsListView;
    @FXML
    private Label lbStatus;
    @FXML
    private TextField deleteInput;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private TextField searchField;
    private List<String> dealsList;
    private DealsListTask dealsListTask;
    private ObservableList<String> results; // cambiar tipo de ObservableList
    private String requestedDeal;

    public DealListController(String requestedDeal) {
        this.requestedDeal = requestedDeal;
        this.results = FXCollections.observableArrayList(); // cambiar tipo de ObservableList
    }

    public void initialize() {
        this.results.clear();
        this.resultsListView.setItems(this.results); // establece el adaptador de lista
        DealsListTask dealsListTask = new DealsListTask(requestedDeal, this.results,progressBar);
        dealsListTask.messageProperty().addListener((observableValue, oldValue, newValue) -> this.lbStatus.setText(newValue));
        new Thread(dealsListTask).start();
    }

    public void setSearchField(TextField searchField) {
        this.searchField = searchField;
    }

    @FXML
    public void deleteEntry(ActionEvent event) {
        int dealsList = Integer.parseInt(deleteInput.getText());
        this.results.remove(dealsList);
    }


    @FXML
    public void exportCSV(ActionEvent event) {
        String outputFileName = System.getProperty("user.dir") + System.getProperty("file.separator")
                + this.requestedDeal + "dealslist.csv";

        File outputFile = new File(outputFileName);
        try {
            FileWriter writer = new FileWriter(outputFile);
            CSVWriter csvWriter = new CSVWriter(writer);
            List<String[]> data = new ArrayList<String[]>();
            for (String dealsInformationList : this.results){
                data.add(new String[] {dealsInformationList});
            }
            csvWriter.writeAll(data);
            csvWriter.close();
            ZipFile.createZipFile(outputFileName);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void filterGames() {
//

    }

}






