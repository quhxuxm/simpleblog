package com.tongwen.service.api;

import com.tongwen.domain.*;
import com.tongwen.service.exception.ServiceException;

import java.util.List;

public interface IAnthologyService {
    void create(Anthology anthology) throws ServiceException;

    void update(Anthology anthology) throws ServiceException;

    Anthology getAnthology(long id) throws ServiceException;

    AnthologyDetail getAnthologyDetail(long id) throws ServiceException;

    List<AnthologySummary> getAnthologySummaries(long authorId)
            throws ServiceException;

    AnthologyAdditionalInfo getAdditionalInfo(long anthologyId)
            throws ServiceException;
}
