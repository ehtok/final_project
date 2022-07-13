package by.roman.company.Service;

import by.roman.company.Entity.User;

public interface UserService {
    User findByUsername(String name);

    User saveUser(User user);
}
