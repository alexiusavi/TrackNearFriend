package com.example.tracknearfriend;

public class Note {
    private String documentId;
    private String loadEmail;
    private String loadFirstName;
    private String loadLastName;

    public Note() {

    }

    public String getDocumentId() {

        return documentId;
    }

    public void setDocumentId(String documentId) {

        this.documentId = documentId;
    }

    public String getLoadEmail() {
        return loadEmail;
    }

    public void setLoadEmail(String loadEmail) {
        this.loadEmail = loadEmail;
    }

    public String getLoadFirstName() {
        return loadFirstName;
    }

    public void setLoadFirstName(String loadFirstName) {
        this.loadFirstName = loadFirstName;
    }

    public String getLoadLastName() {
        return loadLastName;
    }

    public void setLoadLastName(String loadLastName) {
        this.loadLastName = loadLastName;
    }
}
