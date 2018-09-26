package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.entity.Team;
import pl.coderslab.repository.TeamRepository;

public class TeamConverter implements Converter<String, Team> {
    @Autowired
    TeamRepository teamRepository;

    @Override
    public Team convert(String s) {
        return teamRepository.findOne(Long.valueOf(s));
    }
}
