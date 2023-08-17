package com.tester.api_tester_springboot.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * checkList1.0
 *
 * record 正常日志
 * systemError 系统异常
 *
 * @author 王海虹
 *
 */
public class RcLoggerUtils {
	
	private static final Logger LOG = LogManager.getLogger(RcLoggerUtils.class);


	private static final String FULL_info_LOG 		= "startTime: {}\n requestUrl: {}\n method: {}\n headers:{}\n req:{}\n resp:{}";
	private static final String FULL_error_LOG 		= "requestUrl:{}\n headers:{}\n req:{}\n error:{}";
	private static final String FULL_LOG 			= "<{}><{}><><><>req:[{}], resp:[{}]";
	private static final String FULL_SC_F1_LOG 		= "<{}><{}><{}><{}><>req:[{}], resp:[{}]";
	private static final String FULL_SC_F1_F2_LOG 	= "<{}><{}><{}><{}><{}>req:[{}], resp:[{}]";
	private static final String REQ_LOG 			= "<{}><{}><><><>req:[{}]";
	private static final String REQ_SC_F1_LOG 		= "<{}><{}><{}><{}><>req:[{}]";
	private static final String REQ_SC_F1_F2_LOG	= "<{}><{}><{}><{}><{}>req:[{}]";


	/**
	 * INFO 记录checkList日志 写天网日志+聚合日志
	 *
	 * @param logger
	 * @param methodName
	 * @param startTime
	 * @param requestBody
	 * @param responseBody
	 * @param smallClassify
	 * @param filter1
	 */
	public static void record(Logger logger, String methodName, long startTime,
							  String smallClassify, String filter1, Object requestBody, Object responseBody) {
		String serviceName = getServiceName(logger);

		logger.info(FULL_SC_F1_LOG, serviceName, methodName, smallClassify, filter1, requestBody, responseBody);
	}

	/**
	 * 系统ERROR,code小于0 记录checkList日志 写天网日志+聚合日志
	 *
	 * @param logger
	 * @param methodName
	 * @param startTime
	 * @param requestBody
	 * @param responseBody
	 * @param code
	 * @param exception
	 */
	public static void systemError(Logger logger, String methodName,
								   long startTime, Object requestBody, Object responseBody,
								   Integer code, Exception exception) {
		String serviceName = getServiceName(logger);

		logger.error(FULL_LOG, serviceName, methodName, requestBody, responseBody, exception);
	}

	/**
	* INFO 记录checkList日志
	* @param: requestUrl: 请求url， headers：请求头，
	 *   	  requestBody：请求入参， responseBody：请求出参
	* @return:
	* @date: 2022/7/15
	* @toDo:
	*/
	public static void recordReq(Logger logger, String method,long startTime,
								 String requestUrl, Object headers,
								 Object requestBody, Object responseBody) {
		String timeString = DateUtil.format(new Date(startTime), DatePattern.NORM_DATETIME_PATTERN);
		logger.info(FULL_info_LOG, timeString ,requestUrl,method,headers,requestBody, responseBody);
	}

	public static void recordError(Logger logger, String requestUrl, String headers, Object requestBody, Exception exception) {
		logger.error(FULL_error_LOG, requestUrl,headers, requestBody, exception);
	}

	public static void systemErrorReq(Logger logger, String methodName, long startTime, String smallClassify, String filter1, Object requestBody, Integer code, Exception exception) {
		String serviceName = getServiceName(logger);

		logger.error("<{}><{}><{}><{}><>req:[{}]", new Object[]{serviceName, methodName, smallClassify, filter1, requestBody, exception});
	}

	public static void systemErrorReq(Logger logger, String methodName, long startTime, String smallClassify, String filter1, String filter2, Object requestBody, Integer code, Exception exception) {
		String serviceName = getServiceName(logger);
		logger.error("<{}><{}><{}><{}><>req:[{}]", new Object[]{serviceName, methodName, smallClassify, filter1, filter2, requestBody, exception});
	}

	public static void systemErrorReq(Logger logger, String methodName, long startTime, Object requestBody, Integer code, Exception exception) {
		String serviceName = getServiceName(logger);
		logger.error("<{}><{}><><><>req:[{}]", new Object[]{serviceName, methodName, requestBody, exception});
	}

	public static void systemErrorReq(Logger logger, String moduleName, String methodName, String smallClassify, String filter1, String filter2, long startTime, Object requestBody, Integer code, Exception exception) {
		String serviceName = getServiceName(logger);
		logger.error("<{}><{}><{}><{}><{}>req:[{}]", new Object[]{moduleName, methodName, smallClassify, filter1, filter2, requestBody, exception});
	}

	/**
	 * INFO 记录checkList日志 写天网日志+聚合日志
	 *
	 * @param logger
	 * @param methodName
	 * @param startTime
	 * @param requestBody
	 */
	public static void recordReq(Logger logger, String methodName, long startTime,
								 Object requestBody) {
		String serviceName = getServiceName(logger);
		logger.info(REQ_LOG, serviceName, methodName, requestBody);
	}


	/**
	 * 根据logger获取ServiceName
	 * 
	 * @param logger
	 * @return
	 */
	private static String getServiceName(Logger logger) {
		String fullServiceName = logger.getName();
		int start = fullServiceName.lastIndexOf(".") + 1;
		int end = fullServiceName.length();
		return StringUtils.substring(fullServiceName, start, end);
	}


	
}
