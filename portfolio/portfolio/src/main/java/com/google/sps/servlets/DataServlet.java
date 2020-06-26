// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import com.google.gson.Gson;
import java.util.List;
import java.util.ArrayList;
import com.google.sps.data.Comments;
import javax.servlet.annotation.WebServlet;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.FetchOptions;


/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/comment")
public class DataServlet extends HttpServlet {

    
  //private ArrayList<String>comments;

  //Magic strings
  public String firstName;
  public String lastName;
  public String email;
  public String phone;
  public String message;
  public long timestamp;

  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException{
      int displayAmount = Integer.parseInt(request.getParameter("num"));      
      Query query = new Query("Comments").addSort("timestamp", SortDirection.DESCENDING);

      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      List<Entity> results = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(displayAmount));

      List<Comments> comments = new ArrayList<>();
      for (Entity entity : results) {

          Comments comment = convertEntity(entity);//new Comments(firstName, lastName, email, phone, message);
          comments.add(comment);

        }

        /*User Service
      UserService userService = UserServiceFactory.getUserService();
        if (userService.isUserLoggedIn()) {
        String userEmail = userService.getCurrentUser().getEmail();
        String urlToRedirectToAfterUserLogsOut = "/";
        String logoutUrl = userService.createLogoutURL(urlToRedirectToAfterUserLogsOut);

        response.getWriter().println("<p>Hello " + userEmail + "!</p>");
        response.getWriter().println("<p>Logout <a href=\"" + logoutUrl + "\">here</a>.</p>");
        } else {
        String urlToRedirectToAfterUserLogsIn = "/";
        String loginUrl = userService.createLoginURL(urlToRedirectToAfterUserLogsIn);

        response.getWriter().println("<p>Hello stranger.</p>");
        response.getWriter().println("<p>Login <a href=\"" + loginUrl + "\">here</a>.</p>");
        }
      //The end of User service code*/
      
      
      //String json = convertToJsonUsingGson(comments);
      Gson gson = new Gson();
      

      response.setContentType("application/json;");
      response.getWriter().println(gson.toJson(comments));
    }

    //create a helper function that converts an Entity object into a Comment object and call it within the for loop
    public Comments convertEntity(Entity entity){

        firstName = (String) entity.getProperty("firstName");
        lastName = (String) entity.getProperty("lastName");
        email = (String) entity.getProperty("email");
        phone = (String) entity.getProperty("phone");
        message = (String) entity.getProperty("message");
        timestamp = (long) entity.getProperty("timestamp");

        Comments nComments = new Comments(firstName, lastName, email, phone, message, timestamp);

        return nComments;
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {


        Entity commentEntity = convertComment(request);

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(commentEntity);

        response.sendRedirect("/contact.html");
        
    }
    //so this function is converting request --> entity

    public Entity convertComment(HttpServletRequest request){

        // Get the input from the form.
        firstName = getParameter(request, "firstName", "");
        lastName = getParameter(request, "lastName", "");
        email = getParameter(request, "email", "");
        phone = getParameter(request, "phone", "");
        message = getParameter(request, "message", "");
        timestamp = System.currentTimeMillis();

        Entity ncommentEntity = new Entity("Comments");

        ncommentEntity.setProperty("firstName", firstName);
        ncommentEntity.setProperty("lastName", lastName);
        ncommentEntity.setProperty("email", email);
        ncommentEntity.setProperty("phone", phone);
        ncommentEntity.setProperty("message", message);
        ncommentEntity.setProperty("timestamp", timestamp);

        return ncommentEntity;

    }

   private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }

}//This is the end of the class