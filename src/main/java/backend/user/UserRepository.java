package backend.user;

import backend.common.BaseRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository extends BaseRepository<User, UUID> {

    private static final String PATH = "src/main/java/backend/user/UserDataSource.txt";

    private static UserRepository userRepository;

    private UserRepository() {

    }


    @Override
    protected  String getFilePath() {
        return PATH;
    }

    public static UserRepository getInstance() {
        if (userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    public User findUserByName(String name) {
        for (User user : readFromFile()) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }
}