package com.example.demo.controller;

import com.example.demo.dto.BoardDto;
import com.example.demo.dto.ReplyDto;
import com.example.demo.service.BoardService;
import com.example.demo.service.DupReplyService;
import com.example.demo.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final ReplyService replyService;
    private final DupReplyService dupReplyService;

    @GetMapping(value = "/info")
    public String boardInfo(@RequestParam(value = "page", required = false, defaultValue = "0") String page, Model model) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), 5);
        model.addAttribute("page", boardService.getBoardList(pageable));
        return "/pages/boards/boardInfo";
    }

    @PostMapping(value = "/info/{page}")
    public String boardPages(@PathVariable Integer page, Model model) {
        Pageable pageable = PageRequest.of(page, 5);
        model.addAttribute("page", boardService.getBoardList(pageable));
        return "/pages/boards/pageCard";
    }

    @GetMapping(value = "/form")
    public String boardForm(Model model) {
        model.addAttribute("boardDto", new BoardDto());
        return "/pages/boards/boardForm";
    }

    @PostMapping(value = "/form")
    public String boardSave(@Valid BoardDto boardDto,
                            BindingResult bindingResult,
                            Authentication authentication,
                            Model model) {
        String email = authentication.getName();
        if(bindingResult.hasErrors()) {
            return "/pages/boards/boardForm";
        }
        boardService.saveBoard(boardDto, email);
        return "redirect:/board/info";
    }

    @GetMapping(value = "/detail/{boardId}")
    public String boardDetail(@PathVariable Long boardId, Model model, Authentication authentication) {
        String userEmail = authentication.getName();
        BoardDto boardDto = boardService.showDetail(boardId);
        List<ReplyDto> replyDtoList= replyService.getReplyList(boardId);
        for(ReplyDto r : replyDtoList) {
            r.setDupReplyDtoList(dupReplyService.getDupReplys(r.getId()));
        }
        model.addAttribute("userEmail", userEmail);
        model.addAttribute("boardDto", boardDto);
        model.addAttribute("replies", replyDtoList);
        return "/pages/boards/boardDetail";
    }

    @DeleteMapping(value = "/delete/{boardId}")
    public ResponseEntity boardDelete(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return new ResponseEntity<Long>(boardId, HttpStatus.OK);
    }

    @PatchMapping(value = "/update")
    public ResponseEntity<Long> boardUpdate(@RequestBody BoardDto boardDto) {
        boardService.updateBoard(boardDto);
        return new ResponseEntity<Long>(boardDto.getId(), HttpStatus.OK);
    }
}
