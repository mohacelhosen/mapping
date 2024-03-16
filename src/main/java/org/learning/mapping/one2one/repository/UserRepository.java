package org.learning.mapping.one2one.repository;

import org.learning.mapping.one2one.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
