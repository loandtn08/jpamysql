package com.example.jpamysql.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID", nullable = false)
    private Integer id;

    @Size(max = 3000)
    @Column(name = "POST_NAME", length = 3000)
    private String postName;

    @Size(max = 6000)
    @Column(name = "COMMENT", length = 6000)
    private String comment;

    @Size(max = 45)
    @Column(name = "CREATED_DATE", length = 45)
    private String createdDate;

    @Column(name = "LAST_MODIFIED_DATE")
    private Instant lastModifiedDate;

    public Comment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Comment(Integer id, String postName, String comment) {
        this.id = id;
        this.postName = postName;
        this.comment = comment;
    }
}