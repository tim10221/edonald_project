package com.edonald.order.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edonald.hadmin.dto.MenuDto;
import com.edonald.order.dto.CartDto;
import com.edonald.order.dto.OrderListDto;
import com.edonald.order.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	int smallOrderCost = 2500;
	int criteriaCost = 13000;
	
	@PostMapping("/order/orderMenu")
	public ModelAndView orderMenu(MenuDto menuDto) {
		System.out.println("ordermenu controller!!");
		System.out.println("menu  " + menuDto.getName());
		ModelAndView mav = new ModelAndView();
		mav.addObject("menuDto", menuDto);
		mav.setViewName("/delivery/order/menu");
		return mav;
	}

	@PostMapping("/order/cart/add")
	public String cartAdd(CartDto cartDto, HttpSession session, Authentication authentication) {

		String menu_type = cartDto.getMenu_type();
		int productPrice = 0;
		
		if (menu_type.equals("burger")) {
			productPrice = orderService.calcPriceBurger(cartDto); //수량, 세트 여부 -> 제품가격 계산
			cartDto.setCalc_price(productPrice);
		}
		OrderListDto orderListDto = (OrderListDto) session.getAttribute("orderListDto");
		int orignTotalPrice = orderListDto.getTotal_price();
		if(orignTotalPrice == 0 && productPrice < criteriaCost) {//첫 제품추가시 배달비 2500원 플러스
			productPrice += smallOrderCost;
		}
		
		int newTotalPrice = orignTotalPrice + productPrice;
		int deliverCost = orderListDto.getDeliverCost();
		//제품을 2번이상 추가했는데 만삼천원을 처음으로 넘겼을때  2500원마이너스
		if(newTotalPrice >= criteriaCost && deliverCost == smallOrderCost && orignTotalPrice!=0) {
			newTotalPrice -= smallOrderCost;
			orderListDto.setDeliverCost(0);
		}
		
		orderListDto.setTotal_price(newTotalPrice);
		orderListDto.getCartList().add(cartDto);

		return "redirect:/ed/menuPage";
	}
	
	@GetMapping("/order/cart/del")
	public @ResponseBody OrderListDto cartDel(@RequestParam int cartIndex, HttpSession session){
		OrderListDto orderListDto = (OrderListDto) session.getAttribute("orderListDto");
		List<CartDto> cartList = orderListDto.getCartList();
		int productPrice = cartList.get(cartIndex).getCalc_price();
		int totalPrice = orderListDto.getTotal_price();
		totalPrice -= productPrice;
		int deliverCost = orderListDto.getDeliverCost();
		
		//원래금액이 만삼천 이상이었을때 제품을 삭제하면 만삼천 미만으로 떨어질 :때 2500원 +
		if(totalPrice < criteriaCost && deliverCost == 0 && totalPrice != 0) {
			totalPrice += smallOrderCost;
			orderListDto.setDeliverCost(smallOrderCost);
		}
		
		//장바구니에 제품이 없는경우
		if(totalPrice == smallOrderCost) {
			totalPrice=0;
		}

		orderListDto.setTotal_price(totalPrice);
		cartList.remove(cartIndex);
		orderListDto.setCartList(cartList);
		
		return orderListDto;
	}
	
	
	
}
