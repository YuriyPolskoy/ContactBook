package com.polskoy.ui;

import java.io.BufferedReader;
import java.io.IOException;

import com.polskoy.services.ContactService;

public class CommandLineService {

    private ContactService contactService;
    private BufferedReader br;

    public CommandLineService(
            ContactService contactService,
            BufferedReader br) {
        this.contactService = contactService;
        this.br = br;
    }

    public void run() throws IOException {
        String input;
        do {
            showMenu();
            input = br.readLine();
            if (input.equals("1")) {
                showCreateMenu();
            }

            if (input.equals("2")) {
                showUpdateMenu();
            }

            if (input.equals("3")) {
                showDeleteMenu();
            }

            if (input.equals("4")) {
                contactService.show();
            }

        }
        while (!input.equals("0"));
    }

    public void showMenu() {
        System.out.println("Menu");
        System.out.println("1. Create contact");
        System.out.println("2. Modify contact");
        System.out.println("3. Delete contact");
        System.out.println("4. Show contacts");
        System.out.println("0. Exit");
    }

    public void showCreateMenu() throws IOException {
        System.out.println("Input name:");
        String name = br.readLine();
        System.out.println("Input surname:");
        String surname = br.readLine();
        System.out.println("Input age:");
        int age = readNumber();
        //System.out.println("Input phone:");

        contactService.createContact(name, surname, age);
    }

    public void showUpdateMenu() throws IOException {
        contactService.show();
        System.out.println("Input id of contact for update");
        int id = readNumber();
        System.out.println("Input new name:");
        String name = br.readLine();
        System.out.println("Input new surname:");
        String surname = br.readLine();
        System.out.println("Input new age:");
        int age = readNumber();
        contactService.updateContact(id, name, surname, age);
    }

    public void showDeleteMenu() {
        contactService.show();
        System.out.println("Input id of contact for remove");
        int id = readNumber();
        contactService.deleteContact(id);
    }

    public int readNumber() {
        try {

            Integer integer = new Integer(br.readLine());
            return integer;
        }
        catch (IOException | NumberFormatException e) {
            System.out.println("Wrong format!!!");
            return readNumber();
        }
        catch (Exception e){
            System.out.println("Wrong age!!! Too Old.");
            return readNumber();
        }
    }
}