package com.crio.buildouts.repositoryservices;

import com.crio.buildouts.dto.GetQuestion;
import com.crio.buildouts.dto.Question;
import java.util.List;

public interface QuizRepositoryService {

  List<GetQuestion> getQuestionsbyId(String moduleId);

  List<Question> submitResponsebyId(String moduleId);

  void putQuestionsbyId(List<Question> questions, String moduleId);
  
}