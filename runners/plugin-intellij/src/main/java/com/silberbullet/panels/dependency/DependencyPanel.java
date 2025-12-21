package com.silberbullet.panels.dependency;

import javax.swing.*;

/**
 * =========================
 * Dependency Panel
 * -------------------------
 * - 특정 모듈에 의존성(Module / Library)을 추가·제거하는 UI
 * - Available Modules → Applied Dependencies 구조
 * =========================
 */
public class DependencyPanel {
    
    /* =========================
     * Root Panel (Form Binding)
     * ========================= */
    private JPanel dependencyPanel;
    
    /* =========================
     * Selected Module
     * - 현재 의존성을 설정 중인 대상 모듈
     * ========================= */
    private JLabel selectedModuleLabel;
    
    /* =========================
     * Applied Dependencies
     * - 이미 적용된 의존성 목록
     * ========================= */
    private JPanel appliedDependenciesPanel;
    private JList appliedDependenciesList;
    
    /**
     * 선택된 의존성 제거
     */
    private JButton removeButton;
    
    /* =========================
     * Available Modules
     * - 의존성으로 추가 가능한 모듈 목록
     * ========================= */
    private JPanel availableModulesPanel;
    
    /**
     * 프로젝트 내 사용 가능한 모듈 리스트
     */
    private JList availableModulesList;
    
    /**
     * 선택 모듈을 의존성으로 추가
     */
    private JButton addLibraryButton;
    
}
