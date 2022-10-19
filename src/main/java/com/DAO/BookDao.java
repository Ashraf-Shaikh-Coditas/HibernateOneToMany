package com.DAO;

import com.bean.Author;
import com.bean.Book;

import java.util.List;

public interface BookDao {
    int insertBook(Book booK);
    int updateBook(String newBookName,float bookPrice);
    int deleteBook(int id);
    List<Book> fetchAll();

    void combinedQuery();
}
