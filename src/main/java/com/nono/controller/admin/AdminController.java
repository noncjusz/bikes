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
    public String indexAction() {
        return "redirect:/admin/bikes";
    }
}
