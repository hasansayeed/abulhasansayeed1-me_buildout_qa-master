package com.crio.buildouts.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "questions")
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEntity {

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

}