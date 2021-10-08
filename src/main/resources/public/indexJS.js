let login = document.getElementById("login-btn");
let register = document.getElementById("register-btn");
let loginForm = document.getElementById("login-form");
let registerForm = document.getElementById("register-form");
let loginSubmit = document.getElementById("login-submit");
let registerSubmit = document.getElementById("register-submit");
let username = document.getElementById('username').innerText;
let password = document.getElementById('password').innerText;
let email = document.getElementById('email').innerText;
const BASE_URL = "http://localhost:9000/";

login.addEventListener('click', loginMenu);
register.addEventListener('click', registerMenu);
loginSubmit.addEventListener('click', authenticate(username, password));
registerSubmit.addEventListener('click', registerAccount(username, email));

function loginMenu() {
    registerForm.style.display = "none";
    register.classList.remove("btn-primary");
    register.classList.add("btn-secondary");
    login.classList.remove("btn-secondary");
    login.classList.add("btn-primary");
    loginForm.style.display = "block";
}

function registerMenu() {
    loginForm.style.display = "none";
    login.classList.remove("btn-primary");
    login.classList.add("btn-secondary");
    register.classList.remove("btn-secondary");
    register.classList.add("btn-primary");
    registerForm.style.display = "block";
}

function User(username, password, email) {
	this.username = username;
	this.password = password;
	this.email = email;
}
 
function registerAccount(username, email) {
	let user = new User(username, null, email);
	
	let xhttp = new XMLHttpRequest();
	
	xhttp.open("POST", BASE_URL + "register", true);
	
	xhttp.setRequestHeader('Content-type', 'application/json');
	
	xhttp.onreadystatechange = function(){
		if(this.status == 200 && this.readyState == 4) {
			let response = JSON.parse(this.responseText);
			
			console.log(response);
		}
	}
	
	xhttp.send(JSON.stringify(user));
}

function authenticate(username, password) {
	let user = new User(username, password, null);
	//fetch(BASE_URL + 'authenticate', {
  	//	method: 'POST',
 	//	 headers: {
    //	'Content-Type': 'application/json',
  	//	},
  	//	body: JSON.stringify(user),
	//	})
	//.then(response => response.json())
	//.then(user => {
  	//	console.log('Success:', user);
	//})
	//.catch((error) => {
  	//	console.error('Error:', error);
	//});
	
	let xhttp = new XMLHttpRequest();
	
	xhttp.open('POST', BASE_URL + 'authenticate', true);
	
	xhttp.setRequestHeader('Content-type', 'application/json');
	
	xhttp.onreadystatechange = function(){
		if(this.status == 200 && this.readyState == 4) {
			let response = JSON.parse(this.responseText);
			
			console.log(response);
		}
	}
	
	xhttp.send(JSON.stringify(user));
}