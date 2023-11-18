class Job {
    String title;
    String description;
    double budget;
    User client;
    User freelancer;

    public Job(String title, String description, double budget, User client) {
        this.title = title;
        this.description = description;
            this.budget = budget;


        this.client = client;
    }
}