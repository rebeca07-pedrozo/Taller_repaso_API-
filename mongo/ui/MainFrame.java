package ui;

import dao.MongoDAO;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
    private JTextField keyField;
    private JTextField valueField;
    private MongoDAO mongoDAO;

    public MainFrame() {
        super("Insertar en MongoDB");

        mongoDAO = new MongoDAO("records");

        setLayout(new GridLayout(3, 2));

        add(new JLabel("Clave:"));
        keyField = new JTextField();
        add(keyField);

        add(new JLabel("Valor:"));
        valueField = new JTextField();
        add(valueField);

        JButton insertBtn = new JButton("Insertar");
        insertBtn.addActionListener(this::onInsert);
        add(insertBtn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
    }

    private void onInsert(ActionEvent e) {
        String key = keyField.getText();
        String value = valueField.getText();

        if (key.isEmpty() || value.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Llena todos los campos");
            return;
        }

        Document doc = new Document("key", key).append("value", value);
        mongoDAO.insert(doc);
        JOptionPane.showMessageDialog(this, "Documento insertado!");
        keyField.setText("");
        valueField.setText("");
    }
}
