package com.unkownkoder.services.rules;

import com.unkownkoder.repository.UserRepository;
import com.unkownkoder.utils.exceptions.InvalidNameException;
import com.unkownkoder.utils.exceptions.InvalidPasswordException;
import com.unkownkoder.utils.exceptions.UsernameAlreadyExistsException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserRules {

    @Autowired
    private UserRepository userRepository;
    public void isUsernameValid(String username){
        if(username.length() < 3){
            throw new InvalidNameException("Username must be at least 3 characters long");
        }else if(username.length() > 16){
            throw new InvalidNameException("Username cannot be longer than 16 characters");
        }

    }

    public void checkIfUsernameExists(String username){
        if (userRepository.existsByUsername(username)){
            throw new UsernameAlreadyExistsException();
        }
    }

    public void isPasswordValid(String password){
        if(password.length() < 5){
            throw new InvalidPasswordException("Password length must be greater than 5");
        }else if(!password.matches(".*\\d.*")) {

            throw new InvalidPasswordException("Password must contain at least one digit");
        }
        // more password filters. (Not going to include here because it's just a simple project)
    }

}
