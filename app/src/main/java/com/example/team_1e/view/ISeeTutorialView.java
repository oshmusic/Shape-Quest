package com.example.team_1e.view;

public interface ISeeTutorialView {
    /**
     * Interface that specifies methods needed by other classes that
     * are interested in being notified of events happening in this view.
     */
    interface Listener {
        /**
         * Called when the user wants to see the Stat Tutorial
         */
        void onStatTutorialButton();

        /**
         * Called when the user wants to see the Fight Tutorial
         */
        void onFightTutorialButton();

        /**
         * Called when the user wants to see the Upgrade Tutorial
         */
        void onUpgradeTutorialButton();

        /**
         * Called when the user wants to skip the tutorial
         */
        void onSkipTutorial();
    }
}
