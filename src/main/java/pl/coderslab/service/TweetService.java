package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;
import pl.coderslab.repository.TweetRepository;
import pl.coderslab.repository.UserRepository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class TweetService {
    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    UserRepository userRepository;


    public Page<Tweet> findAll(Pageable pageable) {
        return tweetRepository.findAllByOrderByIdDesc(pageable);
    }

    public List<Tweet> findTweetsByUserId(Long id) {
        User user = userRepository.findOne(id);
        return tweetRepository.findAllByUser(user);
    }

    public void save(Tweet tweet, Long userId) {
        Date date= Date.valueOf(LocalDate.now());
        Time time = Time.valueOf(LocalTime.now());
        tweet.setCreatedDate(date);
        tweet.setCreatedTime(time);
        tweet.setUser(userRepository.findOne(userId));
        tweetRepository.save(tweet);
    }

    public Tweet findOneById(Long id){
        return tweetRepository.findOne(id);
    }



}
