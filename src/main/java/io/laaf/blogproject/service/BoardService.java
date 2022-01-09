package io.laaf.blogproject.service;

import io.laaf.blogproject.model.Board;
import io.laaf.blogproject.model.User;
import io.laaf.blogproject.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void 글쓰기(Board board, User user) { // title, content
        board.setCount(0L);
        board.setUser(user);
        boardRepository.save(board);
    }
}
