package com.be24.api.board;

import com.be24.api.board.model.BoardDto;

public class BoardService {
    // --- BoardService 클래스에 싱글톤 적용 (Bill Pugh 방식) ---
    private BoardService() {
    }

    private static class SingletonHolder {
        private static final BoardService INSTANCE = new BoardService();
    }

    public static BoardService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public BoardDto register(BoardDto dto) {
        // DB에 CRUD 하는 역할을 Repository 클래스로 분리하고 -> 그 메소드를 호출해서 사용
        // BoardRepository 클래스에 싱글톤 적용, new로 객체 생성 못하니까 메소드로 객체 가져옴
        BoardRepository boardRepository = BoardRepository.getInstance();

        // 레포지토리의 결과를 반환받고, 컨트롤러에게 그 결과를 넘겨줌
        BoardDto returnDto = boardRepository.create(dto);

        return returnDto;
    }
}
