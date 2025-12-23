package com.silberbullet.panels.builder.preview;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import com.silberbullet.panels.builder.preview.PreviewModule;

/**
 * =========================
 * Preview Panel
 * -------------------------
 * 모듈 생성 결과를 미리 보여주는 영역
 * <p>
 * 역할
 * - 생성될 디렉터리 / 모듈 구조 트리 표시
 * - 선택 항목 제거 (시뮬레이션 단계)
 * - 실제 파일 생성 전 최종 검증 용도
 * =========================
 */
public class PreviewPanel {
    
    /* =========================
     * Root Panel (Form Binding)
     * ========================= */
    private JPanel previewPanel;
    
    /* =========================
     * Preview Tree
     * - 생성될 구조 시각화
     * ========================= */
    private JTree previewTree;
    
    /* =========================
     * Preview Actions
     * ========================= */
    private JButton removeButton;
    
    private List<PreviewModule> previewModuleList = new ArrayList<>();
}
