package com.app.webnest.mapper;

import com.app.webnest.mapper.quiz.QuizMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
@Slf4j
class QuizMapperTest {

    @Autowired
    private QuizMapper quizMapper;

    @Test
    void selectAll() {
        HashMap<String,Object> map = new HashMap<>();
        map.put("quizDifficult", "중급");
        map.put("quizLanguage", "JAVA");
        map.put("cursor", "1");

        log.info("selectAll: {}", quizMapper.selectAllFilter(map));
    }

    @Test
    void testSelectAll() {
        log.info("testSelectAll, {}", quizMapper.selectAll());
    }

    @Test
    void selectListTotalCount() {
        log.info("testSelectListTotalCount, {}", quizMapper.selectListTotalCount());
    }
}