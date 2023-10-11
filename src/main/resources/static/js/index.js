const filter_button = document.getElementById("filterbutton");

const filter_form = document.getElementById("filterform");

const product_container = document.getElementById("product_container");

window.onload = async function (){
    filter_form.classList.add("hidden");
    filter_button.textContent = "Show Filters"

    addProduct(id="1", image="asd",name="MaxonXYZ",
        type="DC", power="50W", rpm="3000", price="200$");

    addProduct(id="2", image="asd",name="MaxonXYZ2",
        type="DC", power="60W", rpm="3500", price="250$");

    addProduct(id="3", image="asd",name="MaxonXYZ3",
        type="DC", power="70W", rpm="4000", price="300$");

    addProduct(id="4", image="asd",name="MaxonXYZ4",
        type="DC", power="80W", rpm="4500", price="350$");
}

filter_button.addEventListener('click',function ()
{
    if(filter_form.className === "hidden"){
        filter_form.classList.remove("hidden");
        filter_button.textContent = "Hide Filters";
    } else {
        filter_form.classList.add("hidden");
        filter_button.textContent = "Show Filters";
    }
});

function addProduct(id, image, name, type, power, rpm, price){
    const card = document.createElement('card');
    card.id = id;
    card.className = 'product';


    card.innerHTML = `
    <a href="#" class="product_image" title="Click to details">
        <img src="https://picsum.photos/640/360?random=bf1" alt="product">
    </a>
    <a href="#" class="product_name" title="Click to details">`+ name +`</a>
    <h5 class="product_text">Type:`+ type +`</h5>
    <h5 class="product_text">Power:`+ power +`</h5>
    <h5 class="product_text">RPM:`+ rpm +`</h5>
    <h4 class="product_price">Price:`+ price +`</h4>
    <button>Add to cart</button>
    `;

    product_container.appendChild(card);
}

function removeProduct(id) {
    product_container.removeChild(product_container.getElementById(id));
}