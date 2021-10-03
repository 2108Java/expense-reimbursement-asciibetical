let makeRequest = document.getElementById("make-request");
let viewPast = document.getElementById("view-past");
let viewPending = document.getElementById("view-pending");
let viewAll = document.getElementById("view-all");
let updateStatus = document.getElementById("update-status");
let financeManagerDiv = document.getElementById("finance-manager-options");

window.addEventListener('load',isFinanceManager);

makeRequest.addEventListener('mouseover', hoverEffect);
makeRequest.addEventListener('mouseout', nonHoverEffect);
viewPast.addEventListener('mouseover', hoverEffect);
viewPast.addEventListener('mouseout', nonHoverEffect);
viewPending.addEventListener('mouseover', hoverEffect);
viewPending.addEventListener('mouseout', nonHoverEffect);
viewAll.addEventListener('mouseover', hoverEffect);
viewAll.addEventListener('mouseout', nonHoverEffect);
updateStatus.addEventListener('mouseover', hoverEffect);
updateStatus.addEventListener('mouseout', nonHoverEffect);


function isFinanceManager(){
    if(1>2){    // if user is a finance manager
        financeManagerDiv.style.display = "block";
    }else{
        financeManagerDiv.style.display = "none";
    }

}

function hoverEffect() {
    this.classList.remove("btn-secondary");
    this.classList.add("btn-primary");
}

function nonHoverEffect() {
    this.classList.remove("btn-primary");
    this.classList.add("btn-secondary");
}