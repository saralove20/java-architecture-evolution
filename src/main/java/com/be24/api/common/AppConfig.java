package com.be24.api.common;

import com.be24.api.board.BoardController;
import com.be24.api.board.BoardRepository;
import com.be24.api.board.BoardRepositoryImpl;
import com.be24.api.board.BoardService;
import com.be24.api.user.UserController;

import java.util.HashMap;
import java.util.Map;

public class AppConfig {
    // 특정 uri가 어떤 컨트롤러와 매핑되는지 저장해놓을 Map
    private final Map<String, Controller> controllerMap = new HashMap<>();

    // 의존성을 AppConfig 한 곳에서 전부 관리
    // AppConfig에서 객체 생성함 + 각 클래스별로 필요한 의존성(의존 객체)을 주입해줌
    private final BoardRepository boardRepository = new BoardRepositoryImpl();
    private final BoardService boardService = new BoardService(boardRepository);

    // 처음 객체가 생성될 때 controllerMap에 uri를 키로 컨트롤러 객체를 값으로 저장
    public AppConfig() {
        controllerMap.put("/board/register", new BoardController(boardService));
        controllerMap.put("/user/signup", new UserController());
    }

    // 특정 uri를 이용해서 해당하는 특정 컨트롤러 객체를 반환하는 메소드
    public Controller getController(String uri) {
        return controllerMap.get(uri);
    }
}
