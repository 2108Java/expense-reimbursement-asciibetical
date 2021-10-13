let makeRequest = document.getElementById("make-request");
let viewPast = document.getElementById("view-past");
let viewPending = document.getElementById("view-pending");
let viewAll = document.getElementById("view-all");
let updateStatus = document.getElementById("update-status");
let financeManagerDiv = document.getElementById("finance-manager-options");

window.addEventListener('load', startup);
window.addEventListener('load', on);

function startup() {
    if (4 > 2) {    // if user is a finance manager
        financeManagerDiv.style.display = "block";
    } else {
        financeManagerDiv.style.display = "none";
    }

}

function on() {
    document.getElementById("overlay").style.display = "block";
}

function off() {
    document.getElementById("overlay").style.display = "none";
}