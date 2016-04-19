package cs544.lab.ea_blogs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cs544.lab.ea_blogs.model.User;
import cs544.lab.ea_blogs.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User findByUsername(String userName) {
		return userRepository.findByUsername(userName);
	}
	
	public void saveOrUpdate(User user) {
		userRepository.save(user);
	}
}
