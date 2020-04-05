package com.jetbrains.steperev.services;

import com.jetbrains.steperev.list.CheckBoxList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

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

    public void addNewTask(String task) {
        if (tasks.contains(task)) {
            showMessageDialog(null, "This task is already added!");
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

    public void removeFromList(ArrayList<JCheckBox> removedTasks) {
        for (JCheckBox task : removedTasks) {
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

    public void loadTasks(String fileName) {

    }

    private void refreshTaskList() {
        list.setListData(new Object[0]);

        for (JCheckBox task : tasks) {
            list.addCheckbox(task);
        }
    }
}
