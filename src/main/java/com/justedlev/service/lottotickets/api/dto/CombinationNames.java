package com.justedlev.service.lottotickets.api.dto;

public enum CombinationNames {

    ALL("all"), STRONG("strong");

    private final String title;

    CombinationNames(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
