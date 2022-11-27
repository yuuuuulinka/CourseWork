package com.example.homedeviceskursova;

import com.example.homedeviceskursova.dbconnection.DBConnection;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FindDevicePageController {

    @FXML
    private TableColumn<ListFindDevices, String> BrandColumn;

    @FXML
    private Button FindButton;

    @FXML
    private Label FindFindPage;

    @FXML
    private AnchorPane FindPage;

    @FXML
    private HBox HBoxDiapazonFindPage;

    @FXML
    private HBox HBoxTableFindedDevices;

    @FXML
    private Button MenuButton;

    @FXML
    private TableColumn<ListFindDevices, String> NameColumn;

    @FXML
    private TextField Power1TextField;

    @FXML
    private TextField Power2TextField;

    @FXML
    private TableColumn<ListFindDevices, Integer> PowerColumn;

    @FXML
    private TableColumn<ListFindDevices, String> SNColumn;

    @FXML
    private TableView<ListFindDevices> Table;

    @FXML
    private Text TextFindPage;

    @FXML
    private VBox VBoxAllFindPage;
    @FXML
    private Label LabelInfo;

    static Connection MyCon;

    ArrayList<ListFindDevices> list = new ArrayList<>();

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
    private void MenuButtonClick() throws IOException {
        Stage stage = (Stage) MenuButton.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void FindDeviceClick() throws SQLException {
        Power1TextField.setStyle("-fx-border-color: #0000FF");
        Power2TextField.setStyle("-fx-border-color: #0000FF");
        Power1TextField.setPromptText("Enter power in Wt");
        Power2TextField.setPromptText("Enter power in Wt");

        Integer pow1 = 0, pow2 = 0;
        Double tempDoblPow1, tempDoblPow2;
        String strpow1, strpow2;

        strpow1 = Power1TextField.getText().trim();
        if(!strpow1.equals(""))
            pow1 = Integer.parseInt(strpow1);

        strpow2 = Power2TextField.getText().trim();
        if(!strpow2.equals(""))
            pow2 = Integer.parseInt(strpow2);

        if( (pow1>=0 && pow2>0) && (!strpow1.equals(strpow2)))
        {
            LabelInfo.setVisible(false);
            list.removeAll(list);
            for ( int i = 0; i<Table.getItems().size(); i++)
            Table.getItems().clear();
            if(pow1>pow2){
                Integer temp = pow1;
                pow1 = pow2;
                pow2 = temp;
            }

            Statement statement = MyCon.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT serialNumber, name, brand, power FROM device WHERE power >= "+pow1+" AND power<= "+ pow2 +";");
            while(resultSet.next()){
                String sn = resultSet.getString("serialNumber");
                String name = resultSet.getString("name");
                String brand = resultSet.getString("brand");
                int power = resultSet.getInt("power");
                list.add(new ListFindDevices(sn, name, brand, power));
            }
            LabelInfo.setText("Devices with range of power ("+pow1+" Wt; "+pow2+" Wt):");
            LabelInfo.setVisible(true);
            Power1TextField.clear();
            Power2TextField.clear();

            Table.getItems().addAll(list);
            SNColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumb"));
            NameColumn.setCellValueFactory(new PropertyValueFactory<>("nam"));
            BrandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
            PowerColumn.setCellValueFactory(new PropertyValueFactory<>("pwr"));
        }else{
            if (pow1 < 0)
                Power1TextField.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px; -fx-background-radius: 5px; -fx-text-color: #FF0000;");

            if(strpow1.equals(""))
                Power1TextField.setText("0");

            if(strpow2.equals("")){
                Power2TextField.setPromptText("Power wasn't enter!");
                Power2TextField.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px; -fx-background-radius: 5px; -fx-text-color: #FF0000;");
            }else{
                if(pow2 == 0) {
                    Power2TextField.setPromptText("Power was 0!");
                    Power2TextField.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px; -fx-background-radius: 5px; -fx-text-color: #FF0000;");
                }
                if(pow1 == pow2){
                    Power2TextField.setPromptText("Power was "+ pow1 + " Wt!");
                    Power2TextField.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px; -fx-background-radius: 5px; -fx-text-color: #FF0000;");
                }
                if(pow2 < 0){
                    Power2TextField.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px; -fx-background-radius: 5px; -fx-text-color: #FF0000;");
                    Power2TextField.setPromptText("False value!");
                }
            }

        }
    }

    public static class ListFindDevices{
        private final SimpleStringProperty serialNumb;
        private final SimpleStringProperty nam;
        private final SimpleStringProperty brand;
        private final SimpleIntegerProperty pwr;

        public ListFindDevices(String serialNumb, String nam, String brnd,  int pwr){
            this.serialNumb = new SimpleStringProperty(serialNumb);
            this.nam = new SimpleStringProperty(nam);
            this.brand = new SimpleStringProperty(brnd);
            this.pwr = new SimpleIntegerProperty(pwr);
        }

        public String getSerialNumb() {
            return serialNumb.get();
        }

        public SimpleStringProperty serialNumbProperty() {
            return serialNumb;
        }

        public String getNam() {
            return nam.get();
        }

        public SimpleStringProperty namProperty() {
            return nam;
        }

        public String getBrand() {
            return brand.get();
        }

        public SimpleStringProperty brandProperty() {
            return brand;
        }

        public int getPwr() {
            return pwr.get();
        }

        public SimpleIntegerProperty pwrProperty() {
            return pwr;
        }
    }
}
