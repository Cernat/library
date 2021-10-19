package org.internship.library.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path="redirect")
public class RedirectController {

    /**
     * Redirect to external URL
     * @param model Used to build URL attributes
     * @return The external URL
     */
    @GetMapping("/redirectPrefix")
    public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model) {
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:https://youtube.com", model);
//        return new ModelAndView("redirect:/https://youtube.com", model);
    }

    /**
     * Redirect to external URL
     * @param httpServletResponse HTTP response
     */
    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public void method(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", "https://google.com");
        httpServletResponse.setStatus(302);
    }

    /**
     * Redirect to external URL
     * @return The external URL
     */
    @RequestMapping(value = "/redirect/two", method = RequestMethod.GET)
    public ModelAndView method() {
        return new ModelAndView("redirect:" + "https://msn.com");
    }
}
