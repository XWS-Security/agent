package com.example.agent.mail;

public interface MailFormatter<T> {
    String getText(T params);
    String getSubject();
}
