package org.scoula.board.controller;

import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.log4j.Log4j2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2                           // 로깅을 위한 Lombok 어노테이션
@Controller                       // Spring MVC Controller 지정
@RequestMapping("/board")         // 기본 URL 패턴 설정
@RequiredArgsConstructor         // final 필드 생성자 자동 생성
public class BoardController {

    // 의존성 주입: BoardService를 통해 비즈니스 로직 처리
    final private BoardService service;

    // 목록 조회
    @GetMapping("/list")
    public void list(Model model) {
        log.info("list");                           // 로그 출력
        model.addAttribute("list", service.getList()); // Model에 목록 데이터 추가
    }

    // 등록 폼 표시
    @GetMapping("/create")
    public void create() {
        log.info("create");
        // 뷰 이름: "board/create"
    }

    // 등록 처리
    @PostMapping("/create")
    public String create(BoardDTO board, RedirectAttributes ra) {

        // RedirectAttributes : 리다이렉트 시 데이터를 전달하는 Spring MVC 인터페이스

        // RedirectAttributes.addFlashAttribute("K", V) : 리다이렉트 후 한 번만 사용되고 자동 삭제할 데이터 추가
        // - 리다이렉트 전 -> request scope에 추가됨
        // - 리다이렉트 중 -> Session scope에 임시 저장
        // - 리다이렉트 후 -> request scope로 돌아옴

        log.info("create: " + board);           // 입력 데이터 로그
        service.create(board);                  // 게시글 생성

        ra.addFlashAttribute("message", "게시글이 등록 되었습니다.");

        //return "redirect:/board/list";          // 목록으로 리다이렉트
        return "redirect:/board/get?no=" + board.getNo(); // 등록된 게시글 상세 조회 페이지로 리다이렉트
    }

    // 상세 조회
    @GetMapping({ "/get", "/update" })           // 두 개의 URL을 같은 메서드에서 처리
    public void get(@RequestParam("no") Long no, Model model) {
        log.info("/get or update");
        model.addAttribute("board", service.get(no)); // 특정 게시글 조회
    }
    // URL에 따라 뷰 이름 결정: "board/get" 또는 "board/update"

    // 수정 처리
    @PostMapping("/update")
    public String update(BoardDTO board, RedirectAttributes ra) {
        log.info("update:" + board);
        boolean result = service.update(board);                   // 게시글 수정
        log.info("update result: " + result);

        if(result){
            ra.addFlashAttribute("message", "게시글이 수정 되었습니다.");
        }

        //return "redirect:/board/list";           // 목록으로 리다이렉트
        return "redirect:/board/get?no="+board.getNo(); // 상세 조회 리다이렉트
    }

    // 삭제 처리
    @PostMapping("/delete")
    public String delete(@RequestParam("no") Long no,  RedirectAttributes ra) {
        log.info("delete..." + no);
        boolean result = service.delete(no);                      // 게시글 삭제
        log.info("delete result: " + result);

        if(result){
            ra.addFlashAttribute("message", "게시글이 삭제 되었습니다.");
        }

        return "redirect:/board/list";           // 목록으로 리다이렉트
    }

}