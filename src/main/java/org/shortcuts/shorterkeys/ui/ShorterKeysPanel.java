package org.shortcuts.shorterkeys.ui;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.components.JBList;
import org.shortcuts.shorterkeys.stats.StatisticsService;
import org.shortcuts.shorterkeys.stats.StatsObject;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ShorterKeysPanel implements Disposable , PropertyChangeListener {
    private JPanel panel;
    private JButton resetStatisticsButton;
    private JList<StatsObject> stats;
    private DefaultListModel<StatsObject> model;

    private final StatisticsService statService;

    public ShorterKeysPanel(StatisticsService statService) {
        this.statService = statService;
        resetStatisticsButton.addActionListener(e -> getConfirmationToResetStats());
    }

    public JPanel getContent() {
        return panel;
    }

    private void createUIComponents() {
        stats = getStatsList();
    }

    private JList<StatsObject> getStatsList() {
        JList<StatsObject> stats = new JBList<>();
        stats.setCellRenderer(new StatsObjectRenderer());
        model = new DefaultListModel<>();
        stats.setModel(model);
        statService.registerPropertyChangeSupport(this);
        propChange();
        return stats;
    }

    private void getConfirmationToResetStats() {
        if (Messages.showYesNoDialog(
                "Are you sure you want to reset statistics?",
                "Reset Statistics",
                Messages.getQuestionIcon()) == Messages.YES) {
            statService.reset();
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if(e.getPropertyName().equals("stats"))
            propChange();
    }

    private void propChange() {
        model.removeAllElements();
        for (StatsObject statisticsItem : statService.getStats()) {
            model.addElement(statisticsItem);
        }
    }
}
