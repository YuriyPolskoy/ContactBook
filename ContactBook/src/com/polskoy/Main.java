package com.polskoy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.polskoy.dao.impl.ContactDbDAO;
import com.polskoy.ui.CommandLineService;
import com.polskoy.services.ContactService;
import com.polskoy.dao.ContactDAO;
import com.polskoy.dao.impl.ContactDiskDAO;

public class Main {

    public static void main(String[] args) throws IOException {

        ContactDAO dao = new ContactDiskDAO();

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));
        ContactService contactService = new ContactService(dao);
        CommandLineService service = new CommandLineService(contactService, br);
        service.run();
    }
}
