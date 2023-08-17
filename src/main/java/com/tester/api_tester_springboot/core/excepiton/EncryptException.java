package com.tester.api_tester_springboot.core.excepiton;

/**
 * 加密解密exception
 * @author llg
 *
 */
public class EncryptException extends RuntimeException {

	private static final long serialVersionUID = -4179908556841428824L;
	
	private String msg;

    public EncryptException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public EncryptException(String msg, Throwable cause) {
        super(cause);
        this.msg = msg;
    }
    
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
