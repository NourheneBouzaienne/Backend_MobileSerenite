package com.app.ClientService.models;


import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Simulateur {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "simulateur_seq")
    @SequenceGenerator(name = "simulateur_seq", sequenceName = "simulateur_sequence", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Simulateur() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
