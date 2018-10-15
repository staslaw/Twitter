package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;
import pl.coderslab.repository.TweetRepository;
import pl.coderslab.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TweetService {
    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    UserRepository userRepository;


    public Page<Tweet> findAll(Pageable pageable) {
        return tweetRepository.findAll(pageable);
    }



    public List<Tweet> findTweetsByUserId(Long id) {
        User user = userRepository.findOne(id);
        return tweetRepository.findAllByUser(user);
    }

    public void save(Tweet tweet, Long userId) {
        LocalDateTime dateTime= LocalDateTime.now();
        tweet.setCreated(String.valueOf(dateTime));
        tweet.setUser(userRepository.findOne(userId));
        tweetRepository.save(tweet);
    }


}