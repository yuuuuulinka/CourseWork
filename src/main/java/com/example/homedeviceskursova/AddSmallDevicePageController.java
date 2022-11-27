package com.example.homedeviceskursova;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.example.homedeviceskursova.dbconnection.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddSmallDevicePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddSmallDevice;

    @FXML
    private VBox AddSmallDevicePageVBox;

    @FXML
    private AnchorPane AddSmallDeviceStage;

    @FXML
    private TextField BrandTextFieldSmallDevice;

    @FXML
    private TextField EnableStateTextFieldSmallDevice;

    @FXML
    private HBox HBoxAllLabelsTextFieldsAddSmall;

    @FXML
    private HBox HBoxBrandSmallDevice;

    @FXML
    private HBox HBoxEnableStateSmallDevice;

    @FXML
    private HBox HBoxNameSmallDevice;

    @FXML
    private HBox HBoxPluggedInSmallDevice;

    @FXML
    private HBox HBoxPowerInOffStateSmallDevice;

    @FXML
    private HBox HBoxPowerSmallDevice;

    @FXML
    private HBox HBoxRetMenuButtonsSmallDevice;

    @FXML
    private HBox HBoxSNSmallDevice;

    @FXML
    private Label LabelAddSmall;

    @FXML
    private Label LabelBrandSmallDevice;

    @FXML
    private Label LabelDevice;

    @FXML
    private Label LabelEnableStateSmallDevice;

    @FXML
    private Label LabelNameSmallDevice;

    @FXML
    private Label LabelPluggedInSmallDevice;

    @FXML
    private Label LabelPowerInOffStateSmallDevice;

    @FXML
    private Label LabelPowerSmallDevice;

    @FXML
    private Label LabelSNSmallDevice;

    @FXML
    private Label LabelSuccessAddSmallDevice;

    @FXML
    private Button MenuSmallDevice;

    @FXML
    private TextField NameTextFieldSmallDevice;

    @FXML
    private TextField PluggedInTextFieldSmallDevice;

    @FXML
    private TextField PowerInOffStateTextFieldSmallDevice;

    @FXML
    private TextField PowerTextFieldSmallDevice;

    @FXML
    private Button ReturnSmallDevice;

    @FXML
    private TextField SNTextFieldSmallDevice;

    @FXML
    private VBox VBoxBrandPowerEnableStateSmallDevice;

    @FXML
    private VBox VBoxLabelAddSmallDevicePage;

    @FXML
    private VBox VBoxNameSNPluggedIn;

    static Connection MyCon;

    static {
        try {
            MyCon = DBConnection.GetCon();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void ReturnSmallDeviceClick() throws IOException {
        Stage stage = (Stage) ReturnSmallDevice.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("AddDevice.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void MenuSmallDeviceClick() throws IOException {
        Stage stage = (Stage) MenuSmallDevice.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void AddNewSmallDeviceClick() throws SQLException {
        LabelSuccessAddSmallDevice.setVisible(false);
        String serialNumber, name, brand, tempPluggedIn, tempEnableState, tempPower, tempPowerInOff;
        Integer power = null, powerInOffState = null;
        byte pluggedIn, enableState;

        serialNumber = SNTextFieldSmallDevice.getText().trim();
        name = NameTextFieldSmallDevice.getText().trim();
        brand = BrandTextFieldSmallDevice.getText().trim();
        tempPluggedIn = PluggedInTextFieldSmallDevice.getText().trim();
        if(tempPluggedIn.indexOf('n') >= 0)
            pluggedIn = 1;
        else
            pluggedIn = 0;

        tempEnableState = EnableStateTextFieldSmallDevice.getText().trim();
        if(tempEnableState.indexOf('n') >= 0)
            enableState = 1;
        else
            enableState = 0;

        tempPower = PowerTextFieldSmallDevice.getText().trim();
        if(!tempPower.equals("")) power = Integer.parseInt(tempPower);

        tempPowerInOff = PowerInOffStateTextFieldSmallDevice.getText().trim();
        if(!tempPowerInOff.equals("")) powerInOffState = Integer.parseInt(tempPowerInOff);

        if(check(serialNumber, name, brand, tempPluggedIn, tempEnableState, tempPower, tempPowerInOff)){
            SNTextFieldSmallDevice.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            NameTextFieldSmallDevice.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            BrandTextFieldSmallDevice.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            PowerTextFieldSmallDevice.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            PowerInOffStateTextFieldSmallDevice.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            PluggedInTextFieldSmallDevice.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            EnableStateTextFieldSmallDevice.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");

            String  inDevSql = "insert into device VALUES(\""+serialNumber+"\",\""+name+"\",\""+brand+"\","+power+","+pluggedIn+","+enableState+","+powerInOffState+",\"Small\");";
            System.out.println(inDevSql);

            Statement deviceSql = MyCon.createStatement();
            deviceSql.executeUpdate(inDevSql);

            NameTextFieldSmallDevice.clear();
            SNTextFieldSmallDevice.clear();
            PluggedInTextFieldSmallDevice.clear();
            BrandTextFieldSmallDevice.clear();
            PowerTextFieldSmallDevice.clear();
            EnableStateTextFieldSmallDevice.clear();
            PowerInOffStateTextFieldSmallDevice.clear();
            LabelSuccessAddSmallDevice.setText("Device \"" + name + "\" added!");
            LabelSuccessAddSmallDevice.setVisible(true);
        }else{
            if(serialNumber.equals("")) {
                SNTextFieldSmallDevice.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                SNTextFieldSmallDevice.clear();
            }
            else SNTextFieldSmallDevice.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            if(name.equals("")) {
                NameTextFieldSmallDevice.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                NameTextFieldSmallDevice.clear();
            }
            else NameTextFieldSmallDevice.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            if(brand.equals("")) {
                BrandTextFieldSmallDevice.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                BrandTextFieldSmallDevice.clear();
            }
            else BrandTextFieldSmallDevice.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            if(tempPower.equals("")) {
                PowerTextFieldSmallDevice.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                PowerTextFieldSmallDevice.clear();
            }
            else PowerTextFieldSmallDevice.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            if(tempPowerInOff.equals("")){
                PowerInOffStateTextFieldSmallDevice.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                PowerInOffStateTextFieldSmallDevice.clear();
            }
            else PowerInOffStateTextFieldSmallDevice.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            if(tempPluggedIn.equals("")){
                PluggedInTextFieldSmallDevice.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                PluggedInTextFieldSmallDevice.clear();
            }
            else PluggedInTextFieldSmallDevice.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            if(tempEnableState.equals("")){
                EnableStateTextFieldSmallDevice.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                EnableStateTextFieldSmallDevice.clear();
            }
            else EnableStateTextFieldSmallDevice.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
        }
    }

    private boolean check(String serialNumber, String name, String brand, String tempPluggedIn, String tempEnableState, String power, String powerInOffState){
        boolean res = true;
        if(serialNumber.equals("")) return false;
        if(name.equals("")) return false;
        if(brand.equals("")) return false;
        if(power.equals("")) return false;
        if(powerInOffState.equals("")) return false;
        if(tempPluggedIn.equals("")) return false;
        if(tempEnableState.equals("")) return false;
        else
            return res;
    }
}
