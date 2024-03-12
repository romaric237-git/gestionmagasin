package validator;

public enum ValidatorPattern {
    MAIL("yo");

    private String reggex;
    ValidatorPattern(String reggex) {
        this.reggex = reggex;
    }

    public String getReggex() {
        return reggex;
    }
}
