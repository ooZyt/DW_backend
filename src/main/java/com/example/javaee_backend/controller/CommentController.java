package com.example.javaee_backend.controller;

import com.example.javaee_backend.pojo.*;
import com.example.javaee_backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8000","http://localhost:8080"})
@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/createCompetitionComment", method = RequestMethod.POST)
    public int createCompetitionComment(@RequestBody CompetitionCommentDTO commentDTO){
        return commentService.createCompetitionComment(commentDTO);
    }

    @RequestMapping(value = "/createArticleComment", method = RequestMethod.POST)
    public int createArticleComment(@RequestBody CommentDTO commentDTO){
        return commentService.createArticleComment(commentDTO);
    }

    @RequestMapping(value = "/createQaComment", method = RequestMethod.POST)
    public int createQaComment(@RequestBody QaCommentDTO commentDTO){
        return commentService.createQaComment(commentDTO);
    }


    @RequestMapping(value = "/getArticleCommentById/{articleId}", method = RequestMethod.GET)
    public List<CommentDTO> getArticleCommentById(@PathVariable("articleId") int articleId) {
        return commentService.getArticleCommentById(articleId);
    }

    @RequestMapping(value = "/getCompetitionCommentById/{articleId}", method = RequestMethod.GET)
    public List<CompetitionCommentDTO> getCompetitionCommentById(@PathVariable("articleId") int articleId) {
        return commentService.getCompetitionCommentById(articleId);
    }

    @RequestMapping(value = "/getQaCommentById/{qaId}", method = RequestMethod.GET)
    public List<QaCommentDTO> getQaCommentById(@PathVariable("qaId") int qaId) {
        System.out.println("getQaCommentById"+qaId);
        System.out.println("getQaCommentById"+commentService.getQaCommentById(qaId));
        return commentService.getQaCommentById(qaId);
    }

    @RequestMapping(value = "/getAllArticleComments", method = RequestMethod.GET)
    public List<CommentDTO> getAllArticleComments() {
        return commentService.getAllArticleComments();
    }
    @RequestMapping(value = "/getAllCompetitionComments", method = RequestMethod.GET)
    public List<CompetitionCommentDTO> getAllCompetitionComments() {
        return commentService.getAllCompetitionComments();
    }
    @RequestMapping(value = "/getAllQaComments", method = RequestMethod.GET)
    public List<QaCommentDTO> getAllQaComments() {
        return commentService.getAllQaComments();
    }

}

