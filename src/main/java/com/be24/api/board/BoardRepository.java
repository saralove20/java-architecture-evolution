package com.be24.api.board;

import com.be24.api.board.model.BoardDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// DB에 CRUD 하는 부분 Repository로 역할 분리
// DB 서버에 연결 및 sql 실행
public class BoardRepository {
    // --- BoardRepository 클래스에 싱글톤 적용 (Bill Pugh 방식) ---
    private BoardRepository() {

    }

    private static class SingletonHolder {
        private static final BoardRepository INSTANCE = new BoardRepository();
    }

    public static BoardRepository getInstance() {
        return SingletonHolder.INSTANCE;
    }


    // DB 서버에 연결 및 저장하는 부분
    // 게시글 작성(등록)
    public BoardDto create(BoardDto dto) {
        String sql = "INSERT INTO board (title, contents) VALUES ('" + dto.getTitle() + "','" + dto.getContents() + "')";

        try {
            // DB 드라이버 로딩
            Class.forName("org.mariadb.jdbc.Driver");
            // DB 연결
            Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/java_practice", "root", "qwer1234");

            // SQL 실행 및 결과를 받아오는 객체 생성
            try (Statement stmt = conn.createStatement()) {
                Integer affectedRows = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                if (affectedRows > 0) {
                    // 게시글 Dto 반환
                    ResultSet rs = stmt.getGeneratedKeys();
                    if(rs.next()) {
                        // DB 실행결과를 새로운 DTO에 담아서 반환 (요청 DTO와 응답 DTO는 다른 경우가 많기 때문에 굳이 따로 만들어봤음)
                        BoardDto returnDto = new BoardDto(
                                rs.getInt(1),
                                dto.getTitle(),
                                dto.getContents()
                        );
                        return returnDto;
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
