package com.nono.controller.main;

import com.nono.model.Reservation;
import com.nono.model.ReservationRepository;
import com.nono.model.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView indexAction() {
        ModelAndView mav = new ModelAndView("reservations");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        mav.addObject("reservations", reservationRepository.findByUser(userRepository.findByUsername(user.getUsername())));
        return mav;
    }
    
    @RequestMapping(value = "/resign/{reservationId}", method = RequestMethod.GET)
    public String returnAction(@PathVariable Long reservationId, RedirectAttributes redirectAttributes) {
        Reservation editReservation = reservationRepository.findOne(reservationId);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(userRepository.findByUsername(user.getUsername()).getId() != editReservation.getUser().getId()) {
            return "error";
        }

        editReservation.setStatus("resigned");
        editReservation.getBike().setAvailable(Boolean.TRUE);
        reservationRepository.save(editReservation);
        redirectAttributes.addFlashAttribute("success", "You successfully resigned from order.");
        return "redirect:/reservations";
    }
}
