package com.example.javaee_backend.controller;

import com.example.javaee_backend.service.QaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.javaee_backend.pojo.QaDTO;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8000", "http://localhost:8080"})
@RestController
@RequestMapping("/api")
public class QaController {

    @Autowired
    private QaService qaService;

    @PostMapping("/createQa")
    public String createQa(@RequestBody QaDTO qa) {
        return qaService.createQa(qa);
    }

    @GetMapping("/getQa/{qaId}")
    public QaDTO getQa(@PathVariable int qaId) {
        return qaService.getQa(qaId);
    }

    @GetMapping("/getQaByUserId/{userId}")
    public List<QaDTO> getQaByUserId(@PathVariable int userId) {
        return qaService.getQaByUserId(userId);
    }
    @GetMapping("/getQaBySearchKey")
    public List<QaDTO> getQaBySearchKey(@RequestParam String keyword) {
        return qaService.getQaBySearchKey(keyword);
    }

    @PatchMapping("/modifyQa")
    public String modifyQa(@RequestBody QaDTO qa) {
        return qaService.modifyQa(qa);
    }

    @DeleteMapping("/deleteQa/{qaId}")
    public String deleteQa(@PathVariable int qaId) {
        System.out.println("qaId=" + qaId);
        return qaService.deleteQa(qaId);
    }

    @GetMapping("/getAllQas")
    public List<QaDTO> getAllQas() {
        return qaService.getAllQas();
    }

    @RequestMapping(value = "/increaseQaVisitorNumber/{qaId}", method = RequestMethod.PATCH)
    public String increaseQaVisitorNumber(@PathVariable int qaId) {
        System.out.println("qaId="+qaId);
        return qaService.increaseVisitorNumber(qaId);
    }

    @RequestMapping(value = "/increaseQaLikeNumber/{qaId}", method = RequestMethod.PATCH)
    public String increaseQaLikeNumber(@PathVariable int qaId) {
        System.out.println("qaId="+qaId);
        return qaService.increaseLikeNumber(qaId);
    }

    @RequestMapping(value = "/increaseQaCollectNumber/{qaId}", method = RequestMethod.PATCH)
    public String increaseQaCollectNumber(@PathVariable int qaId) {
        System.out.println("qaId="+qaId);
        return qaService.increaseCollectNumber(qaId);
    }
    
}
