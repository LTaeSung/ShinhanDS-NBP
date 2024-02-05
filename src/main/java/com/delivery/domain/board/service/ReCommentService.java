package com.delivery.domain.board.service;

import com.delivery.domain.board.dto.ReCommentDTO;
import com.delivery.domain.board.entity.BoardEntity;
import com.delivery.domain.board.entity.ReCommentEntity;
import com.delivery.domain.board.repository.BoardRepository;
import com.delivery.domain.board.repository.ReCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ReCommentService {
    private final ReCommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public Long save(ReCommentDTO commentDTO) {
        /* 부모엔티티(BoardEntity) 조회 */
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getBoardId());
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            ReCommentEntity commentEntity = ReCommentEntity.toSaveEntity(commentDTO, boardEntity);
            return commentRepository.save(commentEntity).getId();
        } else {
            return null;
        }
    }

    public List<ReCommentDTO> findAll(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        List<ReCommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);
        /* EntityList -> DTOList */
        List<ReCommentDTO> commentDTOList = new ArrayList<>();
        for (ReCommentEntity commentEntity: commentEntityList) {
            ReCommentDTO commentDTO = ReCommentDTO.toCommentDTO(commentEntity, boardId);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }

}
