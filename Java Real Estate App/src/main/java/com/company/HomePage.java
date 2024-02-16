package com.company;

import com.company.fx.RentTableView;
import com.company.fx.SaleTableView;
import com.company.java.HouseForRent;
import com.company.java.HouseForSale;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

//This class is Controller of homePage.fxml
public class HomePage implements Initializable {
    public TextField rentSearchTextField, saleSearchTextField;
    public RentTableView rentTableView;
    public SaleTableView saleTableView;

    //The function that will work when the "Add Estate" button in the Sale section is clicked
    public void saleAddEstateButtonOnClickListener(ActionEvent actionEvent) {

        //Here, a new window opens and the information of the new residence to be added is received from the user.
        Stage stage = new Stage();
        stage.setWidth(350);
        stage.setHeight(270);
        stage.setResizable(false);
        VBox vBox = new VBox();

        stage.initOwner(saleTableView.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);


        TextField idTextField = new TextField();
        TextField roomCountTextField = new TextField();
        TextField provinceTextField = new TextField();
        TextField districtTextField = new TextField();
        TextField typeTextField = new TextField();
        DatePicker listingDatePicker = new DatePicker();
        TextField areaTextField = new TextField();
        TextField purchasePriceTextFiled = new TextField();

        Button saveButton = new Button("Save");
        saveButton.setMaxWidth(999999999);

        //Then, when the save button in the window is pressed, the residence information is added to the tableview
        saveButton.setOnAction(action -> {

            HouseForSale house = new HouseForSale(Integer.valueOf(idTextField.getText()),
                    Integer.valueOf(roomCountTextField.getText()),
                    provinceTextField.getText(),
                    districtTextField.getText(),
                    typeTextField.getText(),
                    listingDatePicker.getValue(),
                    Double.valueOf(areaTextField.getText()),
                    purchasePriceTextFiled.getText());

            saleTableView.addItem(house);


            stage.close();
        });

        //The page layout set
        ArrayList<HBox> hBoxes = new ArrayList<>(Arrays.asList(new HBox(new Label("ID (Integer): "), idTextField),
                new HBox(new Label("Room count (Integer): "), roomCountTextField),
                new HBox(new Label("Province (String): "), provinceTextField),
                new HBox(new Label("District (String): "), districtTextField),
                new HBox(new Label("Type (String): "), typeTextField),
                new HBox(new Label("Listing date: "), listingDatePicker),
                new HBox(new Label("Area (Double & Integer): "), areaTextField),
                new HBox(new Label("Purchase Price: "), purchasePriceTextFiled)));

        for (HBox hbox: hBoxes) {

            Label label = (Label) hbox.getChildren().get(0);
            label.setMinWidth(200);
        }


        vBox.getChildren().addAll(hBoxes);
        vBox.getChildren().add(saveButton);

        stage.setScene(new Scene(vBox));
        stage.show();
    }

    //The only difference from above is that the operations are performed for the "rentTableView"
    public void rentAddEstateButtonOnActionListener(ActionEvent actionEvent) {

        Stage stage = new Stage();
        stage.setWidth(350);
        stage.setHeight(270);
        stage.setResizable(false);
        VBox vBox = new VBox();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(rentTableView.getScene().getWindow());

        TextField idTextField = new TextField();
        TextField roomCountTextField = new TextField();
        TextField provinceTextField = new TextField();
        TextField districtTextField = new TextField();
        TextField typeTextField = new TextField();
        DatePicker listingDatePicker = new DatePicker();
        TextField areaTextField = new TextField();
        TextField rentTextField = new TextField();

        Button saveButton = new Button("Save");
        saveButton.setMaxWidth(999999999);
        saveButton.setOnAction(action -> {

            HouseForRent house = new HouseForRent(Integer.valueOf(idTextField.getText()),
                    Integer.valueOf(roomCountTextField.getText()),
                    provinceTextField.getText(),
                    districtTextField.getText(),
                    typeTextField.getText(),
                    listingDatePicker.getValue(),
                    Double.valueOf(areaTextField.getText()),
                    rentTextField.getText());

            rentTableView.addItem(house);


            stage.close();
        });

        ArrayList<HBox> hBoxes = new ArrayList<>(Arrays.asList(new HBox(new Label("ID (Integer): "), idTextField),
                new HBox(new Label("Room count (Integer): "), roomCountTextField),
                new HBox(new Label("Province (String): "), provinceTextField),
                new HBox(new Label("District (String): "), districtTextField),
                new HBox(new Label("Type (String): "), typeTextField),
                new HBox(new Label("Listing date: "), listingDatePicker),
                new HBox(new Label("Area (Double & Integer): "), areaTextField),
                new HBox(new Label("Rent (String): "), rentTextField)));

        for (HBox hbox: hBoxes) {

            Label label = (Label) hbox.getChildren().get(0);
            label.setMinWidth(200);
        }


        vBox.getChildren().addAll(hBoxes);
        vBox.getChildren().add(saveButton);

        stage.setScene(new Scene(vBox));
        stage.show();
    }

    //After login,"homePage.fxml" adds to stage and this function starts
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //The search filter set for rentTableView
        ObservableList<HouseForRent> rentTableViewdata =  rentTableView.getItems();
        rentSearchTextField.textProperty().addListener((ObservableValue<? extends String> observable,
                                                        String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                rentTableView.setItems(rentTableViewdata);
            }
            String value = newValue.toLowerCase();
            ObservableList<HouseForRent> subentries = FXCollections.observableArrayList();

            long count = rentTableView.getColumns().size();
            for (int i = 0; i < rentTableView.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + rentTableView.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(rentTableView.getItems().get(i));
                        break;
                    }
                }
            }
            rentTableView.setItems(subentries);
        });

        //The search filter set for saleTableView
        ObservableList<HouseForSale> saleTableViewdata =  saleTableView.getItems();
        saleSearchTextField.textProperty().addListener((ObservableValue<? extends String> observable,
                                                        String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                saleTableView.setItems(saleTableViewdata);
            }
            String value = newValue.toLowerCase();
            ObservableList<HouseForSale> subentries = FXCollections.observableArrayList();

            long count = saleTableView.getColumns().size();
            for (int i = 0; i < saleTableView.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + saleTableView.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(saleTableView.getItems().get(i));
                        break;
                    }
                }
            }
            saleTableView.setItems(subentries);
        });
    }

    //This function runs when the rentImport button is clicked.
    public void rentImportButtonOnActionListener(ActionEvent actionEvent) {

        //A file is selected with the file explorer thanks to getOpenFile function of our FileDialog static class
        File file = FileDialog.getOpenFile();
        try {
            if (file != null) { //If user select any csv file
                //RentTableView's items are cleans and items of new csv file are added to rentTableView
                rentTableView.getItems().clear();
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] list = line.split(",");

                    HouseForRent house = new HouseForRent(Integer.valueOf(list[0]), Integer.valueOf(list[1]),
                            list[2], list[3], list[4], LocalDate.of(Integer.parseInt(list[5].split("-")[0]),
                            Integer.parseInt(list[5].split("-")[1]), Integer.parseInt(list[5].split("-")[2])),
                            Double.parseDouble(list[6]), list[7]);

                    rentTableView.addItem(house);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //The same process as above is done for "saleTableView"
    public void saleImportButtonOnActionListener(ActionEvent actionEvent) {

        File file = FileDialog.getOpenFile();
        try {
            if (file != null) {
                saleTableView.getItems().clear();
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] list = line.split(",");

                    HouseForSale house = new HouseForSale(Integer.valueOf(list[0]), Integer.valueOf(list[1]),
                            list[2], list[3], list[4], LocalDate.of(Integer.parseInt(list[5].split("-")[0]),
                            Integer.parseInt(list[5].split("-")[1]), Integer.parseInt(list[5].split("-")[2])),
                            Double.parseDouble(list[6]), list[7]);

                    saleTableView.addItem(house);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //This function runs when the rentExport button is clicked.
    public void rentExportButtonOnActionListener(ActionEvent actionEvent) {

        //A file is selected with the file explorer thanks to getOpenFile function of our FileDialog static class
        String path = FileDialog.getSavePath();

        if (path != null) { //If user select any csv file

            try {
                FileWriter writer = new FileWriter(path);

                //Table items are written to the selected file
                for (HouseForRent house: rentTableView.getItems()
                ) {

                    StringBuilder stringBuilder = new StringBuilder();

                    stringBuilder.append(house.getId()).append(",");
                    stringBuilder.append(house.getRoomCount()).append(",");
                    stringBuilder.append(house.getProvince()).append(",");
                    stringBuilder.append(house.getDistrict()).append(",");
                    stringBuilder.append(house.getType()).append(",");
                    stringBuilder.append(house.getListingDate()).append(",");
                    stringBuilder.append(house.getArea()).append(",");
                    stringBuilder.append(house.getRent());

                    writer.write(stringBuilder.toString()+"\n");
                }

                writer.flush();
                writer.close();

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }


    //The same process as above is done for "saleTableView"
    public void saleExportButtonOnActionListener(ActionEvent actionEvent) {

        String path = FileDialog.getSavePath();

        if (path != null) {
            try {
                FileWriter writer = new FileWriter(path);

                for (HouseForSale house: saleTableView.getItems()
                ) {
                    StringBuilder stringBuilder = new StringBuilder();

                    stringBuilder.append(house.getId()).append(",");
                    stringBuilder.append(house.getRoomCount()).append(",");
                    stringBuilder.append(house.getProvince()).append(",");
                    stringBuilder.append(house.getDistrict()).append(",");
                    stringBuilder.append(house.getType()).append(",");
                    stringBuilder.append(house.getListingDate()).append(",");
                    stringBuilder.append(house.getArea()).append(",");
                    stringBuilder.append(house.getPurchasePrice());

                    writer.write(stringBuilder.toString()+"\n");
                }

                writer.flush();
                writer.close();

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    //File Selection operations are done in this static class
    public static class FileDialog {

        //Returns a save path with "SaveDialog"
        public static String getSavePath() {

            FileChooser chooser = new FileChooser();

            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(
                    "CSV Files", "*.csv"));
            File file = chooser.showSaveDialog(null);

            return file.getPath();
        }

        //Returns a open path with "OpenDialog"
        public static File getOpenFile() {

            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(
                    "CSV Files", "*.csv"));

            return chooser.showOpenDialog(null);
        }
    }
}
