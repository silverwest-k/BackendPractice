package com.example.demo.entity;

import com.example.demo.dto.BoradDto;
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
    private String writer;

    @Builder // class에서 사용하려면 @NoArgsConstructor와 같이 사용못함 - 매서드 레벨에서 사용했음
    Board(String title, String writer) {
        this.title = title;
        this.writer = writer;
    }

    public static Board createBoard(BoradDto boradDto) {
        return Board.builder()
                .title(boradDto.getTitle())
                .writer(boradDto.getWriter())
                .build();
    }
}
