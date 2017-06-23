package com.example.mykha.mastermindandroid;

import android.annotation.TargetApi;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mykha.mastermindandroid.Strategy.Answer;
import com.example.mykha.mastermindandroid.Strategy.Code;
import com.example.mykha.mastermindandroid.Strategy.IStrategy;
import com.example.mykha.mastermindandroid.Strategy.KnuthStrategy;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class GuessingComputer extends AppCompatActivity {

    static IStrategy STRATEGY = new KnuthStrategy(4);

    private static List<ListItem> guessedItemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private static GuessAdapter mAdapter;

    public static Code guess = null;
    public static int tryNumber = 0;

    public static View guessCircle1;
    public static View guessCircle2;
    public static View guessCircle3;
    public static View guessCircle4;

    public static View chooseCircle1;
    public static View chooseCircle2;
    public static View chooseCircle3;
    public static View chooseCircle4;

    public static Button guessButton;

    public static ArrayList<Integer> colors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessing_computer);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_guesses);

        guessCircle1 = (View) findViewById(R.id.guessCicleOne);
        guessCircle2 = (View) findViewById(R.id.guessCicleTwo);
        guessCircle3 = (View) findViewById(R.id.guessCicleThree);
        guessCircle4 = (View) findViewById(R.id.guessCicleFour);

        chooseCircle1 = findViewById(R.id.chooseCicleOne);
        chooseCircle2 = findViewById(R.id.chooseCicleTwo);
        chooseCircle3 = findViewById(R.id.chooseCicleThree);
        chooseCircle4 = findViewById(R.id.chooseCicleFour);

        guessButton = (Button) findViewById(R.id.guessButton);

        chooseCircle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chooseCircle1.getTag().equals("0")){
                    chooseCircle1.setTag("1");
                    chooseCircle1.setBackgroundTintList(getResources().getColorStateList(R.color.colorWhites));
                }else if(chooseCircle1.getTag().equals("1")){
                    chooseCircle1.setTag("2");
                    chooseCircle1.setBackgroundTintList(getResources().getColorStateList(R.color.colorNone));
                }else{
                    chooseCircle1.setTag("0");
                    chooseCircle1.setBackgroundTintList(getResources().getColorStateList(R.color.colorBlacks));
                }
            }
        });

        chooseCircle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chooseCircle2.getTag().equals("0")){
                    chooseCircle2.setTag("1");
                    chooseCircle2.setBackgroundTintList(getResources().getColorStateList(R.color.colorWhites));
                }else if(chooseCircle2.getTag().equals("1")){
                    chooseCircle2.setTag("2");
                    chooseCircle2.setBackgroundTintList(getResources().getColorStateList(R.color.colorNone));
                }else{
                    chooseCircle2.setTag("0");
                    chooseCircle2.setBackgroundTintList(getResources().getColorStateList(R.color.colorBlacks));
                }
            }
        });

        chooseCircle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chooseCircle3.getTag().equals("0")){
                    chooseCircle3.setTag("1");
                    chooseCircle3.setBackgroundTintList(getResources().getColorStateList(R.color.colorWhites));
                }else if(chooseCircle3.getTag().equals("1")){
                    chooseCircle3.setTag("2");
                    chooseCircle3.setBackgroundTintList(getResources().getColorStateList(R.color.colorNone));
                }else{
                    chooseCircle3.setTag("0");
                    chooseCircle3.setBackgroundTintList(getResources().getColorStateList(R.color.colorBlacks));
                }
            }
        });

        chooseCircle4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chooseCircle4.getTag().equals("0")){
                    chooseCircle4.setTag("1");
                    chooseCircle4.setBackgroundTintList(getResources().getColorStateList(R.color.colorWhites));
                }else if(chooseCircle4.getTag().equals("1")){
                    chooseCircle4.setTag("2");
                    chooseCircle4.setBackgroundTintList(getResources().getColorStateList(R.color.colorNone));
                }else{
                    chooseCircle4.setTag("0");
                    chooseCircle4.setBackgroundTintList(getResources().getColorStateList(R.color.colorBlacks));
                }
            }
        });

        getColors();
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               nextGuess();
            }
        });

        mAdapter = new GuessAdapter(this, guessedItemList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        RecyclerView.LayoutManager mLayoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);


        guess(null);
    }

    public void nextGuess(){
        int blacks = 0;
        int whites = 0;
        if(chooseCircle1.getTag().equals("0")){
            blacks++;
        }else if(chooseCircle1.getTag().equals("1")){
            whites++;
        }

        if(chooseCircle2.getTag().equals("0")){
            blacks++;
        }else if(chooseCircle2.getTag().equals("1")){
            whites++;
        }

        if(chooseCircle3.getTag().equals("0")){
            blacks++;
        }else if(chooseCircle3.getTag().equals("1")){
            whites++;
        }

        if(chooseCircle4.getTag().equals("0")){
            blacks++;
        }else if(chooseCircle4.getTag().equals("1")){
            whites++;
        }


        if(blacks == 4) {
            guessedItemList.add(new ListItem(new Code(guess), new Answer(blacks, whites), true));
            reset();
        }
        else {
            guessedItemList.add(new ListItem(new Code(guess), new Answer(blacks, whites), false));
            guess(new Answer(blacks, whites));
        }
        mAdapter.notifyDataSetChanged();
    }

    public void reset(){
        tryNumber = 0;
        guess(null);
    }

    public void guess(Answer answer) {

        if(answer == null)
            guess = STRATEGY.reset();
        else {
            Log.e("tag", answer.toString());

            guess = STRATEGY.guess(answer);
        }

        tryNumber++;
        Log.e("TAG", guess.toString() + " ");
        guessCircle1.setBackgroundTintList(getResources().getColorStateList(colors.get(Character.getNumericValue(guess.toString().charAt(0)) - 1)));
        guessCircle2.setBackgroundTintList(getResources().getColorStateList(colors.get(Character.getNumericValue(guess.toString().charAt(1)) - 1)));
        guessCircle3.setBackgroundTintList(getResources().getColorStateList(colors.get(Character.getNumericValue(guess.toString().charAt(2)) - 1)));
        guessCircle4.setBackgroundTintList(getResources().getColorStateList(colors.get(Character.getNumericValue(guess.toString().charAt(3)) - 1)));
    }

    public void getColors(){
        colors.add(R.color.purple);
        colors.add(R.color.green);
        colors.add(R.color.pink);
        colors.add(R.color.blue);
        colors.add(R.color.red);
        colors.add(R.color.yellow);
    }
}
