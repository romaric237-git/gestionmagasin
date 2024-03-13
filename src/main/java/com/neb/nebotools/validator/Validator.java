package com.neb.nebotools.validator;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Stack;

public class Validator {

    public Validator(Control control, String messageEmpty, String messageNotPatern, String pattern, String messageLength, int lenght){
        if(control instanceof TextInputControl)
            control.setOnKeyReleased(a->{
                TextInputControl textInputControl = (TextInputControl) control;
                textInputControl.setOnKeyReleased(k->{
                    VBox parent = null;
                    if(control.getParent() instanceof VBox) parent = (VBox) control.getParent();
                    else if(control.getParent() instanceof StackPane && control.getParent().getParent().getParent() instanceof VBox)
                        parent = (VBox) control.getParent().getParent().getParent();

                    if(parent != null) {
                        if (parent.getChildren().size() >  1) {
                            parent.getChildren().remove( 1);
                        }
                        if (!messageEmpty.isEmpty() && textInputControl.getText().isEmpty()) {
                            Label error = new Label(messageEmpty);
                            error.getStyleClass().add("label-danger");
                            parent.getChildren().add(error);
                        } else if (!messageNotPatern.isEmpty() && !textInputControl.getText().matches(pattern)) {
                            Label error = new Label(messageNotPatern);
                            error.getStyleClass().add("label-danger");
                            parent.getChildren().add(error);
                        } else if (!messageLength.isEmpty() && textInputControl.getText().length() < lenght) {
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
