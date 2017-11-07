package com.tongwen.service.api;

import com.tongwen.domain.Image;
import com.tongwen.service.exception.ServiceException;

import javax.xml.ws.Service;

public interface IImageService {
    Image load(long id) throws ServiceException;

    String md5(byte[] content) throws ServiceException;

    void create(Image image) throws ServiceException;
}
