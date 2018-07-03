package com.quhxuxm.quh.project.simpleblog.service.api;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.AnthologyAdditionalInfo;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.Anthology;
import com.quhxuxm.quh.project.simpleblog.domain.view.AnthologyDetail;
import com.quhxuxm.quh.project.simpleblog.service.exception.ServiceException;

import java.util.List;

public interface IAnthologyService {
    void create(Anthology anthology) throws ServiceException;

    void update(Anthology anthology) throws ServiceException;

    Anthology getAnthology(long id) throws ServiceException;

    AnthologyDetail getAnthologyDetail(long id) throws ServiceException;

    List<AnthologyDetail> getAnthologySummaries(long authorId, int start, boolean isDesc) throws ServiceException;

    AnthologyAdditionalInfo getAdditionalInfo(long anthologyId) throws ServiceException;
}
