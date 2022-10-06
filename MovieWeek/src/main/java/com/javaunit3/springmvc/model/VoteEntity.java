package com.javaunit3.springmvc.model;

import org.hibernate.SessionFactory;
import javax.persistence.*;

@Entity
@Table(name = "votes")
public class VoteEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "voter_Name")
    private String voterName;

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVoterName() {
        return voterName;
    }

    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }
}

/*
Part4-2: Create a OneToMany entity mapping
P4 - 2a: create voteEntity class in model folder
     - generate id field and a field for the voter’s name
     - include getters and setters.
*/