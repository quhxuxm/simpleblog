package com.tongwen.web.controller;

import com.tongwen.domain.Image;
import com.tongwen.service.api.IImageService;
import com.tongwen.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ImageController {
    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
    private final IImageService imageService;

    public ImageController(IImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/dimage/{imageId}")
    public void loadImage(@PathVariable("imageId") Long imageId, HttpServletResponse response) {
        if (imageId == null) {
            logger.error("Can not load the image because of the image id is null.");
            return;
        }
        try {
            Image image = this.imageService.findById(imageId);
            if (image == null) {
                logger.error(
                    "Can not load the image because of the image id is not exist [id = " + imageId
                        + "].");
                return;
            }
            response.setContentType(image.getType());
            response.getOutputStream().write(image.getContent());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (ServiceException | IOException e) {
            logger.error("Can not load the image because of exception.", e);
            return;
        }
    }
}
