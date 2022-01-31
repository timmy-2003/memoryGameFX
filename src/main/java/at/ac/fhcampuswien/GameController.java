package at.ac.fhcampuswien;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.Timer;
import java.util.TimerTask;

public class GameController {
    @FXML
    private GridPane Grid_Memory;
    private Button[] buttons;
    private Memory memory;
    @FXML
    private Label label_CurrentPlayer;
    @FXML
    private Label label_PlayerOneScore;
    @FXML
    private Label label_PlayerTwoScore;
    @FXML
    private Button newGameButton;
    Timer timer = new Timer();
    private boolean timerRunning;
    String[] christmas = new String[]{"front_1.png", "front_2.png", "front_3.png", "front_4.png", "front_5.png", "front_6.png", "front_7.png", "front_8.png", "front_9.png", "front_10.png",
            "front_11.png", "front_12.png", "front_13.png", "front_14.png", "front_15.png", "front_16.png", "front_17.png", "front_18.png", "front_19.png", "front_20.png", "front_21.png",
            "front_22.png", "front_23.png", "front_24.png"};
    String[] countries = new String[]{"flag (1).png", "flag (2).png", "flag (3).png", "flag (4).png", "flag (5).png", "flag (6).png", "flag (7).png", "flag (8).png", "flag (9).png", "flag (10).png", "flag (11).png", "flag (12).png",
            "flag (13).png", "flag (14).png", "flag (15).png", "flag (16).png", "flag (17).png", "flag (18).png", "flag (19).png", "flag (20).png", "flag (21).png", "flag (22).png", "flag (23).png", "flag (24).png", "flag (25).png", "flag (26).png", "flag (27).png",
            "flag (28).png", "flag (29).png", "flag (30).png", "flag (31).png", "flag (32).png", "flag (33).png", "flag (34).png", "flag (35).png", "flag (36).png", "flag (37).png", "flag (38).png", "flag (39).png", "flag (40).png", "flag (41).png", "flag (42).png", "flag (43).png", "flag (44).png"};

    @FXML
    public void initialize() {
        buttons = new Button[Grid_Memory.getRowCount() * Grid_Memory.getColumnCount()];
        memory = new Memory(countries);

        updateHeaderLabel();
        updatePoints();
        newGameButton.setText("New game!");
        memory.newGame();
        for (int rows = 0; rows < Grid_Memory.getRowCount(); rows++) {  // fills a grid with buttons
            for (int columns = 0; columns < Grid_Memory.getColumnCount(); columns++) {
                Button button = new Button();
                Grid_Memory.add(button, columns, rows);
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // button fills cell
                button.setOnAction(actionEvent -> {
                    Button eventButton = (Button) actionEvent.getSource(); // finds the button that was clicked

                    int buttonIndex = Integer.parseInt(eventButton.getId());
                    if (!timerRunning) {
                        clickOnButton(buttonIndex);
                    }

                });
                button.setId(String.valueOf(columns + rows * Grid_Memory.getColumnCount())); // each button gets an ID that corresponds to its index
                buttons[columns + rows * Grid_Memory.getColumnCount()] = button;
                refreshButton(columns + rows * Grid_Memory.getColumnCount());
            }
        }
    }

    /**
     * @param buttonIndex is used to identify the button that was clicked
     * a timer is used to show two non-matching cards to the player for one second
     */
    public void clickOnButton(int buttonIndex) {

        memory.selectCard(buttonIndex);
        if (memory.getFirstSelectedCard() != null && memory.getSecondSelectedCard() != null) {
            if (memory.checkIfMatch(memory.getCurrentPlayer(), memory.getFirstSelectedCard(), memory.getSecondSelectedCard())) {
                memory.resetFirstSelectedCard();
                memory.resetSecondSelectedCard();
                updatePoints(); // If the cards match, the points are updated
            } else {
                timerRunning = true;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            memory.getBoard().setCardState(memory.getFirstSelectedIndex(), false);
                            memory.getBoard().setCardState(memory.getSecondSelectedIndex(), false);
                            refreshButton(memory.getSecondSelectedIndex());
                            refreshButton(memory.getFirstSelectedIndex());
                            memory.switchPlayer();
                            updateHeaderLabel();
                            memory.resetFirstSelectedCard();
                            memory.resetSecondSelectedCard();
                            timerRunning = false;
                        });
                    }
                }, 1000l);
            }
        }
        refreshButton(memory.getSecondSelectedIndex());
        refreshButton(memory.getFirstSelectedIndex());
        updateHeaderLabel();
    }


    /**
     * refreshButton synchronises the state of the card and correspondingly shows either the front or rear of the card at a specified index
     *
     * @param index specifies the location of the button
     */
    public void refreshButton(int index) {
        Card card = memory.getBoard().getCard(index);
        if (memory.getBoard().isOpen(index)) {
            buttons[index].setGraphic(new ImageView(card.getFront()));
        } else {
            buttons[index].setGraphic(new ImageView(card.getRear()));
        }
    }

    /**
     * clickNewGame is called when the game-reset button is clicked
     */
    @FXML
    public void clickNewGame() {
        timer.cancel();
        timer = new Timer();
        timerRunning = false;
        memory.newGame();
        for (int i = 0; i < buttons.length; i++) {
            refreshButton(i);
        }
        updateHeaderLabel();
        updatePoints();
    }

    public void updateHeaderLabel() { // method controls the header label
        label_CurrentPlayer.setText(memory.getCurrentPlayer().getName() + ", it's your turn!");
        if (memory.checkIfEnd()) {
            label_CurrentPlayer.setText(memory.checkWhoWon());
        }
    }

    public void updatePoints() { // responsible for displaying the points on the GUI

        label_PlayerOneScore.setText(memory.getPlayer1().getName() + ": " + memory.getPlayer1().getPoints());
        label_PlayerTwoScore.setText(memory.getPlayer2().getName() + ": " + memory.getPlayer2().getPoints());

    }

    public Memory getMemory() {
        return memory;
    }

    /**
     * allows the user to choose the card theme, Christmas is chosen by default (in case the user doesn't select anything)
     *
     * @param themeIndex is the index (order) by which the different choices appear in the FX ChoiceBox
     */
    public void selectTheme(int themeIndex) {
        switch (themeIndex) {
            default:
            case 0:
                memory = new Memory(christmas);
                break;
            case 1:
                memory = new Memory(countries);
                break;

        }

        clickNewGame();
    }
}