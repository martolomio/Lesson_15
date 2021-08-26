package domain;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.Date;

public class Application {
    public static void main( String[] args ) {

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        Session session = configuration.buildSessionFactory(serviceRegistry).openSession();

        Post post = new Post();
        post.setTitle("Naturale");
        
        Comment comment = new Comment();
        comment.setAuthorName("Maria");
        post.setComment(comment);

        Post post1 = new Post();
        post1.setTitle("Dream");
        comment.setPost(post1);

        //save to DB
        Transaction transaction = session.beginTransaction();
        session.save(post);
        transaction.commit();

        //read from DB
        Post c = (Post) session.get(Post.class, 1);
        System.out.println(c + "--->" + c.getComment());

        Comment comment1= (Comment) session.get(Comment.class, 2);
        System.out.println(comment1 + "--->" + comment1.getPost());
    }
}
