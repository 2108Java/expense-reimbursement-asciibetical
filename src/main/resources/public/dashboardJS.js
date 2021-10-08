let makeRequest = document.getElementById("make-request");
let viewPast = document.getElementById("view-past");
let viewPending = document.getElementById("view-pending");
let viewAll = document.getElementById("view-all");
let updateStatus = document.getElementById("update-status");
let financeManagerDiv = document.getElementById("finance-manager-options");
const BASE_URL = "http://localhost:9000/";

window.addEventListener('load',startup);

function startup(){
    if(4>2){    // if user is a finance manager
        financeManagerDiv.style.display = "block";
    }else{
        financeManagerDiv.style.display = "none";
    }
    
    
}

window.onload = function(){

	//getAllRequests();
	
}

function createNewRequest() {
	let amount = document.getElementById("request-amount").value;
	let description = document.getElementById("description").value;
	let type = "";
	if(document.getElementById("travel").checked) {
		type = "TRAVEL";
	}
	else if(document.getElementById("lodging").checked) {
		type = "LODGING";
	}
	else if(document.getElementById("food").checked) {
		type = "FOOD";
	}
	else {
		type = "OTHER";
	}
	
	let request = new RequestToBeSent ();
	
	//TODO: send POST request
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

function getPastRequests() {
	
	
	
}

function addPastRow(request) {
	
	let pastTableBody = document.getElementById("past-table-body");
	
	let tableRow = document.createElement("tr");
	
	let idColumn = document.createElement("td");
	let typeColumn = document.createElement("td");
	let amountColumn = document.createElement("td");
	let descColumn = document.createElement("td");
	let statusColumn = document.createElement("td");
	
	idColumn.innerText = request.id;
	typeColumn.innerText = request.type;
	amountColumn.innerText = request.amount;
	descColumn.innerText = request.description;
	statusColumn.innerText = request.status;
	
	tableRow.appendChild(idColumn);
	tableRow.appendChild(typeColumn);
	tableRow.appendChild(amountColumn);
	tableRow.appendChild(descColumn);
	tableRow.appendChild(statusColumn);
	
	pastTableBody.appendChild(tableRow);
}

function populatePastTable(requestArray) {
	
	for(let request of requestArray){
		addPastRow(request);
	}
}

function getPendingRequests() {
	
	
	
}

function addPendingRow(request) {
	
	let pendingTableBody = document.getElementById("pending-table-body");
	
	let tableRow = document.createElement("tr");
	
	let idColumn = document.createElement("td");
	let typeColumn = document.createElement("td");
	let amountColumn = document.createElement("td");
	let descColumn = document.createElement("td");
	let statusColumn = document.createElement("td");
	
	idColumn.innerText = request.id;
	typeColumn.innerText = request.type;
	amountColumn.innerText = request.amount;
	descColumn.innerText = request.description;
	statusColumn.innerText = request.status;
	
	tableRow.appendChild(idColumn);
	tableRow.appendChild(typeColumn);
	tableRow.appendChild(amountColumn);
	tableRow.appendChild(descColumn);
	tableRow.appendChild(statusColumn);
	
	pendingTableBody.appendChild(tableRow);
}

function populatePendingTable(requestArray) {
	
	for(let request of requestArray){
		addPendingRow(request);
	}
}

function getAllRequests() {
	
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function (){ 
		
		console.log(this.readyState);
		
		if(this.readyState == 4 && this.status == 200){
			
			let requestArray = JSON.parse(this.responseText);
			console.log(requestArray);
			
		}
	}
	
	xhttp.open("GET","http://localhost:9000/all");
	
	xhttp.send();
	
//	fetch(BASE_URL + "all")
  //		.then(response => response.json())
  //		.then(data => console.log(data));
	
}

function addAllRow(request) {
	
	let allTableBody = document.getElementById("all-table-body");
	
	let tableRow = document.createElement("tr");
	
	let idColumn = document.createElement("td");
	let typeColumn = document.createElement("td");
	let amountColumn = document.createElement("td");
	let descColumn = document.createElement("td");
	let statusColumn = document.createElement("td");
	
	idColumn.innerText = request.id;
	typeColumn.innerText = request.type;
	amountColumn.innerText = request.amount;
	descColumn.innerText = request.description;
	statusColumn.innerText = request.status;
	
	tableRow.appendChild(idColumn);
	tableRow.appendChild(typeColumn);
	tableRow.appendChild(amountColumn);
	tableRow.appendChild(descColumn);
	tableRow.appendChild(statusColumn);
	
	allTableBody.appendChild(tableRow);
}

function populateAllTable(requestArray) {
	
	for(let request of requestArray){
		addAllRow(request);
	}
}

function resetTable() {
	let allTableRows = document.getElementById("all-table-body").getElementsByTagName("tr");
	
	for(let row of allTableRows) {
		row.remove();
	}
}

function approveOrDenyRequest() {
	let id = document.getElementById("request-id").innerText;
	let status = "";
	
	if (document.getElementById("approved").checked) {
		status = "APPROVED";
	}
	else if (document.getElementById("denied").checked) {
		status = "REJECTED";
	}
	else {
		//TODO: Some reminder that the user never selected an option
	}
	
	//TODO: Send POST request
}

function RequestToBeSent(type, amount, description) {
	this.type = type;
	this.amount = amount;
	this.desciption = description;
}

function ReimRequest( id, username, type, amount, description, timestamp, status) {
		this.id = id;
		this.username = username;
		this.type = type;
		this.amount = amount;
		this.description = description;
		this.timestamp = timestamp;
		this.status = status;
}