/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// [START mailjet_imports]
package com.example.compute.mailjet;

import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Email;
// [END mailjet_imports]

import org.json.JSONArray;
import org.json.JSONObject;

// [START app]
public class MailjetSender{
  private static final String MAILJET_API_KEY = System.getenv("50f5221edb38fd27d71ef828904e6040");
  private static final String MAILJET_SECRET_KEY = System.getenv("0f282ed3e5154c755554245cacacebee");
  private static MailjetClient client = new MailjetClient(MAILJET_API_KEY, MAILJET_SECRET_KEY);
  
  public static void main(String[] args) {
    String recipient = "sfan@google.com";
    String sender = "jsf.fan@gmail.com";

    MailjetRequest email = new MailjetRequest(Email.resource)
        .property(Email.FROMEMAIL, sender)
        .property(Email.FROMNAME, "pandora")
        .property(Email.SUBJECT, "Your email flight plan!")
        .property(Email.TEXTPART,
            "Dear passenger, welcome to Mailjet! May the delivery force be with you!")
        .property(Email.HTMLPART,
            "<h3>Dear passenger, welcome to Mailjet!</h3><br/>May the delivery force be with you!")
        .property(Email.RECIPIENTS, new JSONArray().put(new JSONObject().put("Email", recipient)));

    try {
      // trigger the API call
      MailjetResponse response = client.post(email);
      // Read the response data and status
      System.out.println(response.getStatus());
      System.out.println(response.getData());
    } catch (MailjetException e) {
      System.out.println("Mailjet Exception: " + e);
    }
  }
}
// [END app]
