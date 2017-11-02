package com.tongwen.service.impl;

import com.tongwen.domain.Anthology;
import com.tongwen.repository.mapper.IAnthologyMapper;
import com.tongwen.repository.mapper.IAuthorMapper;
import com.tongwen.service.api.IAnthologyService;
import com.tongwen.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnthologyService implements IAnthologyService {
    private final IAuthorMapper authorMapper;
    private final IAnthologyMapper anthologyMapper;

    @Autowired
    public AnthologyService(IAuthorMapper authorMapper,
            IAnthologyMapper anthologyMapper) {
        this.authorMapper = authorMapper;
        this.anthologyMapper = anthologyMapper;
    }

    @Transactional
    @Override
    public void createAnthology(long authorId, Anthology anthology)
            throws ServiceException {
        if (!this.authorMapper.isExist(authorId)) {
            throw new ServiceException(ServiceException.Code.AUTHOR_NOT_EXIST);
        }
        anthology.setAuthorId(authorId);
        try {
            this.anthologyMapper.create(anthology);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }
}
