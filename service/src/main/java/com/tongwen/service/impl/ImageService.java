package com.tongwen.service.impl;

import com.tongwen.domain.Image;
import com.tongwen.repository.mapper.IImageMapper;
import com.tongwen.service.api.IImageService;
import com.tongwen.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class ImageService implements IImageService {
    private static final Logger logger = LoggerFactory
            .getLogger(ImageService.class);
    private final IImageMapper imageMapper;

    @Autowired
    public ImageService(IImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public Image load(long id) throws ServiceException {
        try {
            return this.imageMapper.findById(id);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Override
    public String md5(byte[] content) throws ServiceException {
        MessageDigest md5Generator = null;
        try {
            md5Generator = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error(
                    "Fail to convert image content to md5 because of exception.",
                    e);
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
        md5Generator.update(content);
        return new BigInteger(1, md5Generator.digest()).toString(16);
    }

    @Transactional(readOnly = true)
    @Override
    public Image loadByMd5(String md5) throws ServiceException {
        try {
            return this.imageMapper.findByMd5(md5);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public void create(Image image) throws ServiceException {
        try {
            this.imageMapper.create(image);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }
}
