package com.jhc.entity;

import java.util.List;

public class Content {
    private String contentId;
    private String content;
    private String probability;
    private String wordList;

    public String getContentId() {
        return contentId;
    }
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getProbability() {
        return probability;
    }
    public void setProbability(String probability) {
        this.probability = probability;
    }
    public String getWordList() {
        return wordList;
    }
    public void setWordList(String wordList) {
        this.wordList = wordList;
    }
}
