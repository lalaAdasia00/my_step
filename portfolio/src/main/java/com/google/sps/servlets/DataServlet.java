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
import com.google.gson.Gson;
import com.google.sps.data.Comments;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import com.google.appengine.api.datastore.FetchOptions;


/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/comment")
public class DataServlet extends HttpServlet {

    
  //private ArrayList<String>comments;
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException{
      int displayAmount = Integer.parseInt(request.getParameter("num"));      
      Query query = new Query("Comments").addSort("firstName", SortDirection.DESCENDING);

      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      List<Entity> results = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(displayAmount));

      List<Comments> comments = new ArrayList<>();
      for (Entity entity : results) {

          Comments comment = convertEntity(entity);//new Comments(firstName, lastName, email, phone, message);
          comments.add(comment);

        }
      
      //String json = convertToJsonUsingGson(comments);
      Gson gson = new Gson();
      

      response.setContentType("application/json;");
      response.getWriter().println(gson.toJson(comments));
    }

    //create a helper function that converts an Entity object into a Comment object and call it within the for loop
    public Comments convertEntity(Entity entity){

        String firstName = (String) entity.getProperty("firstName");
        String lastName = (String) entity.getProperty("lastName");
        String email = (String) entity.getProperty("email");
        String phone = (String) entity.getProperty("phone");
        String message = (String) entity.getProperty("message");

        Comments nComments = new Comments(firstName, lastName, email, phone, message);

        return nComments;
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Get the input from the form.
        String firstName = getParameter(request, "firstName", "");
        String lastName = getParameter(request, "lastName", "");
        String email = getParameter(request, "email", "");
        String phone = getParameter(request, "phone", "");
        String message = getParameter(request, "message", "");
        //String num = getParameter(request, "num");

        Entity commentEntity = new Entity("Comments");
        commentEntity.setProperty("firstName", firstName);
        commentEntity.setProperty("lastName", lastName);
        commentEntity.setProperty("email", email);
        commentEntity.setProperty("phone", phone);
        commentEntity.setProperty("message", message);
        //commentEntity.setProperty("num", num);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(commentEntity);

        response.sendRedirect("/contact.html");
        
    }

   private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }

}//This is the end of the class