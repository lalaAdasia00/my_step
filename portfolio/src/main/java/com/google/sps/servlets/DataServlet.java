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

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/comment")
public class DataServlet extends HttpServlet {

    
  private ArrayList<String>comments;
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      Query query = new Query("Comments").addSort("first-name", SortDirection.DESCENDING);

      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      PreparedQuery results = datastore.prepare(query);

      List<Comments> comments = new ArrayList<>();
      for (Entity entity : results.asIterable()) {
          String text = (String) entity.getProperty("first-name");
          String text2 = (String) entity.getProperty("last-name");
          String email = (String) entity.getProperty("email");
          String phone = (String) entity.getProperty("phone");
          String message = (String) entity.getProperty("message");

          Comments comment = new Comments(text, text2, email, phone, message);
          comments.add(comment);
        }
      
      //String json = convertToJsonUsingGson(comments);
      Gson gson = new Gson();
      

      response.setContentType("application/json;");
      response.getWriter().println(gson.toJson(comments));
    }

    /*private String convertToJsonUsingGson(ArrayList comments){
        Gson gson = new Gson();
        String json = gson.toJson(comments);
        return json;
    }*/

    
    //The forms section

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    String text = getParameter(request, "first-name", "");
    String text2 = getParameter(request, "last-name", "");
    String email = getParameter(request, "email", "");
    String phone = getParameter(request, "phone", "");
    String message = getParameter(request, "message", "");

    Entity commentEntity = new Entity("Comments");
    commentEntity.setProperty("first-name", text);
    commentEntity.setProperty("last-name", text2);
    commentEntity.setProperty("email", email);
    commentEntity.setProperty("phone", phone);
    commentEntity.setProperty("message", message);
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(commentEntity);

    /*response.setContentType("text/html;");
    response.getWriter().println(text);
    response.getWriter().println(text2);
    response.getWriter().println(email);
    response.getWriter().println(phone);
    response.getWriter().println(message);*/

    response.sendRedirect("/contact.html");
        
  }

  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }
}