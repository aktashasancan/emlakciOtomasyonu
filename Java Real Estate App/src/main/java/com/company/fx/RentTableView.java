package com.company.fx;

import com.company.java.HouseForRent;
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

//The same operations as in "SaleTableView" apply here for custom class "RentTableView"
public class RentTableView extends TableView<HouseForRent> {

    public RentTableView() {

        TableColumn<HouseForRent, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<HouseForRent, Integer> roomCountColumn = new TableColumn<>("Room Count");
        roomCountColumn.setCellValueFactory(new PropertyValueFactory<>("roomCount"));

        TableColumn<HouseForRent, String> provinceColumn = new TableColumn<>("Province");
        provinceColumn.setCellValueFactory(new PropertyValueFactory<>("province"));

        TableColumn<HouseForRent, String> districtColumn = new TableColumn<>("District");
        districtColumn.setCellValueFactory(new PropertyValueFactory<>("district"));

        TableColumn<HouseForRent, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<HouseForRent, Date> listingDateColumn = new TableColumn<>("Listing Date");
        listingDateColumn.setCellValueFactory(new PropertyValueFactory<>("listingDate"));

        TableColumn<HouseForRent, Integer> areaColumn = new TableColumn<>("Area");
        areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));

        TableColumn<HouseForRent, String> rentColumn = new TableColumn<>("Rent");
        rentColumn.setCellValueFactory(new PropertyValueFactory<>("rent"));


        TableColumn<HouseForRent, HouseForRent> deleteColumn = new TableColumn<>("Delete");
        TableColumn<HouseForRent, HouseForRent> editColumn = new TableColumn<>("Edit");


        editColumn.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );

        editColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");

            @Override
            protected void updateItem(HouseForRent house, boolean empty) {
                super.updateItem(house, empty);

                if (house == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(editButton);
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
                            TextField rentTextField = new TextField();
                            rentTextField.setText(house.getRent());

                            Button saveButton = new Button("Save");
                            saveButton.setMaxWidth(999999999);
                            saveButton.setOnAction(action -> {

                                house.setId(Integer.valueOf(idTextField.getText()));
                                house.setRoomCount(Integer.valueOf(roomCountTextField.getText()));
                                house.setProvince(provinceTextField.getText());
                                house.setDistrict(districtTextField.getText());
                                house.setType(typeTextField.getText());
                                house.setListingDate(listingDatePicker.getValue());
                                house.setArea(Double.valueOf(areaTextField.getText()));
                                house.setRent(rentTextField.getText());
                                RentTableView.this.refresh();
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
                            stage.showAndWait();
                        }
                );
            }
        });

        deleteColumn.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );

        deleteColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(HouseForRent house, boolean empty) {
                super.updateItem(house, empty);

                if (house == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(event ->
                        getTableView().getItems().remove(house)
                );
            }
        });

        this.getColumns().addAll(Arrays.asList(idColumn, roomCountColumn,
                provinceColumn, districtColumn, typeColumn,
                listingDateColumn, areaColumn, rentColumn,
                editColumn, deleteColumn));
    }

    public void addItem(HouseForRent house) {

        this.getItems().add(house);
    }
}
