package com.be24.api.board;

import com.be24.api.board.model.BoardDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// DB에 CRUD 하는 부분 Repository로 역할 분리
// DB 서버에 연결 및 sql 실행
public class BoardRepository {
    // 게시글 작성(등록)
    public void create(BoardDto dto) {
        String sql = "INSERT INTO board (title, contents) VALUES ('" + dto.getTitle() + "','" + dto.getContents() + "')";

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
    }
}
