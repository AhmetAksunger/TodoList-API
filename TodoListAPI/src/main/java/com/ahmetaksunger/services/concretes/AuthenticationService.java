package com.ahmetaksunger.services.concretes;

import java.util.HashSet;
import java.util.Set;

import com.ahmetaksunger.services.rules.UserRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ahmetaksunger.entity.User;
import com.ahmetaksunger.dto.LoginResponseDTO;
import com.ahmetaksunger.entity.Role;
import com.ahmetaksunger.repository.RoleRepository;
import com.ahmetaksunger.repository.UserRepository;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRules rules;
    public User registerUser(String username, String password){

        rules.checkIfUsernameExists(username);
        rules.isUsernameValid(username);
        rules.isPasswordValid(password);

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);

        return userRepository.save(new User(0, username, encodedPassword, authorities,true));
    }

    public LoginResponseDTO loginUser(String username, String password) throws RuntimeException {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDTO(token);

        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid username or password");
        }
    }

}
