package com.silberbullet.panels.builder.info;

import com.silberbullet.panels.builder.info.listener.ModuleInfoAddFolderListener;
import com.silberbullet.panels.builder.info.listener.ModuleInfoAddModuleListener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * =========================
 * Module Info Panel
 * -------------------------
 * 모듈 생성에 필요한 입력 정보 및 옵션 설정 패널
 * <p>
 * 책임
 * - 모듈 기본 정보 입력
 * - Domain / API 옵션 선택
 * - Adapter (Driving / Driven) 구성
 * - 수동 생성 도구 제공
 * =========================
 */
public class ModuleInfoPanel {
    
    /* =========================
     * Root (Form Binding)
     * ========================= */
    private JPanel moduleInfoPanel;
    
    /* =========================
     * Module Base Info
     * ========================= */
    private JTextField locationField;
    private JTextField folderNameField;
    private JTextField packageNameField;
    private JTextField moduleNameField;
    
    /* =========================
     * Domain / API Options
     * ========================= */
    private JCheckBox domainModuleCheckBox;
    private JPanel domainOptionsPanel;
    
    private JCheckBox apiCheckBox;
    private JPanel apiOptionsPanel;
    
    private JCheckBox domainCheckBox;
    private JCheckBox exceptionCheckBox;
    private JCheckBox readmodelCheckBox;
    private JCheckBox applicationCheckBox;
    
    /* =========================
     * Adapter Selection
     * ========================= */
    private JCheckBox drivingCheckBox;
    private JCheckBox drivenCheckBox;
    
    /* =========================
     * Driving Adapter Options
     * ========================= */
    private JPanel drvingOptionsPanel;
    private JCheckBox webMvcCheckBox;
    private JCheckBox webFluxCheckBox;
    
    /* =========================
     * Driven Adapter Options
     * ========================= */
    private JPanel drivenOptionsPanel;
    private JCheckBox rdbCheckBox;
    private JCheckBox redisCheckBox;
    
    /* =========================
     * Manual Tools
     * ========================= */
    private JPanel manualToolsPanel;
    private JButton addFolderButton;
    private JButton addModuleButton;
    private JButton initButton;
    
    /* =========================
     * Listener
     * ========================= */
    private ModuleInfoAddModuleListener moduleInfoAddModuleListener;
    private ModuleInfoAddFolderListener moduleInfoAddFolderListener;
    
    public void init() {
        // 폴터 추가 버튼 이벤트 정의
        // 모듈 추가 버튼 이벤트 정의
        // 초기화 버튼 클릭 이벤트 정의
        initButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                
                locationField.setText("");
                folderNameField.setText("");
                packageNameField.setText("");
                moduleNameField.setText("");
                
                folderNameField.requestFocus();
            }
        });
    }
    
    public void setLocationField(String path) {
        locationField.setText(path);
    }
}
