package org.scoula.ex03.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.ex03.dto.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // 컨트롤러임을 명시 + Bean 등록
@Log4j2 // log 필드 생성(롬복)
@RequestMapping("/sample") // "/sample"로 시작하는 요청을 현재 컨트롤러로 매핑
public class SampleController {
    // 클래스 레벨 "/sample" + 메서드 레벨 "" -> "/sample" URL 요청 매핑
    // 단, GET, POST 요청만 매핑
    @RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
    public String basic() {
        log.info("[GET] /sample 요청 처리됨");

        // 접두사 : /WEB-INF/views/
        // 접미사 : .jsp
        return "sample/input_page";
    }

    @RequestMapping(value = "/basic", method = {RequestMethod.GET})
    public void basic2() {
        log.info("[GET] /sample/basic2 요청 처리됨");
    }

    @RequestMapping("/board/{id}")
    public void selectBoard(@PathVariable("id") long id) {
        log.info("입력된 id : " + id);
    }

    // GET 요청만 처리 - 조회 작업에 사용 (Safe, Idempotent)
    @GetMapping("/basicOnlyGet")
    public void basicGet2() {
        log.info("basic get only get............");
        // 데이터 조회, 페이지 표시 등 안전한 작업
    }

    // [POST] /sample/ex01
    // - SampleDTO dto : 커맨드 객체(파라미터가 )
    @PostMapping("/ex01")
    public String ex01(SampleDTO dto) {  // HandlerAdapter가 자동으로 객체 생성 및 프로퍼티 바인딩
        log.info("" + dto);   // 바인딩된 데이터 로그 출력으로 확인
        return "sample/ex01"; // ViewResolver에 의해 /WEB-INF/views/sample/ex01.jsp로 forward
    }

    @GetMapping("/ex02")
    public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
        log.info("name : " + name + ", age : " + age);

        return "sample/ex02";
    }

    // @RequestParam 옵션 활용 - 파라미터 누락 및 기본값 처리
    @GetMapping("/ex02-advanced")
    public String ex02Advanced(
            @RequestParam(value="name", required=false, defaultValue="익명") String name,
            @RequestParam(value="age", required=false, defaultValue="10") int age) {
        // required=false: 파라미터가 없어도 에러 발생하지 않음
        // defaultValue: 파라미터가 없을 때 사용할 기본값 (문자열로 지정, 자동 형변환)
        log.info("name : " + name + ", age : " + age);
        return "sample/ex02";
    }
}
