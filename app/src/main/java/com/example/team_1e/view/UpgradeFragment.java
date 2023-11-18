package com.example.team_1e.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team_1e.R;
import com.example.team_1e.databinding.FragmentFightBinding;
import com.example.team_1e.databinding.FragmentUpgradeBinding;
import com.example.team_1e.model.Player;

public class UpgradeFragment extends Fragment implements IUpgradeView {
    /**
     * Reference to graphical elements from xml layout for Upgrade fragments
     */
    private FragmentUpgradeBinding binding;

    /**
     * Observer to be notified of events for Upgrade fragments
     */
    IUpgradeView.Listener listener;

    /**
     * Double representing the physical upgrade option for the current upgrade screen
     */
    private double physUpgrade = Math.random();

    /**
     * Double representing the magic upgrade option for the current upgrade screen
     */
    private double magUpgrade = Math.random();

    /**
     * Double representing the health upgrade option for the current upgrade screen
     */
    private double healthUpgrade = Math.random();

    /**
     * Constructor for UpgradeFragments
     * @param listener An observer to be notified of events
     */
    public UpgradeFragment(IUpgradeView.Listener listener) {this.listener = listener;}

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
       this.binding = FragmentUpgradeBinding.inflate(inflater);
       this.updateHealthUpgradeDisplay();
       this.updateMagUpgradeDisplay();
       this.updatePhysUpgradeDisplay();
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

        this.listener.updatePlayerStatsUpgradeDisplay(this);
        // physical upgrade handler
        this.binding.physicalUpgradeButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Listener method to be called when the Physical Upgrade button is clicked.
             * @param view The view that was clicked on.
             */
            @Override
            public void onClick(View view) {
                UpgradeFragment.this.listener.onPhysicalUpgrade();
            }
        });
        // magic upgrade handler
        this.binding.magicUpgradeButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Listener method to be called when the Magic Upgrade button is clicked.
             * @param view The view that was clicked on.
             */
            @Override
            public void onClick(View view) {
                UpgradeFragment.this.listener.onMagicUpgrade();
            }
        });
        // health upgrade handler
        this.binding.healthUpgradeButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Listener method to be called when the Health Upgrade button is clicked.
             * @param view The view that was clicked on.
             */
            @Override
            public void onClick(View view) {
                UpgradeFragment.this.listener.onHealthUpgrade();
            }
        });
        // skip handler
        this.binding.upgradeSkipButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Listener method to be called when the Skip button is clicked.
             * @param view The view that was clicked on.
             */
            @Override
            public void onClick(View view) { UpgradeFragment.this.listener.onUpgradeSkip(); }
        });
    }

    /**
     * Has the view update the Upgrade display to reflect the options the Player has for their Physical Upgrade.
     */
    @Override
    public void updatePhysUpgradeDisplay() {
        if (this.physUpgrade < 0.5) {
            this.binding.physicalUpgradeButton.setText(R.string.physicalAttackUpgradeButtonText);
        }
        else {
            this.binding.physicalUpgradeButton.setText(R.string.physicalDefenseUpgradeButtonText);
        }
    }

    /**
     * Has the view update the Upgrade display to reflect the options the Player has for their Magic Upgrade.
     */
    @Override
    public void updateMagUpgradeDisplay() {
        if (magUpgrade < 0.5) {
            this.binding.magicUpgradeButton.setText(R.string.magicAttackUpgradeButtonText);
        }
        else {
            this.binding.magicUpgradeButton.setText(R.string.magicDefenseUpgradeButtonText);
        }
    }

    /**
     * Has the view update the Upgrade display to reflect the options the Player has for their Health Upgrade.
     */
    @Override
    public void updateHealthUpgradeDisplay() {
        if (healthUpgrade < 0.8){
            this.binding.healthUpgradeButton.setText(R.string.currentHealthUpgradeButtonText);
        }
        else {
            this.binding.healthUpgradeButton.setText(R.string.maxHealthUpgradeButtonText);
        }
    }

    /**
     * A method that allows the controller class to see the value that determined which Physical
     * Upgrade option was presented the Player.
     * @return a value between 0-1 that was used to determine what the current Physical Upgrade option is
     */
    @Override
    public double getPhysUpgrade() { return this.physUpgrade; }

    /**
     * A method that allows the controller class to see the value that determined which Magic
     * Upgrade option was presented the Player.
     * @return a value between 0-1 that was used to determine what the current Magic Upgrade option is
     */
    @Override
    public double getMagUpgrade() { return this.magUpgrade; }

    /**
     * A method that allows the controller class to see the value that determined which Health
     * Upgrade option was presented the Player.
     * @return a value between 0-1 that was used to determine what the current Health Upgrade option is
     */
    @Override
    public double getHealthUpgrade() { return this.healthUpgrade; }

    /**
     * A method that updates the Player stats display to reflect their current stats.
     * @param p The Player object whose stats should be displayed on screen
     */
    public void updatePlayerStatsUpgradeDisplay(Player p) {
        this.binding.upgradeStatsLabel.setText(p.toString());
    }
}