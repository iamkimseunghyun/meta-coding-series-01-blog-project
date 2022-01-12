package io.laaf.blogproject.service;

import io.laaf.blogproject.config.auth.PrincipalDetail;
import io.laaf.blogproject.model.Board;
import io.laaf.blogproject.model.User;
import io.laaf.blogproject.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public Page<Board> 글목록(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board 글상세보기(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
                });
    }

    @Transactional
    public void 글삭제하기(Long id, PrincipalDetail principal) {
        Board board = boardRepository.findById(id).orElseThrow(()-> {
            return new IllegalArgumentException("글 찾기 실패: 해당 글이 존재하지 않습니다.");
        });

        if (board.getUser().getId() != principal.getUser().getId()) {
            throw new IllegalStateException("글 삭제 실패: 해당 글을 삭제할 권한이 없습니다.");
        }
        boardRepository.delete(board);
    }

    @Transactional
    public void 글수정하기(Long id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
                }); // 영속화 완료
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        // 해당 함수 종료시(Service가 종료될 때) 트랜잭션이 종료 됩니다. 이때 더티체킹 - 자동 업데이트가 됨. db flush
    }
}
