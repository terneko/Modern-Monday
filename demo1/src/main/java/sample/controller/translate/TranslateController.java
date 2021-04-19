package sample.controller.translate;

import com.darkprograms.speech.translator.GoogleTranslate;
import sample.controller.daycounter.DaycounterController;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import javafx.animation.ScaleTransition;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sample.controller.Controller;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TranslateController extends Controller implements Initializable {

    private List<String> languages = new ArrayList<>();
    private List<String> alpha2 = new ArrayList<>();
    public AnchorPane translateWidgetMainPane;
    public AnchorPane translatePageMainPane;
    public ComboBox sourceTranslation;
    public ComboBox targetTranslation;
    public Label mainDescriptionLabel;
    private int tempSwitchLanguage1;
    private int tempSwitchLanguage2;
    public String sourceLanguage;
    public String targetLanguage;
    public TextArea fromText;
    public TextArea toText;
    public Label mainLabel;
    private String text;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFadeTransitionTranslatePage();
        getData();
        sourceLanguage = "en";
        targetLanguage = "th";
        tempSwitchLanguage1 = 1;
        tempSwitchLanguage2 = 0;
        sourceTranslation.getItems().addAll(languages);
        sourceTranslation.setOnAction((event) -> {
            int selectedIndex = sourceTranslation.getSelectionModel().getSelectedIndex();
            sourceLanguage = alpha2.get(selectedIndex);
            tempSwitchLanguage1 = selectedIndex;
            //System.out.println(sourceLanguage);
        });

        targetTranslation.getItems().addAll(languages);
        targetTranslation.setOnAction((event) -> {
            int selectedIndex = targetTranslation.getSelectionModel().getSelectedIndex();
            targetLanguage = alpha2.get(selectedIndex);
            tempSwitchLanguage2 = selectedIndex;
            //System.out.println(targetLanguage);
        });

    }

    private void getData() {
        JSONParser parser = new JSONParser(); //ตัวอ่าน
        JSONArray languageArray = null; //ประกาศ
        try {
            Object object = parser.parse(new FileReader("FileNote/language.json")); //พาทไฟล์
            languageArray = (JSONArray) object;
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        //ไล่อ่านไฟล์
        for (int i = 0; i < languageArray.size(); i++) {
            JSONObject languageObj = (JSONObject) languageArray.get(i);
            String languageInEng = (String) languageObj.get("English");
            String alpabet2 = (String) languageObj.get("alpha2");
            languages.add(languageInEng); //เพิ่มไฟล์เข้าสู่List
            alpha2.add(alpabet2); //เพิ่มไฟล์เข้าสู่List
        }
    }

    public void setFadeTransitionTranslatePage() {
        FadeTransition mainPaneFade = new FadeTransition(Duration.millis(500), translatePageMainPane);
        mainPaneFade.setFromValue(0);
        mainPaneFade.setToValue(9);
        mainPaneFade.play();

        Label[] labels = {mainLabel, mainDescriptionLabel};
        /*for (Label label : labels) {
            TranslateTransition labelTrans = new TranslateTransition();
            labelTrans.setByX(2);
            labelTrans.setDuration(Duration.millis(1200));
            labelTrans.setNode(label);
            labelTrans.play();
        }*/

        for (Label label : labels) {
            ScaleTransition labelTrans = new ScaleTransition(Duration.seconds(0.6), label);
            labelTrans.setFromX(0.98);
            labelTrans.setToX(1);
            labelTrans.setFromY(0.98);
            labelTrans.setToY(1);
            labelTrans.play();
        }
    }

    public void translation() {
        try {
            text = GoogleTranslate.translate(sourceLanguage, targetLanguage, fromText.getText());
            // Copyright Porrapipat canput skrrrr
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void openTranslatorWidget(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/addTranslateWidget.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Translator");
            DaycounterController.dragWidget(root1, stage);
            stage.setScene(new Scene(root1));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchLanguage(MouseEvent mouseEvent) {
        // swap the alphabet
        String temp;
        temp = sourceLanguage;
        sourceLanguage = targetLanguage;
        targetLanguage = temp;

        // swap name of language
        int temp2;
        temp2 = tempSwitchLanguage1;
        tempSwitchLanguage1 = tempSwitchLanguage2;
        tempSwitchLanguage2 = temp2;

        // choose selected index
        sourceTranslation.getSelectionModel().select(tempSwitchLanguage1);
        targetTranslation.getSelectionModel().select(tempSwitchLanguage2);
    }
    public void entered(KeyEvent k) {
        if (k.getCode().equals(KeyCode.ENTER)) {
            translation();
            toText.setText(text);
        }
    }

    public void translateButton(MouseEvent mouseEvent) {
        translation();
        toText.setText(text);
    }

    public void closeWidget(MouseEvent mouseEvent) {
        Stage stage = (Stage) translateWidgetMainPane.getScene().getWindow();
        stage.close();
    }
}
