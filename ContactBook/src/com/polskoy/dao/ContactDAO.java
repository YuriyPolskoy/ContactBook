package com.polskoy.dao;

import com.polskoy.model.Contact;

import java.io.FileNotFoundException;

public interface ContactDAO {

    void saveContact(Contact contact);

    void remove (Contact contact);

    void remove (int id) throws FileNotFoundException;

    void updateContact (Contact contact);

    void show();

}