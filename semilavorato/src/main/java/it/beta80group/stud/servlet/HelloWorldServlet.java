package it.beta80group.stud.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.beta80group.stud.model.TestModel;
import it.beta80group.stud.services.HelloWorldService;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet("/hello/*")
public class HelloWorldServlet extends HttpServlet {

	final Logger logger = LogManager.getLogger(HelloWorldServlet.class);
	private static final long serialVersionUID = 1L;
	private HelloWorldService hwService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorldServlet() {
        super();
		hwService = HelloWorldService.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("CALLED /hello/ doGet");
		String pathInfo = request.getPathInfo();
		if(pathInfo == null || pathInfo.equalsIgnoreCase("/")){
			logger.info("CALLED /hello/ doGet");
			list(request, response);
		}
		else{
			Long id = Long.parseLong(pathInfo.split("/")[1]);
			logger.info("CALLED /hello/{} doGet", id);

			details(id, request, response);
		}

	}

	private void details(Long id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TestModel model = null;
		RequestDispatcher dispatcher = null;
		try {
			model = hwService.getById(id);
			if(model != null){
				dispatcher = request.getRequestDispatcher("/WEB-INF/hello/hello_details.jsp");
				request.setAttribute("test_model", model);
			}
			else{
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				dispatcher = request.getRequestDispatcher("/WEB-INF/not_found.jsp");

			}
			dispatcher.forward(request, response);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/hello/hello.jsp");
		List<TestModel> list;
		try {
			list = hwService.list();
			request.setAttribute("test_model_list", list);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
		logger.info("CALLED /hello/ doPost");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		List<TestModel> list;
		try {
			hwService.save(username, password);
			list = hwService.list();
			request.setAttribute("test_model_list", list);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/hello/hello.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("CALLED /hello/ doDelete");
		String idStr = request.getParameter("id");
		Long id = Long.parseLong(idStr);
		TestModel m = new TestModel();
		m.setId(id);
		List<TestModel> list;
		try {
			hwService.delete(m);
			response.setStatus(HttpServletResponse.SC_OK);
			PrintWriter writer = response.getWriter();
			writer.append("OK");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("CALLED /hello/ doPut");
		String idStr = request.getParameter("id");
		Long id = Long.parseLong(idStr);
		try(InputStream inputStream = request.getInputStream()){
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String json = br.readLine();
			ObjectMapper mapper = new ObjectMapper();
			TestModel testModel = mapper.readValue(json, TestModel.class);
			testModel.setId(id);
			hwService.update(testModel);
			response.setStatus(HttpServletResponse.SC_OK);
			PrintWriter writer = response.getWriter();
			writer.append("OK");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
