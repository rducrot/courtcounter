package com.example.android.courtcounter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Variables corresponding to the views.
    TextView mPlOnePrizes, mPlOneCards, mPlOnePokemons;
    TextView mPlTwoPrizes, mPlTwoCards, mPlTwoPokemons;
    Button mPlOneDrawPrize, mPlOnePlusCard, mPlOneMinusCard, mPlOnePlusPokemon, mPlOneMinusPokemon;
    Button mPlTwoDrawPrize, mPlTwoPlusCard, mPlTwoMinusCard, mPlTwoPlusPokemon, mPlTwoMinusPokemon;
    Button mReset;

    //Variables in wich are stored the players' values.
    int plOnePrizes, plOneCards, plOnePokemons;
    int plTwoPrizes, plTwoCards, plTwoPokemons;

    //Variable that determines if the buttons can be clicked.
    Boolean partyOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlOnePrizes = findViewById(R.id.playerOnePrizes);
        mPlOneCards = findViewById(R.id.playerOneCards);
        mPlOnePokemons = findViewById(R.id.playerOnePokemons);

        mPlTwoPrizes = findViewById(R.id.playerTwoPrizes);
        mPlTwoCards = findViewById(R.id.playerTwoCards);
        mPlTwoPokemons = findViewById(R.id.playerTwoPokemons);

        mPlOneDrawPrize = findViewById(R.id.playerOneDrawPrize);
        mPlOnePlusCard = findViewById(R.id.playerOnePlusCard);
        mPlOneMinusCard = findViewById(R.id.playerOneMinusCard);
        mPlOnePlusPokemon = findViewById(R.id.playerOnePlusPokemon);
        mPlOneMinusPokemon = findViewById(R.id.playerOneMinusPokemon);

        mPlTwoDrawPrize = findViewById(R.id.playerTwoDrawPrize);
        mPlTwoPlusCard = findViewById(R.id.playerTwoPlusCard);
        mPlTwoMinusCard = findViewById(R.id.playerTwoMinusCard);
        mPlTwoPlusPokemon = findViewById(R.id.playerTwoPlusPokemon);
        mPlTwoMinusPokemon = findViewById(R.id.playerTwoMinusPokemon);

        mReset = findViewById(R.id.reset);

        mPlOneDrawPrize.setOnClickListener(this);
        mPlOnePlusCard.setOnClickListener(this);
        mPlOneMinusCard.setOnClickListener(this);
        mPlOnePlusPokemon.setOnClickListener(this);
        mPlOneMinusPokemon.setOnClickListener(this);

        mPlTwoDrawPrize.setOnClickListener(this);
        mPlTwoPlusCard.setOnClickListener(this);
        mPlTwoMinusCard.setOnClickListener(this);
        mPlTwoPlusPokemon.setOnClickListener(this);
        mPlTwoMinusPokemon.setOnClickListener(this);

        mReset.setOnClickListener(this);

        plOnePrizes = Integer.parseInt(getString(R.string.initialPrizes));
        plOneCards = Integer.parseInt(getString(R.string.initialCards));
        plOnePokemons = Integer.parseInt(getString(R.string.initialPokemons));

        plTwoPrizes = Integer.parseInt(getString(R.string.initialPrizes));
        plTwoCards =Integer.parseInt(getString(R.string.initialCards));
        plTwoPokemons = Integer.parseInt(getString(R.string.initialPokemons));

        partyOver = false;

        //Gets the values from saveInstanceState when the view is switched.
        if(savedInstanceState != null){
            //Restores the values of the variables.
            plOnePrizes = savedInstanceState.getInt("playerOnePrizes");
            plOneCards = savedInstanceState.getInt("playerOneCards");
            plOnePokemons = savedInstanceState.getInt("playerOnePokemons");
            plTwoPrizes = savedInstanceState.getInt("playerTwoPrizes");
            plTwoCards = savedInstanceState.getInt("playerTwoCards");
            plTwoPokemons = savedInstanceState.getInt("playerTwoPokemons");
            //Displays the values on the correct views.
            mPlOnePrizes.setText(String.valueOf(savedInstanceState.getInt("playerOnePrizes")));
            mPlOneCards.setText(String.valueOf(savedInstanceState.getInt("playerOneCards")));
            mPlOnePokemons.setText(String.valueOf(savedInstanceState.getInt("playerOnePokemons")));
            mPlTwoPrizes.setText(String.valueOf(savedInstanceState.getInt("playerTwoPrizes")));
            mPlTwoCards.setText(String.valueOf(savedInstanceState.getInt("playerTwoCards")));
            mPlTwoPokemons.setText(String.valueOf(savedInstanceState.getInt("playerTwoPokemons")));
            //Disables the buttons if the party is over.
            partyOver = savedInstanceState.getBoolean("partyOver");
        }

    }

    /**
     * Method to assign the methods with the parameters of their corresponding buttons.
     * @param view
     */
    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.playerOneDrawPrize: {
                subtractValue(mPlOnePrizes, plOnePrizes);
                break;
            }
            case R.id.playerTwoDrawPrize: {
                subtractValue(mPlTwoPrizes, plTwoPrizes);
                break;
            }
            case R.id.playerOnePlusCard: {
                addValue(mPlOneCards, plOneCards);
                break;
            }
            case R.id.playerOneMinusCard: {
                subtractValue(mPlOneCards, plOneCards);
                break;
            }
            case R.id.playerTwoPlusCard: {
                addValue(mPlTwoCards, plTwoCards);
                break;
            }
            case R.id.playerTwoMinusCard: {
                subtractValue(mPlTwoCards, plTwoCards);
                break;
            }
            case R.id.playerOnePlusPokemon: {
                addValue(mPlOnePokemons, plOnePokemons);
                break;
            }
            case R.id.playerOneMinusPokemon: {
                subtractValue(mPlOnePokemons, plOnePokemons);
                break;
            }
            case R.id.playerTwoPlusPokemon: {
                addValue(mPlTwoPokemons, plTwoPokemons);
                break;
            }
            case R.id.playerTwoMinusPokemon: {
                subtractValue(mPlTwoPokemons, plTwoPokemons);
                break;
            }
            case R.id.reset: {
                    reset();
                break;
            }
        }
    }

    /**
     * Method to save the values.
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("playerOnePrizes", plOnePrizes);
        outState.putInt("playerOneCards", plOneCards);
        outState.putInt("playerOnePokemons", plOnePokemons);
        outState.putInt("playerTwoPrizes", plTwoPrizes);
        outState.putInt("playerTwoCards", plTwoCards);
        outState.putInt("playerTwoPokemons", plTwoPokemons);
        outState.putBoolean("partyOver", partyOver);
    }

    /**
     * Method to restore the values.
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        plOnePrizes = savedInstanceState.getInt("playerOnePrizes");
        plOneCards = savedInstanceState.getInt("playerOneCards");
        plOnePokemons = savedInstanceState.getInt("playerOnePokemons");
        plTwoPrizes = savedInstanceState.getInt("playerTwoPrizes");
        plTwoCards = savedInstanceState.getInt("playerTwoCards");
        plTwoPokemons = savedInstanceState.getInt("playerTwoPokemons");
        partyOver = savedInstanceState.getBoolean("partyOver");
    }

    /**
     * Displays a given value to its corresponding view.
     */
    public void displayValue(TextView view, int value) {
        TextView valueView = view;
        valueView.setText(String.valueOf(value));
    }

    /**
     * Adds one to a given value.
     * A player can't have more than a total of 60 cards.
     * A player can't have more than 6 active Pokemons.
     * @param view
     * @param value
     */
    public void addValue(TextView view, int value) {
        // Actions to do if no player has won.
        if (!partyOver) {
            if (view.equals(mPlOneCards)) {
                if (plOnePrizes + plOneCards + plOnePokemons < 60) {
                    value += 1;
                    plOneCards = value;
                } else {
                    Toast toast = Toast.makeText(this, R.string.addCardsAlert, Toast.LENGTH_LONG);
                    toast.show();
                }
            } else if (view.equals(mPlTwoCards)) {
                if (plTwoPrizes + plTwoCards + plTwoPokemons < 60) {
                    value += 1;
                    plTwoCards = value;
                } else {
                    Toast toast = Toast.makeText(this, R.string.addCardsAlert, Toast.LENGTH_LONG);
                    toast.show();
                }
            } else if (view.equals(mPlOnePokemons)) {
                if (plOnePokemons < 6 && plOnePrizes + plOneCards + plOnePokemons < 60) {
                    value += 1;
                    plOnePokemons = value;
                } else {
                    Toast toast = Toast.makeText(this, R.string.addPokemonAlert, Toast.LENGTH_SHORT);
                    toast.show();
                }
            } else if (view.equals(mPlTwoPokemons)) {
                if (plTwoPokemons < 6 && plTwoPrizes + plTwoCards + plTwoPokemons < 60) {
                    value += 1;
                    plTwoPokemons = value;
                } else {
                    Toast toast = Toast.makeText(this, R.string.addPokemonAlert, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            displayValue(view, value);

        } else {
            Toast toast = Toast.makeText(this, R.string.partyOver, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * Subtracts one to a given value.
     * @param view
     * @param value
     */
    public void subtractValue(TextView view, int value) {
        if (!partyOver) {

            value -= 1;

            if (view.equals(mPlOnePrizes)) {
                plOnePrizes = value;
            } else if (view.equals(mPlTwoPrizes)) {
                plTwoPrizes = value;
            } else if (view.equals(mPlOneCards)) {
                plOneCards = value;
            } else if (view.equals(mPlTwoCards)) {
                plTwoCards = value;
            } else if (view.equals(mPlOnePokemons)) {
                plOnePokemons = value;
            } else if (view.equals(mPlTwoPokemons)) {
                plTwoPokemons = value;
            }

            displayValue(view, value);

            endGame();
        } else {
            Toast toast = Toast.makeText(this, R.string.partyOver, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * Message to display at the end of the game on certain conditions:
     * If a player has no remaining prize, he wins.
     * If a player has no remaining card at the beginning of its turn, he loses.
     * If a player has no remaining active Pokemon, he loses.
     * If the party is over, no button can be clicked except the "Reset" button.
     */
    public void endGame(){

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        String message = "";

        if (plOnePrizes == 0) {
            message += getString(R.string.wonPrizes, getString(R.string.playerOne));
            dialog.setMessage(message);
            AlertDialog alert = dialog.create();
            alert.show();
            partyOver = true;
        } else if (plTwoPrizes == 0) {
            message += getString(R.string.wonPrizes, getString(R.string.playerTwo));
            dialog.setMessage(message);
            AlertDialog alert = dialog.create();
            alert.show();
            partyOver = true;
        } else if (plOneCards == 0) {
            message += getString(R.string.wonCards, getString(R.string.playerTwo));
            dialog.setMessage(message);
            AlertDialog alert = dialog.create();
            alert.show();
            partyOver = true;
        } else if (plTwoCards == 0) {
            message += getString(R.string.wonCards, getString(R.string.playerOne));
            dialog.setMessage(message);
            AlertDialog alert = dialog.create();
            alert.show();
            partyOver = true;
        } else if (plOnePokemons == 0) {
            message += getString(R.string.wonPokemons, getString(R.string.playerTwo));
            dialog.setMessage(message);
            AlertDialog alert = dialog.create();
            alert.show();
            partyOver = true;
        } else if (plTwoPokemons == 0) {
            message += getString(R.string.wonPokemons, getString(R.string.playerOne));
            dialog.setMessage(message);
            AlertDialog alert = dialog.create();
            alert.show();
            partyOver = true;
        }
    }

    /**
     * Resets the scores to their initial values.
     */
    public void reset() {
        plOnePrizes = Integer.parseInt(getString(R.string.initialPrizes));
        plTwoPrizes = Integer.parseInt(getString(R.string.initialPrizes));
        plOneCards = Integer.parseInt(getString(R.string.initialCards));
        plTwoCards = Integer.parseInt(getString(R.string.initialCards));
        plOnePokemons = Integer.parseInt(getString(R.string.initialPokemons));
        plTwoPokemons = Integer.parseInt(getString(R.string.initialPokemons));

        displayValue(mPlOnePrizes, plOnePrizes);
        displayValue(mPlTwoPrizes, plTwoPrizes);
        displayValue(mPlOneCards, plOneCards);
        displayValue(mPlTwoCards, plTwoCards);
        displayValue(mPlOnePokemons, plOnePokemons);
        displayValue(mPlTwoPokemons, plTwoPokemons);

        //Buttons can be clicked again.
        partyOver = false;
    }
}
