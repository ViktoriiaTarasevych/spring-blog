package com.example.springblog.service;


import com.example.springblog.model.Comment;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {

    Comment save(Comment comment);
}