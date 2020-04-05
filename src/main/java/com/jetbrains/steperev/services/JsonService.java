package com.jetbrains.steperev.services;

import com.google.gson.stream.JsonReader;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

public class JsonService {
    public ArrayList<JCheckBox> loadTasks(String fileName) {
        ArrayList<JCheckBox> tasks = new ArrayList<>();

        try (JsonReader reader = new JsonReader(new FileReader(fileName))) {
            System.out.println("HERERE");
            reader.beginObject();

            String task = null;
            while (reader.hasNext()) {

                String element = reader.nextName();
                int checked;
                Boolean isChecked = null;

                if (element.equals("task")) {
                    task = reader.nextString(); // get task text
                } else if (element.equals("checked")) {
                    checked = reader.nextInt();
                    isChecked = checked != 0;

                    JCheckBox newTask = new JCheckBox(task);
                    newTask.setSelected(isChecked);
                    tasks.add(newTask);
                } else {
                    reader.skipValue(); //avoid some unhandle events
                }
            }

            reader.endObject();
        } catch (IOException e) {
            showMessageDialog(null, "Your file can not be read!");
            return null;
        }

        return tasks;
    }
}
