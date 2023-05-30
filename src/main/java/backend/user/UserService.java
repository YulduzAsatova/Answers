package backend.user;

import backend.user.dto.UserResponseDto;
import backend.user.dto.UserCreateDto;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class UserService {

    private final UserRepository userRepository = UserRepository.getInstance();

    private static UserService userService;

    private UserService() {

    }


    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }


    public UserResponseDto signIn(String name, String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            User user = userRepository.findUserByName(name);
            if (user != null) {
                byte[] encryptedPassword = digest.digest((password + user.getSalt().toString()).getBytes());
                boolean equals = Arrays.equals(user.getPassword(), encryptedPassword);
                if (equals) {
                    return new UserResponseDto(user);
                }

            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    public UserResponseDto signUp(UserCreateDto userCreatedDto) {
        User userByName = userRepository.findUserByName(userCreatedDto.getName());
        if (userByName != null) {
            return null;
        }
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setCreated(LocalDateTime.now());
        user.setUpdated(LocalDateTime.now());
        user.setUserType(userCreatedDto.getUserType());
        user.setName(userCreatedDto.getName());
        user.setBalance(userCreatedDto.getBalance());
        user.setSalt(UUID.randomUUID());
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encryptedPassword = digest.digest((userCreatedDto.getPassword() + user.getSalt().toString()).getBytes());
            user.setPassword(encryptedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        userRepository.save(user);
        return new UserResponseDto(user);
    }

    public void delete(UUID id){
        userRepository.deleteById(id);
    }

    public List<User> getList(){
        return userRepository.getAll();
    }
}
