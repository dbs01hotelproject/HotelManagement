package com.dbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.mapper.CashierMapper;
import com.dbs.po.Bill;
import com.dbs.po.Customer;
import com.dbs.po.NetworkInformation;
import com.dbs.po.NetworkManagement2;
import com.dbs.po.Reception;
import com.dbs.po.StateInfo;

@Service
@Transactional
public class CashierServiceImpl implements com.dbs.service.CashierService {

	@Autowired
	private CashierMapper cashierMapper;
	
	@Override
	public StateInfo selectforAllreception(StateInfo stateInfo) {

		return cashierMapper.selectforAllreception(stateInfo);
	}


	@Override
	public void updateforLeave(Reception reception) {

		cashierMapper.updateforLeave(reception);
	}

	@Override
	public void updateforRoomState(int r_number) {
		cashierMapper.updateforRoomState(r_number);
	}

	@Override
	public void updateNetworkManagement(NetworkManagement2 neManagement) {
		cashierMapper.updateNetworkManagement(neManagement);
		
	}

	@Override
	public void updateNetworkInformation(NetworkInformation netInformation) {

		cashierMapper.updateNetworkInformation(netInformation);
	}

	@Override
	public void updateNetFee(NetworkInformation netInformation) {

		cashierMapper.updateNetFee(netInformation);
	}


	@Override
	public void GenerateBills(Bill bill) {

		cashierMapper.GenerateBills(bill);
	}


	@Override
	public Bill selectBillInfo(Reception reception) {

		return cashierMapper.selectBillInfo(reception);
		
	}


	@Override
	public NetworkManagement2 selectNetManagement(int n_roomnumber) {
		
		return cashierMapper.selectNetManagement(n_roomnumber);
	}


	@Override
	public NetworkInformation selectNetInfo(int e_roomnumber) {

		return cashierMapper.selectNetInfo(e_roomnumber);
	}


	@Override
	public NetworkInformation selectNetInfo2(int e_roomnumber) {
		return cashierMapper.selectNetInfo2(e_roomnumber);
	}


	@Override
	public Reception selectCheckinDate(int r_roomnumber) {
		
		return cashierMapper.selectCheckinDate(r_roomnumber);
	}


	@Override
	public void updateBillfeetype(Bill bill) {

		cashierMapper.updateBillfeetype(bill);
	}


	@Override
	public void updateBillDateAllPrice(Bill bill) {

		cashierMapper.updateBillDateAllPrice(bill);
	}


	@Override
	public int selectBillfeerates(int b_roomnumber) {
		return cashierMapper.selectBillfeerates(b_roomnumber);
	}

	

	

}
