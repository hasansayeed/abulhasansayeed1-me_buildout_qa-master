package com.crio.buildouts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

import lombok.Data;

@Data
public class GetQuestion {

  @JsonProperty("questionId")
  private String questionId;

  @JsonProperty("title")
  private String title;

  @JsonProperty("type")
  private String type;
  
  @JsonProperty("options")
  private Map<String,String> options;

  public GetQuestion(String questionId,String title,
      String type,Map<String,String> options) {
    this.questionId = questionId;
    this.title = title;
    this.type = type;
    this.options = options;
  }

  public GetQuestion() {
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


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
  
}