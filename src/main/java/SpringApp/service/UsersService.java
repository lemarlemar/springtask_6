package SpringApp.service;

import SpringApp.model.User;
import SpringApp.repositories.UsersRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsersService {

    private UsersRepo usersRepo;

    @Autowired
    public UsersService(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Transactional
    public void addUser(User user) {
        usersRepo.save(user);
    }

    @Transactional
    public void updateUser(int id, User updatedUser) {
        updatedUser.setId(id);
        usersRepo.save(updatedUser);
    }

    @Transactional
    public void deleteUser(int id) {
        usersRepo.deleteById(id);
    }


    public User getUser(int id) {
        Optional<User> foundUser = usersRepo.findById(id);
        return foundUser.orElse(null);
    }


    public List<User> getAllUsers() {
        return usersRepo.findAll();
    }
}
