let financeManagerDiv = document.getElementById("finance-manager-options");

window.addEventListener('load',startup);

function startup(){
    if(4>2){    // if user is a finance manager
        financeManagerDiv.style.display = "block";
    }else{
        financeManagerDiv.style.display = "none";
    }

}
