package com.crio.buildouts.services;

import com.crio.buildouts.dto.Question;
import com.crio.buildouts.exchanges.GetQuestionsResponse;
import com.crio.buildouts.exchanges.SubmitQuestionsRequest;
import com.crio.buildouts.exchanges.SubmitQuestionsResponse;

import java.util.List;

public interface QuizService {

  GetQuestionsResponse getQuestions(String moduleId);

  SubmitQuestionsResponse submitResponse(SubmitQuestionsRequest request, String moduleId);

  void updateQuestions(List<Question> questions, String moduleId);

}