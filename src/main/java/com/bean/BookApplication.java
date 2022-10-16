package com.bean;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookApplication {
    public static void main(String[] args) throws IOException {
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        while (flag) {
            System.out.println("1.Add Record " +
                    "\n2.Get Book By Id" +
                    "\n3.Get Author By Id" +
                    "\n4. Fetch All Records" +
                    "\n5. Update Record" +
                    "\n6. Delete Record" +
                    "\nEnter your choice : ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 :
                    new CRUD().addRecord();
                    break;
                case 2 :
                    new CRUD().getRecordForBook();
                    break;
                case 3 :
                    new CRUD().getRecordForAuthor();
                    break;
                case 4 :
                    new CRUD().fetchAllAuthors();
                    break;
                case 5 :
                    new CRUD().updateAuthor();
                    break;
                case 6 :
                    new CRUD().deleteAuthor();
                    break;
                default:
                    flag = false;
                    break;
            }
        }
    }
}
