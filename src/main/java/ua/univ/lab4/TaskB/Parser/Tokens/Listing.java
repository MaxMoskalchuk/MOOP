package ua.univ.lab4.TaskB.Parser.Tokens;

public class Listing implements IToken {
    private String code;

    public Listing(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
