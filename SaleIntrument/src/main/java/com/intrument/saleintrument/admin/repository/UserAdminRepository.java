package com.intrument.saleintrument.admin.repository;

import com.intrument.saleintrument.web.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAdminRepository extends JpaRepository<User, Long> {

}
