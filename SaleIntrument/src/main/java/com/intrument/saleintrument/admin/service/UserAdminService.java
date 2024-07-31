package com.intrument.saleintrument.admin.service;

import com.intrument.saleintrument.admin.repository.UserAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAdminService {
    @Autowired
    private UserAdminRepository userRepository;
}
