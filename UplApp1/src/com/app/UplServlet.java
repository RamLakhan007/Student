package com.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadFile;

public class UplServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		PrintWriter pw=null;
		pw=res.getWriter();
		res.setContentType("text/html");
		MultipartFormDataRequest mpfd =null;
		UploadBean bean=null;
		Hashtable ht=null;
		Enumeration e=null;
		////general setting
		pw=res.getWriter();
		res.setContentType("text/html");
		try{
			//prepare special request obj
			mpfd=new MultipartFormDataRequest(req);
			//specify file uploading setting
			bean=new UploadBean();
			bean.setFolderstore("");
			bean.setOverwrite(false);
			//complate file uploding
			bean.store(	mpfd);
			//Display the names of uploaded files
			pw.println("<h1>The uploadded files are</h1>");
			ht=	mpfd.getFiles();
			e=ht.elements();
			while(e.hasMoreElements()){
				UploadFile file=(UploadFile)e.nextElement();
				pw.println("<br>"+file.getFileName()+" "+file.getFileSize());
			}
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
doGet(req,res);
	}
}