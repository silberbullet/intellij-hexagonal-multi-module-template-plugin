package com.silberbullet.forms;

import com.intellij.openapi.project.Project;

import javax.swing.*;

public class TemplateDialogForm {
    
    public  JPanel rootPanel;
    private JPanel projectPanel;
    private JTextField searchField;
    private JTree projectStructureTree;
    private JLabel selectedPathLabel;
    private JPanel modulesPanel;
    private JList moduleTemplatesList;
    private JButton addModule;

    private Project project;

    public TemplateDialogForm(Project project) {
        this.project = project;
        
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
