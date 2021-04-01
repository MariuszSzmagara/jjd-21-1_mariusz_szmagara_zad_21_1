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

    public static Category createFromDescription(String description) {
        for (Category category : values()) {
            if (category.getDescription().equalsIgnoreCase(description)) {
                return category;
            }
        }
        throw new IncorrectCategoryDescriptionException("Nie istnieje taka kategoria jak: " + description);
    }
}
