package main;

import java.util.Arrays;
import ui.Map;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import ui.GameTextArea;
import ui.Inventory;
import ui.MenuField;
import ui.NextRoom;
import ui.RoomInventory;
import uiText.TextoveRozhrani;

/**
 *  Třída Main - obsahuje metodu main pro spuštění celého programu.
 *  Umožňuje spuštění pouze jako textové verze pomocí parametru
 *  příkazové řádky "text". Rovněž slouží jako hlavní okno grafického
 *  rozhraní, ve kterém vytváříme všechny další komponenty a kontejnery
 *  pro grafické prvky.
 *
 *@author     Jiří Filip
 *@version    4.0
 *@created    listopad 2017
 */
public class Main extends Application {
    
    private Map map;
    private MenuField menu;
    
    private IHra hra;
    
    private GameTextArea centerText;
    private Stage primaryStage;
    
    private Inventory inventory;
    private RoomInventory roomInventory;
    
    private NextRoom nextRoomGroup;
    private VBox leftVBox;
    
    private BorderPane mainBorderPane;

    private Accordion rightAccordion;
    
    private FlowPane bottomPanel;
    
    
    
    /**
     * Methoda slouží pro spuštění grafické verze programu.
     * Vytvoříme v ní instance důležitých grafických komponent a prvků 
     * a ty poté vsadíme do hlavního kontejneru a vytvoříme scénu.
     * 
     * @param primaryStage 
     * @Override
     */
    public void start(Stage primaryStage) {
        hra = new Hra();
        
        this.primaryStage = primaryStage;
        
        
        TextoveRozhrani ui = new TextoveRozhrani(hra);
        
             
        createMenu();
        
        createBottomPanel();
        
        createCenterText();
        
        createInventoryPanel();
        
        createNextRoomMenu();
        
        createLeftPanel();
        
        mainBorderPane = new BorderPane();
        
        mainBorderPane.setCenter(centerText);
        mainBorderPane.setBottom(bottomPanel);
        mainBorderPane.setLeft(leftVBox);
        mainBorderPane.setTop(menu);
        mainBorderPane.setRight(rightAccordion);
        mainBorderPane.setStyle("-fx-background-color: #89af7c;");
        
        
        Scene scene = new Scene(mainBorderPane, 900, 500);

        primaryStage.setTitle("Červená karkulka");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        
    }
    
    /**
     * Metoda vytvoří menu
     */
    private void createMenu() {
        map = new Map(hra);
        menu = new MenuField(this);
    }
    
    /**
     * Metoda vytvoří levý panel s mapou
     * a menu pro vybrání dalšího prostoru.
     */
    private void createLeftPanel() {
        VBox roomGroup = new VBox(nextRoomGroup);
        roomGroup.setPadding(new Insets(5, 5, 5, 5));
        leftVBox = new VBox();
        leftVBox.setPadding(new Insets(10, 10, 10, 10));
        leftVBox.setMaxWidth(270);
        leftVBox.getChildren().addAll(map, roomGroup); 
    }
    
    /**
     * Metoda vytvoří centrální textovou oblast hry
     */
    private void createCenterText() {
        centerText = new GameTextArea(hra);
        
        centerText.textProperty().addListener(evt -> {
            centerText.setScrollTop(Double.MAX_VALUE);
        });
    }
    
    /**
     * Metoda vytvoří panel s inventářem a předměty v prostoru.
     * 
     * Poté je registruje jako subscribery instance hry. 
     */
    public void createInventoryPanel() {
        inventory = new Inventory(hra, this);
        roomInventory = new RoomInventory(hra, this);
        
        Hra game = (Hra) hra;
        game.subscribe(inventory);
        game.subscribe(roomInventory);
        
        rightAccordion = new Accordion();
        
        TitledPane inventoryTitledPane = new TitledPane("Batoh", inventory);
        TitledPane roomInventoryTitledPane = new TitledPane("Věci v prostoru", roomInventory);
        
        rightAccordion.getPanes().addAll(inventoryTitledPane, roomInventoryTitledPane);
    }
    
    /**
     * Metoda vytvoří menu pro výběr další místnosti
     * 
     */
    public void createNextRoomMenu() {
        nextRoomGroup = new NextRoom(hra.getHerniPlan());
        
        nextRoomGroup.subscribe(centerText);
        nextRoomGroup.subscribe(roomInventory);
    }
    
    /**
     * Metoda vytvoří dolní panel s polem pro zadávání příkazů
     * a přiřadí jim event handlery pro kliknutí.
     * 
     */
    private void createBottomPanel() {
        bottomPanel = new FlowPane();
        bottomPanel.setPadding(new Insets(10, 10, 10, 10));
        
        Label enterCommandLabel = new Label();
        enterCommandLabel.setText("Zadej příkaz\t");
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
        
        enterCommandTextField.requestFocus();
        
        bottomPanel.setAlignment(Pos.CENTER);
        bottomPanel.getChildren().addAll(enterCommandLabel, enterCommandTextField);
    }
    
    
    /**
     * Getter pro centerText
     * 
     * @return centerText
     */
    public GameTextArea getCenterText() {
        return centerText;
    }
    
    /**
     * Getter pro primaryStage
     * 
     * @return primaryStage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    /**
     * Main metoda pro start celého programu.
     * Když zadáme při spouštění z příkazové řádky
     * první argument "text", spustíme pouze textovou verzi.
     * V opačném případě spouštíme verzi grafickou.
     * 
     * @param args argumenty příkazové řádky
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
            }
        }

    }

    /**
     * Metoda signalizuje začátek nové hry všem observerům.
     * 
     * 
     */
    public void newGame() {
        hra = new Hra();
        
        centerText.setText(hra.vratUvitani());
        
        map.newGame(hra);
        inventory.newGame(hra);
        roomInventory.newGame(hra);
        nextRoomGroup.newGame(hra);
    }

}
