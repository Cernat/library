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
     * "redirect" prefix is injected into the controller
     * when view name is returned with "redirect" prefix - UrlBasedViewResolver will recognize as a special indication
     * when we use the logical view with "redirect:/" - we're doing a redirect to current Servlet context
     * In browser will receive a 302 HTTP which means "Found" and redirect to the given URL
     * @param model Used to build URL attributes
     * @return The external URL
     */
    @GetMapping("/1")
    public ModelAndView redirectPrefix(ModelMap model) {
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:https://youtube.com", model);
//        return new ModelAndView("redirect:/https://youtube.com", model);
    }

    /**
     * HttpServletResponse will be injected and used to create an HTTP response
     * In the method below we do create a HTTP response with a header parameter called "Location" and URL to redirect
     * And also we do add a setStatus which set the HTTP verb to 302-Found in order to redirect to that URL
     * @param httpServletResponse HTTP response
     */
    @RequestMapping(value = "/2", method = RequestMethod.GET)
    public void method(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", "https://google.com");
        httpServletResponse.setStatus(302);
    }

    /**
     * ModelAndView interface allows us to pass all the information required in one return
     * With the "redirect" prefix we managed to return a 302 HTTP and redirect to specified URL
     * @return The external URL
     */
    @RequestMapping(value = "/3", method = RequestMethod.GET)
    public ModelAndView method() {
        return new ModelAndView("redirect:" + "https://msn.com");
    }
}
