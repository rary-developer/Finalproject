package com.e_um.controller.communicateInfo.friend;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.e_um.model.sevice.communicateInfo.friend.FriendServiceInterface;
import com.e_um.model.vo.userInfo.user.User;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FriendController {
	
	@Autowired
	FriendServiceInterface service;
	
	
	@RequestMapping("/friend/main")
	public String friendMain(Model m, HttpServletRequest rq) {
		User user=(User)rq.getSession().getAttribute("userSession");
//		log.warn("로그인한 회원 id: {}",user.getUserId());
		m.addAttribute("list",service.selectAllUser(user.getUserId()));
//		for(User u:service.selectAllUser()) {
//			log.warn("user: {}",u);
//		}
		return "friend";
	}

	
	@RequestMapping("/friend/openmodal/start")
	public String selectUserOne(@RequestParam(value="userId", required=false) String userId, Model m) {
//		log.warn("boxId: {}",userId);
//		log.warn("boxId: {}",service.selectUserOne(userId));
		m.addAttribute("friend",service.selectUserOne(userId));
//		m.addAttribute("userId",userId);
		return "components/friend/userInfoModal";
	}
	
	
	@ResponseBody
	@RequestMapping("/friend/applyfriend/start")
	public int applyFriend(HttpServletRequest rq, @RequestParam(value="friendId", required=false) String friendId) {
		User user=(User)rq.getSession().getAttribute("userSession");
		Map param=new HashMap();
		param.put("myId", user.getUserId());
		param.put("friendId", friendId);
		return service.insertFriend(param);
	}
	
	
//	@RequestMapping("/friend/infiniteScroll")
//	public 

}
