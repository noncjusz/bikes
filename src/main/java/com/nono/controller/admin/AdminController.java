package com.nono.controller.admin;

import com.nono.model.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author nono
 */
@Controller
public class AdminController {
    
    @Autowired
    BikeRepository bikeRepository;
    
    @RequestMapping("/admin")
    public ModelAndView indexAction() {
        ModelAndView mav = new ModelAndView("admin/index");
        mav.addObject("bikes", bikeRepository.findAll());
        return mav;
    }
}
