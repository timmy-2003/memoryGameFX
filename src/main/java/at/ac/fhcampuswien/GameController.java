package at.ac.fhcampuswien;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {
    @FXML
    private GridPane Grid_Memory;
    private Button[] buttons;

    private Memory memory;
    @FXML private Label label_CurrentPlayer;
    @FXML private Label label_PlayerOneScore;
    @FXML private Label label_PlayerTwoScore;
    Timer timer = new Timer();

    @FXML
    private Button newGameButton;

    @FXML
    public void initialize() {                         //diese Methode wird ausgeführt sobald das Gui geladen ist
        String[] imageArray = new String[]{"Image_01.png", "Image_02.png", "Image_03.png"};
        buttons = new Button[Grid_Memory.getRowCount()*Grid_Memory.getColumnCount()];
        memory = new Memory(imageArray);
        updateCurrentPlayer();
        updatePoints();


        memory.newGame();


        for (int y = 0; y < Grid_Memory.getRowCount(); y++) {  //befüllt beliebig großes Grid mit Buttons
            for (int x = 0; x < Grid_Memory.getColumnCount(); x++) {
                Button button = new Button();
                Grid_Memory.add(button, x, y);
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // button fills cell
                button.setOnAction(actionEvent -> {
                    Button eventButton = (Button) actionEvent.getSource(); //findet den Button der gedrückt wurde

                    int buttonIndex = Integer.parseInt(eventButton.getId());
                    clickOnButton(buttonIndex);

                }); // was passieren soll, wenn man auf den Button clickt
                button.setId(String.valueOf(x+y*Grid_Memory.getColumnCount()));
                buttons[x+y*Grid_Memory.getColumnCount()] = button;
                refreshButton(x+y*Grid_Memory.getColumnCount());
            }
        }

    }

    public void clickOnButton(int index) {



        memory.selectCard(index);



        if (memory.getFirstSelectedCard() != null && memory.getSecondSelectedCard() != null) {
            if (memory.checkIfMatch(memory.getCurrentPlayer(), memory.getFirstSelectedCard(), memory.getSecondSelectedCard())) {
                memory.resetFirstSelectedCard();
                memory.resetSecondSelectedCard();
                updatePoints();

            } else {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            memory.getBoard().setCardState(memory.getFirstSelectedIndex(), false);
                            memory.getBoard().setCardState(memory.getSecondSelectedIndex(), false);
                            refreshButton(memory.getSecondSelectedIndex());
                            refreshButton(memory.getFirstSelectedIndex());
                            memory.switchPlayer();
                            updateCurrentPlayer();
                            memory.resetFirstSelectedCard();
                            memory.resetSecondSelectedCard();
                        });
                    }
                }, 2000l);


            }
        }
        refreshButton(memory.getSecondSelectedIndex());
        refreshButton(memory.getFirstSelectedIndex());

    }


    public void refreshButton(int index) {   //synchronisiert den Zustand der Karte, logik über den zustand(wann Vorderseite, wann Rückseite angezeigt werden soll)
        Card card = memory.getBoard().getCard(index);
        if (memory.getBoard().isOpen(index)) {
            buttons[index].setGraphic(new ImageView(card.getFront()));
        } else {
            buttons[index].setGraphic(new ImageView(card.getBackground()));
        }
    }

    @FXML
    public void clickNewGame(){
        initialize();
    }

    public void updateCurrentPlayer() {
        label_CurrentPlayer.setText(memory.getCurrentPlayer().getName() + ", it's your turn!");
    }

    public void setLabel_PlayerOneScore(){
        label_PlayerOneScore.setText(String.valueOf(memory.getPlayer1().getPoints()));
    }


    public void updatePoints(){
        if (memory.getPlayer1().getPoints() == 0 && memory.getPlayer2().getPoints() == 0){
            label_PlayerOneScore.setText(String.valueOf(memory.getPlayer1().getPoints()));
            label_PlayerTwoScore.setText(String.valueOf(memory.getPlayer2().getPoints()));
        }
        if (memory.getCurrentPlayer() == memory.getPlayer1()){
            label_PlayerOneScore.setText(String.valueOf(memory.getPlayer1().getPoints()));
        }else {
            label_PlayerTwoScore.setText(String.valueOf(memory.getPlayer2().getPoints()));
        }

    }





}