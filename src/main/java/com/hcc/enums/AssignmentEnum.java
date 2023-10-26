package com.hcc.enums;

public enum AssignmentEnum {
    ASSIGNMENT_1(1, "Spring Boot Service"),
    ASSIGNMENT_2(2, "Sprint Boot Data JPA"),
    ASSIGNMENT_3(3, "Docker Compose Setup"),
    ASSIGNMENT_4(4, "React Frontend Hooks"),
    ASSIGNMENT_5(5, "Python Problem Solving"),
    ASSIGNMENT_6(6, "Assembly Boot Sector Hello World"),
    ASSIGNMENT_7(7, "CTF Buffer Overflow"),
    ASSIGNMENT_8(8, "SQL Basics");

    private int assignmentNumber;
    private String assignmentName;

    AssignmentEnum(int assignmentNumber, String assignmentName) {
        this.assignmentNumber = assignmentNumber;
        this.assignmentName = assignmentName;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public int getAssignmentNumber() {
        return assignmentNumber;
    }
}
