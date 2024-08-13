package com.surf0335.AI_Recommendation_System.repository;

import com.surf0335.AI_Recommendation_System.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleRepo extends JpaRepository<Module, Integer> {
    // 根据模块名称查找模块
    Optional<Module> findByName(String name);

    // 根据模块代码查找模块
    Optional<Module> findByCode(String code);

    // 根据教师姓名查找模块
    List<Module> findByTeachername(String teachername);
}
