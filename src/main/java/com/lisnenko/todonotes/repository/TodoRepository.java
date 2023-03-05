package com.lisnenko.todonotes.repository;

import com.lisnenko.todonotes.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {}
