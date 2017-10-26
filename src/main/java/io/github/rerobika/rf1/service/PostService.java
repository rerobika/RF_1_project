package io.github.rerobika.rf1.service;

import io.github.rerobika.rf1.domain.Post;

import java.util.List;

public interface PostService {
    public List<Post> getAll();
    public void addPost(Post post);
    public void removePost(Post post);
    public  Post getPost(long id);
}
