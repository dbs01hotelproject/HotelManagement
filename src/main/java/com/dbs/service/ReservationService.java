package com.dbs.service;

import java.util.List;

import com.dbs.po.Reservation;

public interface ReservationService {

	// 添加预定
	public int insertReservation(Reservation reservation) throws Exception;

	// 查询预定信息
	public List<Reservation> queryReservationList(Reservation reservation) throws Exception;

	// 根据id查询预定信息
	public Reservation queryReservationById(int r_number) throws Exception;

	// 修改预定
	public int updateReservation(Reservation reservation) throws Exception;

	// 删除预定
	public int deleteReservation(String[] ids) throws Exception;
}
