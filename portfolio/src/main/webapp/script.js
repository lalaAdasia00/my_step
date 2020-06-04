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

async function getRandomIntroUsingAsyncAwait() {
  const response = await fetch('/data');
  const intro = await response.text();
  document.getElementById('intro-container').innerText = intro;
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