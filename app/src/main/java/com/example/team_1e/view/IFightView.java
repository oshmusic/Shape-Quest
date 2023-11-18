package com.example.team_1e.view;

import android.view.View;

import com.example.team_1e.model.Enemy;
import com.example.team_1e.model.Player;

public interface IFightView {

    /**
     * Has the view update the Fight display to match the current stats of the
     * Player involved in the current fight.
     * @param p The Player object whose stats should be displayed on screen
     */
    void updatePlayerStatsDisplay(Player p);

    /**
     * Has the view update the Fight display to match the current stats of the
     * Enemy involved in the current fight.
     * @param e The Enemy object whose stats should be displayed on screen
     */

    void updateEnemyStatsDisplay(Enemy e);

    /**
     * Has the view update its images to match the current Player and Enemy objects
     * @param p The current Player object
     * @param e The current Enemy object
     */
    void updateImages(Player p, Enemy e);

    /**
     * Interface that specifies methods needed by other classes that
     * are interested in being notified of events happening in this view.
     */
    interface Listener{

        /**
         * Passes information from the controller to an IFightView object
         * to update the player stats display.
         * @param view The IFightView where the stats display is located
         */
        void updatePlayerStatsDisplay(IFightView view);
        /**
         * Passes information from the controller to an IFightView object
         * to update the enemy stats display.
         * @param view The IFightView where the stats display is located
         */

        void updateEnemyStatsDisplay(IFightView view);

        /**
         * Called when the user selects a magic attack. Resolves the following round of combat.
         */
        void onMagicAttack();

        /**
         * Called when the user selects a physical attack. Resolves the following round of combat.
         */
        void onPhysicalAttack();

        /**
         * Resets the Enemy object associated with the controller. Called when the IFightView is
         * initialized.
         */
        void setEnemy();

        void updateImages(IFightView view);
    }
}
