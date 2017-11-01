package com.tongwen.repository.mapper;

import com.tongwen.domain.Role;
import org.apache.ibatis.annotations.Param;

public interface IRoleMapper {
    Role findRoleByName(@Param("name") String name);
}
