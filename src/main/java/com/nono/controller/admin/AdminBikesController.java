package com.nono.controller.admin;

import com.nono.model.Bike;
import com.nono.model.BikeRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/admin/bikes")
public class AdminBikesController {

    @Autowired
    BikeRepository bikeRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView indexAction(Bike bike) {
        ModelAndView mav = new ModelAndView("admin/bikes");
        mav.addObject("bikes", bikeRepository.findAll());
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addView(Bike bike) {
        return "admin/newbike";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addAction(@Valid Bike bike, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/newbike";
        } else {
            bikeRepository.save(bike);
            redirectAttributes.addFlashAttribute("success", "You successfully added new bike.");
            return "redirect:/admin/bikes";
        }
    }

    @RequestMapping(value = "/edit/{bike}", method = RequestMethod.GET)
    public String editView(@PathVariable Bike bike, Model model) {
        model.addAttribute("bikeId", bike.getId());
        return "admin/editbike";
    }

    @RequestMapping(value = "/edit/{bikeId}", method = RequestMethod.POST)
    public String editAction(@PathVariable Long bikeId, @Valid Bike bike, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bikeId", bikeId);
            return "admin/editbike";
        } else {
            Bike editBike = bikeRepository.findOne(bikeId);
            editBike.setName(bike.getName());
            editBike.setDescription(bike.getDescription());
            editBike.setImage(bike.getImage());
            bikeRepository.save(editBike);
            redirectAttributes.addFlashAttribute("success", "You successfully changed bike.");
            return "redirect:/admin/bikes";
        }
    }

    @RequestMapping(value = "/delete/{bike}", method = RequestMethod.GET)
    public String removeView(@PathVariable Bike bike, RedirectAttributes redirectAttributes) {
        return "admin/deletebike";
    }

    @RequestMapping(value = "/delete/{bike}", method = RequestMethod.POST)
    public String removeAction(@PathVariable Bike bike, RedirectAttributes redirectAttributes) {
        bikeRepository.delete(bike);
        redirectAttributes.addFlashAttribute("success", "You successfully removed bike.");
        return "redirect:/admin/bikes";
    }
}
