package com.example.springblog.service.impl;

import com.example.springblog.model.Post;
import com.example.springblog.model.User;
import com.example.springblog.repository.PostRepository;
import com.example.springblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Optional<Post> findForId(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.saveAndFlush(post);
    }


    @Override
    public Page<Post> findByUserOrderedByDatePageable(User user, int page) {
        Pageable pageable = PageRequest.of(subtractPageByOne(page), 5);
        return postRepository.findByUserOrderByCreateDateDesc(user, pageable);
    }


    @Override
    public Page<Post> findAllOrderedByDatePageable(int page) {
        Pageable pageable = PageRequest.of(subtractPageByOne(page), 5);
        return postRepository.findAllByOrderByCreateDateDesc(pageable);
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }

    private int subtractPageByOne(int page){
        return (page < 1) ? 0 : page - 1;
    }
}