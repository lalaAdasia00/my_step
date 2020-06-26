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

package com.google.sps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public final class FindMeetingQuery {

    /*private static final String PERSON_LALA = "Lala";
    private static final String PERSON_KAYLA = "Kayla";
    private static final String PERSON_IFE = "Ife";
    private static final String PERSON_ISIS = "Isis";
    private static final String PERSON_CHARLES = "Charles";
    private static final String PERSON_JASMINE = "Jasmine";
    private static final String PERSON_JORDAN = "Jordan";*/

  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    //throw new UnsupportedOperationException("TODO: Implement this method.");

    //Creating an ArrayList of all the time in a day
    //Going to removed the times that are unavailable so 
    //the list will then be a list of available times
    System.out.println("test");
    ArrayList<TimeRange> availableTimes = new ArrayList<>();
    availableTimes.add(TimeRange.WHOLE_DAY);
    System.out.println(availableTimes);
    //return availableTimes;

    /*Making an array of the desired and optional attendees Collections.copy(arr1, arr2);
    ArrayList<String> reqPeople = new ArrayList<String>();
    reqPeople.add(request.getAttendees());
    ArrayList<String> optPeople = new ArrayList<String>();
    optPeople.add(request.getOptionalAttendee());*/

    //Array list of time slots where people are busy
    ArrayList<TimeRange> busyPeople = new ArrayList<TimeRange>();

    //Iterate through the events 
    for(Event event: events){
        //Iterate through the event attendies
        for(String attendee : event.getAttendees()){
            //finding all the attendees in the meeting request that are in this event
            if(request.getAttendees().contains(attendee)){
                //add the duration of time of the meeting to busyPeople
                busyPeople.add(event.getWhen());
            }
        }
    }
    System.out.println(busyPeople);
    return busyPeople;
  }
}