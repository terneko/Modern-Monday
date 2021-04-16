package sample.controller.temperature;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;


public class TemperatureAPI implements Initializable {

    public AnchorPane tempPageMainPane;
    public Label mainLabel;
    public Label mainDescriptionLabel;
    // set API information
    private String  API_KEY = "5f26cbe1a121b474f1e7c6e6f6350943";
    private String LOCATION;
    private String urlString;


    @FXML
    ComboBox<String> selectedProvince;

    public void setFadeTransitionTempPage() {
        FadeTransition mainPaneFade = new FadeTransition(Duration.millis(500), tempPageMainPane);
        mainPaneFade.setFromValue(0);
        mainPaneFade.setToValue(9);
        mainPaneFade.play();

        Label[] labels = {mainLabel, mainDescriptionLabel};
        for (Label label : labels) {
            TranslateTransition labelTrans = new TranslateTransition();
            labelTrans.setByX(2);
            labelTrans.setDuration(Duration.millis(1200));
            labelTrans.setNode(label);
            labelTrans.play();
        }
    }

    public static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {
        }.getType());
        return map;
    }

    public void callAPI() {
        urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY + "&units =imperial&lang=th";
    }

    public  void weather() {
        try {
            callAPI();
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            System.out.println(result);

            Map<String, Object> respMap = jsonToMap(result.toString());
            Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
            Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());

            System.out.println("Current Temperature: " + mainMap.get("temp"));
            System.out.println("Current Humidity: " + mainMap.get("humidity"));
            System.out.println("Wind Speed: " + windMap.get("speed"));
            System.out.println("Wind Angle: " + windMap.get("deg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFadeTransitionTempPage();
        ArrayList<String> province = new ArrayList<String>();
        Scanner input = null;
        try {
            input = new Scanner(new FileReader("src/main/resources/province"));
            while (input.hasNext()) {
                province.add(input.next());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(province);
        selectedProvince.getItems().addAll(province);
        selectedProvince.setOnAction((event) -> {
            int selectedIndex = selectedProvince.getSelectionModel().getSelectedIndex();
            Object selectedItem = selectedProvince.getSelectionModel().getSelectedItem();

            System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
            System.out.println("   ComboBox.getValue(): " + selectedProvince.getValue());

            LOCATION = selectedProvince.getValue() + ",th";

            System.out.println(LOCATION);
            weather();
        });

    }

//        for (int i = 0; i < 77; i++) {
//
//        }

}
