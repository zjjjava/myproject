package springMVC.service;

import java.util.List;

import springMVC.bean.DepNode;
import springMVC.bean.IPage;
import springMVC.bean.MVCUser;

public interface LoginService {
	IPage list(MVCUser user, int page, int rows, String conditions, String order, String sort);

	/**   
	*    
	* ��Ŀ��ƣ�ssm1     
	* ����������   ��ѯ����
	* �����ˣ��ž���   
	* ����ʱ�䣺2016-10-19 ����7:25:09   
	* �޸��ˣ��ž���   
	* �޸�ʱ�䣺2016-10-19 ����7:25:09   
	* �޸ı�ע��   
	* @version    
	* @throws Exception 
	*    
	*/ 
	List<DepNode> findDeps() throws Exception;
	
	void update(MVCUser user) throws Exception;
}
