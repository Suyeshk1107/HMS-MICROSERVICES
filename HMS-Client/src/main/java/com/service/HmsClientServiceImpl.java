package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Login;


@Service
public class HmsClientServiceImpl implements HmsClientService {

//	@Autowired
//	private RestTemplate restTemplate;
	
	@Autowired
	private LoginService loginService;

	//	public List<MovieDetails> getMovieDetails(String userId) {
//		List<MovieDetails> movieDetailsList=new ArrayList<MovieDetails>();
//		
////		RatingList ratingList=restTemplate.getForObject("http://rating-service/rating/"+userId, RatingList.class);
//		RatingList ratingList=ratingService.getRatingList(userId);
//		
//		for(Rating rating:ratingList.getRatings()) {
////			Movie movie=restTemplate.getForObject("http://movie-service/movies/"+rating.getMovieId(), Movie.class);
//			Movie movie=movieService.getMovie(rating.getMovieId());
//			movieDetailsList.add(new MovieDetails(userId, movie.getMovieName(), rating.getRating()));
//		}
//		
//		return movieDetailsList;
//	}
	
	@Override
	public boolean isValid(String id, String password) {
		
		Login login= loginService.getLogin(id);
		if(password.equals(login.getPassword())) {
			return true;
		}
		
		return false;
		
	}
	
	@Override
	public boolean registerUser(String userId, String password) {
		Login login = loginService.saveLogin(userId, password);
		
		if(login.getId().equals(userId)) {
			return true;
		}
		return false;
	}
	
	
	
}