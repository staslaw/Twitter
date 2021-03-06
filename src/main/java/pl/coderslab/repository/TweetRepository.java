package pl.coderslab.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;

import java.util.List;

public interface TweetRepository extends PagingAndSortingRepository<Tweet, Long> {

    Page<Tweet> findAllByUserOrderByIdDesc(User user, Pageable pageable);

    Page<Tweet> findAllByOrderByIdDesc(Pageable pageable);

}
