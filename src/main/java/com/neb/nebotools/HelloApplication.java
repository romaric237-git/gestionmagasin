package com.neb.nebotools;

import com.neb.nebotools.sql.DatabaseInitializer;
import com.neb.nebotools.utils.Utils;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {

        DatabaseInitializer.destroy();
        DatabaseInitializer.init();


//        Utils.openApp(stage,"view/component/lineStock.fxml");
        Utils.openApp(stage, "view/login.fxml");
    }

    public static void main(String[] args) throws FileNotFoundException {
        launch();
    }

}