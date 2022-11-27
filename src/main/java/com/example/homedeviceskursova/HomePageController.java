package com.example.homedeviceskursova;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePageController {
    @FXML
    private Button AdButton;

    @FXML
    private Button CalcUsingPowerButton;

    @FXML
    private Button ChangeStateButton;

    @FXML
    private Button DeleteDeviceButton;

    @FXML
    private Button FindDeviceButton;

    @FXML
    private HBox HBoxAllButtonsHomePage;

    @FXML
    private VBox HomePageVBox;

    @FXML
    private AnchorPane HomeStage;

    @FXML
    private Label MenuLabel;

    @FXML
    private Label TextHomeDevices;

    @FXML
    private VBox VBoxAddChangeCalculButtons;

    @FXML
    private VBox VBoxDeleteFindViewButtons;

    @FXML
    private Button ViewSortButton;

    @FXML
    private void AddButtonClick() throws IOException {
        Stage stage = (Stage) AdButton.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("AddDevice.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void DeleteDeviceButtonClick() throws IOException {
        Stage stage = (Stage) DeleteDeviceButton.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("DeleteDevice.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void ViewSortButtonClick() throws IOException {
        Stage stage = (Stage) ViewSortButton.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("ViewSortDevicePage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void FindDeviceButtonClick() throws IOException {
        Stage stage = (Stage) FindDeviceButton.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("FindDevicePage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void CalcUsingPowerButtonClick() throws IOException {
        Stage stage = (Stage) CalcUsingPowerButton.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("CalculatePage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void ChangeStateButtonClick() throws IOException {
        Stage stage = (Stage) ChangeStateButton.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("ChangeStatePage.fxml"));
        stage.getScene().setRoot(newRoot);
    }
}
