package com.be24.api.board;

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
public class BoardServlet extends HttpServlet {
    // 클라이언트가 Http POST 방식으로 요청했을 때 실행되는 메소드
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 요청 데이터를 받아오는 부분 (POST 방식인데 getParameter 쓸 거면 x-www-form-urlencoded 이 방식으로 보내야 함)
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");

        // DB 서버에 연결 및 저장하는 부분
        String sql = "INSERT INTO board (title, contents) VALUES ('" + title + "','" + contents + "')";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/java_practice", "root", "qwer1234");

            // SQL 실행 및 결과를 받아오는 객체 생성
            try (Statement stmt = conn.createStatement()) {
                Integer affectedRows = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                if (affectedRows > 0) {
                    // 게시글 Dto 반환
                    ResultSet rs = stmt.getGeneratedKeys();
                    if(rs.next()) {
                        System.out.println("저장한 게시글의 idx : " + rs.getInt(1));
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 응답하는 부분
        resp.getWriter().write("잘 저장됨");
    }
}
