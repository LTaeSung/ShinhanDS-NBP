package com.delivery.domain.board.repository;


import com.delivery.domain.board.entity.BoardEntity;
import com.delivery.domain.board.entity.ReCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReCommentRepository extends JpaRepository<ReCommentEntity, Long> {
    // select * from comment_table where board_id=? order by id desc;
    List<ReCommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity);
}
