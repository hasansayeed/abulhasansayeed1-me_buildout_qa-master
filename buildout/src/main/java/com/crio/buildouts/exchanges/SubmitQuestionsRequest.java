package com.crio.buildouts.exchanges;

import com.crio.buildouts.dto.Response;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Data;

@Data
public class SubmitQuestionsRequest {

  @JsonProperty("responses")
  List<Response> responses;

  public SubmitQuestionsRequest(List<Response> responses) {
    this.responses = responses;
  }

  public SubmitQuestionsRequest() {
    super();
  }

  public List<Response> getResponses() {
    return responses;
  }

}