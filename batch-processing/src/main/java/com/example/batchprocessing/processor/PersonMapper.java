package com.example.batchprocessing.processor;

import com.example.batchprocessing.entity.Person;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * @author Great
 */
public class PersonMapper implements FieldSetMapper<Person> {
    @Override
    public Person mapFieldSet(FieldSet fieldSet)
            throws BindException {
        Person person = new Person();
        person.setFirstName(fieldSet.readString("firstName"));
        person.setLastName(fieldSet.readString("lastName"));
        return person;
    }
}
