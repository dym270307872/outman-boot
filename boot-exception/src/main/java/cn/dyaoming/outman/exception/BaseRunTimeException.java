package cn.dyaoming.outman.exception;

/**
 * @author dym
 *
 */
public class BaseRunTimeException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public BaseRunTimeException(String message) {
		super(message);
	}
}
