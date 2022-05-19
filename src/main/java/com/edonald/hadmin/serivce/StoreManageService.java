package com.edonald.hadmin.serivce;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.edonald.hadmin.dto.StoreDto;
import com.edonald.hadmin.dto.StorePageCriteria;
import com.edonald.member.dto.MemberDto;

public interface StoreManageService {
	public void registerStore(StoreDto dto);
	public List<StoreDto> getStoreList();
	public int getTotalNum();
	public String joinSadmin(MemberDto dto);
	public StoreDto getStore(String store_code);
	public void updateStore(StoreDto dto);
}
