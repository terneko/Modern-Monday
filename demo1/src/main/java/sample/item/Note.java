package sample.item;

import org.json.simple.JSONObject;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Note {

    private static FileWriter file;
    private int num = 0;
    protected String description;
    protected LocalDate saveDate;

    public Note(String description) {
        this.description = description;
        this.saveDate = LocalDate.now();
        readCountFile();
    }

    public LocalDate getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(LocalDate saveDate) {
        this.saveDate = saveDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void readCountFile() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("FileNote/Count"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        num = sc.nextInt();
    }

    public void saveJSON() {
        JSONObject obj = new JSONObject();
        obj.put("Description", description);
        obj.put("Day", saveDate);

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

    @Override
    public String toString() {
        return "Note{" +
                "num=" + num +
                ", description='" + description + '\'' +
                ", saveDate=" + saveDate +
                '}';
    }
}
