package com.example.homedeviceskursova;

import com.example.homedeviceskursova.dbconnection.DBConnection;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CalculatePageController {
    @FXML
    private Button CalculateButton;

    @FXML
    private AnchorPane CalculatePage;

    @FXML
    private HBox HBoxInfo;

    @FXML
    private Label LabelCP;

    @FXML
    private Label LabelCalculate;

    @FXML
    private Button MenuButton;

    @FXML
    private Label Result;

    @FXML
    private VBox VBoxAll;

    @FXML
    private VBox VBoxCalculatePower;

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
    private void MenuClick() throws IOException {
        Stage stage = (Stage) MenuButton.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void CalculateCP() throws SQLException {
        Result.setVisible(false);
        Statement statement = MyCon.createStatement();
        int sumPower = 0, sumPowerInOff = 0;
        ResultSet resultSet;
        resultSet = statement.executeQuery("SELECT SUM(power) as 'sum' FROM device\n" +
                "WHERE pluggedIn is true \n" +
                "AND enableState is true;");
        while(resultSet.next())
            sumPower = resultSet.getInt("sum");

        resultSet = statement.executeQuery("SELECT SUM(powerInOffState) as 'suminoff' FROM device\n" +
                "WHERE pluggedIn is true \n" +
                "AND enableState is false;");

        while(resultSet.next())
            sumPowerInOff = resultSet.getInt("suminoff");

        sumPower += sumPowerInOff;
        Result.setText(sumPower + " Wt");
        Result.setVisible(true);
    }
}
