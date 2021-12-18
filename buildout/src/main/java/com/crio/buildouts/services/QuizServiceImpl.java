package com.crio.buildouts.services;

import com.crio.buildouts.dto.GetQuestion;
import com.crio.buildouts.dto.Question;
import com.crio.buildouts.dto.QuestionAnswer;
import com.crio.buildouts.dto.Response;
import com.crio.buildouts.exchanges.GetQuestionsResponse;
import com.crio.buildouts.exchanges.SubmitQuestionsRequest;
import com.crio.buildouts.exchanges.SubmitQuestionsResponse;
import com.crio.buildouts.repositoryservices.QuizRepositoryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@Log4j2
public class QuizServiceImpl implements QuizService {

  @Autowired
  private QuizRepositoryService quizRepositoryService;

  @Override
  public void updateQuestions(List<Question> questions,String moduleId) {

    quizRepositoryService.putQuestionsbyId(questions,moduleId);
    
  }

  @Override
  public GetQuestionsResponse getQuestions(String moduleId) {
    List<GetQuestion> questions = quizRepositoryService.getQuestionsbyId(moduleId);
    GetQuestionsResponse response = new GetQuestionsResponse(questions);
    return response;
  }

  public SubmitQuestionsResponse submitResponse(SubmitQuestionsRequest request,
      String moduleId) {
    List<Question> questions = quizRepositoryService.submitResponsebyId(moduleId);
    SubmitQuestionsResponse response = new SubmitQuestionsResponse();
    if (questions != null) {
      response = makeResponse(request,questions);
    }
    return response;
  }


  private SubmitQuestionsResponse makeResponse(SubmitQuestionsRequest request,
      List<Question> questions) {
    
    List<QuestionAnswer> questionAnswers = new ArrayList<QuestionAnswer>();
    Map<String,Integer> summary = new HashMap<String,Integer>();
    List<Response> responses = request.getResponses();
    Integer score = 0;
    Integer total = responses.size();
    summary.put("total",total);

    for (Response response : responses) {
      QuestionAnswer questionAnswer = new QuestionAnswer();
      for (Question question : questions) {
        if (question.getQuestionId().equals(response.getQuestionId())) {
          questionAnswer.setQuestionId(question.getQuestionId());
          questionAnswer.setTitle(question.getTitle());
          questionAnswer.setDescription(question.getDescription());
          questionAnswer.setType(question.getType());
          questionAnswer.setOptions(question.getOptions());
          questionAnswer.setCorrect(question.getCorrectAnswer());
          questionAnswer.setUserAnswer(response.getUserResponse());
          // squestion.setExplanation(null);
          if (question.getCorrectAnswer().equals(response.getUserResponse())) {
            score = score + 1;
            questionAnswer.setAnswerCorrect(true);
          } else {
            questionAnswer.setAnswerCorrect(false);
          }
        }
      }
      summary.put("score",score);
      questionAnswers.add(questionAnswer);
    }
    SubmitQuestionsResponse sqresponse = new SubmitQuestionsResponse(questionAnswers,summary);
    System.out.println(sqresponse);
    return sqresponse;
  }

}