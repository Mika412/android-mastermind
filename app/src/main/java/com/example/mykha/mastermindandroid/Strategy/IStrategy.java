package com.example.mykha.mastermindandroid.Strategy;


public interface IStrategy {
	Code reset();
	Code guess(Answer answer);
}
