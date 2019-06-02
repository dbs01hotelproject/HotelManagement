package com.dbs.service;

import com.dbs.po.NetworkManagement;
import com.dbs.po.RoomInformation;

public interface RoominformationService {
	// 添加房务信息
	public void addRoominformation(RoomInformation roominformation) throws Exception;

	// 查看房务信息
	public RoomInformation findRoominformationByNumber(int r_number)throws Exception;

	// 删除房务信息
	public int deleteRoominformationByNumber(int r_number)throws Exception;

	// 开通网络处理信息
	public NetworkManagement openNetworkManagement(int r_number)throws Exception;
	//修改客房服务
	public void updateRoominformationByNumber(RoomInformation roominformation)throws Exception;
	
	//删除网络名单
	public void deleteNetworkManagementByNumber(int r_number)throws Exception;

}
