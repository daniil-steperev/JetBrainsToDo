package com.jetbrains.steperev.services;

import com.jetbrains.steperev.list.CheckBoxList;
import res.ResourceLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import static javax.swing.JOptionPane.showMessageDialog;

public class ToDoService {
    private ArrayList<JCheckBox> tasks;
    private CheckBoxList list;

    public ToDoService(JList list) {
        tasks = new ArrayList<JCheckBox>();
        this.list = (CheckBoxList) list;
    }

    public ArrayList<JCheckBox> getTasks() {
        return tasks;
    }

    public void addNewTask() {
        DialogService addTaskDialog = new DialogService();
        String task = addTaskDialog.selectValue();

        if (task != null && task.length() == 0) {
            showMessageDialog(null, "You can not add en empty task!");
            return;
        }

        final JCheckBox checkBox = new JCheckBox(task);
        checkBox.setSelected(false);
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox.isSelected()) {
                    checkBox.setBackground(Color.GRAY);
                } else {
                    checkBox.setBackground(Color.GREEN);
                }
            }
        });

        tasks.add(checkBox);
        list.addCheckbox(checkBox);
    }

    public void removeMarkedTasks() {
        Iterator iterator = tasks.iterator();
        while (iterator.hasNext()) {
            JCheckBox task = (JCheckBox) iterator.next();
            if (task.isSelected()) {
                iterator.remove();
            }
        }

        refreshTaskList();
    }

    public void removeFromList() {
        RemoveDialogService removeDialog = new RemoveDialogService(tasks);
        ArrayList<JCheckBox> selectedTasks = removeDialog.selectValue();

        if (selectedTasks != null || selectedTasks.size() != 0) {
            for (JCheckBox task : selectedTasks) {
                for (int i = 0; i < tasks.size(); i++) {
                    JCheckBox curElem = tasks.get(i);
                    if (curElem.getText().equals(task.getText())) {
                        tasks.remove(i);
                        break;
                    }
                }
            }

            refreshTaskList();
        }
    }

    public void loadTasks() {
        DialogService loadDialog = new DialogService();
        String fileName = loadDialog.selectValue() + ".json";

        File loadFile = null;
        File folder = ResourceLoader.getQuestionsFolder();
        for (File f : Objects.requireNonNull(folder.listFiles())) {
            if (f.getName().equals(fileName)) {
                loadFile = f;
                break;
            }
        }

        if (loadFile == null) {
            showMessageDialog(null, "Put your file to the directory, please!");
            return;
        }

        String folderDir = ResourceLoader.getFolderName();
        System.out.println(folderDir + fileName);
        JsonService jsonService = new JsonService();
        ArrayList<JCheckBox> loadedTasks = jsonService.loadTasks(folderDir + fileName);

        if (loadedTasks != null) {
            for (JCheckBox task : loadedTasks) {
                list.addCheckbox(task);
                tasks.add(task);
            }
        }
    }

    private void refreshTaskList() {
        list.setListData(new Object[0]);

        for (JCheckBox task : tasks) {
            list.addCheckbox(task);
        }
    }
}
