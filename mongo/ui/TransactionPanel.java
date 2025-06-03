package ui;

import db.PostgresConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionPanel extends JPanel {
    private JButton commitBtn;
    private JButton rollbackBtn;
    private Connection connection;

    public TransactionPanel() {
        setLayout(new FlowLayout());

        commitBtn = new JButton("Commit");
        rollbackBtn = new JButton("Rollback");

        add(commitBtn);
        add(rollbackBtn);

        try {
            connection = db.PostgresConnection.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error conectando a PostgreSQL");
        }

        commitBtn.addActionListener(this::commit);
        rollbackBtn.addActionListener(this::rollback);
    }

    private void commit(ActionEvent e) {
        try {
            connection.commit();
            JOptionPane.showMessageDialog(this, "Commit realizado");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en commit");
        }
    }

    private void rollback(ActionEvent e) {
        try {
            connection.rollback();
            JOptionPane.showMessageDialog(this, "Rollback realizado");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en rollback");
        }
    }
}
