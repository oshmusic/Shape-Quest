package com.example.team_1e.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team_1e.R;
import com.example.team_1e.databinding.FragmentFightBinding;
import com.example.team_1e.model.Enemy;
import com.example.team_1e.model.Player;

/**
 * Implements IFightView using an Android fragment.
 */
public class FightFragment extends Fragment implements IFightView {
    /**
     * Reference to graphical elements from xml layout for Fight fragments
     */
    private FragmentFightBinding binding;

    /**
     * Observer to be notified of events for Fight fragments
     */
    Listener listener;


    /**
     * Constructor method for FightFragments.
     * @param listener An observer to be notified of events
     */
    public FightFragment(Listener listener){
        this.listener = listener;
        this.listener.setEnemy();
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
      this.binding = FragmentFightBinding.inflate(inflater);

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
    public void onViewCreated(View view, Bundle savedInstanceState){

        this.listener.updatePlayerStatsDisplay(this);
        this.listener.updateEnemyStatsDisplay(this);
        this.listener.updateImages(this); //not yet implemented, currently does nothing

        //Magic attack button handler
        this.binding.magicAttackButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Listener method to be called when the Magic Attack button is clicked.
             * @param view The view that was clicked on.
             */
            @Override
            public void onClick(View view) {
                FightFragment.this.listener.onMagicAttack();
                FightFragment.this.listener.updateEnemyStatsDisplay(FightFragment.this);
                FightFragment.this.listener.updatePlayerStatsDisplay(FightFragment.this);
            }
        });

        //Physical attack button handler
        this.binding.physicalAttackButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Listener method to be called when the Physical Attack button is clicked.
             * @param view The view that was clicked on.
             */
            @Override
            public void onClick(View view) {
                FightFragment.this.listener.onPhysicalAttack();
                FightFragment.this.listener.updateEnemyStatsDisplay(FightFragment.this);
                FightFragment.this.listener.updatePlayerStatsDisplay(FightFragment.this);
            }
        });

    }

    /**
     * Has the view update the Fight display to match the current stats of the
     * Player involved in the current fight.
     * @param p The Player object whose stats should be displayed on screen
     */
    @Override
    public void updatePlayerStatsDisplay(Player p) {
        this.binding.playerStats.setText(p.toString());
    }

    /**
     * Has the view update the Fight display to match the current stats of the
     * Enemy involved in the current fight.
     * @param e The Enemy object whose stats should be displayed on screen
     */
    @Override
    public void updateEnemyStatsDisplay(Enemy e) {
        this.binding.enemyStats.setText(e.toString());
    }


    /**
     * Has the view update the images on the Fight screen to match the current
     * Player and Enemy objects involved in the current fight.
     * @param p The current Player object
     * @param e The current Enemy object
     */
    public void updateImages(Player p, Enemy e) {
        switch (p.getImageTracker()) {
            //balanced Player:
            case 1:
                this.binding.playerImage.setImageResource(R.drawable.balancedplayer_2);
                break;
            //Magic focus Player
            case 2:
                this.binding.playerImage.setImageResource(R.drawable.magicplayer_2);
                break;
            //Physical focus Player
            default:
                this.binding.playerImage.setImageResource(R.drawable.physicalplayer_2);
                break;
        }
        switch (e.getImageTracker()){
            //Easy balanced Enemy
            case 1:
                this.binding.enemyImage.setImageResource(R.drawable.balancednormalenemy);
                break;
            //Easy magic Enemy
            case 2:
                this.binding.enemyImage.setImageResource(R.drawable.magicnormalenemy);
                break;
            //Easy physical Enemy
            case 3:
                this.binding.enemyImage.setImageResource(R.drawable.physicalnormalenemy);
                break;
            //MiniBoss
            case 4:
                this.binding.enemyImage.setImageResource(R.drawable.miniboss);
                break;
            //Hard balanced Enemy
            case 5:
                this.binding.enemyImage.setImageResource(R.drawable.balancedhardenemy);
                break;
            //Hard magic Enemy
            case 6:
                this.binding.enemyImage.setImageResource(R.drawable.magichardenemy);
                break;
            //Hard physical Enemy
            case 7:
                this.binding.enemyImage.setImageResource(R.drawable.physicalhardenemy);
                break;
            //Boss
            default:
                this.binding.enemyImage.setImageResource(R.drawable.boss);
                break;
        }
    }
}