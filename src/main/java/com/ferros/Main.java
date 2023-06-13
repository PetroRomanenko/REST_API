package com.ferros;

import com.ferros.repository.FileRepository;
import com.ferros.repository.hibernate.HibernateFileRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        FileRepository repository = new HibernateFileRepositoryImpl();

        System.out.println(repository.getById(1));
    }
}