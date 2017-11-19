package io.github.rerobika.rf1.service.impl;

import io.github.rerobika.rf1.domain.Post;
import io.github.rerobika.rf1.domain.User;
import io.github.rerobika.rf1.repository.PostRepository;
import io.github.rerobika.rf1.service.PostService;
import io.github.rerobika.rf1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRep;
    @Autowired
    private UserService userServ;


    @Override
    public List<Post> getAll()
    {
        if(postRep.count()!=0)
            return (List<Post>) postRep.findAllByParentIsNull();
        else return  new ArrayList<Post>();
    }

    @Override
    public void addPost(Post post)
    {
        postRep.save(post);
    }

    @Override
    public void removePost(Post post)
    {
        postRep.delete(post);
    }

    @Override
    public Post getPost(long id)
    {
        return postRep.findOne(id);
    }

    @Override
    public List<Post> getPostWithStructure(long id)
    {
        Post post = postRep.findOne(id);
        Post parent = post.getParent();
        return (List<Post>) postRep.findByParent(parent);
    }

    @Override
    public Post getParent(Post post)
    {
        return postRep.findOne(post.getParent().getId());
    }

    @Override
    public List<Post> getPostByUser(User user)
    {
        return (List<Post>) postRep.findByFromAndParentIsNull(user);
    }

    @Override
    public List<Post> getPostToUser(User user) {
        return (List<Post>) postRep.findByToAndParentIsNull(user);
    }

    @Override
    public List<Post> getComments(Post post) {
        return (List<Post>) postRep.findByParent(post);
    }

    @Override
    public List<Post> getComments(List<Post> posts) {
        List<Post> commentsForPosts = new ArrayList<Post>();
        for(Post post : posts)
        {
            commentsForPosts.addAll(getComments(post));
        }
        return  commentsForPosts;
    }

}
