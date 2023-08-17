package com.tester.api_tester_springboot.core.excepiton;

/**
 * exception 接口
 * @author llg
 *
 */
public interface ExceptionInterface {
	
	/**
	 * getCode
	 * @return
	 */
    public Integer getCode();
    
    /**
     * getMsg
     * @return
     */
    public String getMsg();

}