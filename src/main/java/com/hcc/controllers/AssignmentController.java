package com.hcc.controllers;

import com.hcc.DTOs.AssignmentResponseDto;
import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.enums.AuthorityEnum;
import com.hcc.services.AssignmentService;
import com.hcc.services.AuthorityUtil;
import com.hcc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/assignments/")
public class AssignmentController {
    @Autowired
    AssignmentService assignmentService;

    @Autowired
    UserService userService;

    @GetMapping()
    public ResponseEntity<?> getAssignmentsByUser(@AuthenticationPrincipal User user) {
    return ResponseEntity.ok(assignmentService.findByUser(user));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getAssignmentById(@PathVariable Long id, @AuthenticationPrincipal User user) {
        Optional<Assignment> assignmentOpt = assignmentService.findById(id);
        return ResponseEntity.ok(new AssignmentResponseDto(assignmentOpt.orElse(new Assignment())));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateAssignmentById(@PathVariable Long id,
                                                  @RequestBody Assignment assignment,
                                                  @AuthenticationPrincipal User user) {

        if (assignment.getCodeReviewer() != null) {
            User codeReviewer = assignment.getCodeReviewer();
            codeReviewer = userService.findByUsername(codeReviewer.getUsername()).get();

            if (AuthorityUtil.hasRole(AuthorityEnum.ROLE_REVIEWER.name(), codeReviewer)) {
                assignment.setCodeReviewer(codeReviewer);
            }
        }

        Assignment updatedAssignment = assignmentService.save(assignment);
        return ResponseEntity.ok(assignmentService.save(updatedAssignment));
    }

    @PostMapping()
    public ResponseEntity<?> createNewAssignment(@AuthenticationPrincipal User user) {
    return ResponseEntity.ok(assignmentService.save(user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAssignment(@PathVariable Long id,
                                              @AuthenticationPrincipal User user) {
        return assignmentService.delete(id);
    }
}
