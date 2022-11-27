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

public class ViewSortSmallDevicesPageController {
    @FXML
    private TableColumn<ListSmallDevices, String> BrandColumn;

    @FXML
    private TableColumn<ListSmallDevices, String> EnableStateColumn;

    @FXML
    private HBox HBoxRetMenuButtonsViewSortSmallDevices;

    @FXML
    private HBox HBoxTableViewSortSmallDevices;

    @FXML
    private Label LabelViewSortSmallDevices;

    @FXML
    private Button MenuViewSortSmallDevices;

    @FXML
    private TableColumn<ListSmallDevices, String> NameColumn;

    @FXML
    private TableColumn<ListSmallDevices, String> PluggedInColumn;

    @FXML
    private TableColumn<ListSmallDevices, Integer> PowerColumn;

    @FXML
    private TableColumn<ListSmallDevices, Integer> PowerInOffColumn;

    @FXML
    private Button ReturnViewSortSmallDevices;

    @FXML
    private TableColumn<ListSmallDevices, String> SNColumn;

    @FXML
    private TableView<ListSmallDevices> TableViewViewSortSmallDevices;

    @FXML
    private Text TextViewSortSmallDevices;

    @FXML
    private VBox VBoxAllViewSortSmallDevices;

    @FXML
    private AnchorPane ViewSortSmallDevicesStage;

    ArrayList<ListSmallDevices> list = new ArrayList<>();

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
    private void ReturnViewSortSmallDevicesClick() throws IOException {
        Stage stage = (Stage) ReturnViewSortSmallDevices.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("ViewSortDevicePage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void MenuViewSortSmallDevicesClick() throws IOException {
        Stage stage = (Stage) MenuViewSortSmallDevices.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    void initialize()  throws SQLException {
        list.removeAll(list);
        for ( int i = 0; i<TableViewViewSortSmallDevices.getItems().size(); i++)
            TableViewViewSortSmallDevices.getItems().clear();

        Statement statement = MyCon.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM device WHERE deviceType LIKE \"Small\";");
        while(resultSet.next()){
            String sn = resultSet.getString("serialNumber");
            String name = resultSet.getString("name");
            String brand = resultSet.getString("brand");
            int power = resultSet.getInt("power");
            boolean pluggedIn = resultSet.getBoolean("pluggedIn");
            boolean enableState = resultSet.getBoolean("enableState");
            int powerInOff = resultSet.getInt("powerInOffState");
            list.add(new ListSmallDevices(sn, name, brand, power, pluggedIn, enableState, powerInOff));
        }

        TableViewViewSortSmallDevices.getItems().addAll(list);
        SNColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumb"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("nam"));
        BrandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        PowerColumn.setCellValueFactory(new PropertyValueFactory<>("pwr"));
        PluggedInColumn.setCellValueFactory(new PropertyValueFactory<>("plugged"));
        EnableStateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        PowerInOffColumn.setCellValueFactory(new PropertyValueFactory<>("pwrinoff"));
    }


    public static class ListSmallDevices{
        private final SimpleStringProperty serialNumb;
        private final SimpleStringProperty nam;
        private final SimpleStringProperty brand;
        private final SimpleIntegerProperty pwr;
        private final SimpleStringProperty plugged;
        private final SimpleStringProperty state;
        private final SimpleIntegerProperty pwrinoff;

        public ListSmallDevices(String serialNumb, String nam, String brnd,  int pwr, boolean plugged, boolean state,  int pwrinoff){
            this.serialNumb = new SimpleStringProperty(serialNumb);
            this.nam = new SimpleStringProperty(nam);
            this.brand = new SimpleStringProperty(brnd);
            this.pwr = new SimpleIntegerProperty(pwr);
            if(plugged)
                this.plugged = new SimpleStringProperty("On");
            else
                this.plugged = new SimpleStringProperty("Off");
            if(state)
                this.state = new SimpleStringProperty("On");
            else
                this.state = new SimpleStringProperty("Off");
            this.pwrinoff = new SimpleIntegerProperty(pwrinoff);
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

        public void setSerialNumb(String serialNumb) {
            this.serialNumb.set(serialNumb);
        }

        public void setNam(String nam) {
            this.nam.set(nam);
        }

        public void setBrand(String brand) {
            this.brand.set(brand);
        }

        public void setPwr(int pwr) {
            this.pwr.set(pwr);
        }

        public String getPlugged() {
            return plugged.get();
        }

        public SimpleStringProperty pluggedProperty() {
            return plugged;
        }

        public void setPlugged(String plugged) {
            this.plugged.set(plugged);
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

        public int getPwrinoff() {
            return pwrinoff.get();
        }

        public SimpleIntegerProperty pwrinoffProperty() {
            return pwrinoff;
        }

        public void setPwrinoff(int pwrinoff) {
            this.pwrinoff.set(pwrinoff);
        }
    }

}
