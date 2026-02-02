package com.be24.api.board;

import com.be24.api.board.model.BoardDto;

public class BoardService {
    public void register(BoardDto dto) {
        // DB에 CRUD 하는 역할을 Repository 클래스로 분리하고 -> 그 메소드를 호출해서 사용
        BoardRepository boardRepository = new BoardRepository();
        boardRepository.create(dto);
    }
}
