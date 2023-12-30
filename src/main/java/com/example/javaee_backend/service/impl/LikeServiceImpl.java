package com.example.javaee_backend.service.impl;

import com.example.javaee_backend.mapper.LikeMapper;
import com.example.javaee_backend.pojo.LikeDTO;
import com.example.javaee_backend.pojo.QaLikeDTO;
import com.example.javaee_backend.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeMapper likeMapper;

    @Override
    public String createLike(LikeDTO like) {
        int result = likeMapper.createLike(like);
        if (result > 0) {
            return "点赞成功";
        }
        throw new IllegalArgumentException("创建点赞失败");
    }
    @Override
    public String createQaLike(QaLikeDTO like) {
        int result = likeMapper.createQaLike(like);
        if (result > 0) {
            return "点赞成功";
        }
        throw new IllegalArgumentException("创建点赞失败");
    }

    @Override
    public int getLikeCountByArticleId(int articleId) {
        return likeMapper.getLikeCountByArticleId(articleId);
    }

    @Override
    public int getLikeCountByQaId(int qaId) {
        return likeMapper.getLikeCountByQaId(qaId);
    }

    @Override
    public String deleteLike(int articleId, int userId) {
        int result = likeMapper.deleteLike(articleId, userId);
        if (result > 0) {
            return "取消点赞成功";
        }
        throw new IllegalArgumentException("取消点赞失败");
    }
    @Override
    public String deleteQaLike(int qaId, int userId) {
        int result = likeMapper.deleteQaLike(qaId, userId);
        if (result > 0) {
            return "取消点赞成功";
        }
        throw new IllegalArgumentException("取消点赞失败");
    }

    @Override
    public boolean checkLike(int articleId, int userId) {
        return likeMapper.checkLike(articleId, userId) > 0;
    }

    @Override
    public boolean checkQaLike(int qaId, int userId) {
        return likeMapper.checkQaLike(qaId, userId) > 0;
    }

    @Override
    public List<Integer> getArticleIdsByUserId(int userId) {
        return likeMapper.getArticleIdsByUserId(userId);
    }
    @Override
    public List<Integer> getQaIdsByUserId(int userId) {
        return likeMapper.getQaIdsByUserId(userId);
    }

}