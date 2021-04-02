package pl.javastart.model;

public enum Category {
    FOOD("Art. spożywcze"), HOME("Art. gosp. domowego"), OTHERS("Inne");
    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
