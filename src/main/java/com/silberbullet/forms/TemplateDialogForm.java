package com.silberbullet.forms;

import com.intellij.openapi.project.Project;

import javax.swing.*;

/**
 * Module Template Dialog UI Form
 *
 * 역할:
 * - 멀티 모듈 프로젝트 구조 시각화
 * - Domain / Manual 모드 기반 모듈 구성
 * - Preview 기반 구조 시뮬레이션
 * - 기존 모듈 Dependency 주입
 */
public class TemplateDialogForm {
    
    /* =========================
     * Root
     * ========================= */
    public JPanel rootPanel;
    
    /* =========================
     * Project Structure (LEFT)
     * - 실제 프로젝트의 폴더 / 모듈 구조
     * ========================= */
    private JPanel projectStructurePanel;
    private JTree projectStructureTree;
    
    /* =========================
     * Module Builder (CENTER)
     * ========================= */
    private JPanel moduleBuilderPanel;
    
    /* --- Module Header / Info --- */
    private JPanel moduleHeaderPanel;
    private JTextField locationField;      // 선택된 생성 위치
    private JTextField folderNameField;    // 생성될 폴더명
    private JTextField packageNameField;   // base package
    private JTextField moduleNameField;    // gradle module name
    
    /* --- Domain Mode Toggle --- */
    private JCheckBox domainModuleCheckBox; // Domain Module 여부
    
    /* --- Domain Options Panel (Domain ON 시 활성화) --- */
    private JPanel domainOptionsPanel;
    
    // api layer
    private JCheckBox apiCheckBox;
    private JPanel apiOptionsPanel;
    private JCheckBox domainCheckBox;
    private JCheckBox exceptionCheckBox;
    private JCheckBox readmodelCheckBox;
    
    // application layer
    private JCheckBox applicationCheckBox;
    
    // driven layer
    private JCheckBox drivenCheckBox;
    private JPanel drivenOptionsPanel;
    private JCheckBox rdbCheckBox;
    private JCheckBox redisCheckBox;
    
    // driving layer
    private JCheckBox drivingCheckBox;
    private JPanel drvingOptionsPanel;
    private JCheckBox webMvcCheckBox;
    private JCheckBox webFluxCheckBox;
    
    /* --- Manual Composition Tools (Domain OFF 시 활성화) --- */
    private JPanel manualToolsPanel;
    private JButton initButton;
    private JButton addModuleButton;
    private JButton addFolderButton;
    private JButton removeButton;
    
    /* =========================
     * Preview Panel (CENTER-BOTTOM)
     * - 생성될 구조 시뮬레이션
     * ========================= */
    private JPanel previewPanel;
    private JTree previewTree;
    
    /* =========================
     * Dependency Panel (RIGHT)
     * ========================= */
    private JPanel dependencyPanel;
    
    // 선택된 Preview 모듈 표시
    private JLabel selectedModuleLabel;
    
    // 주입 가능한 기존 프로젝트 모듈
    private JPanel availableModulesPanel;
    private JList availableModulesList;
    private JButton addLibraryButton;
    
    // 현재 Preview 모듈에 적용된 의존성
    private JPanel appliedDependenciesPanel;
    private JList appliedDependenciesList;
    private JButton removeButton1;
    
    /* =========================
     * Dialog Actions
     * ========================= */
    private JButton generateButton;
    private JButton cancelButton;
    
    /* =========================
     * Context
     * ========================= */
    private Project project;
    
    public TemplateDialogForm(Project project) {
        this.project = project;
    }
    
    private void createUIComponents() {
        // Custom UI components (필요 시만 사용)
    }
}
