package com.dbService.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbService.entity.CharacterClass;


import java.util.List;
import java.util.Optional;


@Repository
public interface CharacterClassRepository extends JpaRepository<CharacterClass, Long> {
    List<CharacterClass> findByParentId(Long parentId);

	List<CharacterClass> findAllByOrderByIdAsc();


}