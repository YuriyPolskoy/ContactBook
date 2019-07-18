package com.polskoy.dao.impl;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.polskoy.dao.ContactDAO;
import com.polskoy.model.Contact;

public class ContactDiskDAO implements ContactDAO{

    private final File file = new File("ContactBook.txt");
    private int id = nextId(file);


    @Override
    public void saveContact(Contact contact) {
        try (FileWriter fileWriter = new FileWriter(file, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter) {
             }) {
            contact.setId(id++);
            printWriter.println(contact);
            printWriter.flush();
        }
        catch (IOException e) {
            System.out.println("Something going wrong!");
        }
    }

    @Override
    public void remove(Contact contact) {

    }

    @Override
    public void remove(int id) {

        Map<Integer, Contact> store;

        store = fileToMap(file);

        store.remove(id);

        mapToFile(store);

    }

    @Override
    public void updateContact(Contact contact) {

        Map<Integer, Contact> store;

        store = fileToMap(file);

        store.put(contact.getId(), contact);

        mapToFile(store);
    }

    @Override
    public void show() {

        Map<Integer, Contact> store;
        store = fileToMap(file);
        if (store.isEmpty()) System.out.println("CONTACT BOOK IS EMPTY!!!"); else {
        for (Contact contact : store.values()) {
                System.out.println(contact);
            }
        }
    }

    private Map fileToMap(File file){
        Map<Integer, Contact> hashMap = new HashMap<>();
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                String[] strings = line.split(" ");
                int id = Integer.valueOf(strings[0]);
                String name = strings[1];
                String surname = strings[2];
                int age = Integer.valueOf(strings[3]);
                hashMap.put(id, new Contact(id, name, surname, age));
            }
        }
        catch (IOException e) {
            System.out.println("Something going wrong!");
        }
        return hashMap;
    }

    private void mapToFile(Map hashMap){

        try {
            new FileWriter(file).close(); //clean the file
        } catch (IOException e) {
            System.out.println("Something going wrong with FileWriter!");
        }

        try  {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            for (Contact contact : ((Map<Integer, Contact>) hashMap).values()) {
                printWriter.println(contact);
            }

            printWriter.flush();

        }
        catch (IOException e) {
            System.out.println("Something going wrong!");
        }

    }

    private int nextId  (File file){
        int generator = 0;
        if (file.exists()){
            Map<Integer, Contact> store;
            store = fileToMap(file);
            if(store.isEmpty()) return generator;
            Contact lastContact = null;
            for (Contact contact:store.values()
                 ) {
                lastContact = contact;
            }
            generator = lastContact.getId() + 1;
        }
        return generator;
    }
}
