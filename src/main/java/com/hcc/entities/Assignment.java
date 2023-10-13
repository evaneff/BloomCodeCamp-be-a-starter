package com.hcc.entities;

import javax.persistence.Entity;

@Entity
public class Assignment {
    private Long id;
    private String status;
    private int number;
    private String githubUrl;
    private String branch;
    private String reviewVideoUrl;
    private User user;
    private User codeReviewer;

    public Assignment() {
    }

    public Assignment(String status, int number, String githubUrl, String branch, String reviewVideoUrl, User user, User codeReviewer) {
        this.status = status;
        this.number = number;
        this.githubUrl = githubUrl;
        this.branch = branch;
        this.reviewVideoUrl = reviewVideoUrl;
        this.user = user;
        this.codeReviewer = codeReviewer;
    }
}
