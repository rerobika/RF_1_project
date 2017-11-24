package io.github.rerobika.rf1.service.impl;

import io.github.rerobika.rf1.domain.Person;
import io.github.rerobika.rf1.domain.Post;
import io.github.rerobika.rf1.domain.User;
import io.github.rerobika.rf1.repository.PostRepository;
import io.github.rerobika.rf1.service.PersonService;
import io.github.rerobika.rf1.service.PostService;
import io.github.rerobika.rf1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRep;
    @Autowired
    private PersonService personService;


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

        return (List<Post>) postRep.findByToAndParentIsNullOrderByDateDesc(user);
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

    @Override
    public List<Post> getAllFriendsPost(Person profilePerson) {
        List<Person> friends = personService.getFriends(profilePerson);
        friends.add(profilePerson);
        List<Post> friendsPosts = new LinkedList<Post>();

        for (Person p : friends) {
            friendsPosts.addAll(postRep.findByFromAndParentIsNullOrderByDateDesc(p.getUser()));
        }

        Collections.sort(friendsPosts, (o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        return friendsPosts;
    }

}
