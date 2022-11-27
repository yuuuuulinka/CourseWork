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

public class AddLargeDeviceWithoutECClassController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddLDDWithoutEEClass;

    @FXML
    private VBox AddLDDWithoutEEClassDevicePageVBox;

    @FXML
    private AnchorPane AddLDDWithoutEEClassStage;

    @FXML
    private TextField BrandTextFieldLDDWithoutEEClass;

    @FXML
    private TextField EnableStateTextFieldLDDWithoutEEClass;

    @FXML
    private HBox HBoxAllLabelsTextFieldsAddLDDWithoutEEClass;

    @FXML
    private HBox HBoxBrandLDDWithoutEEClass;

    @FXML
    private HBox HBoxEnableStateLDDWithoutEEClass;

    @FXML
    private HBox HBoxNameLDDWithoutEEClass;

    @FXML
    private HBox HBoxPluggedInLDDWithoutEEClass;

    @FXML
    private HBox HBoxPowerInOffStateLDDWithoutEEClass;

    @FXML
    private HBox HBoxPowerLDDWithoutEEClass;

    @FXML
    private HBox HBoxRetMenuButtonsLDDWithoutEEClass;

    @FXML
    private HBox HBoxSNLDDWithoutEEClass;

    @FXML
    private HBox HBoxTypeLDDWithoutEEClass;

    @FXML
    private Label LabelAddLDDWithoutEEClass;

    @FXML
    private Label LabelBrandLDDWithoutEEClass;

    @FXML
    private Label LabelEnableStateLDDWithoutEEClass;

    @FXML
    private Label LabelNameLDDWithoutEEClass;

    @FXML
    private Label LabelPluggedInLDDWithoutEEClass;

    @FXML
    private Label LabelPowerInOffStateLDDWithoutEEClass;

    @FXML
    private Label LabelSuccessAddLDDWithoutEEC;

    @FXML
    private Label LabelPowerLDDWithoutEEClass;

    @FXML
    private Label LabelSNLDDWithoutEEClass;

    @FXML
    private Label LabelTypeLDDWithoutEEClass;

    @FXML
    private Button MenuLDDWithoutEEClass;

    @FXML
    private TextField NameTextFieldLDDWithoutEEClass;

    @FXML
    private TextField PluggedInTextFieldLDDWithoutEEClass;

    @FXML
    private TextField PowerInOffStateTextFieldLDDWithoutEEClass;

    @FXML
    private TextField PowerTextFieldLDDWithoutEEClass;

    @FXML
    private Button ReturnLDDWithoutEEClass;

    @FXML
    private TextField SNTextFieldLDDWithoutEEClass;

    @FXML
    private TextField TypeTextFieldLDDWithoutEEClass;

    @FXML
    private VBox VBoxBrandPowerEnableStateTypeWithoutEEClass;

    @FXML
    private VBox VBoxNameSNPluggedInPowerInOffState;

    static Connection MyCon;

    static {
        try {
            MyCon = DBConnection.GetCon();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ReturnLDDWithoutEEClassClick() throws IOException {
        Stage stage = (Stage) ReturnLDDWithoutEEClass.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("AddLargeDevice.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void MenuLDDWithoutEEClassClick() throws IOException {
        Stage stage = (Stage) MenuLDDWithoutEEClass.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void AddLDDWithoutEEClassClick() throws SQLException {
        LabelSuccessAddLDDWithoutEEC.setVisible(false);
        String serialNumber, name, brand, tempPluggedIn, tempEnableState, type, tempPower, tempPowerInOff;
        Integer power = null, powerInOffState = null;
        byte pluggedIn, enableState;

        serialNumber = SNTextFieldLDDWithoutEEClass.getText().trim();
        name = NameTextFieldLDDWithoutEEClass.getText().trim();
        brand = BrandTextFieldLDDWithoutEEClass.getText().trim();
        tempPluggedIn = PluggedInTextFieldLDDWithoutEEClass.getText().trim();
        type = TypeTextFieldLDDWithoutEEClass.getText().trim();

        if(tempPluggedIn.indexOf('n') >= 0)
            pluggedIn = 1;
        else
            pluggedIn = 0;

        tempEnableState = EnableStateTextFieldLDDWithoutEEClass.getText().trim();
        if(tempEnableState.indexOf('n') >= 0)
            enableState = 1;
        else
            enableState = 0;

        tempPower = PowerTextFieldLDDWithoutEEClass.getText().trim();
        if(!tempPower.equals("")) power = Integer.parseInt(tempPower);

        tempPowerInOff = PowerInOffStateTextFieldLDDWithoutEEClass.getText().trim();
        if(!tempPowerInOff.equals("")) powerInOffState = Integer.parseInt(tempPowerInOff);

        if(check(serialNumber, name, brand, tempPluggedIn, tempEnableState, type, tempPower, tempPowerInOff)){
            SNTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            NameTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            BrandTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            TypeTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            PowerTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            PowerInOffStateTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            PluggedInTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            EnableStateTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");

            String inDevSql = "INSERT INTO device VALUES(\""+serialNumber+"\",\""+name+"\",\""+brand+"\","+power+","+pluggedIn+","+enableState+","+powerInOffState+",\"Large\");";
            String inLDDSql = "INSERT INTO largeDomesticDevice VALUES(\""+serialNumber+"\",\""+type+"\");";

            Statement deviceSql = MyCon.createStatement();
            deviceSql.executeUpdate(inDevSql);
            deviceSql.executeUpdate(inLDDSql);

            NameTextFieldLDDWithoutEEClass.clear();
            SNTextFieldLDDWithoutEEClass.clear();
            PluggedInTextFieldLDDWithoutEEClass.clear();
            BrandTextFieldLDDWithoutEEClass.clear();
            PowerTextFieldLDDWithoutEEClass.clear();
            EnableStateTextFieldLDDWithoutEEClass.clear();
            PowerInOffStateTextFieldLDDWithoutEEClass.clear();
            TypeTextFieldLDDWithoutEEClass.clear();
            LabelSuccessAddLDDWithoutEEC.setText("Device \"" + name + "\" added!");
            LabelSuccessAddLDDWithoutEEC.setVisible(true);
        }else{
            if(serialNumber.equals("")) {
                SNTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                SNTextFieldLDDWithoutEEClass.clear();
            }
            else SNTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            if(name.equals("")) {
                NameTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                NameTextFieldLDDWithoutEEClass.clear();
            }
            else NameTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            if(brand.equals("")) {
                BrandTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                BrandTextFieldLDDWithoutEEClass.clear();
            }
            else BrandTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            if(tempPower.equals("")) {
                PowerTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                PowerTextFieldLDDWithoutEEClass.clear();
            }
            else PowerTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            if(tempPowerInOff.equals("")){
                PowerInOffStateTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                PowerInOffStateTextFieldLDDWithoutEEClass.clear();
            }
            else PowerInOffStateTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            if(tempPluggedIn.equals("")){
                PluggedInTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                PluggedInTextFieldLDDWithoutEEClass.clear();
            }
            else PluggedInTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            if(tempEnableState.equals("")){
                EnableStateTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                EnableStateTextFieldLDDWithoutEEClass.clear();
            }
            else EnableStateTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            if(type.equals("")){
                TypeTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                TypeTextFieldLDDWithoutEEClass.clear();
            }
            else TypeTextFieldLDDWithoutEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
          }


    }

    private boolean check(String serialNumber, String name, String brand, String tempPluggedIn, String tempEnableState, String type, String power, String powerInOffState){
        boolean res = true;
        if(serialNumber.equals("")) return false;
        if(name.equals("")) return false;
        if(brand.equals("")) return false;
        if(type.equals("")) return false;
        if(power.equals("")) return false;
        if(powerInOffState.equals("")) return false;
        if(tempPluggedIn.equals("")) return false;
        if(tempEnableState.equals("")) return false;
        else
            return res;
    }
}
