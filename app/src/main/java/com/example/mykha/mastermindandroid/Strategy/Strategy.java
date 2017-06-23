package com.example.mykha.mastermindandroid.Strategy;

import java.util.ArrayList;
import java.util.List;

public abstract class Strategy implements IStrategy {

	protected final int CODE_LENGTH;
	protected final List<Code> allCodes;
	protected final List<Answer> allAnswers;
	protected List<Code> consistentCodes;
	protected Code lastGuess;

	public Strategy(int codeLength) {
		this.CODE_LENGTH = codeLength;
		this.allCodes = Code.createAllCodes(this.CODE_LENGTH);
		this.consistentCodes = new ArrayList<Code>();
		this.allAnswers = Answer.createAllAnswers(this.CODE_LENGTH);
	}

	public Code reset() {
		this.consistentCodes.clear();
		this.consistentCodes.addAll(this.allCodes);
		this.lastGuess = Code.createRandom(CODE_LENGTH);
		return this.lastGuess;
	}

	protected List<Code> getConsistentCodes(List<Code> codes,
											Code lastGuess, Answer answer) {
		List<Code> result = new ArrayList<>();
		for (Code code : codes) {
			if (lastGuess.compare(code).equals(answer)) {
				result.add(code);
			}
		}
		return result;
	}

	protected void removeInconsistentCodes(List<Code> codes, Code lastGuess,
										   Answer answer) {
		for (int i = codes.size() - 1; i >= 0; i--) {
			if (!lastGuess.compare(codes.get(i)).equals(answer)) {
				codes.remove(i);
			}
		}
	}
}