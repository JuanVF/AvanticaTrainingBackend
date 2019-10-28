package com.avantica.proa.Models;

import javax.persistence.*;

@Entity
@Table(name="resource")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long resource_id;

    @Column
    private String description;

    @Column
    private String url;

    @ManyToOne
    @JoinColumn(name="topic_id")
    private Topic topic;

    public Resource() {
    }

    public Resource(String description, String url, Topic topic) {
        this.description = description;
        this.url = url;
        this.topic = topic;
    }

    public long getResource_id() {
        return resource_id;
    }

    public void setResource_id(long resource_id) {
        this.resource_id = resource_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
