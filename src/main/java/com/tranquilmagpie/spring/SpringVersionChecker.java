package com.tranquilmagpie.spring;

import org.springframework.core.SpringVersion;

public class SpringVersionChecker {
    public static void main(String[] args) {
        String version = SpringVersion.getVersion();
        System.out.println("Spring Framework version: " + version);
    }
}
