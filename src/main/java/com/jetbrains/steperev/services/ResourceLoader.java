package com.jetbrains.steperev.services;

import java.io.File;

public class ResourceLoader {

    private static final String QUESTION_FOLDERNAME = "loadFiles";

    /**
     * Returns the folder containing all the questions
     *
     */
    public static File getQuestionsFolder(){
        String userDirectory = System.getProperty("user.dir");
        return new File(userDirectory+"\\"+QUESTION_FOLDERNAME);
    }

    public static String getFolderName() {
        String userDirectory = System.getProperty("user.dir");
        return userDirectory + "\\" + QUESTION_FOLDERNAME + "\\";
    }
}