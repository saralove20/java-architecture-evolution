package com.be24.api.board;

import com.be24.api.board.model.BoardDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// DB에 CRUD 하는 부분 Repository로 역할 분리
// DB 서버에 연결 및 sql 실행
public interface BoardRepository {
    public BoardDto create(BoardDto dto);
}