package io.github.rerobika.rf1.service;

import io.github.rerobika.rf1.domain.Post;
import io.github.rerobika.rf1.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    public List<Post> getAll();
    public void addPost(Post post);
    public void removePost(Post post);
    public Post getPost(long id);

    /**
     * This function returns the post with its direct environment eg. siblings and parent.
     * @param id
     * @return The list of posts.
     */
    public List<Post> getPostWithStructure(long id);
    public Post getParent(Post post);
    public List<Post> getPostByUser(User user);
    public List<Post> getComments(Post post);
}
