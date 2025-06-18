package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
@Log4j2
public class BoardController {
    private final BoardService service;

//    @GetMapping("")
//    public List<BoardDTO> getList() {
//        return service.getList();
//    }

    @GetMapping("")
    public ResponseEntity<List<BoardDTO>> getList() {
        log.info("============> 게시글 전체 목록 조회");

        List<BoardDTO> list = service.getList();
        return ResponseEntity.ok(list); // 200 OK + 데이터 반환
    }

    @GetMapping("/{no}")
    public ResponseEntity<BoardDTO> get(@PathVariable Long no) {
        log.info("============> 게시글 상세 조회: " + no);

        BoardDTO board = service.get(no);
        return ResponseEntity.ok(board);
    }

    @PostMapping("")
    public ResponseEntity<BoardDTO> create(@RequestBody BoardDTO board) {
        log.info("============> 게시글 생성: " + board);

        // 새 게시글 생성 후 결과 반환
        BoardDTO createdBoard = service.create(board);
        return ResponseEntity.ok(createdBoard);
    }

    @PutMapping("/{no}")
    public ResponseEntity<BoardDTO> update(
            @PathVariable Long no,           // URL에서 게시글 번호 추출
            @RequestBody BoardDTO board      // 수정할 데이터 (JSON)
    ) {
        log.info("============> 게시글 수정: " + no + ", " + board);

        // 게시글 번호 설정 (안전성을 위해)
        board.setNo(no);
        BoardDTO updatedBoard = service.update(board);
        return ResponseEntity.ok(updatedBoard);
    }

    @DeleteMapping("/{no}")
    public ResponseEntity<BoardDTO> delete(@PathVariable Long no) {
        log.info("============> 게시글 삭제: " + no);

        // 삭제된 게시글 정보를 반환
        BoardDTO deletedBoard = service.delete(no);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok(deletedBoard);
    }
}
