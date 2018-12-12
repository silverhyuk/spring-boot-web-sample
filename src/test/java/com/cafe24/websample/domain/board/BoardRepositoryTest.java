package com.cafe24.websample.domain.board;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @After
    public void cleanup() {
        boardRepository.deleteAll();
    }

    @Test
    public void boardSaveAndRead() {
        //given
        boardRepository.save(Board.builder()
                                .title("테스트 제목")
                                .content("테스트 본문")
                                .userId(5l)
                                .regDateTime(new Date())
                                .build());
        //when
        List<Board> boardList = boardRepository.findAll();

        //then
        Board board = boardList.get(0);
        assertThat(board.getTitle()).isEqualTo("테스트 제목");
        assertThat(board.getContent()).isEqualTo("테스트 본문");
    }
}