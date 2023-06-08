package com.example.Email2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/{id}")
    public void sendEmailToStudent(@PathVariable Long id, @RequestBody NotificationDTO notificationDTO) {
        try {
            studentService.sendEmailToStudent(id, notificationDTO.getTitle(), notificationDTO.getText());
        }catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with contact ID " + id, e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error sending email", e);
        }
    }
}
