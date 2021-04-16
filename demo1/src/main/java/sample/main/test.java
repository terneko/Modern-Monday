package sample.main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class test {
//    public static void main(String[] args) {
//        Scanner read = null;
//        try {
//            read = new Scanner(new File("FileNote/noteDemo.json"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        //System.out.println(read.nextLine());
//
//        String ter = (String) read.next();
//        String description = "Description";
//        String day = "Day";
//        System.out.println(ter);
//
//        JSONParser parser = new JSONParser();
//
//        try {
//            Object obj = parser.parse(ter);
//            JSONArray array = (JSONArray) obj;
//            System.out.println(array.get(0));
//            obj = parser.parse(description);
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        try {
//            Object obj = parser.parse(new FileReader("/FileNote/noteDemo.json"));
//            JSONObject jsonObject = (JSONObject)obj;
//            String name = (String)jsonObject.get("Description");
//            String course = (String)jsonObject.get("Day");
//            JSONArray subjects = (JSONArray)jsonObject.get("Subjects");
//            System.out.println("Name: " + name);
//            System.out.println("Course: " + course);
//            System.out.println("Subjects:");
//            Iterator iterator = subjects.iterator();
//            while (iterator.hasNext()) {
//                System.out.println(iterator.next());
//            }
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//    }
public static void main(String[] args) {
    JSONParser parser = new JSONParser();
    
    try {
        Object object = parser.parse(new FileReader("FileNote/noteDemo.json"));
        JSONArray noteArray = (JSONArray) object;
        System.out.println(noteArray.get(1));
        JSONObject noteObject = (JSONObject)noteArray.get(1);

        String description = (String) noteObject.get("Description");
        System.out.println(description);

        String day = (String) noteObject.get("Day");
        System.out.println(day);


    } catch (ParseException e) {
        e.printStackTrace();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}