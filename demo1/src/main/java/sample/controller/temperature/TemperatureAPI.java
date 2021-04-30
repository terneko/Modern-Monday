package sample.controller.temperature;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.logging.Logger;

import static sample.controller.daycounter.DaycounterController.dragWidget;

public class TemperatureAPI implements Initializable {

    public AnchorPane tempPageMainPane;
    public Label mainLabel;
    public Label mainDescriptionLabel;
    public Label numberOfTemperature;
    public Label locationStatus;
    public Label dayStatus;
    public Label timeStatus;
    public Label weatherStatus;
    public ImageView cloudMain;
    public ImageView cloud;
    public ImageView cloudGradiant;
    public Label widgetTemp1;
    public Label widgetTemp2;
    public Label widgetTemp3;
    public Label widgetTemp4;
    public ImageView dayNightWid1;
    public ImageView dayNightWid2;
    public ImageView dayNightMain;
    public Label weatherStatus1;
    public Label provinceWidget1;
    public Label provinceWidget4;
    public Pane widgetPane2;
    public ImageView cImage;
    public ImageView fImage;
    public ImageView img1;
    public Pane widgetPane1;
    public Pane widgetPane3;
    public Pane widgetPane4;
    // set API information
    private String API_KEY = "5f26cbe1a121b474f1e7c6e6f6350943";
    private static String LOCATION = "กรุงเทพมหานคร,th";
    private String urlString;
    private static double temperature;
    private boolean celcius = true;
    private boolean farenheit = false;
    private static DecimalFormat twoDecimalPlace = new DecimalFormat("#.##");
    private static String weather;

    public static String getLOCATION() {
        return LOCATION;
    }

    public static double getTemperature() {
        return temperature;
    }

    public static String getWeather() {
        return weather;
    }

    @FXML
    ComboBox<String> selectedProvince;
    private JSONArray dataArray;

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

    public void setTooltipTempPage() {
        Tooltip.install(widgetPane1, new Tooltip("Click to open, Widget Style: background image."));
        Tooltip.install(widgetPane2, new Tooltip("Click to open, Widget Style: Transparent."));
        Tooltip.install(widgetPane3, new Tooltip("Click to open, Widget Style: random background color."));
        Tooltip.install(widgetPane4, new Tooltip("Click to open, Widget Style: background image."));
    }

    public void setTransitionWidget() {
//        Pane[] panes = {widgetPane1, widgetPane2, widgetPane3, widgetPane4};
//        widgetPane1.setOnMouseEntered(event -> {
//            ScaleTransition trans = new ScaleTransition(Duration.millis(200), widgetPane1);
//            trans.setFromX(1);
//            trans.setToX(1.05);
//            trans.setFromY(1);
//            trans.setToY(1.05);
//            trans.play();
//        });
    }

    public static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {
        }.getType());
        return map;
    }

    public void callAPI() {
        urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY + "&units =imperial&lang=th";
    }

    public void weather() {
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
            String response = result.toString();
            JSONObject obj = new JSONObject(response);
            Map<String, Object> respMap = jsonToMap(result.toString());
            Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
            Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());
            weather = (respMap.get("weather").toString()).substring(2, respMap.get("weather").toString().length() - 2);
            String[] weatherArray;
            weatherArray = weather.split(", ");
            for (String a : weatherArray) {
                String[] b = a.split("=");
                if (b[0].equals("description")) {
                    weather = b[1];
                }
            }

//            System.out.println("Current Temperature: " + mainMap.get("temp"));
//            System.out.println("Current Humidity: " + mainMap.get("humidity"));
//            System.out.println("Wind Speed: " + windMap.get("speed"));
//            System.out.println("Wind Angle: " + windMap.get("deg"));

            //get temperature
            temperature = (double) mainMap.get("temp");
            System.out.println(temperature);
            //weather = (String) weatherMap.get("main");
            setTemperature();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFadeTransitionTempPage();
        setProvinceToComboBox();
        setActionComboBox();
        weather();
        setColorPane();
        //setTransitionWidget();
        setTooltipTempPage();
    }

    public void setProvinceToComboBox() {
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
        selectedProvince.getItems().addAll(province);
    }

    public void setActionComboBox() {
        selectedProvince.setOnAction((event) -> {
            int selectedIndex = selectedProvince.getSelectionModel().getSelectedIndex();
            Object selectedItem = selectedProvince.getSelectionModel().getSelectedItem();
//            System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
//            System.out.println("   ComboBox.getValue(): " + selectedProvince.getValue());
            LOCATION = selectedProvince.getValue() + ",th";
            setNewTemperature();
            setTemperature();
        });
    }

    private void setTemperature() {
        if (celcius == true) {
            numberOfTemperature.setText(String.valueOf((int) (temperature - 273.15)));
            cImage.setOpacity(1);
            fImage.setOpacity(0.3);
        } else {
            numberOfTemperature.setText(String.valueOf(String.valueOf((int) (((getTemperature() - 273.15) * 9 / 5) + 32))));
            cImage.setOpacity(0.3);
            fImage.setOpacity(1);
        }
        widgetToSyncTemp();
        setIconDayAndNight();
        setInfoBar();
    }

    public void setNewTemperature() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            weather();
        }),
                new KeyFrame(Duration.seconds(60 * 5))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void widgetToSyncTemp() {
        widgetTemp1.setText(String.valueOf((int) (temperature - 273.15)));
        widgetTemp2.setText(String.valueOf((int) (temperature - 273.15)));
        widgetTemp3.setText(String.valueOf((int) (temperature - 273.15)));
        widgetTemp4.setText(String.valueOf((int) (temperature - 273.15)));

    }

    public void setIconDayAndNight() {
        LocalTime currentTime = LocalTime.now();
        if (currentTime.getHour() >= 5 && currentTime.getHour() <= 17) {
            System.out.println(currentTime.getHour());
            dayNightWid1.setImage(new Image("@../../sun.png"));
            dayNightWid2.setImage(new Image("@../../sun.png"));
            dayNightMain.setImage(new Image("@../../sun.png"));
        } else {
            System.out.println(currentTime.getHour());
            dayNightWid1.setImage(new Image("@../../night.png"));
            dayNightWid2.setImage(new Image("@../../night.png"));
            dayNightMain.setImage(new Image("@../../night.png"));
        }
    }

    public void setInfoBar() {
        LocalTime currentTime = LocalTime.now();
        Date d = new Date();
        String hourText = "00" + currentTime.getHour();
        String minutesText = "00" + (currentTime.getMinute());
        hourText = hourText.substring(hourText.length() - 2);
        minutesText = minutesText.substring(minutesText.length() - 2);
        String[] oneWeek = {
                "Sunday"
                , "Monday"
                , "Tuesday"
                , "Wednesday"
                , "Thursday"
                , "Friday"
                , "Saturday"};
        timeStatus.setText(hourText + ":" + minutesText);
        locationStatus.setText(LOCATION.substring(0, LOCATION.length() - 3));
        dayStatus.setText(String.valueOf(oneWeek[d.getDay()]));
        weatherStatus.setText(weather);
        weatherStatus1.setText(weather);
        provinceWidget1.setText(String.valueOf(getLOCATION()).substring(0, String.valueOf(getLOCATION()).length() - 3));
        provinceWidget4.setText(String.valueOf(getLOCATION()).substring(0, String.valueOf(getLOCATION()).length() - 3));
    }

    public void widgetTemp1(MouseEvent mouseEvent) {
        loadTemperatureWidget("widgetTemp1.fxml");
    }

    public void widgetTemp2(MouseEvent mouseEvent) {
        loadTemperatureWidget("widgetTemp2.fxml");
    }

    public void widgetTemp3(MouseEvent mouseEvent) {
        loadTemperatureWidget("widgetTemp3.fxml");
    }

    public void widgetTemp4(MouseEvent mouseEvent) {
        loadTemperatureWidget("widgetTemp4.fxml");
    }

    public void loadTemperatureWidget(String nameTemp) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/temperature/" + nameTemp));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("temperature");
            dragWidget(root1, stage);
            Scene scene = new Scene(root1);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setColorPane() {
        Stop[] stops = new Stop[] { new Stop(0, Color.YELLOW), new Stop(1, Color.GREEN)};
        LinearGradient lgcolor = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        BackgroundFill bgfill = new BackgroundFill(lgcolor, CornerRadii.EMPTY, Insets.EMPTY);
        widgetPane2.setBackground(new Background(bgfill));
    }


    public void cCLick(MouseEvent mouseEvent) {
        celcius = true;
        farenheit = false;
        numberOfTemperature.setText(String.valueOf((int) (temperature - 273.15)));
        cImage.setOpacity(1);
        fImage.setOpacity(0.3);
    }

    public void fClick(MouseEvent mouseEvent) {
        celcius = true;
        farenheit = false;
        numberOfTemperature.setText(String.valueOf(String.valueOf((int) (((temperature - 273.15) * 9 / 5) + 32))));
        cImage.setOpacity(0.3);
        fImage.setOpacity(1);
    }
}
