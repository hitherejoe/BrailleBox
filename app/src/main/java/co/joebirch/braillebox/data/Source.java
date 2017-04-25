package co.joebirch.braillebox.data;

enum Source {
    BBC_NEWS("bbc-news");

    private String id;

    Source(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

}
