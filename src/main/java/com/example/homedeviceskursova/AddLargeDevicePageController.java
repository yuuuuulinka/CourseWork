package com.example.homedeviceskursova;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddLargeDevicePageController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AddLargeDevicePage;

    @FXML
    private HBox HBoxAllButtonsAddLargeDevice;

    @FXML
    private Label LabelAddLarge;

    @FXML
    private Label LabelLargeDevice;

    @FXML
    private Button MenuLargeDevice;

    @FXML
    private Button ReturnLargeDevice;

    @FXML
    private VBox VBoxAddLargeDevicePage;

    @FXML
    private VBox VBoxLabelAddLargeDevicePage;

    @FXML
    private VBox VBoxWithReturnButtons;

    @FXML
    private VBox VBoxWithoutMenuButtons;

    @FXML
    private Button WithECClassButton;

    @FXML
    private Button WithoutECClassButton;

    @FXML
    private void ReturnLargeDeviceClick() throws IOException {
        Stage stage = (Stage) ReturnLargeDevice.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("AddDevice.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void MenuLargeDeviceClick() throws IOException {
        Stage stage = (Stage) MenuLargeDevice.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void WithECClassButtonClick() throws IOException {
        Stage stage = (Stage) WithECClassButton.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("AddLargeDeviceWithECClass.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void WithoutECClassButtonClick() throws IOException {
        Stage stage = (Stage) WithoutECClassButton.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("AddLargeDeviceWithoutECClass.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    void initialize() {
    }

}
