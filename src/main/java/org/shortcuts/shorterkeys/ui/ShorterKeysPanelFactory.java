package org.shortcuts.shorterkeys.ui;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;
import org.shortcuts.shorterkeys.stats.StatisticsService;

import javax.swing.*;


/**
 * UI class for tool window
 **/
public class ShorterKeysPanelFactory  implements ToolWindowFactory, DumbAware {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ShorterKeysPanel panel = new ShorterKeysPanel(ApplicationManager.getApplication().getService(StatisticsService.class));
        JBScrollPane toolWindowContent = new JBScrollPane(panel.getContent());
        toolWindowContent.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        Content content = ContentFactory.SERVICE.getInstance().createContent(toolWindowContent, "", false);
        content.setPreferredFocusableComponent(toolWindowContent);
        content.setDisposer(panel);
        toolWindow.getContentManager().addContent(content);
    }
}
