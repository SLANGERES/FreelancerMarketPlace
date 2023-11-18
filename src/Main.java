import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Main class of java

public class Main {
    private static Map<String, User> users = new HashMap<>();
    private static Map<String, Job> jobs = new HashMap<>();
    private static User loggedInUser;

    //Main Function of the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Main Dashboard
        while (true) {
            System.out.println("Welcome to the Freelance Marketplace!");
            System.out.println("1. Register");
            System.out.println("2. Log in");
            System.out.println("3. Dashboard");
            System.out.println("4. Browse jobs");
            System.out.println("5. Post jobs");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    displayDashboard();
                    break;
                case 4:
                    browseJobs();
                    break;
                case 5:
                    postJob();
                    break;

                case 6:
                    System.out.println("Exiting the Freelance Marketplace. Goodbye!");
                    System.exit(0);
            }
        }
    }

    private static void registerUser() {
        //Register function where we used to register the new client or a freelancer

        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");


        System.out.println("Enter a new username:");
        String username = scanner.nextLine();
        if(username==null){
            System.out.println("Username Cannot be empty");
        }

        System.out.println("Enter a password:");
        String password = scanner.nextLine();
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");

        System.out.println("Are you a client or a freelancer? (Enter 'client' or 'freelancer'):");
        String userTypeInput = scanner.nextLine();
        UserType userType = UserType.valueOf(userTypeInput.toUpperCase());
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");


        User user = new User(username, password, userType);
        users.put(username, user);

        System.out.println("User registered successfully!");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    private static void loginUser() {
        //Login the user with their credentials

        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");


        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        User user = users.get(username);

        if (user != null && user.password.equals(password)) {
            loggedInUser = user;
            System.out.println("Login successful! Welcome, " + loggedInUser.username + "!");
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    private static void postJob() {

        //Post job function but firstly you have to login in the progamm as a client
        if (loggedInUser == null || loggedInUser.type != UserType.CLIENT) {
            System.out.println("You must be logged in as a client to post a job.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");


        System.out.println("Enter job title:");
        String title = scanner.nextLine();
        System.out.println("Enter job description:");
        String description = scanner.nextLine();
        System.out.println("Enter job budget:");
        double budget=50;
        try{
            budget= scanner.nextDouble();

        }
        catch (Exception e){
            System.out.println("Wrong input");
        }

        Job job = new Job(title, description, budget, loggedInUser);
        jobs.put(title, job);

        System.out.println("Job posted successfully!");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    private static void browseJobs() {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
        if (loggedInUser == null || loggedInUser.type != UserType.FREELANCER) {
            System.out.println("You must be logged in as a freelancer to browse jobs.");
            return;
        }

        System.out.println("Available Jobs:");
        for (Job job : jobs.values()) {
            if (job.freelancer == null) {
                System.out.println("Title: " + job.title);
                System.out.println("Description: " + job.description);
                System.out.println("Budget: $" + job.budget);
                System.out.println("Client: " + job.client.username);
                System.out.println();
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");

                System.out.println();
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title of the job you want to apply for:");
        String jobTitle = scanner.nextLine();

        Job job = jobs.get(jobTitle);

        if (job != null && job.freelancer == null) {
            job.freelancer = loggedInUser;
            System.out.println("Job applied successfully!");
        } else {
            System.out.println("Invalid job title or job already taken. Please try again.");
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }
    private static void displayDashboard() {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
        if (loggedInUser == null) {
            System.out.println("You must be logged in to view the dashboard.");
            return;
        }

        System.out.println("Dashboard for " + loggedInUser.username + ":");
        System.out.println("User Type: " + loggedInUser.type);

        if (loggedInUser.type == UserType.CLIENT) {
            System.out.println("Jobs Posted:");
            for (Job job : jobs.values()) {
                if (job.client == loggedInUser) {
                    System.out.println("- " + job.title);
                }
            }
        } else if (loggedInUser.type == UserType.FREELANCER) {
            System.out.println("Jobs Applied For:");
            for (Job job : jobs.values()) {
                if (job.freelancer == loggedInUser) {
                    System.out.println("- " + job.title);
                }
            }
        }

        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

}