package com.nono.controller.admin;

import com.nono.model.Bike;
import com.nono.model.Reservation;
import com.nono.model.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/admin/reservations")
public class AdminReservationController {

    @Autowired
    ReservationRepository reservationRepostitory;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView indexAction(Bike bike) {
        ModelAndView mav = new ModelAndView("admin/reservations");
        mav.addObject("reservations", reservationRepostitory.findAll());
        return mav;
    }
    
    @RequestMapping(value = "/rent/{reservationId}", method = RequestMethod.GET)
    public String rentAction(@PathVariable Long reservationId, RedirectAttributes redirectAttributes) {
        Reservation editReservation = reservationRepostitory.findOne(reservationId);
        editReservation.setStatus("rented");
        editReservation.getBike().setAvailable(Boolean.FALSE);
        reservationRepostitory.save(editReservation);
        redirectAttributes.addFlashAttribute("success", "You successfully rented bike.");
        return "redirect:/admin/reservations";
    }
    
    @RequestMapping(value = "/return/{reservationId}", method = RequestMethod.GET)
    public String returnAction(@PathVariable Long reservationId, RedirectAttributes redirectAttributes) {
        Reservation editReservation = reservationRepostitory.findOne(reservationId);
        editReservation.setStatus("returned");
        editReservation.getBike().setAvailable(Boolean.TRUE);
        reservationRepostitory.save(editReservation);
        redirectAttributes.addFlashAttribute("success", "You successfully returned bike.");
        return "redirect:/admin/reservations";
    }
}
