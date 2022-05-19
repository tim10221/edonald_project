package com.edonald.hadmin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.edonald.hadmin.dto.MenuDto;
import com.edonald.hadmin.serivce.AdminMenuService;
import com.edonald.hadmin.serivce.FileUploadService;
import com.edonald.hadmin.serivce.StoreManageService;
import com.edonald.member.dto.MemberDto;
import com.edonald.member.service.MemberService;

@Controller
public class HadminController {
	@Autowired
	private AdminMenuService bService;
	@Autowired
	private FileUploadService fService; 
	@Autowired
	private StoreManageService sService;
	@Autowired
	private MemberService mService;
	
	
	@RequestMapping(value = "/hadmin/index", method = RequestMethod.GET)
	public String hadminIndex() {
		return "admin/hadmin/index2";
	}
	
// 프로모션 관리
	@RequestMapping(value = "/hadmin/promotion", method =RequestMethod.GET)
	public String hadminPromotionPage(Model model) {
		return "admin/hadmin/board/promotion";
	}
	
// 회원관리
	//page
	@RequestMapping(value = "/hadmin/userPage",method = RequestMethod.GET)
	public String hadminUserPage(Model model,String user_status) {
		if(user_status != null) {
			if(user_status.equals("0")) {
				model.addAttribute("list",mService.getMemberList(0) );
			}else {
				model.addAttribute("list",mService.getMemberList(1) );
			}
		}else {
			model.addAttribute("list",mService.getMemberList(1) );
		}
		
		return "admin/hadmin/usercheck/usercheck";
	}
	//insertPage
	@RequestMapping(value = "/hadmin/CMSadmin", method = RequestMethod.GET)
	public String hadminCreateModifySadmin(Model model,String user_email) {
		if(user_email != null) {
			model.addAttribute("user",mService.getMemberById(user_email));
		}
		return "admin/hadmin/usercheck/admincreate";
	}
	//insertMemberSadmin.do
	@ResponseBody
	@RequestMapping( value = "/hadmin/createSadmin.do",method = RequestMethod.POST)
	public String hadminCreate(MemberDto dto) {
		String msg = sService.joinSadmin(dto);
		String result = "{ \"msg\":\"" +msg+"\"}";
		return result;
	}
	// 계정 비활성화 활성화
	@ResponseBody
	@RequestMapping( value = "/hadmin/unactivity.do", method = RequestMethod.POST)
	public void hadminUnactivityMember(MemberDto dto) {
		mService.activityMemberControl(dto);
	}
	
	@ResponseBody
	@RequestMapping( value ="/hadmin/modifyMember.do",method = RequestMethod.POST)
	public void hadminModifyMember(MemberDto dto) {
		mService.changeAccountByAdmin(dto);
	}

// 메뉴 관리 
	@RequestMapping(value = "/hadmin/menu", method = RequestMethod.GET)
	public String hadminBurgerMenu(Model model,String type) {
		model.addAttribute("list",bService.listAll(type));
		model.addAttribute("type",type);
		return "admin/hadmin/menu/burgerNset2";
	}
	
	@RequestMapping(value = "/hadmin/updateStatus.do", method = RequestMethod.POST)
	public String updateStatus(String seq, int status) {
		bService.updateStatus(seq,status);
		return "admin/hadmin/menu/burgerNset2";
	}
	
	@RequestMapping(value = "/hadmin/register/menu", method = RequestMethod.GET)
	public String hadminRegisterBurger(Model model, String seq) {
		if(seq != null) {
			model.addAttribute("dto",bService.getMenu(seq));
			model.addAttribute("pageSetting","수정");
		}
		return "admin/hadmin/menu/burgerRegister";
	}
	
	@ResponseBody
	@RequestMapping(value = "/hadmin/insert", method = RequestMethod.POST)
	public String insertMenu(MenuDto dto,@RequestParam("mainUploadFile") MultipartFile mainUploadFile,@RequestParam("subUploadFile") MultipartFile subUploadFile) {
		String path = "menu/burger";
		dto.setStatus(1);
		dto.setImg_path(fService.fileUpload(mainUploadFile, path));
		if(subUploadFile.getOriginalFilename().equals("")) {
			dto.setSet_img_path("");
		}else {
			dto.setSet_img_path(fService.fileUpload(subUploadFile, path));
		}
		bService.insert(dto);
		return "등록완료";
	}
	
	@ResponseBody
	@RequestMapping(value = "/hadmin/update", method = RequestMethod.POST)
	public String updateMenu(MenuDto dto,@RequestParam("mainUploadFile") MultipartFile mainUploadFile,@RequestParam("subUploadFile") MultipartFile subUploadFile) {
		String path = "menu/burger";
		dto.setStatus(1);
		if(mainUploadFile.getOriginalFilename().equals("")) {
		}else {
			dto.setImg_path(fService.fileUpload(mainUploadFile, path));
		}
		if(subUploadFile.getOriginalFilename().equals("")) {
		}else {
			dto.setSet_img_path(fService.fileUpload(subUploadFile, path));
		}
		bService.update(dto);
		return "수정완료";
	}
	
	@ResponseBody
	@RequestMapping(value = "/hadmin/updateSubstitue.do", method = RequestMethod.POST)
	public void updateSubstitue(MenuDto dto) {
		bService.updateSubstitue(dto);
	}
	
}
