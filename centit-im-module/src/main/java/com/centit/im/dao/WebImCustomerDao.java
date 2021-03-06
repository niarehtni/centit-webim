package com.centit.im.dao;

import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.hibernate.dao.BaseDaoImpl;
import com.centit.im.po.WebImCustomer;
import com.centit.support.algorithm.DatetimeOpt;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * WebImCustomerDao  Repository.
 * create by scaffold 2017-05-23 
 * @author codefan@sina.com
 * 匿名用户（ 用于客服模式）匿名用户（ 用于客服模式）

用户的ID为 各个系统的用户ID ，或者由前端根据 前端的硬件属性生产一个唯一的编号   
*/

@Repository
public class WebImCustomerDao extends BaseDaoImpl<WebImCustomer,String>
	{

	public static final Log log = LogFactory.getLog(WebImCustomerDao.class);
	
	@Override
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("osId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("userCode" , CodeBook.EQUAL_HQL_ID);


			filterField.put("userName" , CodeBook.EQUAL_HQL_ID);

			filterField.put("customerService" , CodeBook.EQUAL_HQL_ID);

			filterField.put("creator" , CodeBook.EQUAL_HQL_ID);

			filterField.put("createTime" , CodeBook.EQUAL_HQL_ID);

		}
		return filterField;
	}

	public List<WebImCustomer> listCustByType (String userType) {
		 return this.listObjects("From WebImCustomer where userType = ?",userType);
	}

	public List<WebImCustomer> listCustomerService() {
		return this.listObjects("From WebImCustomer where userType = 'S' or userType = 'P'");
	}

	public List<WebImCustomer> listCustomerServiceByOptId(String optId) {
		return this.listObjects("From WebImCustomer " +
				"where (userType = 'S' or userType = 'P') and serviceOpts like ? ",
				"%"+optId+"%");
	}

	public List<WebImCustomer> listServiceCustomer(String serviceUserCode, Date lastServiceDate) {
		Date lsd = lastServiceDate==null? DatetimeOpt.addMonths(
					DatetimeOpt.currentUtilDate(),-1) :lastServiceDate;
		return this.listObjects(
				"From WebImCustomer where customerService = ? and lastActiveDate >= ?" +
						" order by lastActiveDate desc",
				new Object[]{serviceUserCode,lsd});
	}
}
