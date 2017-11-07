package com.tongwen.web.controller;

import com.tongwen.domain.AnthologyDetail;
import com.tongwen.service.api.IAnthologyService;
import com.tongwen.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/anthology")
public class AnthologyController {
    private final IAnthologyService anthologyService;

    @Autowired
    public AnthologyController(IAnthologyService anthologyService) {
        this.anthologyService = anthologyService;
    }

    @GetMapping("/{anthologyId}/view")
    public ModelAndView view(
            @PathVariable("anthologyId")
                    Long anthologyId) {
        try {
            AnthologyDetail anthologyDetail= this.anthologyService.getAnthologyDetail(anthologyId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ModelAndView("anthology");
    }

    @PostMapping("/create")
    public ModelAndView create(HttpSession session) {
        return null;
    }

    @PostMapping("/{anthologyId}/update")
    public ModelAndView update(
            @PathVariable("anthologyId")
                    Long anthologyId, HttpSession session) {
        return null;
    }
}
