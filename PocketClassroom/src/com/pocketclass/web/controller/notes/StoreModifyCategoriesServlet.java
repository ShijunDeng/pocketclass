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
			// start:�޸�
			if (modifiedNames.length == 1) {// 1.�ų���û�����κ�ɾ��ʱ������ɾ����ŵ�����ĳ���ҲΪ1
				if (customNamelist.contains(modifiedNames[0])) {// ˵����������һ���޸�
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
			// end:�޸�
			// start:����
			for (int i = 0; i < idCustoms.length; i++) {
				if (idCustoms[i].equals("0")) {// ��0Ϊ��ţ�˵��Ϊ����
					CustomCategory custom = new CustomCategory();
					custom.setCustomName(customNames[i]);
					custom.setUsername(username);

					service.insertCustomCategory(custom);
				}
			}
			// end:����
			// start:ɾ��
			for (int i = 0; i < categorys.length; i++) {
				if (!categorys[i].equals("0")) {// 1.��Ų�Ϊ0,˵��ɾ������ԭ�еķ���
					service.deleteCustomCategory(categorys[i]);
				}
			}
			// end:ɾ��
			request.getRequestDispatcher(
					"/servlet/UserNoteCenterServlet?username=" + username)
					.forward(request, response);

			/*------------------------���Խ��������ɾ��-----------------------------*/
			/*���壺�û����������Ȼ�����޸ģ������ɾ��*/
			//�Զ�����:1
			//�Զ�����:2
			//�Զ�����:3
			//�Զ�����:4
			//�Զ�����:5
			//�Զ�������:Ĭ�Ϸ���
			//�Զ�������:01�ҵķ���1
			//�Զ�������:01�ҵķ���2
			//�Զ�������:01�ҵķ���3
			//�Զ�������:01�ҵķ���4
			//�Զ���ʼ���:1
			//�Զ���ʼ���:6
			//�Զ���ʼ���:4
			//�Զ���ʼ���:0
			//�Զ���ʼ���:0
			//1
			//ɾ����id��0(�˴�Ҫע�⣬û�����κ�ɾ��ʱ������ɾ����ŵ�����ĳ���ҲΪ1)
			//4
			//�޸ĵ�id��2
			//�޸ĵ�id��3
			//�޸ĵ�id��4
			//�޸ĵ�id��0
			//4
			//�޸ĵ����ƣ�01�ҵķ���1
			//�޸ĵ����ƣ�01�ҵķ���2
			//�޸ĵ����ƣ�01�ҵķ���3
			//�޸ĵ����ƣ�66666677777
			/*-----------------------------���Խ��------------------------------------*/
				//���Դ��룬����ɾ//
			/*	  	String idCustoms[] = request.getParameterValues("idCustom");
					for(String idCustom:idCustoms)
					{
						System.out.println("�Զ�����:"+idCustom);
					}
					String customNames[] = request.getParameterValues("customName");
					for(String customName:customNames)
					{
						System.out.println("�Զ�������:"+customName);
					}
					String noteAmounts[] = request.getParameterValues("noteAmount");
					for(String noteAmount:noteAmounts)
					{
						System.out.println("�Զ���ʼ���:"+noteAmount);
					}
				//	String isopens[] = request.getParameterValues("customName");  ������ʾ�Ĺ�����ʱ��ʵ��
					//ɾ���˵ķ��࣬����idӦ��������
					String deletedCategory = request.getParameter("deletedCategory");
			System.out.println("ɾ������"+deletedCategory);
					if(deletedCategory != null)
					{
						String categorys[] = deletedCategory.split(",");
						System.out.println(categorys.length);
						for(String category:categorys)
						{
							System.out.println("ɾ����id��"+category);
						}
					}
					
					String modifiedId = request.getParameter("modifiedId");
					if(modifiedId != null)
					{
						String modifiedIds[] = modifiedId.split(",");
						System.out.println(modifiedIds.length);
						for(String category:modifiedIds)
						{
							System.out.println("�޸ĵ�id��"+category);
						}
					}
					
					String modifiedName = request.getParameter("modifiedName");
					if(modifiedName != null)
					{
						String modifiedNames[] = modifiedName.split(",");
						System.out.println(modifiedNames.length);
						for(String category:modifiedNames)
						{
							System.out.println("�޸ĵ����ƣ�"+category);
						}
					}
			*/
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "������!");
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
