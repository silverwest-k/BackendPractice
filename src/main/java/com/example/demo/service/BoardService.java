package com.example.demo.service;

import com.example.demo.dto.BoradDto;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Board saveBoard(BoradDto boradDto) {
        return boardRepository.save(Board.createBoard(boradDto));
    }

    public List<BoradDto> getBoardList() {
        List<BoradDto> boardDtos = new ArrayList<>();   // 앞에 제네릭 타입 명시 했으면 뒤에는 생략 가능
        for(Board board : boardRepository.findAll()) {
            boardDtos.add(BoradDto.of(board));
        }
        return boardDtos;
    }
}
