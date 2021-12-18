package com.crio.buildouts.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

import com.crio.buildouts.QuizApplication;
import com.crio.buildouts.dto.GetQuestion;
import com.crio.buildouts.dto.Question;
import com.crio.buildouts.exchanges.GetQuestionsResponse;
import com.crio.buildouts.exchanges.SubmitQuestionsRequest;
import com.crio.buildouts.exchanges.SubmitQuestionsResponse;
import com.crio.buildouts.repositoryservices.QuizRepositoryService;
import com.crio.buildouts.utils.FixtureHelpers;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {QuizApplication.class})
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class QuizServiceMockitoTest {

  protected List<GetQuestion> gquestions;
  protected SubmitQuestionsRequest request;
  protected SubmitQuestionsResponse responses;
  protected List<Question> questions;
  
  @InjectMocks
  protected QuizServiceImpl quizService;
  
  @MockBean
  protected QuizRepositoryService quizRepositoryServiceMock;
  private ObjectMapper objectMapper;

  @BeforeEach
  void setup() {
    MockitoAnnotations.initMocks(this);
    objectMapper = new ObjectMapper();
  }

  
  @BeforeEach
  public void initializeQuestionObjects() throws IOException {
    String fixture1 =
        FixtureHelpers.fixture("fixtures/sample_get_questions_response.json");
    GetQuestionsResponse allQuestions = objectMapper.readValue(fixture1,
        GetQuestionsResponse.class);
    gquestions = allQuestions.getQuestions();
    String fixture2 =
        FixtureHelpers.fixture("fixtures/sample_submit_question_request.json");
    request = objectMapper.readValue(fixture2, SubmitQuestionsRequest.class);
    String fixture3 =
        FixtureHelpers.fixture("fixtures/sample_submit_question_response.json");
    responses = objectMapper.readValue(fixture3, SubmitQuestionsResponse.class);
    String fixture4 =
        FixtureHelpers.fixture("fixtures/data_set.json");
    questions = objectMapper.readValue(fixture4, new TypeReference<List<Question>>(){
    });
  }

  @Test
  public void testGetAndSubmitQuestions() throws IOException {

    lenient().when(quizRepositoryServiceMock
        .getQuestionsbyId("1"))
        .thenReturn((gquestions));

    lenient().when(quizRepositoryServiceMock
        .submitResponsebyId("1")).thenReturn((questions));

    GetQuestionsResponse allQuestions = quizService
         .getQuestions("1");
    assertEquals(3, allQuestions.getQuestions().size());
    assertEquals("001", allQuestions.getQuestions().get(0).getQuestionId());
    assertEquals("002", allQuestions.getQuestions().get(1).getQuestionId());

    SubmitQuestionsResponse allResponses = quizService
         .submitResponse(request, "1");
    assertEquals(3, allResponses.getQuestions().size());
    assertEquals(2, allResponses.getSummary().get("score"));
    assertEquals(3, allResponses.getSummary().get("total"));

  }

}