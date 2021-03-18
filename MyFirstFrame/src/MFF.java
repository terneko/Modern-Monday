import java.awt.*;
import javax.swing.*;

public class MFF extends JFrame {

    protected JButton button,cancelButton;
    protected JPanel mainPanel, buttonPanel;

    public MFF(String name) {
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
        MFF wnd = new MFF("My Simple Window");
        wnd.addComponents();
        wnd.setFrameFeature();
    }

    protected void addComponents() {
        button = new JButton("OK");
        //cancelButton = new JButton("Cancel");

        mainPanel = new JPanel();
        buttonPanel = new JPanel();

        buttonPanel.add(button);
        //buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel);

        //add(mainPanel);
        //or setContentPane(mainPanel); this method is same line 35
        setContentPane(mainPanel);

    }

    public void setFrameFeature() {
        // set visible
        setVisible(true);

        // set center screen
        setLocationRelativeTo(null);

        // pack frame
        pack();

        // close program on exit windows.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
