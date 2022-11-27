package com.example.homedeviceskursova;

import com.example.homedeviceskursova.dbconnection.DBConnection;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewSortAllDevicePageController {

    @FXML
    private TableColumn<AllList, String> BrandColumnViewSortAllDevices;

    @FXML
    private TableColumn<AllList, String> ECClassColumnViewSortAllDevices;

    @FXML
    private TableColumn<AllList, String> EnableStateColumnViewSortAllDevices;

    @FXML
    private HBox HBoxRetMenuButtonsColumnViewSortAllDevices;

    @FXML
    private HBox HBoxTableViewSortAllDevices;

    @FXML
    private Label LableViewSortAllDevices;

    @FXML
    private Button MenuViewSortAllDevices;

    @FXML
    private TableColumn<AllList, String> NameColumnViewSortAllDevices;

    @FXML
    private TableColumn<AllList, String> PluggedInColumnViewSortAllDevices;

    @FXML
    private TableColumn<AllList, Integer> PowerColumnViewSortAllDevices;

    @FXML
    private TableColumn<AllList, Integer> PowerInOffColumnViewSortAllDevices;

    @FXML
    private Button ReturnViewSortAllDevices;

    @FXML
    private TableColumn<AllList, String> SNColumnViewSortAllDevices;

    @FXML
    private TableView<AllList> TableViewSortAllDevices;

    @FXML
    private Text TextViewSortAllDevices;

    @FXML
    private TableColumn<AllList, String> TypeColumnViewSortAllDevices;

    @FXML
    private VBox VBoxViewSortAllDevices;

    @FXML
    private AnchorPane ViewSortAllDevicesStage;

    ArrayList<AllList> allList = new ArrayList<>();

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
    private void ReturnViewSortAllDevicesClick() throws IOException {
        Stage stage = (Stage) ReturnViewSortAllDevices.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("ViewSortDevicePage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void MenuViewSortAllDevicesClassClick() throws IOException {
        Stage stage = (Stage) MenuViewSortAllDevices.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    void initialize() throws SQLException {
        allList.removeAll(allList);
        for ( int i = 0; i<TableViewSortAllDevices.getItems().size(); i++)
            TableViewSortAllDevices.getItems().clear();

        Statement statement = MyCon.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT d.serialNumber, d.name, d.brand, d.power, d.pluggedIn, d.enableState, d.powerInOffState, ldd.type, lddec.classEC FROM (device d\n" +
                "LEFT JOIN largeDomesticDevice ldd\n" +
                "ON d.serialNumber = ldd.serialNumber)\n" +
                "LEFT JOIN lddEnergyConsumption lddec\n" +
                "ON d.serialNumber = lddec.serialNumber;");

        while(resultSet.next()){
            String sn = resultSet.getString("serialNumber");
            String name = resultSet.getString("name");
            String brand = resultSet.getString("brand");
            int power = resultSet.getInt("power");
            boolean pluggedIn = resultSet.getBoolean("pluggedIn");
            boolean enableState = resultSet.getBoolean("enableState");
            int powerInOffState = resultSet.getInt("powerInOffState");
            String type = resultSet.getString("type");
            String classEC = resultSet.getString("classEC");

            allList.add(new AllList(sn, name, brand, power, pluggedIn, enableState, powerInOffState, type, classEC));
        }

        TableViewSortAllDevices.getItems().addAll(allList);
        SNColumnViewSortAllDevices.setCellValueFactory(new PropertyValueFactory<>("serialNumb"));
        NameColumnViewSortAllDevices.setCellValueFactory(new PropertyValueFactory<>("nam"));
        BrandColumnViewSortAllDevices.setCellValueFactory(new PropertyValueFactory<>("brand"));
        PowerColumnViewSortAllDevices.setCellValueFactory(new PropertyValueFactory<>("pwr"));
        PluggedInColumnViewSortAllDevices.setCellValueFactory(new PropertyValueFactory<>("plggdIn"));
        EnableStateColumnViewSortAllDevices.setCellValueFactory(new PropertyValueFactory<>("state"));
        PowerInOffColumnViewSortAllDevices.setCellValueFactory(new PropertyValueFactory<>("powerInOff"));
        TypeColumnViewSortAllDevices.setCellValueFactory(new PropertyValueFactory<>("type"));
        ECClassColumnViewSortAllDevices.setCellValueFactory(new PropertyValueFactory<>("classEC"));
    }

    public static class AllList{
        private final SimpleStringProperty serialNumb;
        private final SimpleStringProperty nam;
        private final SimpleStringProperty brand;
        private final SimpleIntegerProperty pwr;
        private final SimpleStringProperty plggdIn;
        private final SimpleStringProperty state;
        private final SimpleIntegerProperty powerInOff;
        private final SimpleStringProperty type;
        private final SimpleStringProperty classEC;

        public AllList(String  serialNumb, String nam, String brand, int pwr, boolean plggdIn, boolean state, int powerInOff, String type, String classEC) {
            this.serialNumb = new SimpleStringProperty(serialNumb);
            this.nam = new SimpleStringProperty(nam);
            this.brand = new SimpleStringProperty(brand);
            this.pwr = new SimpleIntegerProperty(pwr);
            if(plggdIn)
                this.plggdIn = new SimpleStringProperty("On");
            else
                this.plggdIn = new SimpleStringProperty("Off");
            if(state)
                this.state = new SimpleStringProperty("On");
            else
                this.state = new SimpleStringProperty("Off");
            this.powerInOff = new SimpleIntegerProperty(powerInOff);
            this.type = new SimpleStringProperty(type);
            this.classEC = new SimpleStringProperty(classEC);
        }

        public String getSerialNumb() {
            return serialNumb.get();
        }

        public SimpleStringProperty serialNumbProperty() {
            return serialNumb;
        }

        public void setSerialNumb(String serialNumb) {
            this.serialNumb.set(serialNumb);
        }

        public String getNam() {
            return nam.get();
        }

        public SimpleStringProperty namProperty() {
            return nam;
        }

        public void setNam(String nam) {
            this.nam.set(nam);
        }

        public String getBrand() {
            return brand.get();
        }

        public SimpleStringProperty brandProperty() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand.set(brand);
        }

        public int getPwr() {
            return pwr.get();
        }

        public SimpleIntegerProperty pwrProperty() {
            return pwr;
        }

        public void setPwr(int pwr) {
            this.pwr.set(pwr);
        }

        public int getPowerInOff() {
            return powerInOff.get();
        }

        public SimpleIntegerProperty powerInOffProperty() {
            return powerInOff;
        }

        public void setPowerInOff(int powerInOff) {
            this.powerInOff.set(powerInOff);
        }

        public String getType() {
            return type.get();
        }

        public SimpleStringProperty typeProperty() {
            return type;
        }

        public void setType(String type) {
            this.type.set(type);
        }

        public String getClassEC() {
            return classEC.get();
        }

        public SimpleStringProperty classECProperty() {
            return classEC;
        }

        public void setClassEC(String classEC) {
            this.classEC.set(classEC);
        }

        public String getPlggdIn() {
            return plggdIn.get();
        }

        public SimpleStringProperty plggdInProperty() {
            return plggdIn;
        }

        public void setPlggdIn(String plggdIn) {
            this.plggdIn.set(plggdIn);
        }

        public String getState() {
            return state.get();
        }

        public SimpleStringProperty stateProperty() {
            return state;
        }

        public void setState(String state) {
            this.state.set(state);
        }
    }
}
