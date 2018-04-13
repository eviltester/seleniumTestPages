package com.seleniumsimplified.pulp;

import com.seleniumsimplified.pulp.domain.PulpBook;

import java.util.ArrayList;

public class PulpBooks {

    private int key;
    private ArrayList<PulpBook> books;

    public PulpBooks(){
        books = new ArrayList<>();
        key = 1;
    }
    public int count() {
        return books.size();
    }

    public void add(String series, String author, String houseAuthor, String title, String seriesId, int year, String publisher) {
        PulpBook book = getNextBook(series, author, houseAuthor, title, seriesId, year, publisher);
        books.add(book);
    }

    private PulpBook getNextBook(String series, String author, String houseAuthor, String title, String seriesId, int year, String publisher) {
        return new PulpBook(String.valueOf(key++), series, author, houseAuthor, title, seriesId, year, publisher);
    }

    public PulpBook get(String key) {
        for(PulpBook book : books){
            if(book.getId().contentEquals(key)){
                return book;
            }
        }

        return PulpBook.UNKNOWN_BOOK;
    }

    public PulpBook findByName(String title) {
        for(PulpBook book : books){
            if(book.getTitle().equalsIgnoreCase(title)){
                return book;
            }
        }

        return PulpBook.UNKNOWN_BOOK;
    }

    public PulpBook findByTitle(String title) {
        return findByName(title);
    }

}
