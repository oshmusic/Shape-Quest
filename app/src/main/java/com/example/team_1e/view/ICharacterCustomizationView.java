package com.example.team_1e.view;

public interface ICharacterCustomizationView {
    /**
     * Interface that specifies methods needed by other classes that
     * are interested in being notified of events happening in this view.
     */
    interface Listener{
        /**
         * Called when the user selects the balanced focus starting stats
         */
        void onBalancedSelection();

        /**
         * Called when the user selects the magic focus starting stats
         */
        void onMagicSelection();

        /**
         * Called when the user selects the physical focus starting stats
         */
        void onPhysicalSelection();
    }
}
