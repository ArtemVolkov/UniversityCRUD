package com.volkov.UniversityCRUD.repository;

import com.volkov.UniversityCRUD.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group,Long> {
}
