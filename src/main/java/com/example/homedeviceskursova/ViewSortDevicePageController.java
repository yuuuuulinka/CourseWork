package com.example.homedeviceskursova;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewSortDevicePageController {
    @FXML
    private Button MenuViewSortPage;

    @FXML
    private Text TextViewSortDevices;

    @FXML
    private VBox VBoxAllButtonsViewSortPage;

    @FXML
    private VBox VBoxAllViewSortPage;

    @FXML
    private Button ViewAllDevices;

    @FXML
    private Button ViewLDDWithEEClass;

    @FXML
    private Button ViewLargeDevices;

    @FXML
    private Button ViewSmallDevices;

    @FXML
    private AnchorPane ViewSortStage;

    @FXML
    private void MenuViewSortPageClick() throws IOException {
        Stage stage = (Stage) MenuViewSortPage.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void ViewAllDevicesClick() throws IOException {
        Stage stage = (Stage) ViewAllDevices.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("ViewSortAllDevicePage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void ViewSmallDevicesClick() throws IOException {
        Stage stage = (Stage) ViewSmallDevices.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("ViewSortSmallDevicesPage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void ViewLargeDevicesClick() throws IOException {
        Stage stage = (Stage) ViewLargeDevices.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("ViewSortLargeDevicesPage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void ViewLDDWithEEClassClick() throws IOException {
        Stage stage = (Stage) ViewLDDWithEEClass.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("ViewSortLDDWithEEClassDevicesPage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

}
