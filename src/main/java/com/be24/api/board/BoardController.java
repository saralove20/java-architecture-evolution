package com.be24.api.board;

import com.be24.api.board.model.BoardDto;
import com.be24.api.common.BaseResponse;
import com.be24.api.util.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// web.xml에 등록하는 것 대신에 사용하는 Annotation
@WebServlet(urlPatterns = {"/board/register"})
public class BoardController extends HttpServlet {
    // 클라이언트가 Http POST 방식으로 요청했을 때 실행되는 메소드
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 요청으로 받은 데이터를 dto 객체 형태로 변환 (요청 데이터 -> 객체)
        // 데이터 담는 부분을 DTO로 역할 분리, 데이터 전달 받아서 DTO에 담기
        // JSON 형식으로 클라이언트가 전달한 요청을 처리
        BoardDto dto = JsonParser.from(req, BoardDto.class);

        // 복잡한 로직을 처리하는 역할을 Service 클래스로 분리하고 메서드를 호출해서 사용
        BoardService boardService = new BoardService();

        // Service의 결과를 반환받게 변경
        BoardDto returnDto = boardService.register(dto);

        // 응답하는 부분, JSON 형식으로 응답을 처리
        BaseResponse res = BaseResponse.success(returnDto);
        resp.getWriter().write(JsonParser.from(res));  // HTTP 응답 Body에 글자를 쓰는 통로, 브라우저/Postman에서 보는 그 응답 내용
    }
}
