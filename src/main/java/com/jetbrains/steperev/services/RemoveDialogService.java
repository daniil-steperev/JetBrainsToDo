package com.jetbrains.steperev.services;

import com.jetbrains.steperev.list.CheckBoxList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RemoveDialogService extends JDialog {

    private JScrollPane pane;
    private boolean cancelled = true;
    private ArrayList<JCheckBox> checkBoxes;
    private ArrayList<JCheckBox> selected;

    public static void main (String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run () {
                DialogService dialog = new DialogService();
                String text = dialog.selectValue();
                System.out.println("Selected: " + text);
            }
        });
    }

    public RemoveDialogService (ArrayList<JCheckBox> checkBoxes) {
        this.checkBoxes = getCheckBoxes(checkBoxes);

        setModal(true);
        setTitle("Select removed tasks");
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout(10, 10));
        getContentPane().add(content);

        JList list = createNewList();
        pane = new JScrollPane(list);

        JButton ok = new JButton("Accept");
        JButton cancel = new JButton("Cancel");
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttons.add(ok);
        buttons.add(cancel);

        content.add(pane, BorderLayout.NORTH);
        content.add(buttons, BorderLayout.SOUTH);
        content.setBorder(new EmptyBorder(15, 15, 15, 15));
        pack();

        ok.addActionListener(new ActionListener() {

            public void actionPerformed (ActionEvent e) {
                cancelled = false;

                selected = getSelected();

                dispose();
            }
        });
        cancel.addActionListener(new ActionListener() {

            public void actionPerformed (ActionEvent e) {
                cancelled = true;
                dispose();
            }
        });
        // default button, allows to trigger ok when pressing enter in the text field
        getRootPane().setDefaultButton(ok);
    }

    private ArrayList<JCheckBox> getSelected() {
        ArrayList<JCheckBox> selected = new ArrayList<JCheckBox>();

        for (JCheckBox task : checkBoxes) {
            if (task.isSelected()) {
                selected.add(task);
            }
        }

        return selected;
    }

    private ArrayList<JCheckBox> getCheckBoxes(ArrayList<JCheckBox> list) {
        ArrayList<JCheckBox> checkElements = new ArrayList<JCheckBox>();

        for (JCheckBox task : list) {
            JCheckBox checkBox = new JCheckBox(task.getText());
            checkElements.add(checkBox);
        }

        return checkElements;
    }

    private JList createNewList() {
        JList list = new CheckBoxList();

        for (JCheckBox task : checkBoxes) {
            ((CheckBoxList) list).addCheckbox(task);
        }

        return list;
    }

    /**
     * Open the dialog (modal, blocks caller until dialog is disposed) and returns the entered value, or null if
     * cancelled.
     */
    public ArrayList<JCheckBox> selectValue () {
        setVisible(true);
        return cancelled ? null : selected;
    }
}