package com.jetbrains.steperev;

import com.jetbrains.steperev.list.CheckBoxList;
import com.jetbrains.steperev.services.DialogService;
import com.jetbrains.steperev.services.RemoveDialogService;
import com.jetbrains.steperev.services.ToDoService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

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
                DialogService addTaskDialog = new DialogService();
                String task = addTaskDialog.selectValue();

                if (task != null && task.length() == 0) {
                    showMessageDialog(null, "You can not add en empty task!");
                } else if (task != null) {
                    toDoService.addNewTask(task);
                }
            }
        });
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveDialogService removeDialog = new RemoveDialogService(toDoService.getTasks());
                ArrayList<JCheckBox> selectedTasks = removeDialog.selectValue();

                if (selectedTasks != null || selectedTasks.size() != 0) {
                    toDoService.removeFromList(selectedTasks);
                }
            }
        });
        filterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toDoService.removeMarkedTasks();
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
