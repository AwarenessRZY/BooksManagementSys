package exception;

public class NoSuchOptPermissionException extends RuntimeException{
	private static final long serialVersionUID = 6911123393230753993L;

	public NoSuchOptPermissionException() {
		super();
		System.err.println("异常：您没有权限执行此操作！");
	}

	
}
