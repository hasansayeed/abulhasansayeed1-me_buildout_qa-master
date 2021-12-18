package com.crio.buildouts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionAnswer {

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

  @JsonProperty("userAnswer")
  private List<String> userAnswer;

  @JsonProperty("correct")
  private List<String> correct;

  @JsonProperty("explanation")
  private String explanation;

  @JsonProperty("answerCorrect")
  private Boolean answerCorrect;

  public QuestionAnswer() {
    super();
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

  public List<String> getCorrect() {
    return correct;
  }

  public void setCorrectAnswer(List<String> correct) {
    this.correct = correct;
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

  public Boolean getAnswerCorrect() {
    return answerCorrect;
  }

  public void setAnswerCorrect(Boolean answerCorrect) {
    this.answerCorrect = answerCorrect;
  }

}