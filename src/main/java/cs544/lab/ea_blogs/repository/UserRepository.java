package cs544.lab.ea_blogs.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs544.lab.ea_blogs.model.User;


@Repository
@Transactional
@Cacheable
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);
	
	@Modifying
	@Query("UPDATE User u SET u.photo=:photo WHERE u.id=:id")
	int updatePhotoByUserId(@Param("id") Integer id, @Param("photo") byte[] photo);
}