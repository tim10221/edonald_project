package com.edonald.hadmin.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.edonald.hadmin.dao.AdminMenuMapper;
import com.edonald.hadmin.dto.MenuDto;
@Service
public class AdminMenuServiceImpl implements AdminMenuService {
	
	@Autowired
	private AdminMenuMapper mapper;
	
	@Override
	public List<MenuDto> listAll() {
		return mapper.listAll();
	}
	
	@Override
	public MenuDto getMenu(String seq) {
		return mapper.getMenu(seq);
	}

	@Override
	public void insert(MenuDto dto) {
		// TODO Auto-generated method stub
		mapper.insert(dto);
	}
	@Override
	public void updateStatus(String code,int status) {
		mapper.updateStatus(code, status);
	}
	
	@Override
	public void update(MenuDto dto) {
		mapper.update(dto);
	}
	
	@Override
	public MenuDto read(int bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modify(MenuDto dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int bno) {
		// TODO Auto-generated method stub

	}
	
}