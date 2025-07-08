package org.scoula.todo.controller;

import lombok.RequiredArgsConstructor;
import org.scoula.todo.domain.TodoDTO;
import org.scoula.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 컨트롤러 명시 + Bean 등록
@RequestMapping("/todo") // /todo로 시작하는 요청 매핑
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService; // 의존성 주입

    /** 할 일 삽입
     *
     * @param todo 커맨드 객체(전달된 파라미터(title, description) 필드에 저장)
     * @return
     */
    @PostMapping("/insert")
    public String insertTodo(TodoDTO todo){

        // 서비스 호출
        todoService.insertTodo(todo);

        return "redirect:/"; // 메인 페이지 리다이렉트
    }

    @PostMapping("/update")
    public String updateTodo(@RequestParam("id") Long id) {
        todoService.updateTodo(id);

        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteTodo(@RequestParam("id") Long id) {
        todoService.deleteTodo(id);
        return "redirect:/";
    }

}
