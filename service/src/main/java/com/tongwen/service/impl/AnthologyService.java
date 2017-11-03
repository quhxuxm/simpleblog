package com.tongwen.service.impl;

import com.tongwen.repository.mapper.IAnthologyMapper;
import com.tongwen.repository.mapper.IAuthorMapper;
import com.tongwen.service.api.IAnthologyService;
import com.tongwen.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Map;

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
    public void createAnthology(AnthologyEditDetail anthologyEditDetail) throws ServiceException {
        if (anthologyEditDetail.getAuthorId() == null) {
            throw new ServiceException(ServiceException.Code.ILLEGAL_STATUS);
        }
        if (!this.authorMapper.isExist(anthologyEditDetail.getAuthorId())) {
            throw new ServiceException(ServiceException.Code.AUTHOR_NOT_EXIST);
        }
        try {
            this.anthologyMapper.create(anthologyEditDetail);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Map<Long, String> getAuthorAnthologyTitles(long authorId) throws ServiceException {
        try {
            return Collections
                .unmodifiableMap(this.anthologyMapper.findAnthologyIdAndTitleByAuthorId(authorId));
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }
}
