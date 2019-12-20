package biz;

import impl.BookInfoBizImplv1;

public class BizFactory {
	/**
	 * ����ҵ�������ƻ����Ӧҵ�����ʵ��
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
