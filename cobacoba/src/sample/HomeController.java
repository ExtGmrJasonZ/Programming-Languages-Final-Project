package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HomeController extends DBConnect implements Initializable {


    public static String id = "";
    public static String name = "";
    public static String capitalPrice = "";

    public static Stage revenueStage;
    public static Stage sellStage;

    @FXML
    TextField idField;

    @FXML
    TextField nameField;

    @FXML
    TextField priceField;

    @FXML
    ImageView imageView;

    @FXML
    Text sellValidation;

    @FXML
    Text insertValidation;

    @FXML
    Text updateValidation;

    @FXML
    Text deleteValidation;


    @FXML
    private TableView<Item> tableView;

    @FXML
    private TableColumn<Item , String> idCol;
    @FXML
    private TableColumn<Item , String> nameCol;
    @FXML
    private TableColumn<Item , String> priceCol;
    @FXML
    private TableColumn<Item , String> dateCol;

    ObservableList<Item> history = FXCollections.observableArrayList();

    public String imageLink = "";

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy");
    LocalDate date = LocalDate.now();

    public void loadData(){
        // set up the columns in the table
        tableView.getItems().clear();
        try {
            Connection con = DBConnect.getConnection();
            String sql = "select * from products";
            ResultSet rs =  con.createStatement().executeQuery(sql);
            while (rs.next()) {
                history.add(new Item(rs.getString("id"), rs.getString("name"), rs.getString("price"), rs.getString("date") , rs.getString("image") , "" ,""));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        idCol.setCellValueFactory(new PropertyValueFactory<Item, String>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Item, String>("price"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Item, String>("date"));


        // load dummy data
        tableView.setItems(history);
    }

    public void addItem(){
        String id = idField.getText();
        String name = nameField.getText();
        String price = priceField.getText();
        insertData(id , name , price , dtf.format(date) , imageLink);

        idField.setText("");
        priceField.setText("");
        nameField.setText("");
        setToDefault();
        loadData();
        insertValidation.setText("DATA INSERTED");
    }
    public void setToDefault(){

        String fileName = "muka.jpg";
        File file = new File(fileName);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    public void update(){
        insertValidation.setText("");
        String id = tableView.getSelectionModel().getSelectedItem().getId();
        String name = tableView.getSelectionModel().getSelectedItem().getName();
        String price = tableView.getSelectionModel().getSelectedItem().getPrice();
        imageLink = tableView.getSelectionModel().getSelectedItem().getImage();

        Image image = new Image(imageLink);
        imageView.setImage(image);

        idField.setText(id);
        nameField.setText(name);
        priceField.setText(price);

        tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
        deleteRow(id);
    }

    public void delete(){
        insertValidation.setText("");
        String id = tableView.getSelectionModel().getSelectedItem().getId();
        deleteRow(id);
        tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
        updateValidation.setText("DELETE SUCCESS");
    }

    public void setImage(){
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        Image image = new Image(selectedFile.toURI().toString());
        imageLink = selectedFile.toURI().toString();
        imageView.setImage(image);
    }

    public void goToSellPage() throws IOException {
        id = tableView.getSelectionModel().getSelectedItem().getId();
        name = tableView.getSelectionModel().getSelectedItem().getName();

        capitalPrice = tableView.getSelectionModel().getSelectedItem().getPrice();

        System.out.println(capitalPrice);
        Parent root = FXMLLoader.load(getClass().getResource("sell_item.fxml"));
        sellStage = new Stage();
        sellStage.setScene(new Scene(root, 300, 300));
        sellStage.show();
    }

    public void goToRevenuePage() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("revenue.fxml"));
        revenueStage = new Stage();
        revenueStage.setScene(new Scene(root, 800, 600));
        revenueStage.show();
    }

    public void logout() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("logsign_page.fxml"));
        Main.primaryStage = new Stage();
        Main.primaryStage.setScene(new Scene(root, 800, 600));
        Main.primaryStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
