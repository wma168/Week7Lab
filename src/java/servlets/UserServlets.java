package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.UserService;

/**
 *
 * @author xbali
 */
public class UserServlets extends HttpServlet {
 
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         UserService us= new UserService();
         HttpSession session=request.getSession();
         String action = request.getParameter("action");
         ArrayList<User> users;
         try {
            if (action != null && !action.equals("")) {
                String email = request.getParameter("email").replaceAll(" ", "\\+");
                switch (action) {
                    case "edit":
                        User user = us.get(email);
                        session.setAttribute("user", user);
                        break;
                    case "delete":
                        us.delete(email);
                }
                
            } 
            users=us.getAll();
            session.setAttribute("users", users);
        } 
        catch (Exception ex) {
            Logger.getLogger(UserServlets.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }
         
         getServletContext().getRequestDispatcher("/WEB-INF/users.jsp" ).forward(request, response); 
       
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserService us = new UserService();
        HttpSession session = request.getSession();
        String action = request.getParameter("submit");
        
        if(action.equals("Cancel")) {
            session.setAttribute("user", null);
            getServletContext().getRequestDispatcher("/WEB-INF/users.jsp" ).forward(request, response);  
            return;
        }
        
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String password = request.getParameter("password");
        String roleID = request.getParameter("role");
        
        if(email==""||firstName==""||lastName==""||password==""||roleID==""){
            request.setAttribute("fillAll","All fields are required");
            request.setAttribute("email",email);
            request.setAttribute("firstname",firstName);
            request.setAttribute("lastname",lastName);
            getServletContext().getRequestDispatcher("/WEB-INF/users.jsp" ).forward(request, response);  
        }
        
        
        if(action!=null&&action.equals("Add User")){
            try {
                ArrayList<User> users = us.getAll();
                for(int i=0;i<users.size();i++){
                    if(users.get(i).getEmail().equals(email)){
                        request.setAttribute("fillAll", "Email is in use");
                    getServletContext().getRequestDispatcher("/WEB-INF/users.jsp" ).forward(request, response);  
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(UserServlets.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(action!=null&&action.equals("Add User")){
            try {
                us.insert(email,firstName,lastName,password,Integer.parseInt(roleID));
            } catch (Exception ex) {
                Logger.getLogger(UserServlets.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(action!=null&&action.equals("Update")){
            try {
                us.update(email,firstName,lastName,password,Integer.parseInt(roleID));
                session.setAttribute("user",null);
            } catch (Exception ex) {
                Logger.getLogger(UserServlets.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            ArrayList<User> users = us.getAll();
            session.setAttribute("users",users);
        } catch (Exception ex) {
            Logger.getLogger(UserServlets.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp" ).forward(request, response);
    }



}
