package com.example.javaee_backend.controller;

import com.example.javaee_backend.pojo.LikeDTO;
import com.example.javaee_backend.pojo.QaLikeDTO;
import com.example.javaee_backend.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8000","http://localhost:8080"})
@RestController
@RequestMapping("/api")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @RequestMapping(value = "/createLike", method = RequestMethod.POST)
    public String createLike(@RequestBody LikeDTO likeDTO) {

        return likeService.createLike(likeDTO);
    }

    @RequestMapping(value = "/createQaLike", method = RequestMethod.POST)
    public String createQaLike(@RequestBody QaLikeDTO qaLikeDTO) {
        return likeService.createQaLike(qaLikeDTO);
    }

    @RequestMapping(value = "/checkLike/{articleId}/{userId}", method = RequestMethod.GET)
    public boolean checkLike(@PathVariable("articleId") int articleId, @PathVariable("userId") int userId) {
        return likeService.checkLike(articleId, userId);
    }
    @RequestMapping(value = "/checkQaLike/{qaId}/{userId}", method = RequestMethod.GET)
    public boolean checkQaLike(@PathVariable("qaId") int qaId, @PathVariable("userId") int userId) {
        System.out.println("qaId="+qaId+" userId="+userId);
        return likeService.checkQaLike(qaId, userId);
    }


    // 这个也应该房放在UserController中
    @RequestMapping(value="/getLikeCountByArticleId/{articleId}",method = RequestMethod.GET)
    public int getLikeCountByArticleId(@RequestParam(value = "articleId") int articleId){
        return likeService.getLikeCountByArticleId(articleId);
    }
    @RequestMapping(value="/getLikeCountByQaId/{qaId}",method = RequestMethod.GET)
    public int getLikeCountByQaId(@RequestParam(value = "qaId") int qaId){
        return likeService.getLikeCountByQaId(qaId);
    }

    // 同上
    @RequestMapping(value = "/deleteLike/{articleId}/{userId}", method = RequestMethod.DELETE)
    public String deleteLike(@PathVariable("articleId") int articleId, @PathVariable("userId") int userId) {
        return likeService.deleteLike(articleId, userId);
    }
    @RequestMapping(value = "/deleteQaLike/{qaId}/{userId}", method = RequestMethod.DELETE)
    public String deleteQaLike(@PathVariable("qaId") int qaId, @PathVariable("userId") int userId) {
        return likeService.deleteQaLike(qaId, userId);
    }

    // 这个应该房放在UserController中
    @RequestMapping(value="/getLikeArticleIdsByUserId/{userId}",method = RequestMethod.GET)
    public List<Integer> getArticleIdsByUserId(@RequestParam(value = "userId") int userId){
        return likeService.getArticleIdsByUserId(userId);
    }
    @RequestMapping(value="/getLikeQaIdsByUserId/{userId}",method = RequestMethod.GET)
    public List<Integer> getQaIdsByUserId(@RequestParam(value = "userId") int userId){
        return likeService.getQaIdsByUserId(userId);
    }
}