package com.nono.controller.main;

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
public class ReservationsController {
    
    @Autowired
    BikeRepository bikeRepository;
    
    @RequestMapping("/reservation")
    public ModelAndView indexAction() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("bikes", bikeRepository.findAll());
        return mav;
    }
}
