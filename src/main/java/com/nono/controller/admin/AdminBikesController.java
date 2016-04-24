package com.nono.controller.admin;

import com.nono.model.Bike;
import com.nono.model.BikeRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author nono
 */
@Controller
@RequestMapping("/admin")
public class AdminBikesController {
    
    @Autowired
    BikeRepository bikeRepository;
    
    @RequestMapping("/bikes")
    public ModelAndView indexAction(Bike bike) {
        ModelAndView mav = new ModelAndView("admin/bikes");
        mav.addObject("bikes", bikeRepository.findAll());
        return mav;
    }
    
    @RequestMapping(value="/bikes/add", method=RequestMethod.GET)
    public String newAction(Bike bike) {
        return "admin/newbike";
    }
    
    @RequestMapping(value="/bikes/add", method=RequestMethod.POST)
    public String saveAction(@Valid Bike bike, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()) {
            return "admin/newbike";
        } else {
            bikeRepository.save(bike);
            redirectAttributes.addFlashAttribute("success", "You successfully added new bike.");
            return "redirect:/admin/bikes";
        }
    }
}
