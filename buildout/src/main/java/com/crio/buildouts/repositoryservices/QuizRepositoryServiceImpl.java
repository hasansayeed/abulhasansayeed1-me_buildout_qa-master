package com.crio.buildouts.repositoryservices;

import  com.crio.buildouts.dto.GetQuestion;
import com.crio.buildouts.dto.Question;
import com.crio.buildouts.models.QuestionEntity;
import com.crio.buildouts.reposiories.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Provider;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Primary
@Service
public class QuizRepositoryServiceImpl implements QuizRepositoryService {

  @Autowired
  private Provider<ModelMapper> modelMapperProvider;

  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public List<GetQuestion> getQuestionsbyId(String moduleId) {

    List<GetQuestion> getQuestions = new ArrayList<GetQuestion>();
    List<QuestionEntity> questions = mongoTemplate.findAll(QuestionEntity.class);

    for (QuestionEntity question : questions) {
      GetQuestion gquestion = modelMapperProvider.get().map(question,GetQuestion.class);
      getQuestions.add(gquestion);
    }
    return getQuestions;
  }

  @Override
  public List<Question> submitResponsebyId(String moduleId) {
    
    List<Question> questions = new ArrayList<Question>();
    List<QuestionEntity> questionEntities = questionRepository.findAll();

    for (QuestionEntity questionEntity : questionEntities) {
      Question question = modelMapperProvider.get().map(questionEntity,Question.class);
      questions.add(question);
    }
    return questions;
  }

  @Override
  public void putQuestionsbyId(List<Question> questions, String moduleId) {
    List<QuestionEntity> questionEntities = new ArrayList<QuestionEntity>();
    for (Question question : questions) {
      QuestionEntity questionEntity = modelMapperProvider.get().map(question,QuestionEntity.class);
      questionEntities.add(questionEntity);
    } 
    questionRepository.saveAll(questionEntities);
  }

}