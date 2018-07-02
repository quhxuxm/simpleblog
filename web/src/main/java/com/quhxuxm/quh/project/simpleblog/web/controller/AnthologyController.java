package com.quhxuxm.quh.project.simpleblog.web.controller;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.AnthologyAdditionalInfo;
import com.quhxuxm.quh.project.simpleblog.domain.Author;
import com.quhxuxm.quh.project.simpleblog.service.api.IAnthologyService;
import com.quhxuxm.quh.project.simpleblog.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/anthology")
public class AnthologyController {
    private final IAnthologyService anthologyService;

    @Autowired
    public AnthologyController(IAnthologyService anthologyService) {
        this.anthologyService = anthologyService;
    }

    @GetMapping("/{anthologyId}/view")
    public ModelAndView view(@PathVariable("anthologyId") Long anthologyId, HttpSession session)
        throws Exception {
        ModelAndView result = new ModelAndView("anthology");
        AnthologyDetail anthologyDetail = this.anthologyService.getAnthologyDetail(anthologyId);
        AnthologyAdditionalInfo anthologyAdditionalInfo =
            this.anthologyService.getAdditionalInfo(anthologyDetail.getId());
        result.addObject("anthologyDetail", anthologyDetail);
        result.addObject("anthologyAdditionalInfo", anthologyAdditionalInfo);
        Author authorInSession =
            (Author) session.getAttribute(IConstant.ISessionAttributeName.AUTHENTICATED_AUTHOR);
        if (authorInSession == null) {
            result.addObject("isAnthologyBelongToAuthor", false);
            return result;
        }
        if (!anthologyDetail.getAuthorId().equals(authorInSession.getId())) {
            result.addObject("isAnthologyBelongToAuthor", false);
            return result;
        }
        result.addObject("isAnthologyBelongToAuthor", true);
        return result;
    }

    @PostMapping("/create")
    public ModelAndView create(HttpSession session) {
        return null;
    }

    @PostMapping("/{anthologyId}/update")
    public ModelAndView update(@PathVariable("anthologyId") Long anthologyId, HttpSession session)
        throws Exception {
        return null;
    }

    @GetMapping("/authorAnthologySummariesCollection/{authorId}")
    public ModelAndView authorAnthologySummariesCollection(@PathVariable("authorId") Long authorId,
        @RequestParam(name = "start", defaultValue = "0", required = false) int start,
        @RequestParam(name = "desc", defaultValue = "true", required = false) boolean isDesc,
        HttpSession session) throws ServiceException {
        ModelAndView result = new ModelAndView("/fragment/anthology/summariesCollection");
        List<AnthologySummary> summariesCollection =
            this.anthologyService.getAnthologySummaries(authorId, start, isDesc);
        result.addObject("summariesCollection", summariesCollection);
        int summariesCollectionSize = 0;
        summariesCollectionSize = summariesCollection.size();
        int nextStart = start + summariesCollectionSize;
        result.addObject("nextStart", nextStart);
        Map<Long, AnthologyAdditionalInfo> additionalInfoMap = new HashMap<>();
        for (AnthologySummary anthologySummary : summariesCollection) {
            additionalInfoMap.put(anthologySummary.getId(),
                this.anthologyService.getAdditionalInfo(anthologySummary.getId()));
        }
        result.addObject("anthologyAdditionalInfoMap", additionalInfoMap);
        return result;
    }
}
