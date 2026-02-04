package Service;

import Models.User;
import Repository.UsersRepository;

public class UserService {

    private final UsersRepository repo = new UsersRepository();

    public boolean register(String email, String password, String type) {
        if (type == null) return false;

        type = type.trim().toUpperCase();

        if (!type.equals("PERSONAL") &&
                !type.equals("CREATOR") &&
                !type.equals("BUSINESS")) {
            System.out.println("Invalid account type");
            return false;
        }

        return repo.register(new User(email, password, type));
    }


    public User login(String email, String password) {
        return repo.login(email, password);
    }
}
