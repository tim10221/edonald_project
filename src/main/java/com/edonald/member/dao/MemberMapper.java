package com.edonald.member.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edonald.hadmin.dto.StoreDto;
import com.edonald.member.dto.AddressDto;
import com.edonald.member.dto.MemberDto;
import com.edonald.order.dto.OrderListDto;

@Repository
public interface MemberMapper {
	public void joinMemberAddr(AddressDto dto);
	public void joinMemberInfo(MemberDto dto);
	public MemberDto getMemberById(String username);
	public List<AddressDto> getAddress(String username);
	public void oauthNaver(String user_email);
	public AddressDto getAddressBySeq(int address_seq);
	public List<StoreDto> getNearStoreList(AddressDto deliverAddress);
	public List<OrderListDto>getOrderList(String user_email);
	
}
