package com.example.demo.entity;

import com.example.demo.dto.BoardDto;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "board")
@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자를 만들어줌

public class Board {
    @Id
    @Column(name = "Board_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

//    @Column(nullable = false)
    private String writer;

    @Builder // class에서 사용하려면 @NoArgsConstructor와 같이 사용못함 - 매서드 레벨에서 사용했음
    Board(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public static Board createBoard(BoardDto boardDto) {
        return Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(boardDto.getWriter())
                .build();
    }
    public void updateBoard(BoardDto boardDto) {
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
    }
}
