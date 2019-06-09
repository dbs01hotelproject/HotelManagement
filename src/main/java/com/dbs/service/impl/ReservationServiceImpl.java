package com.dbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.mapper.ReservationMapper;
import com.dbs.po.Reservation;
import com.dbs.service.ReservationService;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationMapper reservationMapper;

	@Override
	public int insertReservation(Reservation reservation) throws Exception {
		return reservationMapper.insertReservation(reservation);
	}

	@Override
	public List<Reservation> queryReservationList(Reservation reservation) throws Exception {
		return reservationMapper.queryReservationList(reservation);
	}

	@Override
	public int updateReservation(Reservation reservation) throws Exception {
		return reservationMapper.updateReservation(reservation);
	}

	@Override
	public int deleteReservation(String[] ids) throws Exception {
		return reservationMapper.deleteReservation(ids);
	}

	@Override
	public Reservation queryReservationById(int r_number) throws Exception {
		return reservationMapper.queryReservationById(r_number);
	}



}
