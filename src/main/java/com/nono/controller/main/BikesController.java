package com.nono.controller.main;

import com.nono.model.Bike;
import com.nono.model.BikeRepository;
import com.nono.model.Reservation;
import com.nono.model.ReservationRepository;
import com.nono.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author nono
 */
@Controller
@RequestMapping("/bikes")
public class BikesController {
    
    @Autowired
    BikeRepository bikeRepository;
    
    @Autowired
    ReservationRepository reservationRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView indexAction() {
        ModelAndView mav = new ModelAndView("bikes");
        mav.addObject("bikes", bikeRepository.findAll());
        return mav;
    }
    
    @RequestMapping(value = "/order/{bike}", method = RequestMethod.GET)
    public String editView(@PathVariable Bike bike, Model model) {
        if(!bike.getAvailable()) {
            return "error";
        }
        model.addAttribute("bikeId", bike.getId());
        return "orderbike";
    }

    @RequestMapping(value = "/order/{bike}", method = RequestMethod.POST)
    public String editAction(@PathVariable Bike bike, Model model, RedirectAttributes redirectAttributes) {
        if(!bike.getAvailable()) {
            return "error";
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Reservation reservation = new Reservation();
        reservation.setBike(bike);
        reservation.setStatus("requested");
        reservation.setUser(userRepository.findByUsername(user.getUsername()));
        
        reservationRepository.save(reservation);
        
        redirectAttributes.addFlashAttribute("success", "You successfully requested bike.");
        return "redirect:/bikes";
    }
}
