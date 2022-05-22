package com.example.firststepsintoadulthood2.model;

import java.util.List;
import java.util.Objects;

public class Messages {

    private String source, destination;
    private List<String> messages;

    public Messages() {

    }

    public Messages(String source, String destination, List<String> messages) {

        this.source = source;
        this.destination = destination;
        this.messages = messages;
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Messages)) return false;
        Messages messages1 = (Messages) o;
        return Objects.equals(source, messages1.source) && Objects.equals(destination, messages1.destination) && Objects.equals(messages, messages1.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination, messages);
    }


    @Override
    public String toString() {
        return "Messages{" +
                "source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", messages=" + messages +
                '}';
    }

}
