package com.example.team_1e.view;

public interface IVictoryView {
    /**
     * Interface that specifies methods needed by other classes that
     * are interested in being notified of events happening in this view.
     */
    interface Listener{
        /**
         * Called when the user wants to return to the application's main menu.
         */
        void onMainMenuReturn();
    }
}
