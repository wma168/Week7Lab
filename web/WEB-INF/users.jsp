<%-- 
    Document   : users
    Created on : 12-Mar-2023, 8:28:00 PM
    Author     : xbali
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
    </head>
    <body>
        <h1>Manage Users</h1>
        ${message}
        <c:if test="${users.isEmpty()}">No users found.  Please add a user</c:if>
        
        
        <c:if test="${!users.isEmpty()}">
            <table border="1">
            <tr><th>Email</th><th>First Name</th><th>Last Name</th><th>Role</th><th></th><th></th></tr>
            
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.getEmail()}</td>
                    <td>${user.getFirstName()}</td>
                    <td>${user.getLastName()}</td>
                    <td>${user.getRole().getRoleName()}</td>
                    <td><a href="users?action=edit&amp;email=${user.getEmail()}">Edit</a></td>
                    <td><a href="users?action=delete&amp;email=${user.getEmail()}">Delete</a></td>
                </tr>
            </c:forEach>
            </table>
        </c:if>
        
        <c:if test="${user eq null}">
            <h2>Add User</h2>
            <form action="" method="post">
                Email: <input type="text" name="email" value="${email}"><br>
                First name <input type="text" name="firstname" value="${firstname}"><br>
                Last name <input type="text" name="lastname" value="${lastname}"><br>
                Password: <input type="password" name="password"><br>
                Role:
                <select name="role">
                    <option value="1">system admin</option>
                    <option value="2">regular user</option>
                </select><br>
                <input type="submit" name="submit" value="Add User">
            </form>
                ${fillAll}
        </c:if>
            
            <c:if test="${user ne null}">
                <h2>Edit User</h2>
            <form action="" method="post">
                Email: ${user.getEmail()}<br>
                First name <input type="text" name="firstname" value="${firstname}" ><br>
                Last name <input type="text" name="lastname" value="${lastname}"><br>
                Password: <input type="password" name="password"><br>
                Role: 
                <select name="role">
                <option value="1">system admin</option>
                <option value="2" ${user.getRole().getRoleID() == '2' ? 'selected' : ''}>regular user</option>
                </select><br>
                <input type="hidden" name="role" value="${user.getRole().getRoleID()}">
                <input type="submit" name="submit" value="Update">
                <input type="submit" name="submit" value="Cancel">
            </form>
            ${fillAll}

            </c:if>
    </body>
</html>