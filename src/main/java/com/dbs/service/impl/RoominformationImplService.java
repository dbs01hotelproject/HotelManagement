package com.dbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.mapper.RoomInformationMapper;
import com.dbs.po.NetworkManagement;
import com.dbs.po.RoomInformation;
import com.dbs.service.RoominformationService;

@Service
@Transactional
public class RoominformationImplService implements RoominformationService {

	@Autowired
	private RoomInformationMapper roominformationMapper;

	@Override
	public void addRoominformation(RoomInformation roominformation) throws Exception {
		this.roominformationMapper.addRoominformation(roominformation);
	}

	@Override
	public RoomInformation findRoominformationByNumber(int r_number) throws Exception {
		return this.roominformationMapper.findRoominformationByNumber(r_number);

	}

	@Override
	public int deleteRoominformationByNumber(int r_number) throws Exception {
		return this.roominformationMapper.deleteRoominformationByNumber(r_number);

	}

	@Override
	public NetworkManagement openNetworkManagement(int r_number) throws Exception {
		return this.roominformationMapper.openNetworkManagement(r_number);

	}

	@Override
	public void updateRoominformationByNumber(RoomInformation roominformation) throws Exception {
		this.roominformationMapper.updateRoominformationByNumber(roominformation);

	}

	@Override
	public void deleteNetworkManagementByNumber(int r_number) throws Exception {
		this.roominformationMapper.deleteNetworkManagementByNumber(r_number);
	}

}
