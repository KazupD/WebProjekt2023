const backtomain_button = document.getElementById("backtomain");
const login_button = document.getElementById("login");

backtomain_button.addEventListener('click',function ()
{
    location.assign('/');
});

login_button.addEventListener('click',function ()
{
    aname = document.getElementById("adminname").value
    apwd = document.getElementById("adminpwd").value

    location.assign('/admin?' + "aname=" + aname + '&' + "apwd=" + apwd);
});