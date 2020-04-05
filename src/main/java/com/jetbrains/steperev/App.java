package com.jetbrains.steperev;

import com.jetbrains.steperev.list.CheckBoxList;
import com.jetbrains.steperev.services.ToDoService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private JPanel rootPanel;
    private JButton addBtn;
    private JButton removeBtn;
    private JButton filterBtn;
    private JButton loadBtn;
    private JScrollPane scrollPane;

    private ToDoService toDoService;

    private JList list;

    public App() {
        toDoService = new ToDoService(list);

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toDoService.addNewTask();
            }
        });
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toDoService.removeFromList();
            }
        });
        filterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toDoService.removeMarkedTasks();
            }
        });
        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toDoService.loadTasks();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");

        frame.setContentPane(new App().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setSize(600, 800);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        list = new CheckBoxList();
    }
}