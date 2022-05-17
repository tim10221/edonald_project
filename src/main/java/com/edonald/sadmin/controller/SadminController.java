package com.edonald.sadmin.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edonald.hadmin.dto.MenuDto;
import com.edonald.hadmin.dto.StoreDto;
import com.edonald.member.dto.MemberDto;
import com.edonald.member.dto.SecurityUser;
import com.edonald.order.dto.OrderListDto;
import com.edonald.sadmin.service.SadminMenuService;
import com.edonald.sadmin.service.SadminService;

@Controller
public class SadminController {
	
	@Autowired
	private SadminMenuService sService;
	@Autowired
	private SadminService service;
	
	@RequestMapping( value = "/sadmin/test" , method = RequestMethod.GET)
	public String testMenu(Model model,String type,String store) {
		model.addAttribute("list",sService.listAll(type));
		model.addAttribute("store",store);
		return "admin/sadmin/menu/burgerNset";
	}
	
	@RequestMapping( value = "/sadmin/index", method = RequestMethod.GET)
	public String sadminHome(HttpSession session,Authentication authentication) {
		SecurityUser user = (SecurityUser)authentication.getPrincipal();
		MemberDto member = user.getMemberDto();
		int store_code = member.getStore_code();
		member.setDeliverStore(service.getStore(store_code));
		return "admin/sadmin/index";
	}
	
	@RequestMapping(value = "/sadmin/menu", method = RequestMethod.GET)
	public String sadminMenu(Model model,String type, Authentication authentication) {
		SecurityUser user = (SecurityUser)authentication.getPrincipal();
		MemberDto sessionDto = (MemberDto) user.getMemberDto();
		String store_code = Integer.toString(sessionDto.getStore_code());
		List<Integer> listBlock = sService.listBlock(store_code);
		List<MenuDto> listAll = sService.listAll(type);
		for(Integer seq : listBlock) {
			for(MenuDto dto : listAll) {
				if(dto.getSeq() == seq) {
					dto.setBlock_status(1);
					break;
				}
			}
		}
		model.addAttribute("list",listAll);
		model.addAttribute("type",type);
		return "admin/sadmin/menu/burgerNset";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/sadmin/blockMenu.do", method = RequestMethod.GET)
	public void sadminBlockList(Authentication authentication,@RequestParam("seq") String seq) {
		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		MemberDto sessionDto = (MemberDto) user.getMemberDto();
		String store = Integer.toString(sessionDto.getStore_code());
		Map<String, String> map = new HashMap<String, String>();
		map.put("store", store);
		map.put("seq", seq);
		sService.insertBlock(map);
	}
	@ResponseBody
	@RequestMapping(value = "/sadmin/blockMenu.do", method = RequestMethod.DELETE)
	public void sadminUnBlockList(Authentication authentication,@RequestParam("seq") String seq) {
		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		MemberDto sessionDto = (MemberDto) user.getMemberDto();
		String store = Integer.toString(sessionDto.getStore_code());
		Map<String, String> map = new HashMap<String, String>();
		map.put("store", store);
		map.put("seq", seq);
		sService.deleteBlock(map);
	}
	
	@ResponseBody
	@RequestMapping(value = "/sadmin/storeStatus.do", method = RequestMethod.GET)
	public void sadminStoreStatus(Authentication authentication,@RequestParam("store_status") String store_status) {
		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		MemberDto sessionDto = (MemberDto) user.getMemberDto();
		String store_code = Integer.toString(sessionDto.getStore_code());
		Map<String, String> map = new HashMap<String, String>();
		map.put("store_status", store_status);
		map.put("store_code", store_code);
		service.updateStoreStatus(map);
	}
}
