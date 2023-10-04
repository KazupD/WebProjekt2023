
const loginform = document.getElementById("loginform");
const adminpage = document.getElementById("adminpage");

const backtomain_button = document.getElementById("backtomain");
const login_button = document.getElementById("login");
const logout_button = document.getElementById("logout");

const name_input = document.getElementById("adminname");
const password_input = document.getElementById("adminpwd");

window.onload = async function () {

    if(sessionStorage.getItem("admintoken") !== ""){
        loginform.classList.add("hidden");
        adminpage.classList.remove("hidden");
    } else {
        loginform.classList.remove("hidden");
        adminpage.classList.add("hidden");
    }
    name_input.value = "";
    password_input.value = "";
}

backtomain_button.addEventListener('click',function ()
{
    location.assign('/');
});

login_button.addEventListener('click',function ()
{
    aname_input = name_input.value;
    apwd_input = password_input.value;

    send_credentials(name=aname_input, password=aname_input)

});

logout_button.addEventListener('click',function ()
{
    sessionStorage.setItem("admintoken", "");
    location.assign('/');
});

async function send_credentials(name, password){
    await fetch('/entercredentials', {
        method: 'POST',
        body: JSON.stringify(
            {
                aname: aname_input,
                apwd: apwd_input
            }
        )
    }).then(response => response.text())
        .then((response) => {
            sessionStorage.setItem("admintoken", response);
            if(sessionStorage.getItem("admintoken") !== ""){
                loginform.classList.add("hidden");
                adminpage.classList.remove("hidden");
            }
            else{
                loginform.classList.remove("hidden");
                adminpage.classList.add("hidden");
                alert("Invalid admin credentials!")
            }
        })
        .catch(err => console.log(err))
}
