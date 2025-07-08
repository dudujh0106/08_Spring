package org.scoula.todo.service;

import org.scoula.todo.domain.TodoDTO;

import java.util.List;

public interface TodoService {

    List<TodoDTO> selectAll();

    // 삽입
    int insertTodo(TodoDTO todo);

    // 완료 여부 수정
    int updateTodo(Long id);

    // 삭제
    int deleteTodo(Long id);
}
