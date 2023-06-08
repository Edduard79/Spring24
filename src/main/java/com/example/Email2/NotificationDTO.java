package com.example.Email2;

public class NotificationDTO {
    private Long id;
    private String title;
    private String text;

    public NotificationDTO() {}

    public NotificationDTO(Long id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public Long getContactId() {
        return id;
    }

    public void setContactId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
