package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RevenueController extends DBConnect implements Initializable {

    @FXML
    TableView tableView;

    @FXML
    TableColumn idCol;
    @FXML
    TableColumn nameCol;
    @FXML
    TableColumn capPriceCol;
    @FXML
    TableColumn soldPriceCol;
    @FXML
    TableColumn revenueCol;

    @FXML
    Text totalRevenue;

    ObservableList<Item> history = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadRevenue();
    }

    public void loadRevenue(){

        int revenueAmount = 0;


        tableView.getItems().clear();
        try {
            Connection con = DBConnect.getConnection();
            String sql = "select * from revenue_table";
            ResultSet rs =  con.createStatement().executeQuery(sql);
            while (rs.next()) {
                history.add(new Item(rs.getString("id"), rs.getString("name"), rs.getString("price"), "", "",rs.getString("sold_price")  ,rs.getString("revenue")));
                revenueAmount += Integer.parseInt(rs.getString("revenue"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        idCol.setCellValueFactory(new PropertyValueFactory<Item, String>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        capPriceCol.setCellValueFactory(new PropertyValueFactory<Item, String>("price"));
        soldPriceCol.setCellValueFactory(new PropertyValueFactory<Item, String>("soldPrice"));
        revenueCol.setCellValueFactory(new PropertyValueFactory<Item, String>("revenue"));

        // load dummy data
        tableView.setItems(history);
        totalRevenue.setText(Integer.toString(revenueAmount));

    }

    public void goToHome(){
        HomeController.revenueStage.close();
    }
}
