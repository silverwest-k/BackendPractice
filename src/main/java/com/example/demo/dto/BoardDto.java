package com.example.demo.dto;

import com.example.demo.entity.Board;
import lombok.*;
import org.modelmapper.ModelMapper;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    @NotBlank(message = "😃 제목을 입력하세요.")
    private String title;
    @NotBlank(message = "😃 내용을 입력하세요.")
    private String content;
    private String writer;

    private static ModelMapper modelMapper = new ModelMapper();

    public static BoardDto of(Board board) {
        return modelMapper.map(board, BoardDto.class); // .map(변환 할 요소, 변화 할 타입 = 반환타입)
    }
}
