package ua.univ.lab8.Utils;

public class OptionPair {
    public String optionText;
    public String optionValue;

    public OptionPair() {}

    public String getOptionText() {
        return optionText;
    }

    public OptionPair setOptionText(String optionText) {
        this.optionText = optionText;
        return this;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public OptionPair setOptionValue(String optionValue) {
        this.optionValue = optionValue;
        return this;
    }

    public String getShortValue() {
        return optionValue.substring(5);
    }
}