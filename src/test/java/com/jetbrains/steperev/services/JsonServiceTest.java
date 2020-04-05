package com.jetbrains.steperev.services;


import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;

public class JsonServiceTest {
    private static JsonService service = new JsonService();
    private static String folder = ResourceLoader.getFolderName();

    @Test
    public void simpleTest() {
        ArrayList<JCheckBox> tasks = service.loadTasks(folder + "test1.json");

        Assert.assertEquals(2, tasks.size()); // all elements are added
        Assert.assertEquals(false, tasks.get(1).isSelected()); // second is selected
        Assert.assertEquals(true, tasks.get(0).isSelected());  // first is not selected
    }

    @Test
    public void incorrectFileTest() {
        ArrayList<JCheckBox> tasks = service.loadTasks("incorrect.json");

        Assert.assertEquals(null, tasks);
    }

    @Test
    public void stressfulTest() {
        long start = System.currentTimeMillis();
        service.loadTasks(folder + "test2.json");
        long finish = System.currentTimeMillis();

        long time = finish - start;
        Assert.assertTrue(time < 1000); // less than 1 second
    }
}