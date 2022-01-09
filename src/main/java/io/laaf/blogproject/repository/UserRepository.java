package io.laaf.blogproject.repository;

import io.laaf.blogproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

// DAO
// 자동으로 Bean 등록이 된다
// @Repository 생략 가능하다.
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
