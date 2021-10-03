let login = document.getElementById("login-btn");
let register = document.getElementById("register-btn");
let loginForm = document.getElementById("login-form");
let registerForm = document.getElementById("register-form");
let loginSubmit = document.getElementById("login-submit");
let registerSubmit = document.getElementById("register-submit");

login.addEventListener('click', loginMenu);
register.addEventListener('click', registerMenu);
// loginSubmit.addEventListener('click', null);    // TODO
// registerSubmit.addEventListener('click', null); //TODO

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