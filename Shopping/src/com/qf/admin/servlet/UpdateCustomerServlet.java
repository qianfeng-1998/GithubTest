package com.qf.admin.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.qf.admin.service.UpdateCustomerService;
import com.qf.bean.CustomerInfo;

/**
@Author:
@desc:修改用户信息，如果原来的值没有改变，则不予修改
@date:
*/
@WebServlet("/UpdateCustomerServlet")
public class UpdateCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Map map=request.getParameterMap();
		CustomerInfo ci=new CustomerInfo();
		try {
			BeanUtils.populate(ci, map);
			int row=UpdateCustomerService.updateCustomer(ci);
			if (row>0) {
				response.sendRedirect(request.getContextPath()+"/SeeAllCustomerServlet?pageNo=1");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/jsp/failed.jsp");
				return;
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
