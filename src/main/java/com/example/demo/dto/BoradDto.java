package com.example.demo.dto;

import com.example.demo.entity.Board;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoradDto {
    private Long id;
    private String title;
    private String writer;

    private static ModelMapper modelMapper = new ModelMapper();

    public static BoradDto of(Board board) {
        return modelMapper.map(board, BoradDto.class);  // .map(변환 할 요소, 변화 할 타입 = 반환타입)
    }
}
