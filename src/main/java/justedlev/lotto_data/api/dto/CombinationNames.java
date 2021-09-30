package justedlev.lotto_data.api.dto;

public enum CombinationNames {

    ALL("all"), STRONG("strong");

    private String title;

    CombinationNames(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
