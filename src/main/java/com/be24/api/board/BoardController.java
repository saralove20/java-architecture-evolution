package com.be24.api.board;

import com.be24.api.board.model.BoardDto;
import com.be24.api.common.BaseResponse;
import com.be24.api.common.Controller;
import com.be24.api.util.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 이제 라우팅 처리를 DispatcherServlet & AppConfig가 해주기 때문에 주석 처리 (= 필요 없음)
// @WebServlet(urlPatterns = {"/board/register"})
public class BoardController implements Controller {
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("BoardController 실행 됨");

        return "";
    }
}
