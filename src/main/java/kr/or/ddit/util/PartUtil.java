package kr.or.ddit.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PartUtil {
	private static final String UPLOAD_PATH = "d:\\springUpload\\";
	private static final Logger logger = LoggerFactory
			.getLogger(PartUtil.class);

//	/**
//	 * Method : getFileName 작성자 : PC01 변경이력 :
//	 * 
//	 * @param contentDisposition
//	 * @return Method 설명 : contentDisposition에서 파일명을 반환한다
//	 */
//	public static String getFileName(String contentDisposition) {
//
//		String[] splited = contentDisposition.split("; ");
//		for (String split : splited) {
//			if (split.startsWith("filename=")) {
//				int startIndex = split.indexOf("\"") + 1;
//				int lastIndex = split.lastIndexOf("\"");
//				return split.substring(startIndex, lastIndex);
//			}
//		}
//		return "";
//	}

	/**
	 * Method : getExt 작성자 : PC01 변경이력 :
	 * 
	 * @param fileName
	 * @return Method 설명 : 파일명으로부터 파일 확장자를 반환
	 */
	public static String getExt(String fileName) {
		String ext = "";
		String[] splitted = fileName.split("[.]");

		if (splitted.length != 1) {
			ext = splitted[splitted.length - 1];
		}
		return ext.equals("") ? "" : "." + ext;

	}

	public static void checkUploadFolder(String yyyy, String mm) {
		// 년도에 해당한느 폴더가 있는지, 년도안에 월에 해당하는 폴더가 있는지
		File yyyyFolder = new File(UPLOAD_PATH + yyyy);

		if (!yyyyFolder.exists())
			yyyyFolder.mkdir();

		File mmFolder = new File(UPLOAD_PATH + yyyy + File.separator + mm);
		if (!mmFolder.exists())
			mmFolder.mkdir();

	}

	public static String getUploadPath() {

		Date dt = new Date();
		SimpleDateFormat yyyyMMSdf = new SimpleDateFormat("yyyyMM");
		String yyyyMM = yyyyMMSdf.format(dt);
		String yyyy = yyyyMM.substring(0, 4);
		String mm = yyyyMM.substring(4, 6);

		PartUtil.checkUploadFolder(yyyy, mm);
		return UPLOAD_PATH + yyyy + File.separator + mm;
	}
}
