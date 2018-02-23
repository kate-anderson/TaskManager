/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbHelper.User_AddMyTasksQuery;
import java.util.Date;
import model.Tasks;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author btfra
 */
@WebServlet(name = "Admin_AddMyTasksServlet", urlPatterns = {"/admin/addMyTask"})
public class Admin_AddMyTasksServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet User_AddMyTasksServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet User_AddMyTasksServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
              //capture the logged in user
                String email = request.getUserPrincipal().getName();
        
                
                //Create a ReadQuery helper object
                User_AddMyTasksQuery rq = new User_AddMyTasksQuery();
            
            //get the data
            
            String taskname = request.getParameter("taskname");
            String taskcat = request.getParameter("taskcat");
            
            String taskduedate = request.getParameter("taskduedate");
            
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date parsedDate = formatter.parse(taskduedate); 
            
            String taskdetails = request.getParameter("taskdetails");
           

            
            //set up a task object
            Tasks task = new Tasks();
          
            task.setTaskName(taskname);
            task.setTaskcat(taskcat);
            task.setTaskduedate(parsedDate);
            task.setTaskdetails(taskdetails);
            task.setEmail(email);

            
            
            //set up an addquery object
            User_AddMyTasksQuery aq = new User_AddMyTasksQuery();
            
            //pass the task to add to the database
            aq.doAdd(task);
            
            //pass execution control to the readservlet
            String url = "./readMyTasks";
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Admin_AddMyTasksServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Admin_AddMyTasksServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Admin_AddMyTasksServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}