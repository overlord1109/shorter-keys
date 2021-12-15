package org.shortcuts.shorterkeys.action;

import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;

/**
 * Create a balloon pop-up notification using the given title and notification content
 **/
public class Notifier {

    private final NotificationGroup notificationGroup;

    public Notifier() {
        this.notificationGroup = NotificationGroupManager.getInstance().getNotificationGroup("Shorter Keys");
    }

    public void showNotif(String title, String content) {
        notificationGroup.createNotification(title, content, NotificationType.INFORMATION).notify(null);
    }

}
