package org.example.config;

import org.example.entity.UserEntity;
import org.example.repository.RoleRepository;
import org.example.repository.UserRepository;
import org.example.service.HelperFunction;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class DefaultUsers extends HelperFunction implements ApplicationRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public DefaultUsers(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        var users = List.of("admin", "user", "manager");
        userRepository.saveAll(createUsers(users));

    }

    public List<UserEntity> createUsers(List<String> users ) {
        List<UserEntity> userEntities = new ArrayList<>();
        users.forEach(el -> {
            if (!((List<UserEntity>) userRepository.findAll()).stream().anyMatch(user -> user.getUsername().equals(el))) {
                UserEntity user = new UserEntity();
                user.setFirstName(el);
                user.setLastName(el);
                var role = roleRepository.findByName(el.toUpperCase());
                user.setRoles(Set.of(role));
                user.setUsername(el);
                user.setPassword(hashString("123456"));
                userEntities.add(user);
            }
        });

        return userEntities;
    }


}
