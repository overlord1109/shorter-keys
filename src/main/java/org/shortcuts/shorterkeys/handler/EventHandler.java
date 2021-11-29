package org.shortcuts.shorterkeys.handler;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.apache.batik.w3c.dom.events.KeyboardEvent;
import org.shortcuts.shorterkeys.action.Notifier;
import org.shortcuts.shorterkeys.action.ShortcutMapper;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

public class EventHandler {

    private final Notifier notifier;
    private final ShortcutMapper mapper;
    private final ActionManager actionManager;

    public EventHandler(Notifier notifier, ShortcutMapper mapper) {
        this.notifier = notifier;
        this.mapper = mapper;
        this.actionManager = ActionManager.getInstance();
    }

    public void handle(AnAction action, AnActionEvent event) {
        InputEvent inputEvent = event.getInputEvent();
        String shortcut = mapper.getKeyboardShortcut(actionManager.getId(action));
        if(shortcut.isEmpty())
            return;
        if(inputEvent instanceof MouseEvent)
            handleMouseEvent(shortcut);
        else if(inputEvent instanceof KeyboardEvent)
            handleKeyboardEvent();
    }

    private void handleMouseEvent(String shortcut) {
        String title = "ShorterKeys";
        String content = "You could have saved mouse clicks by using the following shortcut:" +
                "\n" +
                shortcut;
        notifier.showNotif(title, content);
    }

    private void handleKeyboardEvent() {
        //count statistic
    }

}
