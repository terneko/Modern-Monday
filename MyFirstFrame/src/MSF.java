import java.awt.*;
import javax.swing.*;


public class MSF extends MFF {

    protected JButton cancelButton;
    protected JPanel buttonPanel2,mainPanel2;

    public MSF(String name) {
        super(name);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public static void createAndShowGUI() {
        MSF wnd = new MSF("Jirathon G2");
        wnd.addComponents();
        wnd.setFrameFeature();
    }

    @Override
    protected void addComponents() {
        buttonPanel2 = new JPanel();
        cancelButton = new JButton("Cancel");
        buttonPanel2.add(cancelButton);
        buttonPanel2.add(mainPanel);
        mainPanel2.add(buttonPanel2);
        setContentPane(mainPanel2);
    }

}
