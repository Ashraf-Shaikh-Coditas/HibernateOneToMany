package com.DAO;

import com.bean.Author;
import com.bean.Book;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;

import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

public class BookDaoImplementation implements BookDao{

    Configuration configuration = new Configuration().configure();
    SessionFactory sessionFactory = configuration.buildSessionFactory();

    public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


    @Override
    public int insertBook(Book book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        int i = (int) session.save(book);

        transaction.commit();
        session.close();
        sessionFactory.close();

        if(i==1) {
            System.out.println("Record Added Successfully ...");
        } else {
            System.out.println("Try Again");
        }

        return i;
    }

    @Override
    public int updateBook(String newBookName, float bookPrice) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("update Book set  book_price= :book_price" +
                " where book_name = :book_name");
        query.setParameter("book_name",newBookName);
        query.setParameter("book_price",bookPrice);
        int i = query.executeUpdate();

        transaction.commit();
        session.close();

        if(i==1) {
            System.out.println("Record Updated Successfully ...");
        } else {
            System.out.println("Try Again");
        }

        return i;
    }

    @Override
    public int deleteBook(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("delete from Book" +
                " where book_id = :book_id");
        query.setParameter("book_id",id);

        int i = query.executeUpdate();

        transaction.commit();
        session.close();

        if(i==1) {
            System.out.println("Record Deleted Successfully ...");
        } else {
            System.out.println("Try Again");
        }

        return i;
    }

    @Override
    public List<Book> fetchAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Book");
//        List<Book> list = (List<Book>) ((org.hibernate.query.Query<?>) query).list();
        List<Book> list = query.getResultList();
        return list;

    }

    public List<Book> fetchByCriteria(float price)  {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Book.class);
        criteria.add(Restrictions.gt("book_price",price));

        return criteria.list();
    }

    public List<Book> fetchByDescOrder()  {
        System.out.println("Books in descending order of Price");
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Book.class);
        criteria.addOrder(Order.desc("book_price"));
        return criteria.list();
    }

    public List<Object> fetchOnlyBookNameAndPrice()  {


        System.out.println("Fetching only two columns : ");
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Book.class);
        Projection projection1 = Projections.property("book_name");
        Projection projection2 = Projections.property("book_price");
        List<Object> projectionList = (List<Object>) Projections.projectionList();
        projectionList.add(projection1);
        projectionList.add(projection2);
        criteria.setProjection(projection1);
        return criteria.list();
//        return projectionList;
    }

    public void combinedQuery() {
        System.out.println("Combined Query : ");
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Book.class);
        Projection projection1 = Projections.property("book_name");
        Projection projection2 = Projections.property("author_name");
        Projection projection3 = Projections.property("book_price");
        ProjectionList projectionList=Projections.projectionList();
        projectionList.add(projection1);
        projectionList.add(projection2);
        projectionList.add(projection3);
        criteria.setProjection(projectionList);
        criteria.add(Restrictions.gt("book_price",400.76f));
        criteria.addOrder(Order.asc("book_price"));
        List list=criteria.list();
        Iterator iterator=list.iterator();
        while (iterator.hasNext())
        {
            Object ob[]=(Object[])iterator.next();
            System.out.println(ob[0]+" "+ob[1]+" "+ob[2]);
        }
    }
}


// One for Order =: fetch books by desc order as per price
// One for projection = : select only book name and price and fetch details
// One for order, projection , restriction :=
// Select Book name , author name and price where price >=400 and order is asc as per price.