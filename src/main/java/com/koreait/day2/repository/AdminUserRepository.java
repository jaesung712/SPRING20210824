package com.koreait.day2.repository;

import com.koreait.day2.model.entity.AdminUser;
import com.koreait.day2.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {


}
