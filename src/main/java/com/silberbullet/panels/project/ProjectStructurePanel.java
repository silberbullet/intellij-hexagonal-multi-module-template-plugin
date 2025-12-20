package com.silberbullet.panels.project;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.silberbullet.panels.project.listener.ProjectStructureListener;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
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
     * Listener
     * ========================= */
    private ProjectStructureListener listener;
    
    /* =========================
     * Context
     * ========================= */
    private Project project;
    
    /* =========================
     * ProjectStructurePanel Init
     * ========================= */
    public void init(Project project, ProjectStructureListener listener) {
        Objects.requireNonNull(project, "project is null");
        Objects.requireNonNull(listener, "listener is null");
        
        this.project = project;
        this.listener = listener;
        
        initProjectStructureTree();
        initMouseListener();
    }
    
    private void initMouseListener() {
        projectStructureTree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
            if (!(node.getUserObject() instanceof VirtualFile vf)) return;
            
            String basePath = project.getBasePath();
            if(basePath == null) return;
            
            String relativePath = vf.getPath().replace(basePath, "");
            
            listener.onPathSelected(relativePath);
        });
    }
    
    /* =========================
     * Tree Initialization
     * ========================= */
    private void initProjectStructureTree() {
        String basePath = project.getBasePath();
        if (basePath == null) return;
        
        VirtualFile rootDir = LocalFileSystem.getInstance().findFileByPath(basePath);
        if (rootDir == null) return;
        
        DefaultTreeModel treeModel = new DefaultTreeModel(buildTreeNode(rootDir));
        
        projectStructureTree.setModel(treeModel);
        projectStructureTree.setCellRenderer(createTreeCellRenderer());
        projectStructureTree.setRootVisible(true);
        projectStructureTree.setShowsRootHandles(true);
    }
    
    /* =========================
     * Tree Node Builder
     * ========================= */
    private DefaultMutableTreeNode buildTreeNode(VirtualFile rootDir) {
        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(rootDir);
        
        if (rootDir.isDirectory()) {
            for (VirtualFile child : rootDir.getChildren()) {
                // .git .idea 제외
                if (child.getName().startsWith(".")
                        || child.getName().endsWith("build")) continue;
                
                if (child.isDirectory()) {
                    treeNode.add(buildTreeNode(child));
                }
            }
        }
        return treeNode;
    }
    
    /* =========================
     * Tree Renderer
     * ========================= */
    private TreeCellRenderer createTreeCellRenderer() {
        return (tree, value, selected, expanded, leaf, row, hasFocus) -> {
            JLabel label = new JLabel();
            
            Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
            if (userObject instanceof VirtualFile vf) {
                label.setText(vf.getName());
            }
            
            if (selected) {
                label.setOpaque(true);
                label.setBackground(UIManager.getColor("Tree.selectionBackground"));
                label.setForeground(UIManager.getColor("Tree.selectionForeground"));
            }
            
            return label;
        };
    }
}
