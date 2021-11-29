package org.shortcuts.shorterkeys.action;

import com.intellij.openapi.actionSystem.Shortcut;
import com.intellij.openapi.keymap.Keymap;
import com.intellij.openapi.keymap.KeymapManager;
import com.intellij.openapi.keymap.KeymapUtil;

public class ShortcutMapper {

    private final KeymapManager keyMapManager = KeymapManager.getInstance();

    public String getKeyboardShortcut(String actionId) {
        Keymap keymap = keyMapManager.getActiveKeymap();
        Shortcut[] shortcuts = keymap.getShortcuts(actionId);

        if(shortcuts.length == 0)
            return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < shortcuts.length; i++) {
            Shortcut shortcut = shortcuts[i];
            if (i > 0) {
                sb.append(" or ");
            }
            sb.append("'").append(KeymapUtil.getShortcutText(shortcut)).append("'");
        }
        return sb.toString();
    }
}
