package com.hcc.entities;

import javax.persistence.*;


@Entity
public class Authority {
    private Long id;
    private String authority;
    @ManyToOne
    private User user;


    public Authority() { }

    public Authority(String authority, User user) {
        this.authority = authority;
        this.user = user;
    }

    public Authority(String authority) {
        this.authority = authority;
    }
}
