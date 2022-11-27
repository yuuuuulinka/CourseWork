package com.example.homedeviceskursova;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class AddDevicePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label AddDeviceLabel;

    @FXML
    private VBox AddDevicePageVBox;

    @FXML
    private AnchorPane AddDeviceStage;

    @FXML
    private Button AddLargeDeviceButton;

    @FXML
    private Button AddSmallDeviceButton;

    @FXML
    private Button MenuButton;

    @FXML
    private void MenuButtonClick() throws IOException {
        Stage stage = (Stage) MenuButton.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void AddSmallDeviceButtonClick() throws IOException {
        Stage stage = (Stage) AddSmallDeviceButton.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("AddSmallDevice.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void AddLargeDeviceButtonClick() throws IOException {
        Stage stage = (Stage) AddLargeDeviceButton.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("AddLargeDevice.fxml"));
        stage.getScene().setRoot(newRoot);
    }

}
