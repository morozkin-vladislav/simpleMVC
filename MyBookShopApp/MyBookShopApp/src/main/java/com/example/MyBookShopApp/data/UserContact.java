package com.example.MyBookShopApp.data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_contact")
public class UserContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "user_id")
    private Integer userID;

    @Enumerated(EnumType.STRING)
    private ContactType type;

    private boolean approved;

    private String code;
    private Integer code_trails;

    @Column(nullable = false)
    private LocalDateTime code_time;

    @Column(nullable = false)
    private String contact;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCode_trails() {
        return code_trails;
    }

    public void setCode_trails(Integer code_trails) {
        this.code_trails = code_trails;
    }

    public LocalDateTime getCode_time() {
        return code_time;
    }

    public void setCode_time(LocalDateTime code_time) {
        this.code_time = code_time;
    }
}
