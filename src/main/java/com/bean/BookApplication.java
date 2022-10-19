package com.bean;


import com.DAO.BookDaoImplementation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;

import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class BookApplication {
//    public static void main(String[] args) throws IOException {
//        boolean flag = true;
//        Scanner scanner = new Scanner(System.in);
//        while (flag) {
//            System.out.println("1.Add Record " +
//                    "\n2.Get Book By Id" +
//                    "\n3.Get Author By Id" +
//                    "\n4. Fetch All Records" +
//                    "\n5. Update Record" +
//                    "\n6. Delete Record" +
//                    "\nEnter your choice : ");
//            int choice = scanner.nextInt();
//            switch (choice) {
//                case 1 :
//                    new CRUD().addRecord();
//                    break;
//                case 2 :
//                    new CRUD().getRecordForBook();
//                    break;
//                case 3 :
//                    new CRUD().getRecordForAuthor();
//                    break;
//                case 4 :
//                    new CRUD().fetchAllAuthors();
//                    break;
//                case 5 :
//                    new CRUD().updateAuthor();
//                    break;
//                case 6 :
//                    new CRUD().deleteAuthor();
//                    break;
//                default:
//                    flag = false;
//                    break;
//            }
//        }
//    }

    public static void main(String[] args) throws IOException {
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (flag) {
            System.out.println("1.Add Record " +
                    "\n4. Fetch All Records" +
                    "\n2. Update Record" +
                    "\n3. Delete Record" +
                    "\n5 Fetch By Price Criteria." +
                    "\n6 Fetch By Order ." +
                    "\n7 Fetch using Projection." +
                    "\nEnter your choice : ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the name of Book");
                    System.out.println("Enter the name of Author");
                    System.out.println("Enter the price of Book");

                    Book book = new Book(bufferedReader.readLine(),
                            bufferedReader.readLine(), Float.parseFloat(bufferedReader.readLine()));
                    new BookDaoImplementation().insertBook(book);
                    break;
                case 2:
                    System.out.println("Enter name of book and new price to be updated : ");
                    String bookName = bufferedReader.readLine();
                    Float price = Float.parseFloat(bufferedReader.readLine());

                    new BookDaoImplementation().updateBook(bookName, price);
                    break;
                case 3:

                    System.out.println("Enter id of book to be deleted : ");
                    int id = Integer.parseInt(bufferedReader.readLine());

                    new BookDaoImplementation().deleteBook(id);
                    break;
                case 4:
                    List<Book> list = new BookDaoImplementation().fetchAll();
                    list.stream().forEach(System.out::println);
                    break;

                case 5:
                    System.out.println("Enter your price for criteria : ");
                    List<Book> list1 = new BookDaoImplementation().fetchByCriteria(Float.parseFloat(bufferedReader.readLine()));
                    list1.stream().forEach(System.out::println);

                case 6:
                    List<Book> list2 = new BookDaoImplementation().fetchByDescOrder();
                    list2.stream().forEach(System.out::println);
                    break;
                case 7 :
                    List<Object> list3 = new BookDaoImplementation().fetchOnlyBookNameAndPrice();
                    Iterator<Object> iterator = list3.iterator();
                    while (iterator.hasNext()) {
                        Object ob[] = (Object[]) iterator.next();

                        System.out.println(ob[0]+" : "+ob[1]);
                    }
                case 8 :
                {
                    System.out.println("The resulsts are...");
                    new BookDaoImplementation().combinedQuery();
                    break;
                }



                default:
                    flag = false;
                    break;
            }
        }
    }
}

// One for Order =: fetch books by desc order as per price
// One for projection = : select only book name and price and fetch details
// One for order, projection , restriction :=
// Select Book name , author name and price where price >=400 and order is asc as per price.