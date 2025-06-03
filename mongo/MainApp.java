import db.MongoConnection;
import ui.MainFrame;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        MongoConnection.connect();

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });

    }
}
