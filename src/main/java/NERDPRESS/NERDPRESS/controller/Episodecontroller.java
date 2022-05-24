package NERDPRESS.NERDPRESS.controller;


import NERDPRESS.NERDPRESS.Domain.episode;
import NERDPRESS.NERDPRESS.service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Episodecontroller {

    EpisodeService service;
    private NERDPRESS.NERDPRESS.Domain.episode episode;

    //스프링 빈 넣는곳
    @Autowired
    public Episodecontroller(EpisodeService service) {
        this.service = service;
    }

    @GetMapping("/write")
    public String write() {
        return "episode/createEpisode";
    }

    @PostMapping("/write")
    public String writeform(@ModelAttribute Model model) {

        System.out.println(model);
        model.addAttribute("episode",model);

        //DB저장
        service.join(episode);
        
        return "episode/listEpisode";
    }
}
