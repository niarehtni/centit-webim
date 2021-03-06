package com.centit.im.po;

import java.util.Date;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.hibernate.validator.constraints.NotBlank;

/**
 * WebImReadGroupId  entity.
 * create by scaffold 2017-05-23 
 * @author codefan@sina.com
 * 用户组信息查看时间用来记录 组信息 成功推送到给这个用户的时间，这个时间之后的信息 都是这个用户 关于该组的未读信息   
*/
//用户组信息查看时间 的主键
@Embeddable
public class WebImReadGroupId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;



	/**
	 * 用户代码 null 
	 */
	@Column(name = "USER_CODE")
	@NotBlank(message = "字段不能为空")
	private String userCode;

	/**
	 * 组代码 null 
	 */
	@Column(name = "UNIT_CODE")
	@NotBlank(message = "字段不能为空")
	private String unitCode;

	// Constructors
	/** default constructor */
	public WebImReadGroupId() {
	}
	/** full constructor */
	public WebImReadGroupId( String userCode, String unitCode) {
		this.userCode = userCode;
		this.unitCode = unitCode;	
	}

  

  
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
  
	public String getUnitCode() {
		return this.unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WebImReadGroupId))
			return false;
		
		WebImReadGroupId castOther = (WebImReadGroupId) other;
		boolean ret ;
  
		ret = this.getUserCode() == castOther.getUserCode() ||
					   (this.getUserCode() != null && castOther.getUserCode() != null
							   && this.getUserCode().equals(castOther.getUserCode()));
  
		ret = ret && ( this.getUnitCode() == castOther.getUnitCode() ||
					   (this.getUnitCode() != null && castOther.getUnitCode() != null
							   && this.getUnitCode().equals(castOther.getUnitCode())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;

		result = 37 * result +
		 	(this.getUserCode() == null ? 0 :this.getUserCode().hashCode());
  
		result = 37 * result +
		 	(this.getUnitCode() == null ? 0 :this.getUnitCode().hashCode());
	
		return result;
	}
}
