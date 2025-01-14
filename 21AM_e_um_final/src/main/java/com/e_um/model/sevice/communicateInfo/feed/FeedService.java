package com.e_um.model.sevice.communicateInfo.feed;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_um.model.dao.communicateInfo.feed.FeedDaoInterface;
import com.e_um.model.sevice.userInfo.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FeedService implements FeedServiceInterface {
	@Autowired
	FeedDaoInterface dao;
	@Autowired
	SqlSessionTemplate session;
}
