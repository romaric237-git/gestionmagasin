package com.neb.nebotools.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.util.Callback;

public class CustomControllerFactory implements Callback<Class<?>, Object> {
    @Override
    public Object call(Class<?> param) {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(this::instantiateController);
        return loader;
    }
    private Object instantiateController(Class<?> type) {
        try {
            Object controller = type.newInstance();
            if (controller instanceof Initializable) {
                Initializable initializable = (Initializable) controller;
//                initializable.init();
            }
//             controller.setResourceBundle(resourceBundle);

            return controller;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
