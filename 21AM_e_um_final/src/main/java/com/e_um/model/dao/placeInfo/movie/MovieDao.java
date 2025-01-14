package com.e_um.model.dao.placeInfo.movie;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.e_um.model.sevice.userInfo.user.UserService;
import com.e_um.model.vo.placeinfo.movie.movie.Movie;
import com.e_um.model.vo.placeinfo.movie.personInfo.MoviePersonInfo;
import com.e_um.model.vo.placeinfo.movie.review.MovieReview;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MovieDao implements MovieDaoInterface {
	@Override
	public List<Movie> movieList(SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectList("movie.movieList");
	}

	@Override
	public Movie moviePoster(String movieSeq, SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectOne("movie.moviePoster",movieSeq);
	}

	@Override
	public Movie movieVideo(SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectOne("movie.movieVideo");
	}

	@Override
	public Movie movieDetail(String movieSeq, SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectOne("movie.movieDetail",movieSeq);
	}

	@Override
	public List<Movie> movieSearch(String search, SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectList("movie.movieSearch",search);
	}

	@Override
	public MoviePersonInfo moviePerson(String person, SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectOne("movie.moviePerson",person);
	}

	@Override
	public List<MovieReview> movieReview(String movieSeq, SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectList("movie.movieReview",movieSeq);
	}

	@Override
	public int movieWrite(Map param, SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.insert("movie.movieWrite",param);
	}

	@Override
	public int movieReviewCount(SqlSessionTemplate session,String movieSeq) {
		// TODO Auto-generated method stub
		return session.selectOne("movie.movieReviewCount",movieSeq);
	}
	
	
	
	
	
	
	
}
