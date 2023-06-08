package com.example.Email2;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final List<Student> students;

    @Autowired
    private JavaMailSender emailSender;

    public StudentService() {
        students = new ArrayList<>();
        students.add(new Student(1L, "Eduard", "Ancuta", "eduard962010@gmail.com"));
        students.add(new Student(2L, "Marco", "Rossi", "marcorossi@qualcosa.com"));
        students.add(new Student(3L, "Marta", "Bianchi", "martabianchi@qualcosa.com"));
        students.add(new Student(4L, "Andrea", "Verdi", "andreaverdi@qualcosa.com"));
    }

    public void sendEmailToStudent(long contactId, String subject, String text) {
        Optional<Student> optionalStudent = students.stream().filter(s -> s.getId() == contactId).findFirst();
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            String htmlContent = "<h1>Hello " + student.getName() + " " + student.getSurname() + "</h1>"
                    + "<h2>New message:</h2>"
                    + "<img src='http://www.snut.fr/wp-content/uploads/2015/08/image-de-paysage.jpg' alt='Bella foto! :D'>"
                    + "<h3>" + text + "</h3>";
            MimeMessage message = emailSender.createMimeMessage();
            try {
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setTo(student.getEmail());
                helper.setSubject(subject);
                helper.setText(htmlContent);
                helper.setReplyTo("dantesandwich@gmail.com");
                helper.setFrom("alessio.limina90@gmail.com");
                emailSender.send(helper.getMimeMessage());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Student not found with contact ID " + contactId);
        }
    }
}
