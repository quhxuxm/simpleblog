package com.tongwen.service.api;

import com.tongwen.domain.Anthology;
import com.tongwen.service.exception.ServiceException;

public interface IAnthologyService {
    void create(Anthology anthology) throws ServiceException;

    void update(Anthology anthology) throws ServiceException;

    Anthology getAnthology(long id) throws ServiceException;
}
