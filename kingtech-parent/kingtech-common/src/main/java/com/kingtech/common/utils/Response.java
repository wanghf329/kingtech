package com.kingtech.common.utils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response implements Serializable{
	
	private static final long serialVersionUID = -4705328383262605237L;
	
	private Boolean success; // 是否成功
	private Object data; // 返回的数据
	private String msg; // 消息
	
	/**
	 * 只返回成功的布尔值
	 * @return
	 */
	public static Response success() {
		return new Response(true, null, null);
	}
	
	/**
	 * 返回成功的布尔值和数据
	 * @param data
	 * @return
	 */
	public static Response success(Object data) {
		return new Response(true, data, null);
	}
	
	/**
	 * 返回成功的布尔值、数据和消息
	 * @param data
	 * @param msg
	 * @return
	 */
	public static Response success(Object data, String msg) {
		return new Response(true, data, msg);
	}
	
	/**
	 * 只返回失败的布尔值
	 * @return
	 */
	public static Response failure() {
		return new Response(false, null, null);
	}
	
	/**
	 * 返回失败的布尔值和数据
	 * @param data
	 * @return
	 */
	public static Response failure(Object data) {
		return new Response(false, data, null);
	}
	
	/**
	 * 返回失败的布尔值、数据和消息
	 * @param data
	 * @param msg
	 * @return
	 */
	public static Response failure(Object data, String msg) {
		return new Response(false, data, msg);
	}
	
	public static String attachment(String fileName, String format) {
		return attachment(fileName + "." + format);
	}
	
	public static String attachment(String fullFileName) {
		String encodedFileName = "";
		try {
			encodedFileName = new String(fullFileName.getBytes(), "ISO8859-1"); // 解决中文乱码问题
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "attachment; filename=\"" + encodedFileName + "\"";
	}
	
}
