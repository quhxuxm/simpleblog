package com.tongwen.service.impl;

import com.tongwen.domain.Anthology;
import com.tongwen.domain.AnthologyDetail;
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
    public AnthologyService(IAuthorMapper authorMapper, IAnthologyMapper anthologyMapper) {
        this.authorMapper = authorMapper;
        this.anthologyMapper = anthologyMapper;
    }

    @Transactional
    @Override
    public void create(Anthology anthology) throws ServiceException {
        if (anthology.getAuthorId() == null) {
            throw new ServiceException(ServiceException.Code.AUTHOR_NOT_ASSIGNED);
        }
        if (!this.authorMapper.isExist(anthology.getAuthorId())) {
            throw new ServiceException(ServiceException.Code.AUTHOR_NOT_EXIST);
        }
        try {
            this.anthologyMapper.create(anthology);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Transactional
    @Override
    public void update(Anthology anthology) throws ServiceException {
        if (anthology.getAuthorId() == null) {
            throw new ServiceException(ServiceException.Code.AUTHOR_NOT_ASSIGNED);
        }
        if (!this.authorMapper.isExist(anthology.getAuthorId())) {
            throw new ServiceException(ServiceException.Code.AUTHOR_NOT_EXIST);
        }
        try {
            this.anthologyMapper.update(anthology);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Transactional
    @Override
    public Anthology getAnthology(long id) throws ServiceException {
        try {
            return this.anthologyMapper.getOne(id);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public AnthologyDetail getAnthologyDetail(long id) throws ServiceException {
        try {
            return this.anthologyMapper.getAnthologyDetail(id);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }
}
