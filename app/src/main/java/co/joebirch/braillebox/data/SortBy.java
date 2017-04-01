package co.joebirch.braillebox.data;

public enum SortBy {
    TOP("top"),
    LATEST("latest"),
    POPULAR("popular");

    private String label;

    SortBy(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

}
