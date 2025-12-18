package com.silberbullet.panels.builder.info;

import javax.swing.*;

/**
 * =========================
 * Module Info Panel
 * -------------------------
 * 모듈 생성에 필요한 모든 입력 정보를 담당
 * <p>
 * 역할
 * - 모듈 기본 메타 정보 입력
 * - Domain 기반 모듈 옵션 선택
 * - Adapter (Driving / Driven) 구성
 * - 수동 생성 도구 제공
 * =========================
 */
public class ModuleInfoPanel {
    
    /* =========================
     * Root Panel (Form Binding)
     * ========================= */
    private JPanel moduleInfoPanel;
    
    /* =========================
     * Module Base Information
     * - 생성 위치 및 이름 정의
     * ========================= */
    /**
     * 모듈 생성 위치 (예: /services, /core)
     */
    private JTextField locationField;
    
    /**
     * 실제 생성될 폴더명
     */
    private JTextField folderNameField;
    
    /**
     * base package (ex: com.example.auth)
     */
    private JTextField packageNameField;
    
    /**
     * Gradle/Maven module name
     */
    private JTextField moduleNameField;
    
    /* =========================
     * Domain Module
     * - 도메인 모듈 여부
     * ========================= */
    /**
     * Domain Module 여부
     */
    private JCheckBox domainModuleCheckBox;
    
    /**
     * Domain Module 선택 시 활성화되는 옵션 영역
     */
    private JPanel domainOptionsPanel;
    
    /* =========================
     * API Options
     * - Domain API 구성
     * ========================= */
    /**
     * API 모듈 생성 여부
     */
    private JCheckBox apiCheckBox;
    
    /**
     * API 세부 옵션 영역
     */
    private JPanel apiOptionsPanel;
    
    /**
     * domain 계층 생성
     */
    private JCheckBox domainCheckBox;
    
    /**
     * exception 계층 생성
     */
    private JCheckBox exceptionCheckBox;
    
    /**
     * read-model (CQRS Projection) 생성
     */
    private JCheckBox readmodelCheckBox;
    
    /**
     * application 계층 생성
     */
    private JCheckBox applicationCheckBox;
    
    /* =========================
     * Adapter Selection
     * ========================= */
    /**
     * Driven Adapter 사용 여부
     */
    private JCheckBox drivenCheckBox;
    
    /**
     * Driving Adapter 사용 여부
     */
    private JCheckBox drivingCheckBox;
    
    /* =========================
     * Driving Adapter Options
     * - 외부 → 내부 진입 어댑터
     * ========================= */
    private JPanel drvingOptionsPanel;
    
    /**
     * Spring WebMvc Adapter
     */
    private JCheckBox webMvcCheckBox;
    
    /**
     * Spring WebFlux Adapter
     */
    private JCheckBox webFluxCheckBox;
    
    /* =========================
     * Driven Adapter Options
     * - 내부 → 외부 리소스 연동
     * ========================= */
    private JPanel drivenOptionsPanel;
    
    /**
     * RDB Adapter (JPA, QueryDSL 등)
     */
    private JCheckBox rdbCheckBox;
    
    /**
     * Redis Adapter
     */
    private JCheckBox redisCheckBox;
    
    /* =========================
     * Manual Tools
     * - 수동 조작 영역
     * ========================= */
    private JPanel manualToolsPanel;
    
    /**
     * 폴더 직접 추가
     */
    private JButton addFolderButton;
    
    /**
     * 모듈 직접 추가
     */
    private JButton addModuleButton;
    
    /**
     * 입력값 초기화
     */
    private JButton initButton;
}
