package com.example.mykha.mastermindandroid.Strategy;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by mykha on 01/04/2017.
 */
public class Code {

    String code;

    public Code(Code code) {
        this.code = code.toString();
    }

    public Code(String nCode) {
        this.code = nCode;
    }

    @Override
    public String toString() {
        return code.toString();
    }

    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Code) {
            Code that = (Code) other;
            result = this.code.equals(that.code);
        }
        return result;
    }
    public Answer compare(Code other) {
        int blacks = 0; int whites = 0;
        for (int i = 0; i < this.code.length(); i++) {
            if (code.charAt(i) == other.code.charAt(i)) blacks++;
            else if (this.code.contains(other.code.charAt(i)+"")) whites++;
        }
        return new Answer(blacks, whites);
    }

    public static List<Code> createAllCodes(int length) {
        final List<Code> result = new LinkedList<Code>();
        _createAllCodes(result, length, "");
        return result;
    }

    private static void _createAllCodes(List<Code> codes, int length, String ts) {
        if (length == 0) codes.add(new Code(ts));
        else
            for (int i = 1 ;  i <= 6; i++)
                _createAllCodes(codes, length - 1, ts + i);
    }

    public static Code createRandom(int length) {
        return _createRandom(length, "");
    }

    private static Code _createRandom(int length, String ts) {

        if (length == 0) return new Code(ts);
        else
            return _createRandom(length - 1, ts + (new Random().nextInt(4) + 1));
    }
}
