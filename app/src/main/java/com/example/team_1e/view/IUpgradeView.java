package com.example.team_1e.view;

import com.example.team_1e.model.Player;

public interface IUpgradeView {
    /**
     * Called to update the physical stat upgrade button on the view to reflect which upgrade
     * is available to the user.
     */
    void updatePhysUpgradeDisplay();

    /**
     * Called to update the magic stat upgrade button on the view to reflect which upgrade
     * is available to the user.
     */
    void updateMagUpgradeDisplay();

    /**
     * Called to update the health stat upgrade button on the view to reflect which upgrade
     * is available to the user.
     */
    void updateHealthUpgradeDisplay();

    /**
     * Allows controller class to see which physical upgrade is available to the user.
     * @return a value between 0-1 that was used to determine what the current physical upgrade option is
     */
    double getPhysUpgrade();

    /**
     * Allows controller class to see which magic upgrade is available to the user.
     * @return a value between 0-1 that was used to determine what the current magic upgrade option is
     */
    double getMagUpgrade();

    /**
     * Allows controller class to see which health upgrade is available to the user.
     * @return a value between 0-1 that was used to determine what the current health upgrade option is
     */
    double getHealthUpgrade();

    /**
     * Called to update the Player stats display to reflect their current stats.
     * @param p The Player object whose stats should be displayed on screen
     */
    void updatePlayerStatsUpgradeDisplay(Player p);

    /**
     * Interface that specifies methods needed by other classes that
     * are interested in being notified of events happening in this view.
     */
    interface Listener{
        /**
         * Called when the user wants to upgrade a physical stat
         */
        void onPhysicalUpgrade();

        /**
         * Called when the user wants to upgrade a magic stat
         */
        void onMagicUpgrade();

        /**
         * Called when the user wants to upgrade a health stat
         */
        void onHealthUpgrade();

        /**
         * Called when the user wants to skip upgrading their stats
         */
        void onUpgradeSkip();

        /**
         * Passes information from the controller to an IUpgradeView object to update
         * the player stats display.
         * @param view The IUpgradeView where the stats display is located
         */
        void updatePlayerStatsUpgradeDisplay(IUpgradeView view);
    }
}
