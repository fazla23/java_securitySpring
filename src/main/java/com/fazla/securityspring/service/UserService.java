package com.fazla.securityspring.service;

import com.fazla.securityspring.model.User;
import com.fazla.securityspring.model.UserType;
import com.fazla.securityspring.repository.UserRepository;
import com.fazla.securityspring.repository.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {


    private final UserRepository userRepository;

    private final UserTypeRepository userTypeRepository;

    public User getUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public List<User> getUserByUserTypes(String userTypes){
       /* return userRepository.findUserByUserTypes(userTypes);*/
        UserType userType =  userTypeRepository.findUserTypeByType(userTypes);
        return userRepository.findUserByUserTypes(userType);
        /*return userRepository.findAll().stream().filter(user -> {
            Optional<UserType> type = user.getUserTypes().stream().filter(t->t.getType().equals(userTypes)).findAny();
            return user.getUserTypes().contains(type.get());
        }).collect(Collectors.toList());*/
    }
}
