package com.silberbullet.panels.project;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.Objects;

/**
 * =========================
 * Project Structure Panel
 * -------------------------
 * 실제 프로젝트의 폴더 / 모듈 구조를
 * 읽어서 트리 형태로 표시하는 패널
 * <p>
 * 책임
 * - Project 기반 루트 디렉터리 조회
 * - VirtualFile 트리 탐색
 * - JTree 시각화
 * <p>
 * 주의
 * - 읽기 전용 패널
 * - 파일 생성/수정 책임 없음
 * =========================
 */
public class ProjectStructurePanel {
    
    /* =========================
     * Root Panel (Form Binding)
     * ========================= */
    public JPanel projectStructurePanel;
    
    /* =========================
     * Project Structure Tree
     * ========================= */
    private JTree projectStructureTree;
    
    /* =========================
     * Context
     * ========================= */
    private final Project project;
    
    public ProjectStructurePanel(Project project) {
        Objects.requireNonNull(project, "project is null");
        this.project = project;
        initProjectStructureTree();
    }
    
    /* =========================
     * Tree Initialization
     * ========================= */
    private void initProjectStructureTree() {
        String basePath = project.getBasePath();
        if (basePath == null) return;
        
        VirtualFile rootDir =
                LocalFileSystem.getInstance().findFileByPath(basePath);
        if (rootDir == null) return;
        
        DefaultTreeModel treeModel =
                new DefaultTreeModel(buildTreeNode(rootDir));
        
        projectStructureTree.setModel(treeModel);
        projectStructureTree.setRootVisible(true);
        projectStructureTree.setShowsRootHandles(true);
    }
    
    /* =========================
     * Tree Node Builder
     * ========================= */
    private DefaultMutableTreeNode buildTreeNode(VirtualFile rootDir) {
        DefaultMutableTreeNode treeNode =
                new DefaultMutableTreeNode(rootDir.getName());
        
        if (rootDir.isDirectory()) {
            for (VirtualFile child : rootDir.getChildren()) {
                if (child.isDirectory()) {
                    treeNode.add(buildTreeNode(child));
                }
            }
        }
        return treeNode;
    }
}
