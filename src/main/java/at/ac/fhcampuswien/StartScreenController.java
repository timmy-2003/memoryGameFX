package at.ac.fhcampuswien;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;

public class StartScreenController {
    @FXML
    private TextField Textfield_1;
    @FXML
    private TextField Textfield_2;
    @FXML
    private Button Play_Button;


    @FXML

    public void submitNamePlayer1(ActionEvent event){
       // memory.getPlayer1().setName(Textfield_1.getText());
    }

    public void submitNamePlayer2(ActionEvent event){
        //memory.getPlayer2().setName(Textfield_2.getText());
    }
}
