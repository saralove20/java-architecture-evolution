package com.be24.api.board;

import com.be24.api.board.model.BoardDto;
import com.be24.api.common.BaseResponse;
import com.be24.api.common.Controller;
import com.be24.api.util.JsonParser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 이제 라우팅 처리를 DispatcherServlet & AppConfig가 해주기 때문에 주석 처리 (= 필요 없음)
// @WebServlet(urlPatterns = {"/board/register"})
public class BoardController implements Controller {
    private final BoardService boardService;

    // DI 의존성 주입 (AppConfig로부터 매개변수를 통해 의존성을 주입받음 (의존 객체 주입 받음))
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @Override
    public BaseResponse process(HttpServletRequest req, HttpServletResponse resp) {
        BoardDto returnDto = null;

       if (req.getRequestURI().contains("register") && req.getMethod().equals("POST")) {    // 게시글 작성 기능
           // 클라이언트가 JSON 형식으로 전달한 요청을 처리 -> Dto 객체로 만듦
           BoardDto dto = JsonParser.from(req, BoardDto.class);
           // Service의 결과를 반환 받게 변경
           returnDto = boardService.register(dto);
       } else if (req.getRequestURI().contains("read") && req.getMethod().equals("GET")) {  // 게시글 조회 기능
          String boardIdx = req.getParameter("idx");
          returnDto = boardService.read(boardIdx);
       }

        return BaseResponse.success(returnDto);
    }
}
