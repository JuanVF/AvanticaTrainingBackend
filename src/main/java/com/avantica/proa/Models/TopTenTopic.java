package com.avantica.proa.Models;

public class TopTenTopic {
    private String topic;
    private int num_resources;

    public TopTenTopic(String topic, int num_resources) {
        this.topic = topic;
        this.num_resources = num_resources;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getNum_resources() {
        return num_resources;
    }

    public void setNum_resources(int num_resources) {
        this.num_resources = num_resources;
    }
}
