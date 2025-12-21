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
    public JPanel moduleInfoPanel;
    
    /* =========================
     * Module Base Info
     * ========================= */
    private JTextField locationField;
    private JTextField folderNameField;
    private JTextField packageNameField;
    private JTextField moduleNameField;
    
    /* =========================
     * Domain Options
     * ========================= */
    private JCheckBox domainModuleCheckBox;
    private DomainOptionsPanel domainOptionsPanel;
    
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
        domainOptionsPanel.init();
        
        // 도메인 체크 박스 클릭 이벤트
        domainModuleCheckBox.addChangeListener(e ->{
            initDomainOption(domainModuleCheckBox.isSelected());
        });
        
        // 폴터 추가 버튼 이벤트 정의
        addFolderButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        
        // 모듈 추가 버튼 이벤트 정의
        addModuleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        
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
    
    private void initDomainOption(boolean isDomainModule) {
        domainOptionsPanel.setVisible(isDomainModule);
    }
}
