package com.dbs.mapper;

import com.dbs.po.Customer;
import com.dbs.po.Network;
import com.dbs.po.Room;

/**
 * ������Ϣ
 * @author 16675
 *
 */
public interface ReservationMapper {
	//���Ӷ�����Ϣ,�����ڷ����, �û��µ����û�����,����Ŵ���ڽӴ���Ϣ����,��Ҫ�޸ĵ�ֻ�Ƕ�����Ϣ��;
	//public void addReservarion(Customer customer);
	
	//�鿴������Ϣ
	public void queryReservation(Integer number);
	
	
	//ɾ��������Ϣ
	public void deleteReservation(Integer number);
	
	
	
	//��ѯ������Ϣ,��ͨ������Ϣ�Ķ��������ҹ�
	public Network queryNetworkbyroomnumber(Integer roomnumber);
	
	
	//�Է�������ѯ������Ϣ
	public Room queryRoombynumber(Integer number);
	
	
}
