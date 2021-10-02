package net.mikhailov.books.library;

import net.mikhailov.books.library.kafka.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryServiceApplication implements CommandLineRunner {

    @Autowired
    Producer producer;

    public static void main(String[] args) {
        SpringApplication.run(LibraryServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        producer.sendMessage("TEST");
    }
}
