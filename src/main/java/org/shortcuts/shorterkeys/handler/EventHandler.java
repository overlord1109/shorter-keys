package org.shortcuts.shorterkeys.handler;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.shortcuts.shorterkeys.action.Notifier;
import org.shortcuts.shorterkeys.action.ShortcutMapper;
import org.shortcuts.shorterkeys.stats.StatisticsService;
import org.shortcuts.shorterkeys.stats.StatsObject;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class EventHandler {

    private final Notifier notifier;
    private final ShortcutMapper mapper;
    private final ActionManager actionManager;
    private final StatisticsService statService;

    public EventHandler(Notifier notifier, ShortcutMapper mapper, StatisticsService statService) {
        this.notifier = notifier;
        this.mapper = mapper;
        this.actionManager = ActionManager.getInstance();
        this.statService = statService;
    }

    public void handle(AnAction action, AnActionEvent event) {
        InputEvent inputEvent = event.getInputEvent();
        String id = actionManager.getId(action);
        String shortcut = mapper.getKeyboardShortcut(id);
        String description = event.getPresentation().getText();
        if (shortcut.isEmpty())
            return;
        if (inputEvent instanceof MouseEvent) {
            handleMouseEvent(description, shortcut);
        } else if (inputEvent instanceof KeyEvent) {
            handleKeyboardEvent(description);
        }
    }

    private void handleMouseEvent(String description, String shortcut) {
        statService.shortcutMissed(description, shortcut);
        StatsObject stat = statService.getStatsForAction(description);

        String title = "ShorterKeys";
        StringBuilder content = new StringBuilder("Use ")
                .append(shortcut)
                .append(" to perform the '")
                .append(description)
                .append("' action.");

        if (stat != null) {
            content.append("You have missed this shortcut ")
                    .append(stat.getMissedCount())
                    .append(" times.");
        }
        notifier.showNotif(title, content.toString());
    }

    private void handleKeyboardEvent(String description) {
        //count statistic
        statService.shortcutUsed(description);
    }

}
