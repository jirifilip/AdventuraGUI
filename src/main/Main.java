/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Arrays;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;

/**
 *
 * @author filj03
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        IHra hra = new Hra();
        TextoveRozhrani ui = new TextoveRozhrani(hra);
        
        Button btn = new Button();
        BorderPane borderPane = new BorderPane();
        
        TextArea centerText = new TextArea();
        centerText.setText(hra.vratUvitani());
        centerText.setEditable(false);
        borderPane.setCenter(centerText);
        
        
        Label enterCommandLabel = new Label();
        enterCommandLabel.setText("Zadej příkaz");
        enterCommandLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        TextField enterCommandTextField = new TextField("Sem zadej příkaz");
        enterCommandTextField.setOnAction(evt -> {
            String enteredCmd = enterCommandTextField.getText();
            String row = hra.zpracujPrikaz(enteredCmd);
            
            centerText.appendText("\n\n> " + enteredCmd);
            centerText.appendText("\n\n> " + row);
            
            enterCommandTextField.clear();
            
            if (hra.konecHry()) {
                 enterCommandTextField.setEditable(false);
            }
        });
        
        centerText.textProperty().addListener(evt -> {
            centerText.setScrollTop(Double.MAX_VALUE);
        });
        
        FlowPane bottomPanel = new FlowPane();
        bottomPanel.setAlignment(Pos.CENTER);
        bottomPanel.getChildren().addAll(enterCommandLabel, enterCommandTextField);
        
        FlowPane imagePane = new FlowPane();
        ImageView image = new ImageView(
                new Image(Main.class.getResourceAsStream("/zdroje/mapa.jpg"),
                250,
                156,
                false,
                false
        ));
        
        imagePane.getChildren().add(image);
        
        borderPane.setBottom(bottomPanel);
        borderPane.setLeft(image);
       
        Scene scene = new Scene(borderPane, 700, 500);

        primaryStage.setTitle("Červená karkulka");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        enterCommandTextField.requestFocus();
        
        borderPane.setOnKeyPressed(e -> {
            printAll("sdf", "sdfs", "dfds");
            if (e.getCode().equals(KeyCode.ESCAPE)) {
                primaryStage.close();
            }
        });
    }
    
    public static void printAll(String... strings) {
        Arrays
            .stream(strings)
            .forEach(System.out::println);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        } else {
            if (args[0].equals("text")) {
                IHra hra = new Hra();
                TextoveRozhrani ui = new TextoveRozhrani(hra);
                ui.hraj();
            } else {
                launch(args);
                
//                System.out.println("Neplatný parametr");
//                System.exit(1);
            }
        }

    }

}
