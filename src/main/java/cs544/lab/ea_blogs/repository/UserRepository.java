package cs544.lab.ea_blogs.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs544.lab.ea_blogs.model.User;


@Repository
@Transactional
@Cacheable
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);
}