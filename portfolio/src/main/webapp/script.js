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

//These are for the Google charts :)
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(pieChart);

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings=
      ['I make excellence the standard and not the goal!', 'My favorite color is PINK!', 'I love to play basketball', 'I am a musician'];

  // Pick a random fact.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}


//This is practice code for using fetch()

/*async function getRandomIntroUsingAsyncAwait() {
  const response = await fetch('/data');
  const intro = await response.text();
  document.getElementById('intro-container').innerText = intro;
}
*/
//using JSON

function getComment(){
    
    var value = document.getElementById("quantity").value;
    fetch('/comment?num='+value).then(response => response.json()).then((comment) => {
        
        const node = document.getElementById('comment-container');
        while(node.firstChild){
            node.removeChild(node.firstChild);
        }
        document.getElementById('comment-container').appendChild(convertObjects(comment));
        console.log(comment);
    });
}

function convertObjects(comment){

    const node = document.createElement("ul");

    for(let i = 0; i < comment.length; i++){

        const nextNode = document.createElement("li");
        nextNode.innerText = comment[i].firstName + "," + comment[i].lastName + "," + comment[i].message;
        node.appendChild(nextNode);

    }
    return node;
}

//Creating the pie charts
function pieChart(){

    //Chart code for incarcerated
    var data = google.visualization.arrayToDataTable([
          ['Race','Incarcerated'],
          ['White', 796],
          ['Hispanic', 1908],
          ['Black', 4607]
    ]);

    var options = {

          title: 'Men and women Incarcerated in the US',
          is3D: true,
    };

    var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
    chart.draw(data, options);
    //---------------------------------------------------------------------------------//

    //chart code for educated
    var data2 = google.visualization.arrayToDataTable([
        ['Race', 'Educated'],
        ['White ', 34084],
        ['Hispanic', 2652],
        ['Black', 3259]
    ]);

    var options2 = {

        title: 'College Graduates in the US',
        is3D: true,
    };
    
    var chart2 = new google.visualization.PieChart(document.getElementById('piechart_3d2'));
    chart2.draw(data2, options2);
    //------------------------------------------------------------------------------------//
}

//Function to creat a Map on the main portfolio page
function createMap() {
  const map = new google.maps.Map(
      document.getElementById('map'), {
          center: {lat: 29.749907, lng: -95.358421},
          zoom: 4
        }
    );
    //This is the marker for where I was born
    const grMarker = new google.maps.Marker({
        position: {lat: 42.963795, lng: -85.670006},
        map: map,
        title: 'Where I was born'
    });

    //This is the marker for where I was raised
    const htMarker = new google.maps.Marker({
        position: {lat: 29.749907, lng: -95.358421},
        map: map,
        title: 'Where I was raised'
    });

    //This is the marker for where I go to college
    const hvMarker = new google.maps.Marker({
        position: {lat: 37.034946, lng: -76.360123},
        map: map,
        title: 'Where I attend college'
    });
}


/*function addNameToDom(name) {
  console.log('Adding quote to dom: ' + name);

  const nameContainer = document.getElementById('name-container');
  nameContainer.innerText = quote;
}


function handleResponse(response) {
  console.log('Handling the response.');

  const textPromise = response.text();

  textPromise.then(addNameToDom);
}


function getRandomName() {
  console.log('Fetching a random name.');

  const responsePromise = fetch('/random-name');

  responsePromise.then(handleResponse);
}

//Or 
async function getRandomNameUsingAsyncAwait() {
  const response = await fetch('/random-name');
  const name = await response.text();
  document.getElementById('name-container').innerText = name;
}*/