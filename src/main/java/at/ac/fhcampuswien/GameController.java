package at.ac.fhcampuswien;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.Timer;
import java.util.TimerTask;




public class GameController {
    @FXML
    private GridPane Grid_Memory;
    private Button[] buttons;

    @FXML private Label label_CurrentPlayer;
    @FXML private Button newGameButton;
    @FXML private Label label_PlayerOneScore, label_PlayerTwoScore;

    Timer timer = new Timer();

    private Memory memory;
    private Board board;
    @FXML
    public void initialize() {                         //diese Methode wird ausgeführt sobald das Gui geladen ist
        String[] imageArray = new String[]{"Image_01.png", "Image_02.png", "Image_03.png"};
        buttons = new Button[Grid_Memory.getRowCount()*Grid_Memory.getColumnCount()];

        Player player1 = new Player(null, 0);
        Player player2 = new Player(null, 0);



        memory = new Memory(imageArray);
        board = new Board();

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
        refreshButton(index);

        if(memory.checkIfMatch(memory.getCurrentPlayer(), memory.getFirstSelectedCard(), memory.getSecondSelectedCard())){
            memory.selectCard(index);
            memory.setFirstSelectedCard(null);
            memory.setSecondSelectedCard(null);

        }else {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    board.setCardState(memory.getFirstSelectedIndex(), false);
                    board.setCardState(memory.getSecondSelectedIndex(), false);

                }
            }, 2000);
        }
        refreshButton(index);
    }


    public void refreshButton(int index){   //synchronisiert den Zustand der Karte, logik über den zustand(wann Vorderseite, wann Rückseite angezeigt werden soll)
        Card card = memory.getBoard().getCard(index);
        if(memory.getBoard().isOpen(index)){
            buttons[index].setGraphic(new ImageView(card.getFront()));
        }
        else {
            buttons[index].setGraphic(new ImageView(card.getBackground()));
        }
    }


    /*@FXML public void setLabel_CurrentPlayer(String s){
        label_CurrentPlayer.setText(s);
    }
*/

    @FXML public void clickNewGame(){
        initialize();
    }

    @FXML public void setLabel_PlayerOneScore(int points){
        label_PlayerOneScore.setText(String.valueOf(points));
    }

    @FXML public void setLabel_PlayerTwoScore(int points){
        label_PlayerTwoScore.setText(String.valueOf(points));
    }






}