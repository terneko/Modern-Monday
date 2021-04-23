package sample.controller.homePageController;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    public Label welcomeText;
    String[] listWelcomeText = {"What's CRAKINN?, my friend.","เพิ่มข้อความตรงนี้"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        welcomeText.setTextFill(Color.WHITE);
        Random r = new Random();
        welcomeText.setText(listWelcomeText[r.nextInt(listWelcomeText.length)]);
    }

}
