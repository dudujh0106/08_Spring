package org.scoula.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.todo.domain.TodoDTO;
import org.scoula.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
public class HomeController {

    // TodoService를 상속 받아 구현한 TodoServiceImpl Bean 의존성 주입(DI)
    private final TodoService todoService;

    @GetMapping("/")
    public String home(Model model) {

        // Spring Model 객체 : Controller -> View 데이터 전달용 객체
        //                    (Request Scope)

        // 전체 할 일 조회 서비스 호출
        List<TodoDTO> todos = todoService.selectAll();

        // 조회 결과를 Model에 담아서 View로 전달(todos)
        model.addAttribute("todos", todos);

        log.info("===============> HomeController /");
        return "index";
    }
}
