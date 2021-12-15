package org.shortcuts.shorterkeys.action;

import com.intellij.application.Topics;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ex.AnActionListener;
import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.NotNull;
import org.shortcuts.shorterkeys.handler.EventHandler;
import org.shortcuts.shorterkeys.stats.StatisticsService;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Listen to actions and invoke handler for relevant events
 **/
public class EventListener implements AnActionListener, Disposable {

    private final EventHandler eventHandler;

    public EventListener() {
        Topics.subscribe(AnActionListener.TOPIC, this, this);
        this.eventHandler = new EventHandler(new Notifier(), new ShortcutMapper(), ApplicationManager.getApplication().getService(StatisticsService.class));
    }

    @Override
    public void beforeActionPerformed(@NotNull AnAction action, AnActionEvent event) {
        InputEvent inputEvent = event.getInputEvent();
        if (inputEvent instanceof KeyEvent || inputEvent instanceof MouseEvent)
            eventHandler.handle(action, event);
    }

    @Override
    public void dispose() {
    }
}
