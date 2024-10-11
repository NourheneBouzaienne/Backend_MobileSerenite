package com.app.ClientService.models;


import javax.persistence.*;

@Entity
@Table(	name = "test")
public class test{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public test() {
    }

    public test(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
