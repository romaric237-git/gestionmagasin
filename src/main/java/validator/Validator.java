package validator;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Validator {

    private Control control;
    private String messageEmpty;
    private String messageLength;
    private String messageNotPatern;
    public Validator(Control control, String messageEmpty, String messageNotPatern, String pattern, String messageLength, int lenght){
        this.control = control;

        if(control instanceof TextInputControl)
            control.setOnKeyReleased(a->{
                TextInputControl textInputControl = (TextInputControl) control;
                textInputControl.setOnKeyReleased(k->{
                    if(control.getParent() instanceof Pane parent) {
                        if (parent.getChildren().size() > parent.getChildren().indexOf(control) + 1) {
                            parent.getChildren().remove(parent.getChildren().indexOf(control) + 1);
                        }
                        if (!messageEmpty.isEmpty() && textInputControl.getText().isEmpty()) {
                            Label error = new Label(messageEmpty);
                            error.getStyleClass().add("label-danger");
                            parent.getChildren().add(error);
                        } else if (!messageNotPatern.isEmpty() && !textInputControl.getText().matches(pattern)) {
                            Label error = new Label(messageNotPatern);
                            error.getStyleClass().add("label-danger");
                            parent.getChildren().add(error);
                        } else if (!messageLength.isEmpty() && textInputControl.getText().length()<lenght) {
                            Label error = new Label(messageLength);
                            error.getStyleClass().add("label-danger");
                            parent.getChildren().add(error);
                        }
                    }
                });
            });
    }

    public Validator(Control control, String messageEmpty){
        this(control, messageEmpty, "", "", "", 0);
    }

    public Validator(Control control, String messageNotPatern, String pattern){
        this(control, "", messageNotPatern, pattern, "", 0);
    }

    public Validator(Control control, String messageLength, int lenght){
        this(control, "", "", "", messageLength, lenght);
    }

    public Validator(Control control, String messageEmpty, String messageNotPatern, String pattern){
        this(control, messageEmpty, messageNotPatern, pattern, "", 0);
    }

    public Validator(Control control, String messageEmpty, String messageLength, int lenght){
        this(control, messageEmpty, "", "", messageLength, lenght);
    }

    public Validator(Control control,String messageNotPatern, String pattern, String messageLength, int lenght){
        this(control, "", messageNotPatern, pattern, messageLength, lenght);
    }



}
