package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class SellItemController extends DBConnect implements Initializable {

    @FXML
    Text capitalPrice;

    @FXML
    TextField sellPriceText;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        capitalPrice.setText(HomeController.capitalPrice);
    }

    public void sellItem(){
        String soldPrice = sellPriceText.getText();
        String revenue = Integer.toString(Integer.parseInt(soldPrice) - Integer.parseInt(HomeController.capitalPrice));
        inserIntoSellTable(HomeController.id , HomeController.name , HomeController.capitalPrice , soldPrice , revenue);
        deleteRow(HomeController.id);
        HomeController.sellStage.close();
    }

    public void goToHome(){
        HomeController.sellStage.close();
    }


}
