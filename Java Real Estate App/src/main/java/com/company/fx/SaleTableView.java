package com.company.fx;

import com.company.java.HouseForSale;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

//Custom table class displaying house for sale information
public class SaleTableView extends TableView<HouseForSale> {

    public SaleTableView() {

        //Table Columns are created
        TableColumn<HouseForSale, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<HouseForSale, Integer> roomCountColumn = new TableColumn<>("Room Count");
        roomCountColumn.setCellValueFactory(new PropertyValueFactory<>("roomCount"));

        TableColumn<HouseForSale, String> provinceColumn = new TableColumn<>("Province");
        provinceColumn.setCellValueFactory(new PropertyValueFactory<>("province"));

        TableColumn<HouseForSale, String> districtColumn = new TableColumn<>("District");
        districtColumn.setCellValueFactory(new PropertyValueFactory<>("district"));

        TableColumn<HouseForSale, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<HouseForSale, Date> listingDateColumn = new TableColumn<>("Listing Date");
        listingDateColumn.setCellValueFactory(new PropertyValueFactory<>("listingDate"));

        TableColumn<HouseForSale, Integer> areaColumn = new TableColumn<>("Area");
        areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));

        TableColumn<HouseForSale, String> purchasePriceColumn = new TableColumn<>("Purchase Price");
        purchasePriceColumn.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));

        TableColumn<HouseForSale, HouseForSale> deleteColumn = new TableColumn<>("Delete");
        TableColumn<HouseForSale, HouseForSale> editColumn = new TableColumn<>("Edit");


        //Edit button is adds to the edit column
        editColumn.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        editColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");

            @Override
            protected void updateItem(HouseForSale house, boolean empty) {
                super.updateItem(house, empty);

                if (house == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(editButton);
                //Here a new window opens and editing information is received from the user.
                editButton.setOnAction(
                        event -> {
                            Stage stage = new Stage();
                            stage.setWidth(350);
                            stage.setHeight(270);
                            stage.setResizable(false);
                            VBox vBox = new VBox();

                            TextField idTextField = new TextField();
                            idTextField.setText(house.getId().toString());
                            TextField roomCountTextField = new TextField();
                            roomCountTextField.setText(house.getRoomCount().toString());
                            TextField provinceTextField = new TextField();
                            provinceTextField.setText(house.getProvince());
                            TextField districtTextField = new TextField();
                            districtTextField.setText(house.getDistrict());
                            TextField typeTextField = new TextField();
                            typeTextField.setText(house.getType());
                            DatePicker listingDatePicker = new DatePicker();
                            listingDatePicker.setValue(house.getListingDate());
                            TextField areaTextField = new TextField();
                            areaTextField.setText(house.getArea().toString());
                            TextField purchasePriceTextFiled = new TextField();
                            purchasePriceTextFiled.setText(house.getPurchasePrice());

                            Button saveButton = new Button("Save");
                            saveButton.setMaxWidth(999999999);

                            //Then, when the save button in the window is pressed, the residence information changes with textField values
                            saveButton.setOnAction(action -> {

                                house.setId(Integer.valueOf(idTextField.getText()));
                                house.setRoomCount(Integer.valueOf(roomCountTextField.getText()));
                                house.setProvince(provinceTextField.getText());
                                house.setDistrict(districtTextField.getText());
                                house.setType(typeTextField.getText());
                                house.setListingDate(listingDatePicker.getValue());
                                house.setArea(Double.valueOf(areaTextField.getText()));
                                house.setPurchasePrice(purchasePriceTextFiled.getText());
                                SaleTableView.this.refresh();
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
                                    new HBox(new Label("Purchase Price (String): "), purchasePriceTextFiled)));

                            for (HBox hbox: hBoxes
                            ) {

                                Label label = (Label) hbox.getChildren().get(0);
                                label.setMinWidth(200);
                            }

                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.initOwner(this.getScene().getWindow());

                            vBox.getChildren().addAll(hBoxes);
                            vBox.getChildren().add(saveButton);
                            stage.setScene(new Scene(vBox));
                            stage.show();
                        }
                );
            }
        });

        //Delete button is adds to the edit column
        deleteColumn.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );

        deleteColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(HouseForSale house, boolean empty) {
                super.updateItem(house, empty);

                if (house == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                //When clicked to "delete" button, house is removed
                deleteButton.setOnAction(event ->
                        getTableView().getItems().remove(house)
                );
            }
        });

        //Columns are adds to table
        this.getColumns().addAll(new ArrayList<>(Arrays.asList(idColumn, roomCountColumn,
                provinceColumn, districtColumn, typeColumn,
                listingDateColumn, areaColumn, purchasePriceColumn,
                editColumn, deleteColumn)));

    }

    //This function is add item to table
    public void addItem(HouseForSale house) {

        this.getItems().add(house);
    }
}

