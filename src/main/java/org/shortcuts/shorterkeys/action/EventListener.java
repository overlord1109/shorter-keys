package org.shortcuts.shorterkeys.action;

import com.intellij.application.Topics;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ex.AnActionListener;
import com.intellij.openapi.wm.impl.StripeButton;
import org.jetbrains.annotations.NotNull;
import org.shortcuts.shorterkeys.handler.EventHandler;

import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class EventListener implements AnActionListener, Disposable, AWTEventListener {

    private boolean mouseDrag = false;
    EventHandler eventHandler;

    public EventListener() {
        Topics.subscribe(AnActionListener.TOPIC, this, this);
        setupEventListener();
        this.eventHandler = new EventHandler(new Notifier(), new ShortcutMapper());
    }

    private void setupEventListener() {
        long eventMask = AWTEvent.WINDOW_EVENT_MASK | AWTEvent.WINDOW_STATE_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK;
        Toolkit.getDefaultToolkit().addAWTEventListener(this, eventMask);
    }

    @Override
    public void beforeActionPerformed(@NotNull AnAction action, AnActionEvent event) {
        InputEvent inputEvent = event.getInputEvent();
        if (!(inputEvent instanceof KeyEvent) && !(inputEvent instanceof MouseEvent))
            return;
        eventHandler.handle(action, event);
    }

    @Override
    public void dispose() {
        Toolkit.getDefaultToolkit().removeAWTEventListener(this);
    }

    @Override
    public void eventDispatched(AWTEvent event) {
        int id = event.getID();
        if (id == MouseEvent.MOUSE_DRAGGED) {
            mouseDrag = true;
            return;
        }

        if (id == MouseEvent.MOUSE_RELEASED && ((MouseEvent) event).getButton() == MouseEvent.BUTTON1) {
            if (!mouseDrag) {
                if (event.getSource() instanceof StripeButton) {
                    NotificationGroupManager.getInstance().getNotificationGroup("Shorter Keys")
                            .createNotification("Test", NotificationType.INFORMATION).notify(null);
                }
            }
            mouseDrag = false;
        }
    }
}
