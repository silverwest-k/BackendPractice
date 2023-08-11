package com.example.demo.entity;

import com.example.demo.auditing.BaseEntity;
import com.example.demo.dto.BoardDto;
import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Entity
@Table(name = "board")
@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자를 만들어줌

public class Board extends BaseEntity {
    @Id
    @Column(name = "Board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email") // 작명법 : "테이블명_필드명"
    private Member member;

    @Builder // class에서 사용하려면 @NoArgsConstructor와 같이 사용못함 - 매서드 레벨에서 사용했음
    Board(String title, String content, String writer, Member member) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.member = member;
    }

    public static Board createBoard(BoardDto boardDto, Member member) {
        return Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(boardDto.getWriter())
                .member(member)
                .build();
    }
    public void updateBoard(BoardDto boardDto) {
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
    }
}
