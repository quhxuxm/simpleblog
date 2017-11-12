package com.tongwen.service.impl;

import com.tongwen.domain.Anthology;
import com.tongwen.domain.AnthologyAdditionalInfo;
import com.tongwen.domain.AnthologyDetail;
import com.tongwen.domain.AnthologySummary;
import com.tongwen.repository.mapper.IAnthologyMapper;
import com.tongwen.repository.mapper.IAuthorMapper;
import com.tongwen.service.api.IAnthologyService;
import com.tongwen.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnthologyService implements IAnthologyService {
    private final IAuthorMapper authorMapper;
    private final IAnthologyMapper anthologyMapper;
    @Value("${anthology.summariesCollection.pageSize}")
    private int anthologySummariesCollectionPageSize;

    @Autowired
    public AnthologyService(IAuthorMapper authorMapper, IAnthologyMapper anthologyMapper) {
        this.authorMapper = authorMapper;
        this.anthologyMapper = anthologyMapper;
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public void create(Anthology anthology) throws ServiceException {
        if (anthology.getAuthorId() == null) {
            throw new ServiceException(ServiceException.Code.AUTHOR_NOT_ASSIGNED);
        }
        if (!this.authorMapper.isExist(anthology.getAuthorId())) {
            throw new ServiceException(ServiceException.Code.AUTHOR_NOT_EXIST);
        }
        try {
            AnthologyAdditionalInfo additionalInfo = new AnthologyAdditionalInfo();
            this.anthologyMapper.createAdditionalInfo(additionalInfo);
            anthology.setAdditionalInfoId(additionalInfo.getId());
            this.anthologyMapper.create(anthology);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
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

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    @Override
    public List<AnthologySummary> getAnthologySummaries(long authorId, int start, boolean isDesc)
        throws ServiceException {
        try {
            return this.anthologyMapper.getAuthorAnthologySummaries(authorId, start,
                this.anthologySummariesCollectionPageSize, isDesc);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Override
    public AnthologyAdditionalInfo getAdditionalInfo(long anthologyId) throws ServiceException {
        try {
            return this.anthologyMapper.getAdditionalInfo(anthologyId);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }
}
