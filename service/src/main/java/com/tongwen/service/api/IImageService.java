package com.tongwen.service.api;

import com.tongwen.domain.Image;
import com.tongwen.service.exception.ServiceException;

public interface IImageService {
    Image load(long id) throws ServiceException;
}
