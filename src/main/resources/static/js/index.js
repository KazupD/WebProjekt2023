const filter_button = document.getElementById("filterbutton");

const filterform = document.getElementById("filterform");

window.onload = async function (){
    filterform.classList.add("hidden");
    filter_button.textContent = "Show Filters"
}

filter_button.addEventListener('click',function ()
{
    if(filterform.className === "hidden"){
        filterform.classList.remove("hidden");
        filter_button.textContent = "Hide Filters";
    } else {
        filterform.classList.add("hidden");
        filter_button.textContent = "Show Filters";
    }
});