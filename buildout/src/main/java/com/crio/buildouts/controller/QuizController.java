package com.crio.buildouts.controller;

import com.crio.buildouts.dto.Question;
import com.crio.buildouts.exchanges.GetQuestionsResponse;
import com.crio.buildouts.exchanges.SubmitQuestionsRequest;
import com.crio.buildouts.exchanges.SubmitQuestionsResponse;

import com.crio.buildouts.services.QuizService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(QuizController.QUIZ_API_ENDPOINT)
class QuizController {

  public static final String QUIZ_API_ENDPOINT = "/quiz";
  //public static final String QUESTIONS_API = "/";

  @Autowired
  private QuizService quizService;
  
  @PutMapping("/{moduleId}")
  public ResponseEntity<String> putQuestions(@RequestBody List<Question> questions,
      @PathVariable("moduleId") String moduleId) {
    try {
      Integer.parseInt(moduleId);
    } catch (NumberFormatException e) {
      return ResponseEntity.badRequest().body("Invalid moduleId");
    }
    quizService.updateQuestions(questions, moduleId);
    return ResponseEntity.ok().body("Put Successful");
  }

  @GetMapping("/{moduleId}")
  public ResponseEntity<GetQuestionsResponse> getQuestions(
      @PathVariable("moduleId") String moduleId) {
    try {
      Integer.parseInt(moduleId);
    } catch (NumberFormatException e) {
      return ResponseEntity.badRequest().body(null);
    }
    GetQuestionsResponse questions;
    questions = quizService.getQuestions(moduleId);
    return ResponseEntity.ok().body(questions);
  }
  
  @PostMapping("/{moduleId}")
  public ResponseEntity<SubmitQuestionsResponse> getAnswers(
      @RequestBody SubmitQuestionsRequest request, @PathVariable("moduleId") String moduleId) {
    try {
      Integer.parseInt(moduleId);
    } catch (NumberFormatException e) {
      return ResponseEntity.badRequest().body(null);
    }
    SubmitQuestionsResponse response;
    response = quizService.submitResponse(request, moduleId);
    return ResponseEntity.ok().body(response); 
  }
}