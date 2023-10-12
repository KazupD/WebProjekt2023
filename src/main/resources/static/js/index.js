const filter_button = document.getElementById("filter_button");

const filter_form = document.getElementById("filter_form");

const motor_type_ac = document.getElementById("ac");
const motor_type_dc = document.getElementById("dc");
const motor_type_any = document.getElementById("any");
const power_select = document.getElementById("power");
const min_price_input = document.getElementById("minprice");
const max_price_input = document.getElementById("maxprice");

const remove_filters_button = document.getElementById("remove_filters");
const apply_filters_button = document.getElementById("apply_filters");

const product_container = document.getElementById("product_container");

window.onload = async function (){
    get_all_products();
}

filter_button.addEventListener('click',function ()
{
    if(filter_form.className === "hidden"){
        filter_form.classList.replace("hidden", "filter_form");
        filter_button.textContent = "Hide Filters";
    } else {
        filter_form.classList.replace("filter_form", "hidden");
        filter_button.textContent = "Show Filters";
    }
});

apply_filters_button.addEventListener('click',function ()
{
    get_filtered_products();
});

remove_filters_button.addEventListener('click',function ()
{
    get_all_products();
});

function addProduct(id, image, name, brand, type, power, price){
    const card = document.createElement('div');
    card.id = id;
    card.className = 'product';

    card.innerHTML = `
        <a href="#" class="product_image" title="Click to details">
            <img src="https://picsum.photos/640/360?random=bf1" alt="product">
        </a>
        <a href="#" class="product_name" title="Click to details" id="product_name"></a>
        <h5 class="product_text" id="product_brand"></h5>
        <h5 class="product_text" id="product_type"></h5>
        <h5 class="product_text" id="product_power"></h5>
        <h4 class="product_price" id="product_price"></h4>
        <div>
            <label for="quantity">Quantity</label>
            <input class="number_input" type="number" id="quantity" value="1"/>
            <button class="to_cart_button">Add to cart</button>
        </div>
    `;

    card.querySelector("#product_name").innerText = name;
    card.querySelector("#product_brand").innerText = "Manufacturer: " + brand;
    card.querySelector("#product_type").innerText = "Type: " + type;
    card.querySelector("#product_power").innerText = "Power: " + power;
    card.querySelector("#product_price").innerText = price;


    product_container.appendChild(card);
}

function removeProduct(id) {
    product_container.removeChild(product_container.querySelector(`[id="${id}"]`));
}

function removeAllProducts() {
    while (product_container.firstChild) {
        product_container.removeChild(product_container.lastChild);
    }
}

async function get_all_products(){

    return await fetch('/getallproducts', {
        method: 'POST',
        body: JSON.stringify(
            {}
        )
    }).then(response => response.json())
        .then(response => {
            removeAllProducts();
            for (let i = 0; i < response.length; i++) {
                console.log(response[i]);
                addProduct(id=response[i]["id"], image=response[i]["image_name"],
                    name=response[i]["name"], brand=response[i]["brand"],
                    type=response[i]["type"], power=response[i]["power"],
                    price=response[i]["price"]);
            }

        }).catch(err => console.log(err));

}

async function get_filtered_products(){
    let _type_ = "";
    let _min_power_ = "";
    let _max_power_ = "";
    let _min_price_ = min_price_input.value;
    let _max_price_ = max_price_input.value;
    if(motor_type_ac.checked){_type_="AC";}
    if(motor_type_dc.checked){_type_="DC";}
    if(motor_type_any.checked){_type_="Any";}
    if(power_select.selectedIndex === 0){_min_power_ = 0; _max_power_=99999999;}
    if(power_select.selectedIndex === 1){_min_power_ = 0; _max_power_=100;}
    if(power_select.selectedIndex === 2){_min_power_ = 100; _max_power_=1000;}
    if(power_select.selectedIndex === 3){_min_power_ = 1000; _max_power_=5000;}
    if(power_select.selectedIndex === 4){_min_power_ = 5000; _max_power_=99999999;}
    return await fetch('/filter', {
        method: 'POST',
        body: JSON.stringify(
            {
                type: _type_,
                min_power: _min_power_,
                max_power: _max_power_,
                min_price: _min_price_,
                max_price: _max_price_
            }
        )
    }).then(response => response.json())
        .then(response => {
            removeAllProducts();

            for (let i = 0; i < response.length; i++) {
                console.log(response[i]);
                addProduct(id=response[i]["id"], image=response[i]["image_name"],
                    name=response[i]["name"], brand=response[i]["brand"],
                    type=response[i]["type"], power=response[i]["power"],
                    price=response[i]["price"]);
            }

        }).catch(err => console.log(err));
}