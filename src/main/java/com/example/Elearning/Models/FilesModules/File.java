package com.example.Elearning.Models.FilesModules;

import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;

import javax.persistence.*;
import java.util.Date;
@Entity

public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Date created = new Date();
    private String summary;

    @ContentId
    private String contentId;
    @ContentLength
    private long contentLength;
    private String contentMimeType = "text/plain";

    public File(Long id, String name, Date created, String summary, String contentId, long contentLength, String contentMimeType) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.summary = summary;
        this.contentId = contentId;
        this.contentLength = contentLength;
        this.contentMimeType = contentMimeType;
    }

    public File() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public String getContentMimeType() {
        return contentMimeType;
    }

    public void setContentMimeType(String contentMimeType) {
        this.contentMimeType = contentMimeType;
    }
}
