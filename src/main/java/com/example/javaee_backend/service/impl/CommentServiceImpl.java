package com.example.javaee_backend.service.impl;

import com.example.javaee_backend.mapper.CommentMapper;
import com.example.javaee_backend.pojo.CommentDTO;
import com.example.javaee_backend.pojo.CompetitionCommentDTO;
import com.example.javaee_backend.pojo.ListCommentDTO;
import com.example.javaee_backend.pojo.QaCommentDTO;
import com.example.javaee_backend.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private  CommentMapper commentMapper;

    @Override
    public int createCompetitionComment(CompetitionCommentDTO competitionCommentDTO) {
        commentMapper.createCompetitionComment(competitionCommentDTO);
        return 0;
    }

    @Override
    public int createArticleComment(CommentDTO commentDTO) {
        commentMapper.createArticleComment(commentDTO);
        return 0;
    }
    @Override
    public int createQaComment(QaCommentDTO qaCommentDTO) {
        commentMapper.createQaComment(qaCommentDTO);
        return 0;
    }



    @Override
    public List<CompetitionCommentDTO> getCompetitionCommentById(int competitionId) {
        List<CompetitionCommentDTO> commentList = commentMapper.getCommentsByCompetitionId(competitionId);

        List<CompetitionCommentDTO> parentComments = new ArrayList<>();
        Map<Integer, CompetitionCommentDTO> parentCommentsMap = new HashMap<>();
        Map<Integer, List<CompetitionCommentDTO>> childrenCommentsMap = new HashMap<>();

        commentList.stream().forEach(commentDTO -> {
            CompetitionCommentDTO competitionCommentDTO = new CompetitionCommentDTO();
            BeanUtils.copyProperties(commentDTO, competitionCommentDTO);

            if(competitionCommentDTO.getParentCommentId() == null) {
                parentComments.add(competitionCommentDTO);
                parentCommentsMap.put(competitionCommentDTO.getCommentId(), competitionCommentDTO);
            } else {
                List<CompetitionCommentDTO> children = childrenCommentsMap.get(competitionCommentDTO.getParentCommentId());
                if(children == null) {
                    children = new ArrayList<>();
                    childrenCommentsMap.put(competitionCommentDTO.getParentCommentId(), children);
                }
                children.add(competitionCommentDTO);
            }
        });

        parentComments.forEach(parentComment -> {
            List<CompetitionCommentDTO> children = childrenCommentsMap.get(parentComment.getCommentId());
            parentComment.setChildren(children != null ? children : new ArrayList<>());
        });

        return parentComments;
    }

    @Override
    public List<CommentDTO> getArticleCommentById(int articleId) {
        List<CommentDTO> commentList = commentMapper.getCommentsByArticleId(articleId);

        List<CommentDTO> parentComments = new ArrayList<>();
        Map<Integer, CommentDTO> parentCommentsMap = new HashMap<>();
        Map<Integer, List<CommentDTO>> childrenCommentsMap = new HashMap<>();

        commentList.stream().forEach(commentDTO -> {
            CommentDTO articleCommentDTO = new CommentDTO();
            BeanUtils.copyProperties(commentDTO, articleCommentDTO);

            if(articleCommentDTO.getParentCommentId() == 0) {
                parentComments.add(articleCommentDTO);
                parentCommentsMap.put(articleCommentDTO.getCommentId(), articleCommentDTO);
            } else {
                List<CommentDTO> children = childrenCommentsMap.get(articleCommentDTO.getParentCommentId());
                if(children == null) {
                    children = new ArrayList<>();
                    childrenCommentsMap.put(articleCommentDTO.getParentCommentId(), children);
                }
                children.add(articleCommentDTO);
            }
        });

        parentComments.forEach(parentComment -> {
            List<CommentDTO> children = childrenCommentsMap.get(parentComment.getCommentId());
            parentComment.setChildren(children != null ? children : new ArrayList<>());
        });

        return parentComments;
    }

    @Override
    public List<QaCommentDTO> getQaCommentById(int qaId) {
        List<QaCommentDTO> commentList = commentMapper.getCommentsByQaId(qaId);

        List<QaCommentDTO> parentComments = new ArrayList<>();
        Map<Integer, QaCommentDTO> parentCommentsMap = new HashMap<>();
        Map<Integer, List<QaCommentDTO>> childrenCommentsMap = new HashMap<>();

        commentList.stream().forEach(commentDTO -> {
            QaCommentDTO qaCommentDTO = new QaCommentDTO();
            BeanUtils.copyProperties(commentDTO, qaCommentDTO);

            if(qaCommentDTO.getParentCommentId() == 0) {
                parentComments.add(qaCommentDTO);
                parentCommentsMap.put(qaCommentDTO.getCommentId(), qaCommentDTO);
            } else {
                List<QaCommentDTO> children = childrenCommentsMap.get(qaCommentDTO.getParentCommentId());
                if(children == null) {
                    children = new ArrayList<>();
                    childrenCommentsMap.put(qaCommentDTO.getParentCommentId(), children);
                }
                children.add(qaCommentDTO);
            }
        });

        parentComments.forEach(parentComment -> {
            List<QaCommentDTO> children = childrenCommentsMap.get(parentComment.getCommentId());
            parentComment.setChildren(children != null ? children : new ArrayList<>());
        });

        return parentComments;
    }

    @Override
    public List<CompetitionCommentDTO> getAllCompetitionComments() {
        return commentMapper.getAllCompetitionComments();
    }
    @Override
    public List<CommentDTO> getAllArticleComments() {
        return commentMapper.getAllArticleComments();
    }
    @Override
    public List<QaCommentDTO> getAllQaComments(){
        return commentMapper.getAllQaComments();
    }

}