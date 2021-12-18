package com.crio.buildouts.exchanges;

import com.crio.buildouts.dto.GetQuestion;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Data;


@Data
public class GetQuestionsResponse {

  @JsonProperty("questions")
  private List<GetQuestion> questions;

  public GetQuestionsResponse(List<GetQuestion> questions) {
    this.questions = questions;
  }
  
  public GetQuestionsResponse() {
    super();
  }

  public void setQuestions(List<GetQuestion> questions) {
    this.questions = questions;
  }

  public List<GetQuestion> getQuestions() {
    return questions;
  }
}