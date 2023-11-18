package com.example.team_1e.view;

public interface IMainMenuView {
    /**
     * Interface that specifies methods needed by other classes that
     * are interested in being notified of events happening in this view.
     */
    interface Listener{
        /**
         * Called when the user wants to start a new game
         */
        void onNewGameStart();

        /**
         * Current unimplemented. Will be called when user wants to continue a game using past data.
         */
        void onLoadSavedGame();
    }
}
