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

public class AddLargeDeviceWithECClassController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddLDDWithEEClass;

    @FXML
    private VBox AddLDDWithEEClassDevicePageVBox;

    @FXML
    private AnchorPane AddLDDWithEEClassStage;

    @FXML
    private TextField BrandTextFieldLDDWithEEClass;

    @FXML
    private TextField EEClassTextFieldLDDWithEEClass;

    @FXML
    private TextField EnableStateTextFieldLDDWithEEClass;

    @FXML
    private HBox HBoxAllLabelsTextFieldsAddLDDWithEEClass;

    @FXML
    private HBox HBoxBrandLDDWithEEClass;

    @FXML
    private HBox HBoxEEClassLDDWithEEClass;

    @FXML
    private HBox HBoxEnableStateLDDWithEEClass;

    @FXML
    private HBox HBoxNameLDDWithEEClass;

    @FXML
    private HBox HBoxPluggedInLDDWithEEClass;

    @FXML
    private HBox HBoxPowerInOffStateLDDWithEEClass;

    @FXML
    private HBox HBoxPowerLDDWithEEClass;

    @FXML
    private HBox HBoxRetMenuButtonsLDDWithEEClass;

    @FXML
    private HBox HBoxSNLDDWithEEClass;

    @FXML
    private HBox HBoxTypeLDDWithEEClass;

    @FXML
    private Label LabelAddLDDWithEEClass1;

    @FXML
    private Label LabelAddLDDWithEEClass2;

    @FXML
    private Label LabelBrandLDDWithEEClass;

    @FXML
    private Label LabelEEClassLDDWithEEClass;

    @FXML
    private Label LabelEnableStateLDDWithEEClass;

    @FXML
    private Label LabelNameLDDWithEEClass;

    @FXML
    private Label LabelPluggedInLDDWithEEClass;

    @FXML
    private Label LabelPowerInOffStateLDDWithEEClass;

    @FXML
    private Label LabelPowerLDDWithEEClass;

    @FXML
    private Label LabelSNLDDWithEEClass;

    @FXML
    private Label LabelSuccessAddLDDWithEEC;

    @FXML
    private Label LabelTypeLDDWithEEClass;

    @FXML
    private Button MenuLDDWithEEClass;

    @FXML
    private TextField NameTextFieldLDDWithEEClass;

    @FXML
    private TextField PluggedInTextFieldLDDWithEEClass;

    @FXML
    private TextField PowerInOffStateTextFieldLDDWithEEClass;

    @FXML
    private TextField PowerTextFieldLDDWithEEClass;

    @FXML
    private Button ReturnLDDWithEEClass;

    @FXML
    private TextField SNTextFieldLDDWithEEClass;

    @FXML
    private TextField TypeTextFieldLDDWithEEClass;

    @FXML
    private VBox VBoxBrandPowerEnableStateType;

    @FXML
    private VBox VBoxLabelAddLDDWithEEClassDevicePage;

    @FXML
    private VBox VBoxNameSNPluggedInEEClass;

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
    private void ReturnLDDWithEEClassClick() throws IOException {
        Stage stage = (Stage) ReturnLDDWithEEClass.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("AddLargeDevice.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void MenuLDDWithEEClassClick() throws IOException {
        Stage stage = (Stage) MenuLDDWithEEClass.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void AddLDDWithEEClassClick() throws SQLException {
        LabelSuccessAddLDDWithEEC.setVisible(false);
        String serialNumber, name, brand, tempPluggedIn, tempEnableState, classEE, type, tempPower, tempPowerInOff;
        Integer power = null, powerInOffState = null;
        byte pluggedIn, enableState;

        serialNumber = SNTextFieldLDDWithEEClass.getText().trim();
        name = NameTextFieldLDDWithEEClass.getText().trim();
        brand = BrandTextFieldLDDWithEEClass.getText().trim();
        tempPluggedIn = PluggedInTextFieldLDDWithEEClass.getText().trim();
        classEE = EEClassTextFieldLDDWithEEClass.getText().trim();
        type = TypeTextFieldLDDWithEEClass.getText().trim();

        if(tempPluggedIn.indexOf('n') >= 0)
            pluggedIn = 1;
        else
            pluggedIn = 0;

        tempEnableState = EnableStateTextFieldLDDWithEEClass.getText().trim();
        if(tempEnableState.indexOf('n') >= 0)
            enableState = 1;
        else
            enableState = 0;

        tempPower = PowerTextFieldLDDWithEEClass.getText().trim();
        if(!tempPower.equals("")) power = Integer.parseInt(tempPower);

        tempPowerInOff = PowerInOffStateTextFieldLDDWithEEClass.getText().trim();
        if(!tempPowerInOff.equals("")) powerInOffState = Integer.parseInt(tempPowerInOff);

        if(check(serialNumber, name, brand, tempPluggedIn, tempEnableState, classEE, type, tempPower, tempPowerInOff)){
            SNTextFieldLDDWithEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            NameTextFieldLDDWithEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            BrandTextFieldLDDWithEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            EEClassTextFieldLDDWithEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            TypeTextFieldLDDWithEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            PowerTextFieldLDDWithEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            PowerInOffStateTextFieldLDDWithEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            PluggedInTextFieldLDDWithEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            EnableStateTextFieldLDDWithEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");

            String  inDevSql = "INSERT INTO device VALUES(\""+serialNumber+"\",\""+name+"\",\""+brand+"\","+power+","+pluggedIn+","+enableState+","+powerInOffState+",\"Large with EC\");";
            System.out.println(inDevSql);
            String inLDDSql = "INSERT INTO largeDomesticDevice VALUES(\""+serialNumber+"\",\""+type+"\");";
            System.out.println(inLDDSql);
            String inLDDWithEEClassSql = "INSERT INTO lddEnergyConsumption VALUES(\""+serialNumber+"\",\""+classEE+"\");";
            System.out.println(inLDDWithEEClassSql);

            Statement deviceSql = MyCon.createStatement();
            deviceSql.executeUpdate(inDevSql);
            deviceSql.executeUpdate(inLDDSql);
            deviceSql.executeUpdate(inLDDWithEEClassSql);

            NameTextFieldLDDWithEEClass.clear();
            SNTextFieldLDDWithEEClass.clear();
            PluggedInTextFieldLDDWithEEClass.clear();
            BrandTextFieldLDDWithEEClass.clear();
            PowerTextFieldLDDWithEEClass.clear();
            EnableStateTextFieldLDDWithEEClass.clear();
            PowerInOffStateTextFieldLDDWithEEClass.clear();
            EEClassTextFieldLDDWithEEClass.clear();
            TypeTextFieldLDDWithEEClass.clear();
            LabelSuccessAddLDDWithEEC.setText("Device \"" + name + "\" added!");
            LabelSuccessAddLDDWithEEC.setVisible(true);
        } else {
            if(serialNumber.equals("")) {
                SNTextFieldLDDWithEEClass.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                SNTextFieldLDDWithEEClass.clear();
            }
            else SNTextFieldLDDWithEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            if(name.equals("")) {
                NameTextFieldLDDWithEEClass.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                NameTextFieldLDDWithEEClass.clear();
            }
            else NameTextFieldLDDWithEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            if(brand.equals("")) {
                BrandTextFieldLDDWithEEClass.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                BrandTextFieldLDDWithEEClass.clear();
            }
            else BrandTextFieldLDDWithEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            if(tempPower.equals("")) {
                PowerTextFieldLDDWithEEClass.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                PowerTextFieldLDDWithEEClass.clear();
            }
            else PowerTextFieldLDDWithEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            if(tempPowerInOff.equals("")){
                PowerInOffStateTextFieldLDDWithEEClass.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                PowerInOffStateTextFieldLDDWithEEClass.clear();
            }
            else PowerInOffStateTextFieldLDDWithEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            if(tempPluggedIn.equals("")){
                PluggedInTextFieldLDDWithEEClass.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                PluggedInTextFieldLDDWithEEClass.clear();
            }
            else PluggedInTextFieldLDDWithEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            if(tempEnableState.equals("")){
                EnableStateTextFieldLDDWithEEClass.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                EnableStateTextFieldLDDWithEEClass.clear();
            }
            else EnableStateTextFieldLDDWithEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            if(type.equals("")){
                TypeTextFieldLDDWithEEClass.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                TypeTextFieldLDDWithEEClass.clear();
            }
            else TypeTextFieldLDDWithEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            if(classEE.equals("")){
                EEClassTextFieldLDDWithEEClass.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                EEClassTextFieldLDDWithEEClass.clear();
            }
            else EEClassTextFieldLDDWithEEClass.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            }

    }

    private boolean check(String serialNumber, String name, String brand, String tempPluggedIn, String tempEnableState, String classEE, String type, String power, String powerInOffState){
        boolean res = true;
        if(serialNumber.equals("")) return false;
        if(name.equals("")) return false;
        if(brand.equals("")) return false;
        if(classEE.equals("")) return false;
        if(type.equals("")) return false;
        if(power.equals("")) return false;
        if(powerInOffState.equals("")) return false;
        if(tempPluggedIn.equals("")) return false;
        if(tempEnableState.equals("")) return false;
        else
            return res;
    }

}
