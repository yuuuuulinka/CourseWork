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

public class ViewSortLargeDevicesPageController {

    @FXML
    private TableColumn<LargeList, String> BrandColumn;

    @FXML
    private TableColumn<LargeList, String> EnableStateColumn;

    @FXML
    private HBox HBoxRetMenuButtonsViewSortLargeDevices;

    @FXML
    private HBox HBoxTableViewSortLargeDevices;

    @FXML
    private Label LabelViewSortLargeDevices;

    @FXML
    private Button MenuViewSortLargeDevices;

    @FXML
    private TableColumn<LargeList, String> NameColumn;

    @FXML
    private TableColumn<LargeList, String> PluggedInColumn;

    @FXML
    private TableColumn<LargeList, Integer> PowerColumn;

    @FXML
    private TableColumn<LargeList, Integer> PowerInOffColumn;

    @FXML
    private Button ReturnViewSortLargeDevices;

    @FXML
    private TableColumn<LargeList, String> SNColumn;

    @FXML
    private TableView<LargeList> TableViewViewSortLargeDevices;

    @FXML
    private Text TextViewSortLargeDevices;

    @FXML
    private TableColumn<LargeList, String> TypeColumn;

    @FXML
    private VBox VBoxAllViewSortLargeDevices;

    @FXML
    private AnchorPane ViewSortLargeDevicesStage;

    @FXML
    private TableColumn<LargeList, String> ClassECColumn;

    ArrayList<LargeList> largeList = new ArrayList<>();

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
    private void ReturnViewSortLargeDevicesClick() throws IOException {
        Stage stage = (Stage) ReturnViewSortLargeDevices.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("ViewSortDevicePage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void MenuViewSortLargeDevicesClick() throws IOException {
        Stage stage = (Stage) MenuViewSortLargeDevices.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    void initialize() throws SQLException {
        largeList.removeAll(largeList);
        for ( int i = 0; i<TableViewViewSortLargeDevices.getItems().size(); i++)
            TableViewViewSortLargeDevices.getItems().clear();

        Statement statement = MyCon.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT d.serialNumber, d.name, d.brand, d.power, d.pluggedIn, d.enableState, d.powerInOffState, ldd.type, lddec.classEC FROM (device d\n" +
                "LEFT JOIN largeDomesticDevice ldd\n" +
                "ON d.serialNumber = ldd.serialNumber)\n" +
                "LEFT JOIN lddEnergyConsumption lddec\n" +
                "ON d.serialNumber = lddec.serialNumber\n" +
                "WHERE d.deviceType LIKE \"Large%\";");

        while(resultSet.next()){
            String sn = resultSet.getString("serialNumber");
            String name = resultSet.getString("name");
            String brand = resultSet.getString("brand");
            int power = resultSet.getInt("power");
            boolean pluggedIn = resultSet.getBoolean("pluggedIn");
            boolean enableState = resultSet.getBoolean("enableState");
            int powerInOff = resultSet.getInt("powerInOffState");
            String type = resultSet.getString("type");
            String classEC = resultSet.getString("classEC");
            largeList.add(new LargeList(sn, name, brand, power, pluggedIn, enableState, powerInOff, type, classEC));
        }

        TableViewViewSortLargeDevices.getItems().addAll(largeList);
        SNColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumb"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("nam"));
        BrandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        PowerColumn.setCellValueFactory(new PropertyValueFactory<>("pwr"));
        PluggedInColumn.setCellValueFactory(new PropertyValueFactory<>("plggdIn"));
        EnableStateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        PowerInOffColumn.setCellValueFactory(new PropertyValueFactory<>("powerInOff"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        ClassECColumn.setCellValueFactory(new PropertyValueFactory<>("classEC"));
    }

    public static class LargeList {
        private final SimpleStringProperty serialNumb;
        private final SimpleStringProperty nam;
        private final SimpleStringProperty brand;
        private final SimpleIntegerProperty pwr;
        private final SimpleStringProperty plggdIn;
        private final SimpleStringProperty state;
        private final SimpleIntegerProperty powerInOff;
        private final SimpleStringProperty type;
        private final SimpleStringProperty classEC;

        public LargeList(String  serialNumb, String nam, String brand, int pwr, boolean plggdIn, boolean state, int powerInOff, String type, String classEC) {
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

        public String getClassEC() {
            return classEC.get();
        }

        public SimpleStringProperty classECProperty() {
            return classEC;
        }

        public void setClassEC(String classEC) {
            this.classEC.set(classEC);
        }
    }
}
