package biz;

import impl.BookInfoBizImplv1;

public class BizFactory {
	/**
	 * 根据业务类名称获得相应业务类的实现
	 */
	public static Biz getBiz(String bizName) {
		switch(bizName.toLowerCase()) {
		case "bookinfobiz":
			return new BookInfoBizImplv1();
		case "userbiz":
			//return new UserBizImplv2();
		default:
			return null;
		} 
	}
}
