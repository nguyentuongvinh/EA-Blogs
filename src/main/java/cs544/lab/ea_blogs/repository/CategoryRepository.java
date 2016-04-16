package cs544.lab.ea_blogs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cs544.lab.ea_blogs.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	public List<Category> findAll();
}
