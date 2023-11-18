class User {
    String username;
    String password;
    UserType type;

    public User(String username, String password, UserType type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }
}

enum UserType {
    CLIENT,
    FREELANCER
}