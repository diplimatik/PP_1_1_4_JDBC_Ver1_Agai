package jm.task.core.jdbc.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Table
public class User {
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private Byte age;

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        ans.append(lastName);
        ans.append(" ");
        ans.append(name);
        ans.append("-");
        ans.append(this.age);
        return ans.toString();
    }

    @Override
    public boolean equals (Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        return ((User) obj).getName().equals(this.getName()) && ((User) obj).getLastName().equals(this.getLastName())
                && ((User) obj).getId().equals(this.getId()) && ((User) obj).getAge().equals(this.getAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.age, this.id);
    }

}
