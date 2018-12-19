package ua.univ.lab4.TaskB.Parser.Tokens;

public class Char {
    private char charValue;

    public Char(char ch) {
        this.charValue = ch;
    }

    public char getChar() {
        return charValue;
    }

    public boolean isVowel() {
        return "AEIOUYaeiouy".indexOf(charValue) != -1;
    }

    public boolean isConsonant() {
        return !isVowel();
    }
}
