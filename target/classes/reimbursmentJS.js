let login = document.getElementById("login");
let register = document.getElementById("register");
let loginForm = document.getElementById("login-form");
let registerForm = document.getElementById("register-form");

login.addEventListener('click', loginMenu);
register.addEventListener('click', registerMenu);

function loginMenu(){
    registerForm.style.display="none";
    register.classList.remove("text-primary");
    register.classList.add("text-secondary");
    login.classList.remove("text-secondary");
    login.classList.add("text-primary");
    loginForm.style.display="block";
}

function registerMenu(){
    loginForm.style.display="none";
    login.classList.remove("text-primary");
    login.classList.add("text-secondary");
    register.classList.remove("text-secondary");
    register.classList.add("text-primary");
    registerForm.style.display="block";
}
