package com.crio.buildouts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Data;

@Data
public class Response {

  @JsonProperty("questionId")
  private String questionId;

  @JsonProperty("userResponse")
  private List<String> userResponse;

  public Response(String questionId, List<String> userResponse) {
    this.questionId = questionId;
    this.userResponse = userResponse;
  }

  public Response() {
    super();
  }
}