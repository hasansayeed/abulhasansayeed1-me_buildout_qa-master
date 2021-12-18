package com.crio.buildouts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;


public class Question {

  @JsonProperty("questionId")
  private String questionId;

  @JsonProperty("title")
  private String title;

  @JsonProperty("description")
  private String description;

  @JsonProperty("type")
  private String type;

  @JsonProperty("options")
  private Map<String, String> options;

  @JsonProperty("correctAnswer")
  private List<String> correctAnswer;

  public Question() {
    super();
  }

  public Question(String questionId,String title,String description,String type,
      Map<String,String> options, List<String> correctAnswer) {
    this.questionId = questionId;
    this.title = title;
    this.description = description;
    this.type = type;
    this.options = options;
    this.correctAnswer = correctAnswer;
  }

  public String getQuestionId() {
    return questionId;
  }

  public void setQuestionId(String questionId) {
    this.questionId = questionId;
  }

  public Map<String, String> getOptions() {
    return options;
  }

  public void setOptions(Map<String, String> options) {
    this.options = options;
  }

  public List<String> getCorrectAnswer() {
    return correctAnswer;
  }

  public void setCorrectAnswer(List<String> correctAnswer) {
    this.correctAnswer = correctAnswer;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

}