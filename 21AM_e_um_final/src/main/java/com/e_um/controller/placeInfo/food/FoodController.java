package com.e_um.controller.placeInfo.food;

import static com.e_um.common.renamePolicy.RenamePolicy.renamepolicy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.e_um.model.sevice.placeInfo.food.FoodServiceInterface;
import com.e_um.model.vo.placeinfo.food.food.Food;
import com.e_um.model.vo.placeinfo.food.menu.FoodMenu;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FoodController {

	@Autowired
	FoodServiceInterface service;
	
	@RequestMapping("/food/foodMain")
	public String food(Model m) {
		
		List<Food> list = service.selectAllFood();
		
		m.addAttribute("list", list);
		
		return "food";
//		return "food/foodForm";
	}
	
	
	@RequestMapping("/food/foodForm/start")
	public String foodFormStart(Model model) {
		
		// 카테고리 대분류, 중분류 데이터 가져오기 
		List<String> CategoryMainList = service.selectFoodCategoryMain();
		List<String> CategorySubList = service.selectFoodCategorySub();
				
		model.addAttribute("CategoryMainList", CategoryMainList);
		model.addAttribute("CategorySubList", CategorySubList);
		
		return "food/foodForm";
	}
	
	@RequestMapping("/food/foodForm/end")
	public String foodInsert(HttpServletRequest rq, Food food, String[] menuName, 
								String[] menuPrice, MultipartFile[] menuPhoto, Model model) {
				 
		FoodMenu foodMenu;
		List<FoodMenu> menuList = new ArrayList<>();
		
		for(int i=0; i<menuName.length; i++) {
		
//			log.warn("{}",menuName[i]);
//			log.warn("{}",menuPrice[i]);
//			log.warn("{}",menuPhoto[i].getOriginalFilename());
			
			foodMenu = new FoodMenu();
					 
			if(!menuName[i].equals("")) {
				foodMenu.setMenuName(menuName[i]);
			}
			if(!menuPrice[i].equals("")) {
				foodMenu.setMenuPrice(Integer.parseInt(menuPrice[i]));
			}
			if(!menuPhoto[i].getOriginalFilename().equals("")) {
				foodMenu.setMenuPhoto(renamepolicy(rq, menuPhoto[i], "food"));
			}
					 
			food.getMenus().add(foodMenu);
			menuList.add(foodMenu);
		}
				 
				 // System.out.println("test, food : " + food);
		
		int result = service.foodInsert(food);
		
		model.addAttribute("msg", result > 0 ? "맛집등록성공" : "맛집등록실패");
		model.addAttribute("loc", "/food/foodMain");
		
		return "/common/msg";
	}
	
	
	
	@RequestMapping("/food/foodModal")
	@ResponseBody
	// public String openFoodModal(HttpServletRequest req, HttpServletRequest res) throws Exception {
	public Food openFoodModal(String foodSeq) throws Exception {
		
		// log.warn("{}", req.getParameter("foodSeq"));
				// log.warn("{}", foodSeq);
		
		Food food = service.selectFood(foodSeq);
		
				// log.warn("{}", food);
		
		// return "success!!!";
		return food;
		
	}
	

	@RequestMapping("/food/foodView")
	public String foodView(String foodSeq, Model model) throws Exception {
		
		Food food = service.selectFood(foodSeq);
		
		model.addAttribute("food", food);
		
		return "/food/foodView";
		
	}
	
	@RequestMapping("/food/foodReview/start")
	public String foodReview(String foodSeq, Model model) {
		
		Food food = service.selectFood(foodSeq);
		
		model.addAttribute("food", food);
		
		return "/food/foodReview";
	}
	
	@RequestMapping("/food/foodReview/end")
	public String insertFoodComment(String foodSeq) {
		
		// 로그인한 유저아이디, 리뷰내용 받기 
		
//		int result = service.insertFoodComment();
		
		// msg.jsp 반환
		return "";
	}
	
	@RequestMapping("/food/foodBooking/start")
	public String foodBookingStart(String foodSeq, Model model) throws ParseException {
		
		Food food = service.selectFood(foodSeq);
		
		// 문자열
		String timeStr1 = food.getFoodTimeFirst();
		String timeStr2 = food.getFoodTimeLast();
		
		Date date1 = new SimpleDateFormat("HH:mm").parse(timeStr1);
		Date date2 = new SimpleDateFormat("HH:mm").parse(timeStr2);
	
		long diff = date2.getTime() - date1.getTime();
		
		long result = diff / 60000;
		
			System.out.println("영업시간(분) : " + result);
			System.out.println("영업시간(시간) : " + result/60);
			
		List<String> timeList  = new ArrayList();
		
		long lData = 0;
		
		lData = date1.getTime();
		
			
		for(int i=0; i < result/30; i++) {
			
			
			Date dData = new Date(lData);
			
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		
			String sData = sdf.format(dData);
			
				System.out.println("30분 후 시간 : " + sData);
				
			timeList.add(sData);
		
			lData += 1800000;
		}
		
//		model.addAttribute("foodTime", result);
//		model.addAttribute("date1", date1);
//		model.addAttribute("date2", date2);
		
		model.addAttribute("realTimeList", timeList);
		
		model.addAttribute("food", food);
		
		return "/food/foodBooking";
	}
	
	@RequestMapping("/food/foodBooking/end")
	public String foodBookingEnd() {
		
		return "";
	}
	
}
