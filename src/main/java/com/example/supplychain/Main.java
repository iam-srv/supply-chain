package com.example.supplychain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends Application {
    public static DatabaseConnection connection;
    public static Group root; // for multiple things to render group is declared

    public static String emailId;

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        emailId = "";
        // object of DatabaseConnection
       connection = new DatabaseConnection();

        root = new Group();
        Header header = new Header();

        // adding the product list into the header page
        productPage products = new productPage();
        ListView<HBox> productList = products.showProducts();
        AnchorPane productPane = new AnchorPane();
        productPane.setLayoutX(150);
        productPane.setLayoutY(100);
        productPane.getChildren().add(productList);

        root.getChildren().addAll(header.root , productPane);
        stage.setTitle("Supply Chain");
        stage.setScene(new Scene(root));
        stage.show();


        // Function for closing the database connection when the stage is closed
        stage.setOnCloseRequest(e ->{
            try {
                System.out.println("CONNECTION IS CLOSED");
                connection.con.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}