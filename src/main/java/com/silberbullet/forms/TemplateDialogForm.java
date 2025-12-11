package com.silberbullet.forms;

import com.intellij.openapi.project.Project;

import javax.swing.*;

public class TemplateDialogForm {
    
    public  JPanel rootPanel;
    private JPanel projectStructurePanel;
    private JTree projectStructureTree;
    private JPanel dependencyPanel;
    private JPanel moduleBuilderPanel;
    private JPanel moduleHeaderPanel;
    private JTextField locationField;
    private JTextField folderNameField;
    private JTextField packageNameField;
    private JTextField moduleNameField;
    private JCheckBox domainModuleCheckBox;
    private JPanel previewPanel;
    private JTree previewTree;
    private JButton cancelButton;
    private JButton generateButton;
    private JLabel selectedModuleLabel;
    private JList availableModulesList;
    
    private Project project;

    public TemplateDialogForm(Project project) {
        this.project = project;
        
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
