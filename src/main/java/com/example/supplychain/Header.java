package com.example.supplychain;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class Header {  // loading the fxml file in this
    public AnchorPane root; // AnchorPane is initialized because the header.fmxl fine is used as AnchorPane
   Header() throws IOException{


       root = FXMLLoader.load((getClass().getResource("Header.fxml")));

   }
}
