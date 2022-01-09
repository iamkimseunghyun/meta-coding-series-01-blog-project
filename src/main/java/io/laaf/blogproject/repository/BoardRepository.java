package io.laaf.blogproject.repository;

import io.laaf.blogproject.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {


}
