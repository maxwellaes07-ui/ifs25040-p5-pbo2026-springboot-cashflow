package org.delcom.ifs25040springbootcashflow.domain.repository;

import org.delcom.ifs25040springbootcashflow.domain.entity.Role;
import org.delcom.ifs25040springbootcashflow.domain.entity.User;

import java.util.Optional;

public interface IUserRepository extends IRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    User save(String username, String passwordHash, String fullName, Role role);
}
