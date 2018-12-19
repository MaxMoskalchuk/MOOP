package ua.univ.lab4.TaskB.Parser.Tokens;

public class Word implements IToken {
    private Char[] word;

    public Word(String word) {
        this.word = new Char[word.length()];
        int i = 0;
        for(char ch : word.toCharArray()) {
            this.word[i++] = new Char(ch);
        }
    }

    public Char[] getWord() {
        return word;
    }

    public int getTotalLength() { return word.length; }

    public int getLength() {
        int res = 0;
        for(Char ch : word) {
            if('A' <= ch.getChar() && 'Z' >= ch.getChar()) ++res;
            if('a' <= ch.getChar() && 'z' >= ch.getChar()) ++res;
        }
        return res;
    }

    public int countVowels() {
        int cnt = 0;
        for(Char ch : word) if(ch.isVowel()) ++cnt;
        return cnt;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(Char ch : word) str.append(ch.getChar());
        return str.toString();
    }
}
