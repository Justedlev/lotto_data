package justedlev.lotto_data.api.dto;

public enum CombinationNames {

    FIRST("first"), SECOND("second"), THIRD("third"), FOURTH("fourth"), FIFTH("fifth"), SIXTH("sixth"), STRONG("strong");

    private String combination;

    CombinationNames(String combination) {
        this.combination = combination;
    }

    public String getCombination() {
        return combination;
    }
}
