package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Comment;
import pl.coderslab.repository.CommentRepository;
import pl.coderslab.repository.TweetRepository;
import pl.coderslab.repository.UserRepository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    UserRepository userRepository;

    public void saveComment(String text, Long userId, Long tweetId) {
        Comment comment = new Comment();
        comment.setText(text);
        comment.setUser(userRepository.findOne(userId));
        comment.setTweet(tweetRepository.findOne(tweetId));
        Date date = Date.valueOf(LocalDate.now());
        Time time = Time.valueOf(LocalTime.now());
        comment.setCreatedDate(date);
        comment.setCreatedTime(time);
        commentRepository.save(comment);
    }

    public List<Comment> findAllByTweet(Long id) {
        return commentRepository.findAllByTweet(tweetRepository.findOne(id));
    }
}
