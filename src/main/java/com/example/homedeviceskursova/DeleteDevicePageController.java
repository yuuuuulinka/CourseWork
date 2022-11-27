package com.example.homedeviceskursova;

import com.example.homedeviceskursova.dbconnection.DBConnection;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DeleteDevicePageController {
    @FXML
    private TableColumn<ListInDeletePage, String> BrandColumn;

    @FXML
    private Button DeleteButton;

    @FXML
    private AnchorPane DeleteDevicePage;

    @FXML
    private HBox HBoxMenuViewButtonsDeleteDevice;

    @FXML
    private HBox HBoxTableDeleteDevice;

    @FXML
    private HBox HBoxTextTextFieldSN;

    @FXML
    private Label LabelAboutDelete;

    @FXML
    private Label LabelDeleteDevice;

    @FXML
    private Button MenuDeleteDevice;

    @FXML
    private TableColumn<ListInDeletePage, String> NameColumn;

    @FXML
    private TableColumn<ListInDeletePage, Integer> PowerColumn;

    @FXML
    private TableColumn<ListInDeletePage, String> SNColumn;

    @FXML
    private TableView<ListInDeletePage> TableViewDeviceList;

    @FXML
    private ComboBox ComboBoxDeleteDevice;

    @FXML
    private VBox VBoxAllDeleteDevicePage;



    ArrayList<ListInDeletePage> list = new ArrayList<>();

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
    void initialize() throws SQLException {
        ViewListDeleteDeviceClick();
    }

    @FXML
    private void MenuDeleteDeviceClick() throws IOException {
        Stage stage = (Stage) MenuDeleteDevice.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void DeleteButtonClick() throws SQLException {
        String sn;
        sn = (String) ComboBoxDeleteDevice.getValue();

        if(sn != null){
            Statement deviceSql = MyCon.createStatement();
            LabelAboutDelete.setVisible(false);
            deviceSql.executeUpdate("DELETE FROM device WHERE serialNumber LIKE \"" + sn + "\";");
            LabelAboutDelete.setVisible(true);
        }else
            ComboBoxDeleteDevice.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px; -fx-background-radius: 5px;");
        ViewListDeleteDeviceClick();
    }

    @FXML
    private void ViewListDeleteDeviceClick() throws SQLException {
        list.removeAll(list);
        for(int i =ComboBoxDeleteDevice.getItems().size() - 1; i>=0; i--)
                ComboBoxDeleteDevice.getItems().remove(i);

        for ( int i = 0; i<TableViewDeviceList.getItems().size(); i++)
            TableViewDeviceList.getItems().clear();

        Statement statement = MyCon.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT serialNumber, name, brand, power FROM device;");
        while(resultSet.next()){
            String sn = resultSet.getString("serialNumber");
            String name = resultSet.getString("name");
            String brand = resultSet.getString("brand");
            int power = resultSet.getInt("power");
            ComboBoxDeleteDevice.getItems().add(sn);

            list.add(new ListInDeletePage(sn, name, brand, power));
        }

        TableViewDeviceList.getItems().addAll(list);
        SNColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumb"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("nam"));
        BrandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        PowerColumn.setCellValueFactory(new PropertyValueFactory<>("pwr"));
    }


    public static class ListInDeletePage{
        private final SimpleStringProperty serialNumb;
        private final SimpleStringProperty nam;
        private final SimpleStringProperty brand;
        private final SimpleIntegerProperty pwr;

        public ListInDeletePage(String serialNumb, String nam, String brnd,  int pwr){
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
