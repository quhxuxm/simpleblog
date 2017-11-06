package com.tongwen.service.impl;

import com.tongwen.domain.Image;
import com.tongwen.service.api.IImageService;
import com.tongwen.service.exception.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class ImageService implements IImageService {
    
    @Override
    public Image load(long id) throws ServiceException {
        return null;
    }
}
