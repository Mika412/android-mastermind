package com.example.mykha.mastermindandroid.Strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mykha on 01/04/2017.
 */
public class Answer {

    /** Number of correct buttons on the correct positions */
    public final int blacks;
    /** Number of correct buttons on the wrong positions */
    public final int whites;

    public Answer(int blacks, int whites) {
        this.blacks = blacks;
        this.whites = whites;
    }

    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Answer) {
            Answer that = (Answer) other;
            result = (this.blacks == that.blacks && this.whites == that.whites);
        }
        return result;
    }

    @Override
    public String toString() {
        return "B: " + this.blacks + " W: " + this.whites;
    }

    //Create all possible anwers for a specific length.
    public static List<Answer> createAllAnswers(int length) {
        List<Answer> result = new ArrayList<Answer>();
        for (int blacks = 0; blacks < length; blacks++) {
            for (int whites = 0; whites < length; whites++) {
                int sum = whites + blacks;
                if (sum <= length && !(blacks == length - 1 && whites == 1)) {
                    result.add(new Answer(blacks, whites));
                }
            }
        }
        return result;
    }

}