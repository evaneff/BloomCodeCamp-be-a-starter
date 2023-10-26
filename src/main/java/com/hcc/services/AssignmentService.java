package com.hcc.services;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.enums.AssignmentStatusEnum;
import com.hcc.exceptions.ResourceNotFoundException;
import com.hcc.repositories.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AssignmentService {

    @Autowired
    AssignmentRepository assignmentRepository;


    public Set<Assignment> findByUser(User user) {
        return assignmentRepository.findByUser(user);
    }

    public Optional<Assignment> findById(Long id){
        return assignmentRepository.findById(id);
    }

    public Assignment save(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    public Assignment save(User user) {
        Assignment newAssignment = new Assignment();
        newAssignment.setUser(user);
        newAssignment.setStatus(AssignmentStatusEnum.PENDING_SUBMISSION.getStatus());
        newAssignment.setNumber(findNextAssignmentToSubmit(user));
        return assignmentRepository.save(newAssignment);
    }
    private Integer findNextAssignmentToSubmit(User user) {
        Set<Assignment> assignmentsByUser = assignmentRepository.findByUser(user);
        if (assignmentsByUser == null)
            return 1;

        Optional<Integer> sortedAssignment = assignmentsByUser.stream()
                .sorted((a1, a2) -> {
                    if (a1.getNumber() == null) return 1;
                    if (a2.getNumber() == null) return 1;
                    return a2.getNumber().compareTo(a1.getNumber());
                }).map(assignment -> {
                    if (assignment.getNumber() == null) return 1;

                    return assignment.getNumber() + 1;
                }).findFirst();
        return sortedAssignment.orElse(1);
    }

    public ResponseEntity<?> delete(Long id) {
        Assignment assignmentToDelete = assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment does not exist with id " + id));
        assignmentRepository.delete(assignmentToDelete);
        return ResponseEntity.ok("Deleted Assignment " + id);
    }
}
