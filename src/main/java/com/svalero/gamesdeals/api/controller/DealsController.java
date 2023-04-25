package com.svalero.gamesdeals.api.controller;

import com.opencsv.CSVWriter;
import com.svalero.gamesdeals.api.model.DealGameInfo;
import com.svalero.gamesdeals.api.model.DealResponse;
import com.svalero.gamesdeals.api.task.DealDetailsTask;
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

public class DealsController {
    @FXML
    private Button btDelete;
    @FXML
    private Button btExport;
    @FXML
    private ListView<DealGameInfo> resultsListView;
    @FXML
    private Label lbStatus;
    @FXML
    private TextField deleteInput;
    private DealDetailsTask dealDetailsTask;
    private ObservableList<DealGameInfo> results;
    private String requestedDeal;
    public DealsController(String requestedDeal) {
        this.requestedDeal = requestedDeal;
        this.results = FXCollections.observableArrayList();

    }
    @FXML
    public void initialize(){
        this.results.clear();
        this.resultsListView.setItems(this.results);
        this.dealDetailsTask = new DealDetailsTask(requestedDeal, this.results);
        this.dealDetailsTask.messageProperty().addListener((observableValue, oldValue, newValue) -> this.lbStatus.setText(newValue) ) ;
        new Thread(dealDetailsTask).start();
    }

    @FXML
    public void exportCSV(ActionEvent event) {
        String outputFileName = System.getProperty("user.dir") + System.getProperty("file.separator")
                +  this.requestedDeal + "dealbyid.csv";

        File outputFile = new File(outputFileName);

        try {
            FileWriter writer = new FileWriter(outputFile);
            CSVWriter csvWriter = new CSVWriter(writer);
            List<String[]> data = new ArrayList<String[]>();
            for (DealGameInfo dealGameInfo  :this.results) {
                data.add(new String[] {String.valueOf(dealGameInfo)});
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
