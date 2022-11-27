package com.example.homedeviceskursova;

import com.example.homedeviceskursova.dbconnection.DBConnection;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class ChangeStatePageController {

    @FXML
    private TableColumn<AllList, ?> BrandColumn;

    @FXML
    private Button ChangeStateButton;

    @FXML
    private AnchorPane ChangeStatePage;

    @FXML
    private ComboBox<String> ComboBoxChoice;

    @FXML
    private ComboBox<String> ComboBoxDevicesSN;

    @FXML
    private HBox HBoxTableDeleteDevice;

    @FXML
    private HBox HboxWithComboBox;

    @FXML
    private Label LabelChange;

    @FXML
    private Button MenuButtonChange;

    @FXML
    private TableColumn<AllList, String> NameColumn;

    @FXML
    private TableColumn<AllList, String> PluggedInColumn;

    @FXML
    private TableColumn<AllList, String> PowerColumn;

    @FXML
    private TableColumn<AllList, String> SNColumn;

    @FXML
    private TableColumn<AllList, String> StateColumn;

    @FXML
    private TableView<AllList> TableViewDeviceList;

    @FXML
    private VBox VBoxAll;

    ArrayList<AllList> allList = new ArrayList<>();

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

    String choice;

    @FXML
    void initialize() {
        ComboBoxChoice.getItems().add("Plugged in");
        ComboBoxChoice.getItems().add("Unplug");
        ComboBoxChoice.getItems().add("Turn on");
        ComboBoxChoice.getItems().add("Turn off");
    }

    @FXML
    private void MenuButtonChangeClick() throws IOException {
        Stage stage = (Stage) MenuButtonChange.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    @FXML
    private void ComboBoxDeviceClick() throws SQLException {
        choice = (String) ComboBoxChoice.getValue();
        System.out.println(choice);
        if(choice == null){
            ComboBoxChoice.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
            ComboBoxChoice.setPromptText("First make a choice!");
            ShowList();
        }else{
            ComboBoxChoice.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            ShowList();
        }
    }

    @FXML
    private void ChangeStateButtonClick() throws SQLException {
        String sn = (String) ComboBoxDevicesSN.getValue();
        choice = (String) ComboBoxChoice.getValue();
        if(sn == null){
            ComboBoxDevicesSN.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
            if(choice == null){
                ComboBoxChoice.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
                ComboBoxChoice.setPromptText("First make a choice!");
                ComboBoxDevicesSN.setPromptText("Second make a choice!");
            }else
                ComboBoxDevicesSN.setPromptText("First make a choice!");
        }else{
            ComboBoxChoice.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");
            ComboBoxDevicesSN.setStyle("-fx-border-color: #66FF00; -fx-border-width: 2px;");

            Statement statement = MyCon.createStatement();
            switch (choice){
                case "Plugged in":
                    statement.executeUpdate("UPDATE device SET pluggedIn = true WHERE serialNumber = \'" + sn + "\';");
                    break;
                case "Unplug":
                    statement.executeUpdate("UPDATE device SET pluggedIn = false WHERE serialNumber = \"" + sn + "\";");
                    break;
                case "Turn on":
                    statement.executeUpdate("UPDATE device SET enableState = true WHERE serialNumber LIKE \'" + sn + "\';");
                    break;
                default:
                    statement.executeUpdate("UPDATE device SET enableState = false WHERE serialNumber LIKE \'" + sn + "\';");
                    break;
            }
            ShowList();
        }
    }

    @FXML
    private void ShowList()throws SQLException {
        allList.removeAll(allList);

        for ( int i = 0; i<TableViewDeviceList.getItems().size(); i++)
            TableViewDeviceList.getItems().clear();

        for(int i = ComboBoxDevicesSN.getItems().size() - 1; i>=0; i--)
            ComboBoxDevicesSN.getItems().remove(i);

        Statement statement = MyCon.createStatement();
        ResultSet resultSet;

        if(choice != null){
            switch (choice){
                case "Plugged in":
                    resultSet = statement.executeQuery("SELECT serialNumber, name, brand, power, pluggedIn, enableState FROM device WHERE pluggedIn IS false;");
                    break;
                case "Unplug":
                    resultSet = statement.executeQuery("SELECT serialNumber, name, brand, power, pluggedIn, enableState FROM device WHERE pluggedIn IS true;");
                    break;
                case "Turn on":
                    resultSet = statement.executeQuery("SELECT serialNumber, name, brand, power, pluggedIn, enableState FROM device WHERE enableState IS false AND pluggedIn IS true;");
                    break;
                default:
                    resultSet = statement.executeQuery("SELECT serialNumber, name, brand, power, pluggedIn, enableState FROM device WHERE enableState IS true AND pluggedIn IS true;");
                    break;
            }

            while(resultSet.next()){
                String sn = resultSet.getString("serialNumber");
                String name = resultSet.getString("name");
                String brand = resultSet.getString("brand");
                int power = resultSet.getInt("power");
                boolean pluggedIn = resultSet.getBoolean("pluggedIn");
                boolean enableState = resultSet.getBoolean("enableState");
                ComboBoxDevicesSN.getItems().add(sn);

                allList.add(new AllList(sn, name, brand, power, pluggedIn, enableState));
            }

            TableViewDeviceList.getItems().addAll(allList);
            SNColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumb"));
            NameColumn.setCellValueFactory(new PropertyValueFactory<>("nam"));
            BrandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
            PowerColumn.setCellValueFactory(new PropertyValueFactory<>("pwr"));
            PluggedInColumn.setCellValueFactory(new PropertyValueFactory<>("plggdIn"));
            StateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        }
    }

    public static class AllList {
        private final SimpleStringProperty serialNumb;
        private final SimpleStringProperty nam;
        private final SimpleStringProperty brand;
        private final SimpleIntegerProperty pwr;
        private final SimpleStringProperty plggdIn;
        private final SimpleStringProperty state;

        public AllList(String serialNumb, String nam, String brand, int pwr, boolean plggdIn, boolean state) {
            this.serialNumb = new SimpleStringProperty(serialNumb);
            this.nam = new SimpleStringProperty(nam);
            this.brand = new SimpleStringProperty(brand);
            this.pwr = new SimpleIntegerProperty(pwr);
            if (plggdIn)
                this.plggdIn = new SimpleStringProperty("On");
            else
                this.plggdIn = new SimpleStringProperty("Off");
            if (state)
                this.state = new SimpleStringProperty("On");
            else
                this.state = new SimpleStringProperty("Off");
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
