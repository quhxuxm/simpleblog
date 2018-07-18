package com.quhxuxm.quh.project.simpleblog.service.api;

import com.quhxuxm.quh.project.simpleblog.domain.Anthology;
import com.quhxuxm.quh.project.simpleblog.domain.Author;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.*;

public interface IAnthologyService {
    Long saveAnthology(CreateAnthologyDTO createAnthologyDTO)
            throws ServiceException;

    void assignTagsToAnthology(AnthologyAssignTagsDTO anthologyAssignTagsDTO)
            throws ServiceException;

    void bookmarkAnthology(AnthologyBookmarkDTO anthologyBookmarkDTO)
            throws ServiceException;

    void praiseAnthology(AnthologyPraiseDTO anthologyPraiseDTO)
            throws ServiceException;

    AnthologyDetailDTO viewAnthology(AnthologyViewDTO anthologyViewDTO)
            throws ServiceException;

    void increaseAuthorTagWeightAccordingToAnthologyTags(Author author,
            Anthology anthology) throws ServiceException;
}
