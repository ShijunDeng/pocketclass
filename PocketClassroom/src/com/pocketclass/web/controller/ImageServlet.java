package com.pocketclass.web.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ImageServlet extends HttpServlet {

	private static final long serialVersionUID = 201210200203L;
	public static final int WIDTH = 85;
	public static final int HEIGHT = 25;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
					BufferedImage.TYPE_INT_RGB);

			Graphics g = image.getGraphics();

			// 1.���ñ���
			setBackGround(g);
			// 2.���ñ߿�
			setBorder(g);
			// 3.��������
			drawRandomLine(g);
			// 4.д�����
			String vercode = drawRandomNum((Graphics2D) g);

			// ����֤�����session�У��Ա�У��
			HttpSession session = request.getSession();
			session.setAttribute("vercode", vercode);
			// ���������û�л���
			response.setDateHeader("Expires", -1);
			response.setHeader("Cache-control", "no-cache");
			response.setHeader("Pragma", "no-cache");

			response.setContentType("image/jpeg");// �����������ͼƬ��ʽ��
			ImageIO.write(image, "jpg", response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "������!");
			request.getRequestDispatcher("/jsp/message_jsp/errorMessage.jsp")
					.forward(request, response);
		}
	}

	private void setBackGround(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}

	private void setBorder(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
	}

	private void drawRandomLine(Graphics g) {
		g.setColor(Color.GREEN);
		for (int i = 0; i < 10; i++) {
			int x1 = new Random().nextInt(WIDTH);
			int y1 = new Random().nextInt(HEIGHT);

			int x2 = new Random().nextInt(WIDTH);
			int y2 = new Random().nextInt(HEIGHT);
			g.drawLine(x1, y1, x2, y2);
		}
	}

	private String drawRandomNum(Graphics2D g) {
		g.setColor(Color.RED);
		g.setFont(new Font("����", Font.BOLD, 20));

		String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		int x = 10;
		StringBuffer checkCode = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			String str = base.charAt(new Random().nextInt(base.length())) + "";
			checkCode.append(str);
			Random random = new Random(System.currentTimeMillis());
			int degree = random.nextInt() % 30;// -30~30
			g.rotate(degree * Math.PI / 180, x, 20);// ��ͼƬ������ת
			g.drawString(str, x, 20);
			g.rotate(-degree * Math.PI / 180, x, 20);// ����ת����������Ӱ����һ����
			x += 20;
		}
		return checkCode.toString();

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
