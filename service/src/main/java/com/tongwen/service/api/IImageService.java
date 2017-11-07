package com.tongwen.service.api;

import com.tongwen.domain.Image;
import com.tongwen.service.exception.ServiceException;

public interface IImageService {
    Image load(long id) throws ServiceException;

    String md5(byte[] content) throws ServiceException;

    Image loadByMd5(String md5) throws ServiceException;

    void create(Image image) throws ServiceException;
}
