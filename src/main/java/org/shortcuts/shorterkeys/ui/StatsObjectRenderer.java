package org.shortcuts.shorterkeys.ui;

import com.intellij.util.ui.JBUI;
import org.shortcuts.shorterkeys.stats.StatsObject;

import javax.swing.*;
import java.awt.*;

/**
 * Custom JList rendering for missed shortcuts list
 **/
public class StatsObjectRenderer extends JLabel implements ListCellRenderer<StatsObject>{

    @Override
    public Component getListCellRendererComponent(JList<? extends StatsObject> list, StatsObject obj, int i, boolean isSelected, boolean cellHasFocus) {
        setForeground(list.getForeground());
        setBorder(JBUI.Borders.empty(4, 12));
        setText(obj.toString());
        return this;
    }
}
