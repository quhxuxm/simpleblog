package com.quhxuxm.quh.project.simpleblog.service.api;

import com.quhxuxm.quh.project.simpleblog.domain.Image;
import com.quhxuxm.quh.project.simpleblog.service.exception.ServiceException;

public interface IImageService {
    Image load(long id) throws ServiceException;

    String md5(byte[] content) throws ServiceException;

    Image loadByMd5(String md5) throws ServiceException;

    void create(Image image) throws ServiceException;
}
