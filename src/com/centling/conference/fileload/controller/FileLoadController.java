package com.centling.conference.fileload.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.centling.conference.util.CommonsMethod;
import com.centling.conference.util.exportexcels.ExportExcelsUtil;

@Controller("fileLoadController")
@RequestMapping("/fileload/*")
public class FileLoadController {

	/**
	 * 上传文件功能（包括图片、文档、压缩包等），将文件上传至工程目录下，将路径返回。
	 * 参数区分用户和会议；用户有邮箱、证件照说明；会议有id、banner或者演讲稿区分
	 * type:metting、user
	 * sign:mettingid、user_email
	 * filecategory:证件正面、反面、2寸照片；会议banner、speechDraft
	 * 
	 * 文件夹和文件命名规范：
	 * 每个会议单独文件夹，命名会议id；
	 * 	1.会议banner、2.演讲附件，每个用户单独文件夹，命名用邮箱，多文件，未做日程相关文件
	 * 每个注册用户单独文件夹，命名邮箱账号；
	 * 	1.证件照正面 +前缀front、2.证件照反面+前缀back、3.免冠照片2寸+前缀photo
	 * 
	 * @param file 待上传的文件
	 * @param optiontype 只能取"mettingfileupload"或者"userfileupload"
	 * @param sign 用户传email,会议传mettingid
	 * @param filecategory 	用户传back-证件照反面、front-证件照正面、photo-头像2寸照片；
	 * 						会议传banner-会议banner图标、演讲者传email-上传附件不做删除
	 * @param request 
	 * @return
	 */
	@RequestMapping(value = "/c/upload", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String fileUpload(@RequestParam MultipartFile file,@RequestParam String optiontype,@RequestParam String sign,@RequestParam String filecategory, HttpServletRequest request) {
		String flag = "";
		sign = sign.toLowerCase();
		filecategory = filecategory.toLowerCase();
		//request.getSession().getServletContext().getRealPath("/");linux
		String path = request.getSession().getServletContext().getRealPath("/");//CommonsMethod.getProjectPath();//path的值，D:/programfiles/apache-tomcat-6.0.35/webapps/CONF_MGR/
		StringBuffer pathsBuffer = new StringBuffer("uploadFiles");
		String fileName = "";
		pathsBuffer.append("/");
//		pathsBuffer.append(File.separator);
		
		if(optiontype.equals("metting")){
			pathsBuffer.append("mettings/");
//			pathsBuffer.append(File.separator);//因为图片上传在页面预览的问题，jsp中img标签只能识别左斜
		}else if(optiontype.equals("user")){
			pathsBuffer.append("users/");
//			pathsBuffer.append(File.separator);
		}
		pathsBuffer.append(sign);
		pathsBuffer.append("/");
//		pathsBuffer.append(File.separator);
		String commonCode = CommonsMethod.getNowCorrect2Millisecond();//SecurityCode.getSecurityCode(8,SecurityCodeLevel.Medium, false);
		//是会议文件中的演讲者文件，用邮箱名作为文件分级目录
		if(optiontype.equals("metting") && !filecategory.equals("banner")){
			pathsBuffer.append(filecategory);
			pathsBuffer.append("/");
//			pathsBuffer.append(File.separator);
			fileName += "speaker-";
		}else{
			//传图片之前先删除作废图片
			fileName += filecategory;
			//首先删除该用户之前提交的数据  TODO 前台定义上传流程，先上传再保存，以免未保存但已经上传的数据找不到，故不删除之前文件
//			boolean isDel = FileUploadUtils.deleteFileByFuzzyMatch(path+pathsBuffer.toString(), fileName);
		}
		//更换文件的名字，去除有中文或者其他语言的的影响
		fileName += commonCode + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		
		File targetFile = new File(path+pathsBuffer.toString(),fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
//			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path,targetFile));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		flag = pathsBuffer.toString() + fileName;

		return flag;
	}
	
	@RequestMapping(value = "/c/download", method = RequestMethod.POST)
	public @ResponseBody String fileDownload(HttpServletRequest request,HttpServletResponse response) {
		//response.reset();
		response.setContentType("application/vnd.ms-excel");//;charset=utf-8
		response.setHeader("Content-Disposition", "attachment;filename=aaaatext.xls");
		response.setHeader("Pragma","No-cache");
		response.setHeader ( "Cache-Control", "no-store"); 
		try {
			OutputStream sos = response.getOutputStream();
			List<Map> dataset = new ArrayList<Map>();
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream("E:\\logo.png"));
			byte[] buf = new byte[bis.available()];
			while ((bis.read(buf)) != -1) {
                //
            }
			bis.close();
			for(int i=0;i<10;i++){
				Map<String,Object> map= new HashMap<String,Object>();
				map.put("name", "book"+i);
				map.put("num", i);
				map.put("filepng", buf);
				dataset.add(map);
			}
			String[] headers = new String[]{"名字","数量","图片"};
			ExportExcelsUtil.exportExcel(headers,dataset, sos);
			response.flushBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "成功下载";
		
	}

}
