package controller;

import com.neb.nebotools.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TwoStepController implements Initializable {

    @FXML
    private TextField digit1;

    @FXML
    private TextField digit2;

    @FXML
    private TextField digit3;

    @FXML
    private TextField digit4;

    @FXML
    private TextField digit5;

    @FXML
    private TextField digit6;

    @FXML
    void verify(ActionEvent event) throws IOException {
        Stage stage = ((Stage) (digit1.getScene().getWindow()));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/app.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("french");
        fxmlLoader.setResources(bundle);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("light.css");
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        digit1.textProperty().addListener((observable,oldValue,newValue)->{
            if(digit(digit1,oldValue,newValue))
                digit2.requestFocus();
        });
        digit2.textProperty().addListener((observable,oldValue,newValue)->{
            if(digit(digit2,oldValue,newValue))
                digit3.requestFocus();
        });
        digit3.textProperty().addListener((observable,oldValue,newValue)->{
            if(digit(digit3,oldValue,newValue))
                digit4.requestFocus();
        });
        digit4.textProperty().addListener((observable,oldValue,newValue)->{
            if(digit(digit4,oldValue,newValue))
                digit5.requestFocus();
        });
        digit5.textProperty().addListener((observable,oldValue,newValue)->{
            if(digit(digit5,oldValue,newValue))
                digit6.requestFocus();
        });
        digit6.textProperty().addListener((observable,oldValue,newValue)->{
            digit(digit6,oldValue,newValue);
        });

    }

    private boolean digit(TextField digit, String oldValue, String newValue) {
        if(!newValue.isEmpty() && newValue.charAt(0)>='0' && newValue.charAt(0)<='9')
            digit.setText((oldValue.length() == 1)?oldValue:newValue);
        else
            digit.setText("");
        return !digit.getText().isEmpty();
    }


}
