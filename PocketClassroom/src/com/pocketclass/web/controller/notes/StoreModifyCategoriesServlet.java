package com.pocketclass.web.controller.notes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pocketclass.domain.CustomCategory;
import com.pocketclass.service.NoteService;
import com.pocketclass.service.impl.NoteServiceImpl;

public class StoreModifyCategoriesServlet extends HttpServlet {

	private static final long serialVersionUID = 201211122254L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");

			String username = (String) request.getSession(false).getAttribute(
					"username");
			NoteService service = new NoteServiceImpl();

			String idCustoms[] = request.getParameterValues("idCustom");
			String customNames[] = request.getParameterValues("customName");
			List<String> customNamelist = Arrays.asList(customNames);
			// String noteAmounts[] = request.getParameterValues("noteAmount");

			String modifiedId = request.getParameter("modifiedId");
			String modifiedIds[] = modifiedId.split(",");
			String modifiedName = request.getParameter("modifiedName");
			String modifiedNames[] = modifiedName.split(",");

			String deletedCategory = request.getParameter("deletedCategory");
			String categorys[] = deletedCategory.split(",");
			// start:修改
			if (modifiedNames.length == 1) {// 1.排除掉没有做任何删除时，保存删除编号的数组的长度也为1
				if (customNamelist.contains(modifiedNames[0])) {// 说明做了有且一次修改
					service.modifyCustomCategory(modifiedIds[0],
							modifiedNames[0]);
				}
			} else {
				for (int i = 0; i < modifiedNames.length; i++) {
					if (customNamelist.contains(modifiedNames[i])) {
						service.modifyCustomCategory(modifiedIds[i],
								modifiedNames[i]);
					}
				}
			}
			// end:修改
			// start:新增
			for (int i = 0; i < idCustoms.length; i++) {
				if (idCustoms[i].equals("0")) {// 以0为编号，说明为新增
					CustomCategory custom = new CustomCategory();
					custom.setCustomName(customNames[i]);
					custom.setUsername(username);

					service.insertCustomCategory(custom);
				}
			}
			// end:新增
			// start:删除
			for (int i = 0; i < categorys.length; i++) {
				if (!categorys[i].equals("0")) {// 1.编号不为0,说明删除的是原有的分类
					service.deleteCustomCategory(categorys[i]);
				}
			}
			// end:删除
			request.getRequestDispatcher(
					"/servlet/UserNoteCenterServlet?username=" + username)
					.forward(request, response);

			/*------------------------测试结果：不可删除-----------------------------*/
			/*陷阱：用户新增分类后，然后做修改，最后做删除*/
			//自定义编号:1
			//自定义编号:2
			//自定义编号:3
			//自定义编号:4
			//自定义编号:5
			//自定义名称:默认分类
			//自定义名称:01我的分类1
			//自定义名称:01我的分类2
			//自定义名称:01我的分类3
			//自定义名称:01我的分类4
			//自定义笔记数:1
			//自定义笔记数:6
			//自定义笔记数:4
			//自定义笔记数:0
			//自定义笔记数:0
			//1
			//删除的id：0(此处要注意，没有做任何删除时，保存删除编号的数组的长度也为1)
			//4
			//修改的id：2
			//修改的id：3
			//修改的id：4
			//修改的id：0
			//4
			//修改的名称：01我的分类1
			//修改的名称：01我的分类2
			//修改的名称：01我的分类3
			//修改的名称：66666677777
			/*-----------------------------测试结果------------------------------------*/
				//测试代码，不可删//
			/*	  	String idCustoms[] = request.getParameterValues("idCustom");
					for(String idCustom:idCustoms)
					{
						System.out.println("自定义编号:"+idCustom);
					}
					String customNames[] = request.getParameterValues("customName");
					for(String customName:customNames)
					{
						System.out.println("自定义名称:"+customName);
					}
					String noteAmounts[] = request.getParameterValues("noteAmount");
					for(String noteAmount:noteAmounts)
					{
						System.out.println("自定义笔记数:"+noteAmount);
					}
				//	String isopens[] = request.getParameterValues("customName");  隐藏显示的功能暂时不实现
					//删除了的分类，分类id应当记下来
					String deletedCategory = request.getParameter("deletedCategory");
			System.out.println("删除串："+deletedCategory);
					if(deletedCategory != null)
					{
						String categorys[] = deletedCategory.split(",");
						System.out.println(categorys.length);
						for(String category:categorys)
						{
							System.out.println("删除的id："+category);
						}
					}
					
					String modifiedId = request.getParameter("modifiedId");
					if(modifiedId != null)
					{
						String modifiedIds[] = modifiedId.split(",");
						System.out.println(modifiedIds.length);
						for(String category:modifiedIds)
						{
							System.out.println("修改的id："+category);
						}
					}
					
					String modifiedName = request.getParameter("modifiedName");
					if(modifiedName != null)
					{
						String modifiedNames[] = modifiedName.split(",");
						System.out.println(modifiedNames.length);
						for(String category:modifiedNames)
						{
							System.out.println("修改的名称："+category);
						}
					}
			*/
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "出错啦!");
			request.getRequestDispatcher("/jsp/message_jsp/errorMessage.jsp")
					.forward(request, response);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
