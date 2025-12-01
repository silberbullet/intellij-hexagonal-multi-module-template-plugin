package com.silberbullet.forms;

import com.intellij.openapi.project.Project;

import javax.swing.*;

public class TemplateDialogForm {
    
    public  JPanel rootPanel;
    
    private Project project;
    
    private JTree projectStructureTree;
    private JList moduleList;
    
    public TemplateDialogForm(Project project) {
        this.project = project;
        
    }
}
