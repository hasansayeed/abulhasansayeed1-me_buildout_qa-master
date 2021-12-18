package com.crio.buildouts.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

class QuestionTest {

  @Test
  public void serializeAndDeserializeQuestionJson() throws IOException, JSONException {
    final String jsonString =
        "{\n"
            + "  \"questionId\": \"001\",\n"
            + "  \"title\": \"What is the default IP address of localhost?\",\n"
            + "  \"description\": \"General question\",\n"
            + "  \"type\": \"objective-single\",\n"
            + "  \"options\": {\n"
            + "    \"1\": \"asdas\",\n"
            + "    \"2\": \"safd\",\n"
            + "    \"3\": \"asdfsd\",\n"
            + "    \"4\": \"afdsf\"\n"
            + "  },\n"
            + "  \"correctAnswer\": [\n"
            + "    \"1\",\n"
            + "    \"3\",\n"
            + "    \"4\"\n"
            + "  ]\n"
            + "}";

    // Setting up a question object for testing. The following ensures that question
    // object can deserialize the right question json.
    Question question = new Question();
    question = new ObjectMapper().readValue(jsonString, Question.class);
    //System.out.println(question);
    // Deserialize the Json string from Question class to ensure its done cleanly.
    String actualJsonString = "";
    actualJsonString = new ObjectMapper().writeValueAsString(question);
    JSONAssert.assertEquals(jsonString, actualJsonString, true);
  }
  
}