package org.scoula.todo.mapper;

import org.apache.ibatis.annotations.Select;
import org.scoula.todo.domain.TodoDTO;

import java.util.List;

public interface TodoMapper {

    @Select("Select * FROM todo ORDER BY id DESC")
    List<TodoDTO> selectAll();

    // 삽입
    int insertTodo(TodoDTO todo);

    // 완료 여부 수정
    int updateTodo(Long id);

    // 삭제
    int deleteTodo(Long id);
}
