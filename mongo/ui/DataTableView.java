package ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Record;

public class DataTableView extends Application {
    private TableView<Record> tableView;

    @Override
    public void start(Stage stage) {
        tableView = new TableView<>();

        TableColumn<Record, String> keyCol = new TableColumn<>("Clave");
        keyCol.setCellValueFactory(new PropertyValueFactory<>("key"));

        TableColumn<Record, String> valueCol = new TableColumn<>("Valor");
        valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));

        tableView.getColumns().addAll(keyCol, valueCol);

        ObservableList<Record> data = FXCollections.observableArrayList(
                new Record("1", "x 1"),
                new Record("2", "x 2")
                new Record("3", "x 3")
        );

        tableView.setItems(data);

        Scene scene = new Scene(tableView, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Datos almacenados");
        stage.show();
    }
}
