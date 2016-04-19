package cs544.lab.ea_blogs.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cs544.lab.ea_blogs.repository.ArticleRepository;
import cs544.lab.ea_blogs.repository.UserRepository;


@Service
public class InitDataService {

	private static final Logger logger = LoggerFactory.getLogger(InitDataService.class);
	
	@Value("${read.image.path}")
	private String READ_IMG_PATH;
	
	@Value("${read.image.suffix}")
	private String READ_IMG_SUFFIX;
	
	@Value("${user.image.prefix}")
	private String USER_IMG_PREFIX;
	
	@Value("${article.image.prefix}")
	private String ARTICLE_IMG_PREFIX;
	
	@Resource
	private ArticleRepository articleRepository;
	
	@Resource
	private UserRepository userRepository;

	@PostConstruct
	@Transactional
	public void init(){
		
		logger.info("Initialize image data...");
		int userCount = (int) userRepository.count();
		for (int i=1; i<=userCount; i++){
			userRepository.updatePhotoByUserId(i, readPic(USER_IMG_PREFIX + i + READ_IMG_SUFFIX));
		}
		
		int articleCount = (int) articleRepository.count();
		for (int i=1; i<=articleCount; i++){
			articleRepository.updateImageByArticleId(i, readPic(ARTICLE_IMG_PREFIX + i + READ_IMG_SUFFIX));
		}
		
		logger.info("update and flush...");
		userRepository.flush();		
		
	}
	
	
	private byte[] readPic(String filename) {
		InputStream input = null;
		try {
			input = (new ClassPathResource(READ_IMG_PATH + filename)).getInputStream();
		} catch (IOException e1) {
			logger.error("readPic ERROR: filename: {}", filename);
		}
	    ByteArrayOutputStream output = new ByteArrayOutputStream();
	    byte[] buffer = new byte[4096];
	    int n;

	    try {
			while ((n=input.read(buffer))>0) {
			    output.write(buffer, 0, n);
			}
		} catch (IOException e) {
			logger.error("readPic ERROR: filename: {}", filename);
		}
	    return output.toByteArray();
	}
}
