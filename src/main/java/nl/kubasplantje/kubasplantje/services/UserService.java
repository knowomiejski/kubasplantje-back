package nl.kubasplantje.kubasplantje.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import nl.kubasplantje.kubasplantje.configuration.PasswordEncoderBCrypt;
import nl.kubasplantje.kubasplantje.models.UserModel;
import nl.kubasplantje.kubasplantje.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{

    private UserRepository userRepository;
    private PasswordEncoderBCrypt passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoderBCrypt passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
            .findByUserName(username)
            .orElseThrow(() ->
                new UsernameNotFoundException(String.format("The user: %s is not found", username))
            );
    }

    public UserModel addNewUser(UserModel userModel) {
        userModel.setPassword(passwordEncoder.passwordEncoder().encode(userModel.getPassword()));
        UserModel savedUserModel = this.userRepository.save(userModel);
        return savedUserModel;
    }
}
