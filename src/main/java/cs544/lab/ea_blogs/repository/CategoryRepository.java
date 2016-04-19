package cs544.lab.ea_blogs.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs544.lab.ea_blogs.model.Category;



@Repository
@Transactional
@Cacheable
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	public List<Category> findAll();
}
