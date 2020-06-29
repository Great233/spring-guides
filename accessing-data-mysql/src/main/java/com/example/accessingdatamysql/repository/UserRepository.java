package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Great
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
