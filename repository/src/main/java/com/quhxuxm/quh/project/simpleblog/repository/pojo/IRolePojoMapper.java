package com.quhxuxm.quh.project.simpleblog.repository.pojo;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolePojoMapper {
    Role findOneByName(@Param("name") String name);

    Role findOneById(@Param("id") long id);
}
