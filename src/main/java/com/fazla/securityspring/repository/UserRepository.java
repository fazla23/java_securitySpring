package com.fazla.securityspring.repository;

import com.fazla.securityspring.model.User;
import com.fazla.securityspring.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findUserByUsername(String username);

    List<User> findUserByUserTypes(UserType userTypes);
}
