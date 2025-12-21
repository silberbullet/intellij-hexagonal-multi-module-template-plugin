package com.silberbullet.panels.builder.info;

import com.silberbullet.panels.builder.info.type.DomainOptions;

import javax.swing.*;

/**
 * Domain module 세부 옵션 UI
 * - API / Driving / Driven 옵션 토글
 * - 하위 옵션 패널 상태 동기화
 */
public class DomainOptionsPanel {
    
    /* Root */
    public JPanel domainOptionsPanel;
    
    /* API */
    private JCheckBox apiCheckBox;
    private JPanel apiOptionsPanel;
    private JCheckBox domainCheckBox;
    private JCheckBox exceptionCheckBox;
    private JCheckBox readmodelCheckBox;
    private JCheckBox applicationCheckBox;
    
    /* Driven */
    private JCheckBox drivenCheckBox;
    private JPanel drivenOptionsPanel;
    private JCheckBox rdbCheckBox;
    private JCheckBox redisCheckBox;
    
    /* Driving */
    private JCheckBox drivingCheckBox;
    private JPanel drivingOptionsPanel;
    private JCheckBox webMvcCheckBox;
    private JCheckBox webFluxCheckBox;
    
    /* =========================
     * Init
     * ========================= */
    public void init() {
        initDefaultSelections();
        syncAllPanels();
        initListeners();
        domainOptionsPanel.setVisible(false);
    }
    
    public void setVisible(boolean visible) {
        domainOptionsPanel.setVisible(visible);
    }
    
    /* =========================
     * Snapshot
     * ========================= */
    public DomainOptions collectDomainOptions() {
        return new DomainOptions(apiCheckBox.isSelected()
                , domainCheckBox.isSelected()
                , exceptionCheckBox.isSelected()
                , readmodelCheckBox.isSelected()
                , applicationCheckBox.isSelected()
                , drivingCheckBox.isSelected()
                , webMvcCheckBox.isSelected()
                , webFluxCheckBox.isSelected()
                , drivenCheckBox.isSelected()
                , rdbCheckBox.isSelected()
                , redisCheckBox.isSelected());
    }
    
    /* =========================
     * Initial State
     * ========================= */
    private void initDefaultSelections() {
        apiCheckBox.setSelected(true);
        applicationCheckBox.setSelected(true);
        drivingCheckBox.setSelected(true);
        drivenCheckBox.setSelected(true);
        
        setApiChildren(true);
        setDrivingChildren(true);
        setDrivenChildren(true);
    }
    
    /* =========================
     * Event Binding
     * ========================= */
    private void initListeners() {
        
        apiCheckBox.addActionListener(e -> {
            boolean enabled = apiCheckBox.isSelected();
            apiOptionsPanel.setVisible(enabled);
            setApiChildren(enabled);
        });
        
        drivingCheckBox.addActionListener(e -> {
            boolean enabled = drivingCheckBox.isSelected();
            drivingOptionsPanel.setVisible(enabled);
            setDrivingChildren(enabled);
        });
        
        drivenCheckBox.addActionListener(e -> {
            boolean enabled = drivenCheckBox.isSelected();
            drivenOptionsPanel.setVisible(enabled);
            setDrivenChildren(enabled);
        });
    }
    
    /* =========================
     * Child Sync Helpers
     * ========================= */
    private void syncAllPanels() {
        apiOptionsPanel.setVisible(apiCheckBox.isSelected());
        drivingOptionsPanel.setVisible(drivingCheckBox.isSelected());
        drivenOptionsPanel.setVisible(drivenCheckBox.isSelected());
    }
    
    private void setApiChildren(boolean selected) {
        domainCheckBox.setSelected(selected);
        exceptionCheckBox.setSelected(selected);
        readmodelCheckBox.setSelected(selected);
    }
    
    private void setDrivingChildren(boolean selected) {
        webMvcCheckBox.setSelected(selected);
        webFluxCheckBox.setSelected(selected);
    }
    
    private void setDrivenChildren(boolean selected) {
        rdbCheckBox.setSelected(selected);
        redisCheckBox.setSelected(selected);
    }
}
