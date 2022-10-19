package com.bean;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CRUD {
    Configuration configuration = new Configuration().configure();
    SessionFactory sessionFactory = configuration.buildSessionFactory();

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public void addRecord() throws IOException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        System.out.println("Enter the number of books to be added : ");
        int numberOfBooks = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < numberOfBooks; i++) {
            Book book = new Book();
            System.out.println("Enter the book name : ");
            book.setBook_name(bufferedReader.readLine());
            System.out.println("Enter Author name : ");
            book.setAuthor_name(bufferedReader.readLine());


            System.out.println("Enter number of authors for given book : ");
            int numberOfAuthors = Integer.parseInt(bufferedReader.readLine());

            List<Author> authorList = new ArrayList<Author>();
            for (int j = 0; j < numberOfAuthors; j++) {
                Author author = new Author();
                System.out.println("Enter name of Author " + j + 1);
                author.setAuthorName(bufferedReader.readLine());
                System.out.println("Enter city of Author " + j + 1);
                author.setAuthorCity(bufferedReader.readLine());
                author.setBook(book);
                authorList.add(author);
                session.save(author);

            }
            book.setAuthorList(authorList);
            session.save(book);

            System.out.println("Record Added Successfully ...");
            transaction.commit();


            session.close();
            sessionFactory.close();
        }

    }

    public void getRecordForBook() throws IOException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        System.out.println("Enter the id of Book : ");
        int bookId = Integer.parseInt(bufferedReader.readLine());

        Book book = (Book) session.load(Book.class, bookId);

        System.out.println(book);

    }

    public void getRecordForAuthor() throws IOException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        System.out.println("Enter the id of Author : ");
        int id = Integer.parseInt(bufferedReader.readLine());

        Author author = (Author) session.load(Author.class, id);
        System.out.println(author);
    }

    public void fetchAllBooks() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Book");
        List<Book> list = (List<Book>) ((org.hibernate.query.Query<?>) query).list();
        for (Book book : list) {
            System.out.println(book);
        }
    }

    public void fetchAllAuthors() {
        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Author");
        List<Author> list = (List<Author>) ((org.hibernate.query.Query<?>) query).list();
//        list.stream().forEach(System.out::println);
        for (Author author : list) {
            System.out.println(author);
        }
    }

    public void updateAuthor() throws IOException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update Book set author_name = :author_name" +
                " where book_id = :book_id");
        System.out.println("Enter the Id of Book to be updated : ");
        int id = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Enter the name of new Author : ");
        String name = bufferedReader.readLine();
        query.setParameter("author_name",name);
        query.setParameter("book_id",id);
        query.executeUpdate();
    }

    public void deleteAuthor() throws IOException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from Book " +
                " where book_id = :book_id");
        System.out.println("Enter the Id of Book to be updated : ");
        int id = Integer.parseInt(bufferedReader.readLine());
        query.setParameter("book_id",id);
        query.executeUpdate();
    }






}
