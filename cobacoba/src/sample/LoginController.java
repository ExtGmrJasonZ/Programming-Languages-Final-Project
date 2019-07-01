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
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController extends DBConnect implements Initializable{

    @FXML
    TextField username;

    @FXML
    TextField password;


    @FXML
    Text validationText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void login() throws IOException {
        if(username.getText().equals("admin")&& password.getText().equals("admin")){
            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
            Main.primaryStage.setTitle("GADGET STRORE");
            Main.primaryStage.setScene(new Scene(root, 800, 600));
            Main.primaryStage.show();
        }
        else{
            username.setText("");
            password.setText("");
            validationText.setText("Wrong Username or Password !");
        }
    }
}
