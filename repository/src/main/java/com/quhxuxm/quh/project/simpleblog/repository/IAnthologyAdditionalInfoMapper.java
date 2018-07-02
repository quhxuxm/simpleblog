package com.quhxuxm.quh.project.simpleblog.repository;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.AnthologyAdditionalInfo;
import org.apache.ibatis.annotations.Param;

public interface IAnthologyAdditionalInfoMapper {
    AnthologyAdditionalInfo getOnePojo(@Param("id") long id);

    void createAdditionalInfo(AnthologyAdditionalInfo additionalInfo);

    void updateAdditionalInfo(AnthologyAdditionalInfo additionalInfo);
}
