package com.app.webnest.mapper.quiz;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class QuizMapperTest {

    @Autowired
    private QuizMapper quizMapper;

    @Test
    public void selectTest() {
        log.info("quiz: {}", quizMapper.select(1L));
    }
}