package com.example.team_1e.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team_1e.databinding.FragmentSeeTutorialBinding;

public class SeeTutorialFragment extends Fragment {

    /**
     * Reference to graphical elements from xml layout for See Tutorial fragments
     */
    private FragmentSeeTutorialBinding binding;

    /**
     * Observer to be notified of events for See Tutorial fragments
     */
    ISeeTutorialView.Listener listener;

    /**
     * Constructor method for seeTutorialFragments.
     * @param listener An observer to be notified of events
     */
    public SeeTutorialFragment(ISeeTutorialView.Listener listener) {
        this.listener = listener;
    }

    /**
     * Overrides method of same name from the superclass. Inflates the xml layout
     * associated with the fragment.
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @return The root of the layout that has just been inflated.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentSeeTutorialBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    /**
     * Overrides the method of the same name from the superclass. Called by the Android platform
     * after the layout has been inflated but before the view transitions to the created state.
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // stat tutorial button
        this.binding.statTutorialButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Listener method to be called when the Stat Tutorial button is clicked.
             * @param view The view that was clicked on.
             */
            @Override
            public void onClick(View view) {
                SeeTutorialFragment.this.listener.onStatTutorialButton();
            }
        });
        // combat tutorial button
        this.binding.combatTutorialButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Listener method to be called when the Combat Tutorial button is clicked.
             * @param view The view that was clicked on.
             */
            @Override
            public void onClick(View view) {
                SeeTutorialFragment.this.listener.onFightTutorialButton();
            }
        });
        // upgrade tutorial button
        this.binding.upgradeTutorialButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Listener method to be called when the Upgrade Tutorial button is clicked.
             * @param view The view that was clicked on.
             */
            @Override
            public void onClick(View view) {
                SeeTutorialFragment.this.listener.onUpgradeTutorialButton();
            }
        });
        // skip tutorial button
        this.binding.skipTutorialButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Listener method to be called when the Skip Tutorial button is clicked.
             * @param view The view that was clicked on.
             */
            @Override
            public void onClick(View view) {
                SeeTutorialFragment.this.listener.onSkipTutorial();
            }
        });
    }
}