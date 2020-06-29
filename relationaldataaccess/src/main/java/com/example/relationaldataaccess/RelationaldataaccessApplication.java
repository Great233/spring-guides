package com.example.relationaldataaccess;

import com.example.relationaldataaccess.bean.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class RelationaldataaccessApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(RelationaldataaccessApplication.class);

    JdbcTemplate jdbcTemplate;

    @Autowired
    public RelationaldataaccessApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(RelationaldataaccessApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Creating tables");

        jdbcTemplate.execute("CREATE TABLE customer(" +
                "id SERIAL," +
                "first_name VARCHAR(255)," +
                "last_name VARCHAR(255)" +
                ")");

        List<Object[]> splitUpNames = Stream.of("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long")
                .map(name -> name.split(" "))
                .collect(Collectors.toList());
        splitUpNames.forEach(name -> LOGGER.info("Interesting customer record for {} {}",
                name[0],
                name[1]));

        jdbcTemplate.batchUpdate("INSERT INTO customer(first_name, last_name) VALUES (?, ?)",
                splitUpNames);

        LOGGER.info("Querying for customer records where first_name='John': ");
        jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM customer where first_name=?",
                new Object[]{"John"},
                (rs, rowNum) -> new Customer(
                        rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                )
        ).forEach(customer -> LOGGER.info(customer.toString()));
    }
}
