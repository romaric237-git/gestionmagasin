package com.neb.nebotools.controller;

import java.util.ArrayList;
import java.util.List;

public abstract class Controller {
    private static List<Controller> controllers;

    static {
        controllers = new ArrayList<Controller>();
    }

    public Controller(){
        controllers.add(this);
    }
    public static void changeLanguage(){
        for (Controller controller: controllers)
            controller.refresh();
    }

    public abstract void refresh();


}
