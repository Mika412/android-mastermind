package com.example.mykha.mastermindandroid;

import com.example.mykha.mastermindandroid.Strategy.Answer;
import com.example.mykha.mastermindandroid.Strategy.Code;

/**
 * Created by mykha on 03/04/2017.
 */

public class ListItem {
    private Code mCode;
    private Answer answer;
    private Boolean won;

    public ListItem() {
    }

    public ListItem(Code mCode, Answer answer, Boolean won) {
        this.mCode = mCode;
        this.answer= answer;
        this.won = won;
    }

    public Code getCode() {
        return mCode;
    }

    public void setCode(Code code) {
        this.mCode = code;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setYear(Answer answer) {
        this.answer = answer;
    }

    public Boolean getWon() {
        return won;
    }

    public void setWon(Boolean won) {
        this.won = won;
    }
}