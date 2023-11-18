package com.example.team_1e.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team_1e.R;
import com.example.team_1e.databinding.FragmentTutorialBinding;

public class TutorialFragment extends Fragment implements ITutorialView {
    /**
     * Reference to graphical elements from xml layout for Tutorial fragments
     */
    private @NonNull FragmentTutorialBinding binding;
    /**
     * Observer to be notified of events for Tutorial fragments
     */
    private ITutorialView.Listener listener;
    /**
     * Array that holds the text for the tutorial pages
     */
    private String[] tutorialText;
    /**
     * Integer that represents what page of the tutorial to show to the player
     */
    private int tutorialProgress;

    /**
     * Constructor method for TutorialFragments.
     * @param listener An observer to be notified of events
     */
    public TutorialFragment(ITutorialView.Listener listener) {
        this.listener = listener;
        this.tutorialProgress = this.listener.getGameState().getTutorialProgress();
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
        this.binding = FragmentTutorialBinding.inflate(inflater);
        tutorialText = getContext().getResources().getStringArray(R.array.tutorialStringsArray);
        this.updateTutorialDisplay();
        this.updateTutorialImage();
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
        // continue button handler
        this.binding.tutorialContinueButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Listener method to be called when the Tutorial Continue button is clicked.
             * @param view The view that was clicked on.
             */
            @Override
            public void onClick(View view) {
                TutorialFragment.this.listener.onTutorialContinue();
            }
        });
    }

    /**
     * Has the view update the Tutorial display to reflect the Tutorial Progress of the Player.
     */
    public void updateTutorialDisplay() {
        this.binding.tutorial.setText(tutorialText[tutorialProgress]);
    }

    /**
     * Has the view update the Tutorial image to reflect the Tutorial Progress of the Player.
     */
  @Override
  public void updateTutorialImage(){
        switch (tutorialProgress){
            //Fight screen
            case 1:
                this.binding.tutorialImage.setImageResource(R.drawable.fightscreengreen);
                break;
            //upgrade screen
            case 2:
                this.binding.tutorialImage.setImageResource(R.drawable.upgradescreengreen);
                break;
            default:
                this.binding.tutorialImage.setImageResource(R.drawable.stats_symbols);
                break;
        }
    }
}