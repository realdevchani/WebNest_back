package com.app.webnest.repository;

import com.app.webnest.repository.quiz.QuizDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
@Slf4j
class QuizDAOTest {

    @Autowired
    private QuizDAO quizDAO;

    @Test
    void selectQuizAll() {
        HashMap<String,String> map = new HashMap<>();
        map.put("quizDifficult", "중급");
        map.put("quizLanguage", "ORACLE");
        map.put("cursor", "1");

    }

    @Test
    void selectAllCountTest() {
        log.info("selectAllCount: {}", quizDAO.selectAllCount());
    }
}