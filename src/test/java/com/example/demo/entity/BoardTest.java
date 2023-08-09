package com.example.demo.entity;

import com.example.demo.dto.BoardDto;
import com.example.demo.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional  // 테스트가 온전히 동작하는지만... 되든 안되든 롤백
/*@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})*/
class BoardTest {
    @Autowired
    private BoardService boardService;

    private BoardDto createBoardDto() {
        BoardDto boardDto = new BoardDto();
        boardDto.setTitle("test1");
        boardDto.setWriter("me");
        return boardDto;
    }

//    @Test
//    @DisplayName("게시글 저장 테스트") // 선택사항(필수아님)
//    public void saveTest() {
//        BoardDto boardDto = createBoardDto();
//        Board savedBoard = boardService.saveBoard(boardDto);
//
//        assertEquals(boardDto.getTitle(), savedBoard.getTitle());
//        assertEquals(boardDto.getWriter(), savedBoard.getWriter());
//    }

}