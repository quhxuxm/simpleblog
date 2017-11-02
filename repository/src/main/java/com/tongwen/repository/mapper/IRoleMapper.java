package com.tongwen.repository.mapper;

import com.tongwen.domain.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleMapper {
    Role findRoleByName(
            @Param("name")
                    String name);
}
