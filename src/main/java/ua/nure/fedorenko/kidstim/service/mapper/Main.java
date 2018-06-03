package ua.nure.fedorenko.kidstim.service.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String s="123456";
        System.out.println(encoder.encode(s));
    }
}
