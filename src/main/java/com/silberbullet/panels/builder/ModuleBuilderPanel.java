package com.silberbullet.panels.builder;

import com.silberbullet.panels.builder.info.ModuleInfoPanel;
import com.silberbullet.panels.builder.preview.PreviewPanel;

import javax.swing.*;

/**
 * =========================
 * Module Builder Panel
 * -------------------------
 * 모듈 생성 UI의 상위 컨테이너
 * <p>
 * 역할
 * - 모듈 생성에 필요한 입력 영역 조합
 * - 하위 패널 간 상태 조정 및 이벤트 중계
 * - Preview 패널과의 동기화 담당
 * <p>
 * 구성
 * - ModuleInfoPanel : 모듈 메타 정보 및 옵션 입력
 * - PreviewPanel    : 생성될 구조 미리보기
 * =========================
 */
public class ModuleBuilderPanel {
    
    /* =========================
     * Root Panel (Form Binding)
     * ========================= */
    public JPanel moduleBuilderPanel;
    
    /* =========================
     * Module Information
     * - 모듈 기본 정보 및 생성 옵션
     * ========================= */
    private ModuleInfoPanel moduleInfoPanel;
    
    /* =========================
     * Preview Area
     * - 모듈 생성 결과 미리보기
     * ========================= */
    private PreviewPanel previewPanel;
    
    public ModuleInfoPanel getModuleInfoPanel() {
        return moduleInfoPanel;
    }
    
    public PreviewPanel getPreviewPanel() {
        return previewPanel;
    }
}
