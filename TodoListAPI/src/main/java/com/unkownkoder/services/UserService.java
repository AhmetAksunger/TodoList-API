package com.unkownkoder.services;

import com.unkownkoder.dto.UpdateUserRequest;
import com.unkownkoder.entity.Role;
import com.unkownkoder.entity.User;
import com.unkownkoder.dto.ProfileResponse;
import com.unkownkoder.repository.RoleRepository;
import com.unkownkoder.utils.mappers.ModelMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.unkownkoder.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapperService modelMapperService;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("In the user details service");

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
    }

    public ProfileResponse getProfile(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        ProfileResponse profileResponse = modelMapperService.forResponse().map(user,ProfileResponse.class);

        return profileResponse;
    }

    public void updateUser(UpdateUserRequest updateUserRequest){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User tempUser = userRepository.findByUsername(username).orElseThrow();

        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);

        User user = modelMapperService.forRequest().map(updateUserRequest,User.class);

        String encodedPassword = encoder.encode(updateUserRequest.getPassword());

        user.setPassword(encodedPassword);
        user.setAuthorities(authorities);
        user.setUserId(tempUser.getUserId());
        userRepository.save(user);
    }

}
