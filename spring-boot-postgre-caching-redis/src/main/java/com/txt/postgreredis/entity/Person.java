package com.txt.postgreredis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private int age;

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        final Person person = (Person) obj;
        if (this == person) {
            return true;
        } else {
            return (this.name.equals(person.name) && this.age == person.age);
        }
    }

    @Override
    public int hashCode() {
        int hashno = 7;
        hashno = 13 * hashno + (name == null ? 0 : name.hashCode());
        return hashno;
    }
}
