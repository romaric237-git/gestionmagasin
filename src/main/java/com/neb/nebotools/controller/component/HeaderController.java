package com.neb.nebotools.controller.component;

import com.neb.nebotools.controller.Controller;
import com.neb.nebotools.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.text.Text;

public class HeaderController extends Controller {
    @FXML
    private MenuButton notification;

    @FXML
    private Text title;

    @FXML
    private Text titleSub;

    private  String titleString;
    private  String titleSubString;
    @FXML
    void changeDark(ActionEvent event) {

    }

    @FXML
    void changeEnglish(ActionEvent event) {
        Utils.switchToEnglish();
        System.out.println("La langue a été changé en anglais");
    }

    @FXML
    void changeFrench(ActionEvent event) {
        Utils.switchToFrench();
        System.out.println("La langue a été changé en francais");
    }

    @FXML
    void changeLight(ActionEvent event) {

    }

    @FXML
    void changeSystem(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void profile(ActionEvent event) {

    }

    @FXML
    void setting(ActionEvent event) {

    }

    public void changeHeader(String title, String titleSub) {
        changeTitle(title);
        changeTitleSub(titleSub);
    }

    public void changeTitle(String title) {
        titleString = title;
        this.title.setText(Utils.getBundle().getString(title).toLowerCase()+"/");
    }

    public void changeTitleSub(String titleSub) {
        titleSubString = titleSub;
        this.titleSub.setText(Utils.getBundle().getString(titleSub));
    }

    @Override
    public void refresh() {
        changeHeader(titleString, titleSubString);
    }
}
