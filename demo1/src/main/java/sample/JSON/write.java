package sample.JSON;


import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class write {
    private static FileWriter file;
    private static int num = 1;
    private final String text;
    private final LocalDate day;

    public write(String text, LocalDate day) {
        this.text = text;
        this.day = day;
    }

    public void parseJSON() {
        JSONObject obj = new JSONObject();
        obj.put("Description", text);
        obj.put("Day", day);

        System.out.print(obj);

        try {

            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("FileNote/note" + num + ".json");
            file.write(obj.toJSONString());

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
