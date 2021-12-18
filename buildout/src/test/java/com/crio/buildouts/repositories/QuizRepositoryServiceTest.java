package com.crio.buildouts.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.crio.buildouts.QuizApplication;
import com.crio.buildouts.dto.GetQuestion;
import com.crio.buildouts.dto.Question;
import com.crio.buildouts.dto.Response;
import com.crio.buildouts.models.QuestionEntity;
import com.crio.buildouts.repositoryservices.QuizRepositoryService;
import com.crio.buildouts.utils.FixtureHelpers;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Provider;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {QuizApplication.class})
@ActiveProfiles("test")
public class QuizRepositoryServiceTest {

  List<QuestionEntity> allQuestions = new ArrayList<QuestionEntity>();

  @Autowired
  private QuizRepositoryService quizRepositoryService;
  @Autowired
  private MongoTemplate mongoTemplate;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private Provider<ModelMapper> modelMapperProvider;

  @BeforeEach
  void setup() throws IOException {
    try {
      allQuestions = listOfQuestions();
      for (QuestionEntity questionEntity : allQuestions) {
        mongoTemplate.save(questionEntity, "questions");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @AfterEach
  void teardown() {
    mongoTemplate.dropCollection("questions");
  }

  @Test
  void getQuestions(@Autowired MongoTemplate mongoTemplate) {
    assertNotNull(mongoTemplate);
    assertNotNull(quizRepositoryService);

    List<GetQuestion> allQuestions = quizRepositoryService
          .getQuestionsbyId("1");

    //ModelMapper modelMapper = modelMapperProvider.get();
    assertEquals(3, allQuestions.size());
    assertEquals("001", allQuestions.get(0).getQuestionId());
    assertEquals("002", allQuestions.get(1).getQuestionId());
  }

  @Test
  void submitResponse(@Autowired MongoTemplate mongoTemplate) {
    assertNotNull(mongoTemplate);
    assertNotNull(quizRepositoryService);

    List<String> list = new ArrayList<>();
    list.add("1");
    List<String> list2 = new ArrayList<>();
    list2.add("1");
    list2.add("3");
    list2.add("4");
    List<String> list3 = new ArrayList<>();
    list3.add("throwable");
    List<Response> responses = new ArrayList<>();
    responses.add(new Response("001", list));
    responses.add(new Response("002", list2));
    responses.add(new Response("003", list3));

    //SubmitQuestionsRequest request = new SubmitQuestionsRequest(responses);
    
    List<Question> questions = quizRepositoryService
        .submitResponsebyId("1");
    assertEquals(3, questions.size());
    assertEquals("001", questions.get(0).getQuestionId());
    assertEquals("002", questions.get(1).getQuestionId());
  }

  private List<QuestionEntity> listOfQuestions() throws IOException {
    String fixture =
        FixtureHelpers.fixture("fixtures/data_set.json");
    List<QuestionEntity> q = objectMapper.readValue(fixture, 
        new TypeReference<List<QuestionEntity>>() {});
    return q;
  
  }
  
}

