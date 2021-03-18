import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    MyFrame(){
        this.setTitle("Jthis title goes here"); // set title of this
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
        this.setResizable(false); // prevent this from being resized
        this.setSize(420,420); // set the x-dimension, and y-dimension of this
        this.setVisible(true); // make this visible

        ImageIcon image = new ImageIcon("logo (2).png"); // create an ImageIcon
        this.setIconImage(image.getImage()); // change icon of this
        this.getContentPane().setBackground(new Color(123,50,250)); // change color of background

    }
}
