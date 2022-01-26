package com.fazla.securityspring.repository;

import com.fazla.securityspring.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType , Long> {

    UserType findUserTypeByType(String type);
}
