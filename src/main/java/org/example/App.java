package org.example;

import org.example.oneToMany.Comment;
import org.example.oneToMany.Post;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

import static org.example.util.SessionFactoryUtil.getSessionFactory;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        Session session = getSessionFactory().openSession();

        Comment comment = new Comment();
        comment.setAuthorName("custom author");

        Post post1 = new Post();
        post1.setTitle("Custom title 1");
        post1.setComment(comment);

        Post post2 = new Post();
        post2.setTitle("Custom title 2");
        post2.setComment(comment);

        Set<Post> posts = new HashSet<>();
        posts.add(post1);
        posts.add(post2);

        comment.setPosts(posts);

        // save to DB
        Transaction transaction = session.beginTransaction();
        session.save(comment);
        transaction.commit();

        // read from DB
        Comment commentFromDB = (Comment) session.get(Comment.class, 1);
        System.out.println(commentFromDB + "---->" + commentFromDB.getPosts());

        Post postDB = (Post) session.get(Post.class, 1);
        System.out.println(postDB + "---->" + postDB.getComment());
    }
}
