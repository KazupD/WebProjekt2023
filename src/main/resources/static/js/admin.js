
let admintoken = "";

const loginform = document.getElementById("loginform");
const adminpage = document.getElementById("adminpage");

const backtomain_button = document.getElementById("backtomain");
const login_button = document.getElementById("login");
const logout_button = document.getElementById("logout");

window.onload = async function () {
    admintoken = "";
    loginform.style.display = "block";
    adminpage.style.display = "none";
}

backtomain_button.addEventListener('click',function ()
{
    location.assign('/');
});

login_button.addEventListener('click',function ()
{
    aname_input = document.getElementById("adminname").value
    apwd_input = document.getElementById("adminpwd").value

    send_credentials(name=aname_input, password=aname_input)

});

logout_button.addEventListener('click',function ()
{
    alert("You are going back to the main page.")
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
            admintoken = response;
            if(admintoken !== ""){
                loginform.style.display = "none";
                adminpage.style.display = "block";
            }
            else{
                loginform.style.display = "block";
                adminpage.style.display = "none";
                alert("Invalid admin credentials!")
            }
        })
        .catch(err => console.log(err))
}
