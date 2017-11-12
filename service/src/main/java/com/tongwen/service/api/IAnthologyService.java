package com.tongwen.service.api;

import com.tongwen.domain.Anthology;
import com.tongwen.domain.AnthologyAdditionalInfo;
import com.tongwen.domain.AnthologyDetail;
import com.tongwen.domain.AnthologySummary;
import com.tongwen.service.exception.ServiceException;

import java.util.List;

public interface IAnthologyService {
    void create(Anthology anthology) throws ServiceException;

    void update(Anthology anthology) throws ServiceException;

    Anthology getAnthology(long id) throws ServiceException;

    AnthologyDetail getAnthologyDetail(long id) throws ServiceException;

    List<AnthologySummary> getAnthologySummaries(long authorId, int start, boolean isDesc)
        throws ServiceException;

    AnthologyAdditionalInfo getAdditionalInfo(long anthologyId) throws ServiceException;
}
