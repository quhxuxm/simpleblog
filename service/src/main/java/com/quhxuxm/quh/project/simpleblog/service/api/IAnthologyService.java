package com.quhxuxm.quh.project.simpleblog.service.api;

import com.quhxuxm.quh.project.simpleblog.domain.Anthology;
import com.quhxuxm.quh.project.simpleblog.domain.Author;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.*;

import java.util.Optional;
import java.util.OptionalLong;

public interface IAnthologyService {
    OptionalLong saveAnthology(CreateAnthologyDTO createAnthologyDTO)
            throws ServiceException;

    void assignTagsToAnthology(AnthologyAssignTagsDTO anthologyAssignTagsDTO)
            throws ServiceException;

    void bookmarkAnthology(AnthologyBookmarkDTO anthologyBookmarkDTO)
            throws ServiceException;

    void praiseAnthology(AnthologyPraiseDTO anthologyPraiseDTO)
            throws ServiceException;

    Optional<AnthologyDetailDTO> viewAnthology(
            AnthologyViewDTO anthologyViewDTO) throws ServiceException;

    void increaseAuthorTagWeightAccordingToAnthologyTags(Author author,
            Anthology anthology) throws ServiceException;
}
