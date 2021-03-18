import javax.swing.*;
import java.awt.*;

public class MyLabel {

    public static void main(String[] args) {
        // JLabel = a GUI display area for a string of text, animage, or both

        ImageIcon image = new ImageIcon("inte.png");

        JLabel label = new JLabel(); // create a label
        label.setText("BRO, Do you even code?"); //set text of label
        label.setIcon(image);
        label.setHorizontalAlignment(JLabel.CENTER); //set text left,center,right of icon
        label.setVerticalTextPosition(JLabel.TOP); /// settext top center bottom of iimageicon
        label.setForeground(new Color(0x00FF00));
        label.setFont(new Font("MV Boli",Font.PLAIN,20));
        label.setIconTextGap(-25);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.add(label);

    }
}
