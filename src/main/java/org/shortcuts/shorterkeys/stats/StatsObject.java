package org.shortcuts.shorterkeys.stats;

public class StatsObject {

    private final String action;
    private final String shortcut;
    private int missedCount;
    private int usedCount;

    public StatsObject(String action, String shortcut) {
        this.action = action;
        this.shortcut = shortcut;
        this.missedCount = 0;
        this.usedCount = 0;
    }

    public void incrementMiss() {
        this.missedCount++;
    }

    public void incrementUse() {
        this.usedCount++;
    }

    public int getMissedCount() {
        return missedCount;
    }

    public int getUsedCount() {
        return usedCount;
    }

    @Override
    public String toString() {
        return "<html><b>" + shortcut + "</b> for " + action + " (missed count: " + missedCount + ", used count: " + usedCount + ")</html>";
    }
}
