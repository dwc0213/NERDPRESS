package NERDPRESS.NERDPRESS.service;

import NERDPRESS.NERDPRESS.Domain.episode;
import NERDPRESS.NERDPRESS.Repository.JdbcTemplateEpisodeRepository;
import NERDPRESS.NERDPRESS.Repository.EpisodeRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EpisodeService {

    private JdbcTemplateEpisodeRepository repository;

    @Autowired
    public EpisodeService(EpisodeRepositoryInterface repository){
        this.repository = (JdbcTemplateEpisodeRepository) repository;
    }
    // 사용자 입장에서는 등록하는거기 때문에 조인이 맞다
    public void join(episode e) {
        repository.saveEpisode();
    }
}
