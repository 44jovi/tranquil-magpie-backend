package com.tranquilmagpie.domain;

public class TempPrinter {
    public static void main(String[] args) {
        System.out.println("- - - - START of 'main' method in 'Runner' class - - - -");
        User userExample1 = new User(44, "test@test.com", "username123", "Wendy", "Kit", "1904-03-02");
        System.out.println(userExample1);
        System.out.println("- - - - END of 'main' method in 'Runner' class - - - -");
    }
}
