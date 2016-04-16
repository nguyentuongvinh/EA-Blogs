package cs544.lab.ea_blogs.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs544.lab.ea_blogs.model.User;


@Repository
@Transactional
@Cacheable
public interface UserRepository extends JpaRepository<User, Integer> {
	
	List<User> findByUsername(String username);
}