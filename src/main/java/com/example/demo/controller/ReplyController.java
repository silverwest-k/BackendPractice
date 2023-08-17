package com.example.demo.controller;

import com.example.demo.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping(value = "/new")
    public String createReply(@RequestParam(value = "reply") String content,
                              @RequestParam Long boardId,
                              Authentication authentication) {
        String userEmail = authentication.getName();
        replyService.saveReply(boardId, content, userEmail);
        return "redirect:/board/detail/" + boardId;
    }

    @DeleteMapping(value = "/delete/{replyId}")
    public ResponseEntity<Long> deleteReply(@PathVariable Long replyId) {
        replyService.deleteReply(replyId);
        return new ResponseEntity<Long>(replyId, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/modal/{replyId}")
    public  String modalReply(@PathVariable Long replyId) {
        return replyService.getContent(replyId);
    }

    @PatchMapping(value = "/update/{replyId}")
    public String updateReply(@PathVariable Long replyId,
                              @RequestBody String content,  // ajax의 data 부분을 받아옴
                              Authentication authentication,
                              Model model) {

        Long boardId = replyService.updateReply(replyId, content);
        model.addAttribute("replies", replyService.getReplyList(boardId));
        model.addAttribute("userEmail", authentication.getName());

        return "pages/boards/replyCard";
    }

}
