let financeManagerDiv = document.getElementById("finance-manager-options");
let requestSubmit = document.getElementById('request-submit');
let statusSubmit = document.getElementById('status-submit');
let passwordSubmit = document.getElementById('password-submit');
let logoutSubmit = document.getElementById('logout-submit');
let welcomeUser = document.getElementById('welcome-user');
let pendingToggle = document.getElementById('all-pending');
let approvedToggle = document.getElementById('all-approved');
let rejectedToggle = document.getElementById('all-rejected');
let pendingEmployeeToggle = document.getElementById('employee-pending');
let approvedEmployeeToggle = document.getElementById('employee-approved');
let rejectedEmployeeToggle = document.getElementById('employee-rejected');
let allRequests = [];
let employeeRequests = [];
const BASE_URL = "http://localhost:9000/";
const getCookieValue = (name) => (
	document.cookie.match('(^|;)\\s*' + name + '\\s*=\\s*([^;]+)')?.pop() || ''
)

const username = getCookieValue("username");
let isFinanceManagerString = getCookieValue("isFinanceManager");

let isFinanceManager = false;
if (isFinanceManagerString === "true") {
	isFinanceManager = true;
}

let formatter = new Intl.NumberFormat('en-US', {
	style: 'currency',
	currency: 'USD'
});

window.addEventListener('load', startup);

async function startup() {
	if (isFinanceManager) {    // if user is a finance manager
		financeManagerDiv.style.display = "block";
		allRequests = await getAllRequests();
		populateAllTable(allRequests);
	} else {
		financeManagerDiv.style.display = "none";
	}
	employeeRequests = await getEmployeeRequests();
	populateEmployeeTable(employeeRequests);
	welcomeUser.innerText = "Welcome, " + username + "!";
}

requestSubmit.addEventListener('click', createNewRequest);
statusSubmit.addEventListener('click', approveOrDenyRequest);
passwordSubmit.addEventListener('click', updatePassword);
logoutSubmit.addEventListener('click', logoutUser);
pendingToggle.addEventListener('change', function () { filterAllTable(allRequests); });
approvedToggle.addEventListener('change', function () { filterAllTable(allRequests); });
rejectedToggle.addEventListener('change', function () { filterAllTable(allRequests); });
pendingEmployeeToggle.addEventListener('change', function () { filterEmployeeTable(employeeRequests); });
approvedEmployeeToggle.addEventListener('change', function () { filterEmployeeTable(employeeRequests); });
rejectedEmployeeToggle.addEventListener('change', function () { filterEmployeeTable(employeeRequests); });

function logoutUser(){
	fetch(BASE_URL + 'logout',{method: 'GET'})
}

function updatePassword() {
	console.log("Hello?");
	let newpassword = document.getElementById("newpassword").value;
	let pass2 = document.getElementById("newpass2").value;
	if (newpassword == pass2) {
		fetch(BASE_URL + 'user', {
			method: 'PUT',
			headers: { 'Content-Type': 'application/x-www-form-url-encoded', 'Accept': 'application/json' },
			body: 'newpassword=' + newpassword
		})
	}
}

function createNewRequest() {
	let amount = document.getElementById('request-amount').value;
	if (amount > 0 && amount != null) {
		let description = document.getElementById('description').value;
		let type = "";
		if (document.getElementById('travel').checked) {
			type = "TRAVEL";
		}
		else if (document.getElementById('lodging').checked) {
			type = "LODGING";
		}
		else if (document.getElementById('food').checked) {
			type = "FOOD";
		}
		else {
			type = "OTHER";
		}
		fetch(BASE_URL + 'request', {
			method: 'POST',
			headers: { 'Content-Type': 'application/x-www-form-url-encoded', 'Accept': 'application/json' },
			body: 'amount=' + amount + '&description=' + description + '&type=' + type
		})
	}
}

async function getEmployeeRequests() {

	const employeeResponse = await fetch(BASE_URL + "employee");

	return employeeResponse.json();

}

function addEmployeeRow(request) {

	let employeeTableBody = document.getElementById("employee-table-body");

	let tableRow = document.createElement("tr");

	let idColumn = document.createElement("td");
	let typeColumn = document.createElement("td");
	let amountColumn = document.createElement("td");
	let descColumn = document.createElement("td");
	let statusColumn = document.createElement("td");

	idColumn.innerText = request.id;
	typeColumn.innerText = request.type;
	amountColumn.innerText = formatter.format(request.amount);
	descColumn.innerText = request.description;
	statusColumn.innerText = request.status;

	tableRow.appendChild(idColumn);
	tableRow.appendChild(typeColumn);
	tableRow.appendChild(amountColumn);
	tableRow.appendChild(descColumn);
	tableRow.appendChild(statusColumn);

	employeeTableBody.appendChild(tableRow);
}

function populateEmployeeTable(requestArray) {

	for (let request of requestArray) {
		addEmployeeRow(request);
	}
}

async function getAllRequests() {

	const response = await fetch(BASE_URL + "all");

	return response.json();
}

function addAllRow(request) {

	let allTableBody = document.getElementById("all-table-body");

	let tableRow = document.createElement("tr");

	let idColumn = document.createElement("td");
	let nameColumn = document.createElement("td");
	let typeColumn = document.createElement("td");
	let amountColumn = document.createElement("td");
	let descColumn = document.createElement("td");
	let statusColumn = document.createElement("td");

	idColumn.innerText = request.id;
	nameColumn.innerText = request.username;
	typeColumn.innerText = request.type;
	amountColumn.innerText = formatter.format(request.amount);
	descColumn.innerText = request.description;
	statusColumn.innerText = request.status;

	tableRow.appendChild(idColumn);
	tableRow.appendChild(nameColumn)
	tableRow.appendChild(typeColumn);
	tableRow.appendChild(amountColumn);
	tableRow.appendChild(descColumn);
	tableRow.appendChild(statusColumn);

	allTableBody.appendChild(tableRow);
}

function populateAllTable(requestArray) {

	for (let request of requestArray) {
		addAllRow(request);
	}
}

function resetTable() {

	let allTableBody = document.getElementById("all-table-body");
	allTableBody.innerHTML = "";
}

function filterAllTable(requestArray) {
	let newArray = [];
	resetTable();
	if (pendingToggle.checked && !approvedToggle.checked && !rejectedToggle.checked) {
		for (let request of requestArray) {
			if (request.status == "PENDING") {
				newArray.push(request);
			}
		}

		populateAllTable(newArray);
	}
	else if (!pendingToggle.checked && approvedToggle.checked && !rejectedToggle.checked) {
		for (let request of requestArray) {
			if (request.status == "APPROVED") {
				newArray.push(request);
			}
		}

		populateAllTable(newArray);
	}
	else if (!pendingToggle.checked && !approvedToggle.checked && rejectedToggle.checked) {
		for (let request of requestArray) {
			if (request.status == "REJECTED") {
				newArray.push(request);
			}
		}

		populateAllTable(newArray);
	}
	else if (pendingToggle.checked && approvedToggle.checked && !rejectedToggle.checked) {
		for (let request of requestArray) {
			if (request.status == "APPROVED" || request.status == "PENDING") {
				newArray.push(request);
			}
		}

		populateAllTable(newArray);
	}
	else if (!pendingToggle.checked && approvedToggle.checked && rejectedToggle.checked) {
		for (let request of requestArray) {
			if (request.status == "APPROVED" || request.status == "REJECTED") {
				newArray.push(request);
			}
		}

		populateAllTable(newArray);
	}
	else if (pendingToggle.checked && !approvedToggle.checked && rejectedToggle.checked) {

		for (let request of requestArray) {
			if (request.status == "REJECTED" || request.status == "PENDING") {
				newArray.push(request);
			}
		}

		populateAllTable(newArray);
	}
	else if (pendingToggle.checked && approvedToggle.checked && rejectedToggle.checked) {

		populateAllTable(requestArray);
	}
	else {
		populateAllTable(newArray);
	}
}

function filterEmployeeTable(requestArray) {
	let employeeArray = [];
	resetEmployeeTable();
	if (pendingEmployeeToggle.checked && !approvedEmployeeToggle.checked && !rejectedEmployeeToggle.checked) {
		for (let request of requestArray) {
			if (request.status == "PENDING") {
				employeeArray.push(request);
			}
		}

		populateEmployeeTable(employeeArray);
	}
	else if (!pendingEmployeeToggle.checked && approvedEmployeeToggle.checked && !rejectedEmployeeToggle.checked) {
		for (let request of requestArray) {
			if (request.status == "APPROVED") {
				employeeArray.push(request);
			}
		}

		populateEmployeeTable(employeeArray);
	}
	else if (!pendingEmployeeToggle.checked && !approvedEmployeeToggle.checked && rejectedEmployeeToggle.checked) {
		for (let request of requestArray) {
			if (request.status == "REJECTED") {
				employeeArray.push(request);
			}
		}

		populateEmployeeTable(employeeArray);
	}
	else if (pendingEmployeeToggle.checked && approvedEmployeeToggle.checked && !rejectedEmployeeToggle.checked) {
		for (let request of requestArray) {
			if (request.status == "APPROVED" || request.status == "PENDING") {
				employeeArray.push(request);
			}
		}

		populateEmployeeTable(employeeArray);
	}
	else if (!pendingEmployeeToggle.checked && approvedEmployeeToggle.checked && rejectedEmployeeToggle.checked) {
		for (let request of requestArray) {
			if (request.status == "APPROVED" || request.status == "REJECTED") {
				employeeArray.push(request);
			}
		}

		populateEmployeeTable(employeeArray);
	}
	else if (pendingEmployeeToggle.checked && !approvedEmployeeToggle.checked && rejectedEmployeeToggle.checked) {

		for (let request of requestArray) {
			if (request.status == "REJECTED" || request.status == "PENDING") {
				employeeArray.push(request);
			}
		}

		populateEmployeeTable(employeeArray);
	}
	else if (pendingEmployeeToggle.checked && approvedEmployeeToggle.checked && rejectedEmployeeToggle.checked) {

		populateEmployeeTable(requestArray);
	}
	else {
		populateEmployeeTable(requestArray);
	}
}

function resetEmployeeTable() {

	let employeeTableBody = document.getElementById("employee-table-body");
	employeeTableBody.innerHTML = "";
}

function approveOrDenyRequest() {
	let id = document.getElementById('request-id').value;
	let status = "";

	if (document.getElementById('approved').checked) {
		status = "APPROVED";
	}
	else {
		status = "REJECTED";
	}

	fetch(BASE_URL + 'request', {
		method: 'PUT',
		headers: { 'Content-Type': 'application/x-www-form-url-encoded', 'Accept': 'application/json' },
		body: 'id=' + id + '&status=' + status
	})
}