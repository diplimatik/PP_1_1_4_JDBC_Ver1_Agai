package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl requestStream = new UserServiceImpl();
        requestStream.createUsersTable();
        Scanner scanner = new Scanner(System.in);
        int numberOfIterations = 0;
        System.out.println("how many users you'd like to insert (only accepts numbers)");
        try {
            numberOfIterations = scanner.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < numberOfIterations; ++i) {
            System.out.println("name field:");
            String nameHolder = scanner.next();
            System.out.println("surname field:");
            String surnameHolder = scanner.next();
            System.out.println("age field:");
            byte ageHolder = scanner.nextByte();
            requestStream.saveUser(nameHolder, surnameHolder, ageHolder);
            System.out.println("User с именем – " + nameHolder + " добавлен в базу данных.");
        }

        requestStream.getAllUsers().forEach(user -> System.out.println(user.toString()));
        requestStream.cleanUsersTable();
        requestStream.dropUsersTable();
    }
}
