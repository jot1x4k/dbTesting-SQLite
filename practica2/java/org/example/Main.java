package org.example;

import data.IUserRepository;
import data.SqliteUserRepository;
import data.UserConsoleUI;

public class Main {
    public static void main(String[] args) {
        IUserRepository repository = new SqliteUserRepository();
        UserConsoleUI ui = new UserConsoleUI(repository);

        ui.menu();
    }
}

