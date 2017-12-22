package org.jspider.searchApp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class SearchController extends HttpServlet {

	Connection con;
	PreparedStatement psmt;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "pba206");
			psmt = con.prepareStatement("select * from jdbc.student where id=?");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		try {
			psmt.setInt(1, id);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				StudentDTO dto = new StudentDTO();
				dto.setName(rs.getString(2));
				dto.setPerc(rs.getDouble(4));
				dto.setId(rs.getInt(1));
				dto.setCourse(rs.getString(3));
				RequestDispatcher rd = req.getRequestDispatcher("succ.jsp");
				req.setAttribute("st", dto);
				rd.forward(req, resp);
			}
			else {
				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
				req.setAttribute("msg", "Try Again");
				rd.forward(req, resp);
			}
		
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
