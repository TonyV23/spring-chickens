package com.rungroup.web.controllers;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.models.Club;
import com.rungroup.web.service.ClubService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(final ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public String clubs(Model model) {
        List<ClubDto> clubs = clubService.findAllClubs();
        model.addAttribute("clubs", clubs);
        return "clubs-list";

    }

    @GetMapping("/clubs/new")
    public String newClub(Model model) {
        model.addAttribute("club", new Club());
        return "clubs-create";
    }

    @PostMapping("/clubs/new")
    public String newClub(@Valid @ModelAttribute("club") Club club, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("club", club);
            return "clubs-create";
        }
        clubService.saveClub(club);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClub(@PathVariable int clubId, Model model) {
        ClubDto club = clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "clubs-edit";
    }

    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable int clubId,@Valid @ModelAttribute("club") ClubDto club, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "clubs-edit";
        }
        club.setId(clubId);
        clubService.updateClub(club);
        return "redirect:/clubs";
    }
}
