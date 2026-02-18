package com.be24.api.board;

import com.be24.api.board.model.BoardDto;

public class BoardService {
    private final BoardRepository boardRepository;

    // DI 의존성 주입 (AppConfig로부터 매개변수를 통해 의존성을 주입받음 (의존 객체 주입 받음))
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardDto register(BoardDto dto) {
        // 레포지토리의 결과를 반환받고, 컨트롤러에게 그 결과를 넘겨줌
        BoardDto returnDto = boardRepository.create(dto);

        return returnDto;
    }
}
