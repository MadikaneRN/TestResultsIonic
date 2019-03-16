package za.ac.cput.domain;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String idno;
    private String name;
    private String surname;


    public Person()
    {

    }


    public Person(Builder builder) {

        this.id = builder.id;
        this.idno = builder.idno;
        this.surname = builder.surname;
        this.name = builder.name;
    }



    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }


    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getIdno() {
        return idno;
    }


    public static class Builder {

        //Equivalent to setters
        private long id;
        private String name;
        private String surname;
        private String idno;


        public Builder id(long value)
        {
            this.id = value;
            return this;
        }

        public Builder idno (String idno) {
            this.idno = idno;
            return this;
        }




        public Builder surname(String Surname) {
            this.surname = Surname;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }



        public Builder copy(Person person){
            this.id = person.id;
            this.idno = person.idno;
            this.name = person.name;
            this.surname = person.surname;
            return this;
        }

//

        public Person build() {
            return new Person(this);
        }
    }




}
