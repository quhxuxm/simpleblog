package com.tongwen.service.api;

import com.tongwen.domain.Anthology;
import com.tongwen.domain.Author;
import com.tongwen.service.exception.ServiceException;
import org.springframework.stereotype.Service;

public interface IAnthologyService {
    void createAnthology(long authorId, Anthology anthology)
            throws ServiceException;
}
