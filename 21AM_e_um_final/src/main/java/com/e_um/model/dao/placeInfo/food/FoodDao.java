package com.e_um.model.dao.placeInfo.food;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.e_um.model.sevice.userInfo.user.UserService;
import com.e_um.model.vo.placeinfo.food.food.Food;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class FoodDao implements FoodDaoInterface {
	
	@Override
	public List<Food> selectAllFood(SqlSessionTemplate session) {
		return session.selectList("food.selectAllFood");
	}

	@Override
	public int foodInsert(SqlSessionTemplate session, Food food) {
		return session.insert("food.foodInsert", food);
	}

	@Override
	public List<String> selectFoodCategoryMain(SqlSessionTemplate session) {
		return session.selectList("food.selectFoodCategoryMain");
	}

	@Override
	public List<String> selectFoodCategorySub(SqlSessionTemplate session) {
		return session.selectList("food.selectFoodCategorySub");
	}

}
