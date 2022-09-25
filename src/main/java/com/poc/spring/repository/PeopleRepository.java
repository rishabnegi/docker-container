package com.poc.spring.repository;

import com.poc.spring.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PeopleRepository{

    @Autowired
    private EntityManager entityManager;


    @Transactional
    public void save(){

/*        Query query =entityManager.createNativeQuery("\n" +
                "insert into passport values (4001,'E123456');\n" +
                "insert into passport values (4002,'E123457');\n" +
                "insert into passport values (4003,'E123890');\n" +
                "\n" +
                "\n" +
                "insert into Student values (2001,'Rangna',4001);\n" +
                "insert into Student values (2002,'Adam',4002);\n" +
                "insert into Student values (2003,'Jane',4003);\n" +
                "\n" +
                "insert into review values (5001,'5','GG');\n" +
                "insert into review values (5002,'4','GGG');\n" +
                "insert into review values (5003,'5','GGG');\n" +
                "\n" +
                "\n");*/
        Query query =entityManager.createNativeQuery("insert into course values (1,'Java');\n" +
                "insert into course values (2,'Sql');\n" +
                "insert into course values (3,'Cloud');\n" +
                "\n" +
                "\n" +
                "insert into review values (11,'nice course','5',1);\n" +
                "insert into review values (21,'nice course','5',1);\n" +
                "insert into review values (31,'nice course','5',2);\n");
        query.executeUpdate();

    }

    @Transactional
    public void queryWithJpql(){

   entityManager.createQuery("from Person").getResultList();

    }

    @Transactional
    public void queryWithJpqlq() {

        Course c = new Course();
        c.setName("java");
        Review review = new Review();
        review.setDescription("good");
        review.setRating("5");
        review.setCourse(c);

        Review review1 = new Review();
        review1.setDescription("bad course");
        review1.setRating("1");
        review1.setCourse(c);

        entityManager.persist(c);
        entityManager.persist(review1);
        entityManager.persist(review);


        Course c1 = new Course();
        c.setName("sql");

        Review review2 = new Review();
        review2.setDescription("good course");
        review2.setRating("5");
        review2.setCourse(c1);


        Review review13 = new Review();
        review13.setDescription("bad course");
        review13.setRating("1");
        review13.setCourse(c1);

        c1.getReviewList().add(review2);
        c1.getReviewList().add(review13);


        entityManager.persist(c1);

        entityManager.persist(review2);
        entityManager.persist(review13);
    }


    @Transactional
    public void queryWithJpqlqq(){

        List<Course> c1 =  entityManager.createQuery("select c from Course c join fetch c.reviews",Course.class).getResultList();
        System.out.println("time pass");
        System.out.println(c1.get(0).getReviews().get(0));

    }


}
