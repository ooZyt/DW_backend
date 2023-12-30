package com.example.javaee_backend.service.impl;

import com.example.javaee_backend.mapper.QaMapper;
import com.example.javaee_backend.pojo.QaDTO;
import com.example.javaee_backend.pojo.QaDTO;
import com.example.javaee_backend.service.QaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QaServiceImpl implements QaService {
    @Autowired
    private QaMapper qaMapper;

    @Override
    public String createQa(QaDTO qa) {
        QaDTO existQa = qaMapper.getQa(qa.getQaId());
        if (null == existQa) {
            qaMapper.addQa(qa);
            return String.valueOf(qa.getQaId());
        }
        throw new IllegalArgumentException("已存在相同ID的问答");
    }

    //    private static final Logger logger = LoggerFactory.getLogger(QaServiceImpl.class);
    @Override
    public QaDTO getQa(int qaId) {
        return qaMapper.getQa(qaId);
    }

    @Override
    public String modifyQa(QaDTO qa) {
        QaDTO existQa = qaMapper.getQa(qa.getQaId());
        if (null == existQa) {
            return "不存在该问答";
        }
        qaMapper.updateQa(qa);
        return String.valueOf(qa.getQaId());
    }

    @Override
    public String deleteQa(int qaId) {
        QaDTO existQa = qaMapper.getQa(qaId);
        System.out.println(existQa);
        if (null == existQa) {
            return "不存在该问答";
        }
        qaMapper.deleteQa(qaId);
        return "成功删除问答";
    }

    @Override
    public List<QaDTO> getAllQas() {
        return qaMapper.getAllQas();
    }

    @Override
    public List<QaDTO> getQaByUserId(int userId){
        return qaMapper.getQaByUserId(userId);
    }

    @Override
    public List<QaDTO> getQaBySearchKey(String SearchKey){
        return qaMapper.getQaBySearchKey(SearchKey);
    }

    @Override
    public String increaseVisitorNumber(int qaId) {
        QaDTO existQa = qaMapper.getQa(qaId);
        System.out.println(existQa);
        if (null == existQa) {
            return "error:访问人数+1";
        }
        qaMapper.increaseVisitorNumber(qaId);
        return "succeed:访问人数+1";

    }

    @Override
    public String increaseLikeNumber(int qaId) {
        QaDTO existQa = qaMapper.getQa(qaId);
        System.out.println(existQa);
        if (null == existQa) {
            return "error:点赞人数+1";
        }
        qaMapper.increaseLikeNumber(qaId);
        return "succeed:点赞人数+1";

    }

    @Override
    public String increaseCollectNumber(int qaId) {
        QaDTO existQa = qaMapper.getQa(qaId);
        System.out.println(existQa);
        if (null == existQa) {
            return "error:收藏人数+1";
        }
        qaMapper.increaseCollectNumber(qaId);
        return "succeed:收藏人数+1";

    }
}
