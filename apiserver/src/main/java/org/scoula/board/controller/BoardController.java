package org.scoula.board.controller;

import io.swagger.annotations.*;
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
@Api(tags = "게시글 관리")
public class BoardController {
    private final BoardService service;

//    @GetMapping("")
//    public List<BoardDTO> getList() {
//        return service.getList();
//    }

    @ApiOperation(value = "게시글 목록", notes = "게시글 목록을 얻는 API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공적으로 요청이 처리되었습니다.", response = BoardDTO.class),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 500, message = "서버에서 오류가 발생했습니다.")
    })
    @GetMapping("")
    public ResponseEntity<List<BoardDTO>> getList() {
        log.info("============> 게시글 전체 목록 조회");

        List<BoardDTO> list = service.getList();
        return ResponseEntity.ok(list); // 200 OK + 데이터 반환
    }

    @ApiOperation(value = "상세정보 얻기", notes = "게시글 상세 정보를 얻는 API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공적으로 요청이 처리되었습니다.", response = BoardDTO.class),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 500, message = "서버에서 오류가 발생했습니다.")
    })
    @GetMapping("/{no}")
    public ResponseEntity<BoardDTO> get(
            @ApiParam(value = "게시글 ID", required = true, example = "1")
            @PathVariable Long no) {
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
