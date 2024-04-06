package com.neb.nebotools.utils;

import com.neb.nebotools.controller.Controller;

import java.util.ResourceBundle;

public class Utils {
    private static ResourceBundle bundle = ResourceBundle.getBundle("french");

    public static void switchToEnglish(){
        bundle = ResourceBundle.getBundle("english");
        Controller.changeLanguage();
    }
    public static void switchToFrench(){
        bundle = ResourceBundle.getBundle("french");
        Controller.changeLanguage();
    }

    public static ResourceBundle getBundle(){
        return bundle;
    }
}
