package com.silberbullet.forms;

import com.intellij.openapi.project.Project;
import com.silberbullet.panels.dependency.DependencyPanel;
import com.silberbullet.panels.builder.ModuleBuilderPanel;
import com.silberbullet.panels.project.ProjectStructurePanel;

import javax.swing.*;

/**
 * =========================
 * Module Template Dialog Form
 * -------------------------
 * 멀티모듈 템플릿 생성 다이얼로그
 * <p>
 * 역할
 * - 프로젝트 구조 트리 시각화
 * - Domain / Manual 기반 모듈 생성 설정
 * - 생성 결과 Preview
 * - 기존 모듈 Dependency 주입 관리
 * <p>
 * Layout 개념
 * ┌──────────────────────────────────────────┐
 * │ ProjectStructure │ ModuleBuilder │ Dependency │
 * └──────────────────────────────────────────┘
 * =========================
 */
public class TemplateDialogForm {
    
    /* =========================
     * Root Panel (Dialog Content)
     * ========================= */
    public JPanel rootPanel;
    
    /* =========================
     * Left Area
     * - 프로젝트 구조 트리
     * ========================= */
    private ProjectStructurePanel projectStructurePanel;
    
    /* =========================
     * Center Area
     * - 모듈 생성 설정
     * ========================= */
    private ModuleBuilderPanel moduleBuilderPanel;
    
    /* =========================
     * Right Area
     * - 의존성 설정
     * ========================= */
    private DependencyPanel dependencyPanel;
    
    /* =========================
     * Dialog Actions
     * - 생성 / 취소
     * ========================= */
    private JButton generateButton;
    private JButton cancelButton;
    
    /* =========================
     * Context
     * ========================= */
    private final Project project;
    
    public TemplateDialogForm(Project project) {
        this.project = project;
    }
}
