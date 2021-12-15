package org.shortcuts.shorterkeys.stats;

import com.intellij.util.xmlb.annotations.Transient;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Maintain statistics for keyboard shortcut uses and misses
 **/
public class StatisticsService {

    private final HashMap<String, StatsObject> stats;
    @Transient private final PropertyChangeSupport propChangeSupport;

    public StatisticsService() {
        this.stats = new HashMap<>();
        this.propChangeSupport = new PropertyChangeSupport(this);
    }

    public StatsObject getStatsForAction(String action) {
        return stats.get(action);
    }

    public void shortcutMissed(String action, String shortcut) {
        StatsObject stat;

        if (stats.containsKey(action)) {
            stat = stats.get(action);
        } else {
            stat = new StatsObject(action, shortcut);
            stats.put(action, stat);
        }
        stat.incrementMiss();
        propChangeSupport.firePropertyChange("stats", null, null);
    }

    public void shortcutUsed(String action) {
        if (!stats.containsKey(action))
            return;

        StatsObject stat = stats.get(action);
        stat.incrementUse();
        propChangeSupport.firePropertyChange("stats", null, null);
    }

    public void reset() {
        stats.clear();
        propChangeSupport.firePropertyChange("stats", null, null);
    }

    public List<StatsObject> getStats() {
        return new ArrayList<>(stats.values());
    }

    @Transient
    public void registerPropertyChangeSupport(PropertyChangeListener listener) {
        propChangeSupport.addPropertyChangeListener(listener);
    }

}
