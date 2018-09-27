package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Team;
import pl.coderslab.repository.TeamRepository;
import java.util.List;

@Service
public class TeamService {
    @Autowired
    TeamRepository teamRepository;

    public List<Team> findAll() {
        return teamRepository.findAll();
    }
}
