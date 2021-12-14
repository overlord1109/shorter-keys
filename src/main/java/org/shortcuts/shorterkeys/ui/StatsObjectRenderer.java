package org.shortcuts.shorterkeys.ui;

import com.intellij.util.ui.JBUI;
import org.shortcuts.shorterkeys.stats.StatsObject;

import javax.swing.*;
import java.awt.*;

public class StatsObjectRenderer extends JLabel implements ListCellRenderer<StatsObject>{

    @Override
    public Component getListCellRendererComponent(JList<? extends StatsObject> list, StatsObject obj, int i, boolean isSelected, boolean cellHasFocus) {
        final Color foreground = list.getForeground();
        String message = obj.toString();
        setText(message);
        setForeground(foreground);
        setBorder(JBUI.Borders.empty(2, 10));
        return this;
    }
}
