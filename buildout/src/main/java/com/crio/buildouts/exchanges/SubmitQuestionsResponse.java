package com.crio.buildouts.exchanges;

import com.crio.buildouts.dto.QuestionAnswer;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class SubmitQuestionsResponse {

  @JsonProperty("questions")
  private List<QuestionAnswer> questions;

  @JsonProperty("summary")
  private Map<String,Integer> summary;

  public SubmitQuestionsResponse(List<QuestionAnswer> questions,
      Map<String,Integer> summary) {
    this.questions = questions;
    this.summary = summary;
  }

  public SubmitQuestionsResponse() {
    super();
  }

  public List<QuestionAnswer> getQuestions() {
    return questions;
  }

  public Map<String,Integer> getSummary() {
    return summary;
  }

}