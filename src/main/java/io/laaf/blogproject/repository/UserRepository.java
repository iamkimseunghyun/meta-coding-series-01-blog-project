package io.laaf.blogproject.repository;

import io.laaf.blogproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// DAO
// 자동으로 Bean 등록이 된다
// @Repository 생략 가능하다.
public interface UserRepository extends JpaRepository<User, Long> {

}
