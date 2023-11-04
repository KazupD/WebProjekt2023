const filter_button = document.getElementById("filter_button");

const filter_form = document.getElementById("filter_form");

const motor_type_ac = document.getElementById("ac");
const motor_type_dc = document.getElementById("dc");
const motor_type_any = document.getElementById("any");
const power_select = document.getElementById("power");
const min_price_input = document.getElementById("minprice");
const max_price_input = document.getElementById("maxprice");

const motor_details = {
    /* General */
    "nominal_rpm" : ["Nominal RPM:", "1/min"],
    "nominal_voltage" : ["Nominal voltage:", "V"],
    /* AC specific */
    "nominal_current" : ["Nominal current:", "A"],
    "slip" : ["Slip:", "%"],
    "cosine_phi" : ["Cosine Phi:", "-"],
    "pole_pairs" : ["Pole pairs:", "-"],
    "phase_number" : ["Phase number:", "-"],
    /* DC specific */
    "stall_current" : ["Stall current:", "A"],
    "stall_torque" : ["Stall torque:", "Nm"],
    "power_factor" : ["Power factor:", "-"],
    "steady_state_current" : ["Steady-state current:", "A"],
    "steady_state_torque" : ["Steady-state torque:", "Nm"],
    "commutator_segments" : ["Commutator segments:", "-"]
}

const remove_filters_button = document.getElementById("remove_filters");
const apply_filters_button = document.getElementById("apply_filters");

const info_modal = document.getElementById("infoModal");
const info_modal_data = document.getElementById("infoModalData");
const modal_close_btn = document.getElementById("modalCloseBtn");

const product_container = document.getElementById("product_container");

const MAXPOWER = 1000000;

window.onload = async function (){
    get_all_products();
}

filter_button.onclick = function ()
{
    if(filter_form.className === "hidden"){
        filter_form.classList.replace("hidden", "filter_form");
        filter_button.textContent = "Hide Filters";
    } else {
        filter_form.classList.replace("filter_form", "hidden");
        filter_button.textContent = "Show Filters";
    }
};

apply_filters_button.onclick = function ()
{
    get_filtered_products();
};

remove_filters_button.onclick = function ()
{
    get_all_products();
};

function addProduct(product){
    const card = document.createElement('div');
    card.id = product.id;
    card.className = 'product';

    card.innerHTML = `
        <img src="../images/product_imgs/${product.image}.png" class="product_image" onclick="showProductDetails('${product.id}','${product.name}','${product.brand}','${product.type}','${product.power}','${product.price}','${product.image}')" alt="Motor">
        <div class="product_text_container">
            <div>
                <h4 class="product_name" id="product_name${product.id}" onclick="showProductDetails('${product.id}','${product.name}','${product.brand}','${product.type}','${product.power}','${product.price}','${product.image}')"></h4>
                <h5 class="product_text" id="product_brand${product.id}"></h5>
                <h5 class="product_text" id="product_type${product.id}"></h5>
                <h5 class="product_text" id="product_power${product.id}"></h5>
                <h4 class="product_price" id="product_price${product.id}"></h4>
                <div class="card_input_div">
                    <div class="quantity_div">
                        <label for="quantity${product.id}">Quantity:</label><br>
                        <input class="number_input" type="number" id="quantity${product.id}" value="1" min="1" max="10000"/>
                    </div>
                    <div class="to_cart_div">
                        <button class="to_cart_button" id="to_cart_button${product.id}" onClick="addToCart('${product.id}','${product.name}','${product.brand}','${product.type}','${product.power}','${product.price}')">To Cart</button>
                    </div>
                <div>
            </div>
        </div>
    `;

    card.querySelector(`#product_name${product.id}`).innerText = product.name;
    card.querySelector(`#product_brand${product.id}`).innerText = "Manufacturer: " + product.brand;
    card.querySelector(`#product_type${product.id}`).innerText = "Type: " + product.type;
    card.querySelector(`#product_power${product.id}`).innerText = "Power: " + product.power;
    card.querySelector(`#product_price${product.id}`).innerText = product.price;

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

function showProductDetails(id,name,brand,type,power,price, image){ // Need lost of arguments because it's called from HTML
    info_modal.style.display = "block";

    let detailsPromise = get_motor_details(id, type);
    let details;

    info_modal_data.innerHTML = `
        <img src="../images/product_imgs/${image}.png" class="product_image" alt="Motor">
        <h4 class="detail_name" id="detail_name${id}"></h4>
        <h5 class="detail_text" id="detail_brand${id}"></h5>
        <h5 class="detail_text" id="detail_type${id}"></h5>
        <h5 class="detail_text" id="detail_power${id}"></h5>
        <h4 class="detail_price" id="detail_price${id}"></h4>
        <h4 class="detail_header">Details:</h4>
        <table class="detailTable">
            <tbody class="detail_container" id="detail_container">
            </tbody>
        </table>
        <div class="card_input_div">
            <div class="quantity_div">
                <label for="quantity${id}">Quantity:</label><br>
                <input class="number_input" type="number" id="quantity${id}" value="1" min="1" max="10000"/>
            </div>
            <div class="to_cart_div">
                <button class="to_cart_button" id="to_cart_button${id}" onClick="addToCart('${id}','${name}','${brand}','${type}','${power}','${price}')">To Cart</button>
            </div>
        <div>
    `;

    info_modal_data.querySelector(`#detail_name${id}`).innerText = name;
    info_modal_data.querySelector(`#detail_brand${id}`).innerText = "Manufacturer: " + brand;
    info_modal_data.querySelector(`#detail_type${id}`).innerText = "Type: " + type;
    info_modal_data.querySelector(`#detail_power${id}`).innerText = "Power: " + power;
    info_modal_data.querySelector(`#detail_price${id}`).innerText = "Price: " + price;

    detailsPromise.then(
        function (value) { displayDetails(value); },
        function (error) { console.log(error); }
    );
    let detailContainer = info_modal_data.querySelector(`#detail_container`);
    function displayDetails(details) {
        detailContainer.innerHTML = '';
        Object.keys(details).forEach(function (prop) {
            detailContainer.innerHTML += `
                <tr>
                    <td class="detail_list_element">${motor_details[prop][0]}</td>
                    <td class="detail_list_element">${details[prop]}</td>
                    <td class="detail_list_element">${motor_details[prop][1]}</td>
                </tr>
            `;
        })
    }
}

modal_close_btn.onclick = function () {
    info_modal.style.display = "none";
}

// When the user clicks anywhere outside the modal, close it
window.onclick = function (event) {
    /* The whole page is filled by the modal except the modal_content */
    if (event.target === info_modal) {
        info_modal.style.display = "none"
    }
}

function addToCart(id, name, brand, type, power, price){

    id=Number(id);
    power=Number(power);
    price=Number(price);
    let quantity = document.getElementById(`quantity${id}`).value;

    if(quantity < 1 || Number.isInteger(Number(quantity)) === false){
        document.getElementById(`quantity${id}`).value = 1;
        alert("Quantity must be positive integer")
        return;
    }

    let items_in_cart = JSON.parse(sessionStorage.getItem("cart"));
    if(items_in_cart === null){sessionStorage.setItem("cart", JSON.stringify([]));}
    items_in_cart = JSON.parse(sessionStorage.getItem("cart"));

    for (let i = 0; i < items_in_cart.length; i++) {
        if(items_in_cart[i]["id"] === id){
            items_in_cart[i]["quantity"] += Number(quantity);
            items_in_cart[i]["price_sum"] += price;
            sessionStorage.setItem("cart", JSON.stringify(items_in_cart));
            items_in_cart = sessionStorage.getItem("cart");
            console.log(items_in_cart);
            return;
        }
    }
    items_in_cart.push(
        {"id": Number(id),
        "quantity": Number(quantity),
        "name": name,
        "brand": brand,
        "type": type,
        "power": Number(power),
        "price": Number(price),
        "price_sum": Number(price)*Number(quantity)}
    );
    sessionStorage.setItem("cart", JSON.stringify(items_in_cart));

    items_in_cart = sessionStorage.getItem("cart");
    console.log(items_in_cart);
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
                let product = {
                    id : response[i]["id"],
                    image : response[i]["image_name"],
                    name : response[i]["name"],
                    brand : response[i]["brand"],
                    type : response[i]["type"],
                    power : response[i]["power"],
                    price : response[i]["price"]
                };
                addProduct(product);
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
    if(power_select.selectedIndex === 0){_min_power_ = 0; _max_power_=MAXPOWER;}
    if(power_select.selectedIndex === 1){_min_power_ = 0; _max_power_=100;}
    if(power_select.selectedIndex === 2){_min_power_ = 100; _max_power_=1000;}
    if(power_select.selectedIndex === 3){_min_power_ = 1000; _max_power_=5000;}
    if(power_select.selectedIndex === 4){_min_power_ = 5000; _max_power_=MAXPOWER;}
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
                let product = {
                    id : response[i]["id"],
                    image : response[i]["image_name"],
                    name : response[i]["name"],
                    brand : response[i]["brand"],
                    type : response[i]["type"],
                    power : response[i]["power"],
                    price : response[i]["price"]
                };
                addProduct(product);
            }

        }).catch(err => console.log(err));
}

async function get_motor_details(id, type){
    return await fetch('/getmotordetails', {
        method: 'POST',
        body: JSON.stringify(
            {
                motor_type: type,
                motor_id: id
            }
        )
    }).then(response => response.json())
        .then(response => {
            return response
        }).catch(err => console.log(err));
}
