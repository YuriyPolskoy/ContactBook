package com.polskoy.services;

import com.polskoy.dao.ContactDAO;
import com.polskoy.model.Contact;

import java.io.FileNotFoundException;

public class ContactService {

    private ContactDAO dao;

    public ContactService(ContactDAO contactDAO){ this.dao = contactDAO; }

    public void createContact(String name, String surname, int age) {
        Contact contact = new Contact(name, surname, age);

        System.out.println("CONTACT CREATED");
        dao.saveContact(contact);
    }

    public void updateContact(int id, String name, String surname, int age){
        Contact updatedContact = new Contact(id, name, surname, age);
        dao.updateContact(updatedContact);
        System.out.println("CONTACT UPDATED");

    }

    public void deleteContact(int id){
        try {
            dao.remove(id);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("CONTACT DELETED");
    }

    public void show(){ dao.show();}

}
