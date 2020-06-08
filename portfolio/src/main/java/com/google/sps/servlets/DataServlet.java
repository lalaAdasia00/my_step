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
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
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
      comments = new ArrayList<>();
      comments.add("Comment number 1 from server");
      comments.add("Comment number 2 from server");
      comments.add("Comment number 3 from server");
      
      String json = convertToJsonUsingGson(comments);
      

      response.setContentType("application/json;");
      response.getWriter().println(json);
    }

    private String convertToJsonUsingGson(ArrayList comments){
        Gson gson = new Gson();
        String json = gson.toJson(comments);
        return json;
    }

    
    //The forms section

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    String text = getParameter(request, "first-name", "");
    String text2 = getParameter(request, "last-name", "");
    String email = getParameter(request, "email", "");
    String phone = getParameter(request, "phone", "");
    String message = getParameter(request, "message", "");

    Entity taskEntity = new Entity("Task");
    taskEntity.setProperty("first-name", text);
    taskEntity.setProperty("last-name", text2);
    taskEntity.setProperty("email", email);
    taskEntity.setProperty("phone", phone);
    taskEntity.setProperty("message", message);
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(taskEntity);

    response.setContentType("text/html;");
    response.getWriter().println(text);
  }

  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }
}