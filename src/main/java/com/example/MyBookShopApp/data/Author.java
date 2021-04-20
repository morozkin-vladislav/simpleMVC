package com.example.MyBookShopApp.data;

public class Author {
    private Integer id;
    private String firsName;
    private String lastName;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firsName='" + firsName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public String getFirsName() {
        return firsName;
    }

    public String getLastName() {
        return lastName;
    }
}
