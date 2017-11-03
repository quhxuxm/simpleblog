package com.tongwen.service.api;

import com.tongwen.service.exception.ServiceException;

import java.util.Map;

public interface IAnthologyService {
    void createAnthology(AnthologyEditDetail anthologyEditDetail) throws ServiceException;

    Map<Long, String> getAuthorAnthologyTitles(long authorId) throws ServiceException;
}
