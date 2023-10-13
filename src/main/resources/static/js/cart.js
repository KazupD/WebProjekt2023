const cart_table = document.getElementById("cart_table");

const total_price_head = document.getElementById("total_price");

const clear_cart_button = document.getElementById("clear_cart");

let total_price = 0;

window.onload = async function (){
    refreshCartTable();
}

clear_cart_button.onclick = function (){
    sessionStorage.setItem("cart", JSON.stringify([]));
    removeAllRow();
    refreshCartTable();
}

function removeAllRow() {
    while (cart_table.firstChild) {
        cart_table.removeChild(cart_table.lastChild);
    }
}

function refreshCartTable(){
    let items_in_cart = JSON.parse(sessionStorage.getItem("cart"));
    if(items_in_cart === null){sessionStorage.setItem("cart", JSON.stringify([]));}
    total_price = 0;
    const table_head = document.createElement("tr");
    table_head.id = "table_head";
    table_head.className = "table_head";
    table_head.innerHTML = `
        <tr>
            <th>Quantity</th>
            <th>Name</th>
            <th>Manufacturer</th>
            <th>Type</th>
            <th>Power</th>
            <th>Price</th>
            <th>+1</th>
            <th>-1</th>
            <th>Drop Item</th>
        </tr>
    `;
    cart_table.appendChild(table_head);

    for (let i = 0; i < items_in_cart.length; i++) {

        total_price += Number(items_in_cart[i]["price"]);
        const table_row = document.createElement("tr");
        let id = items_in_cart[i]["id"]
        table_row.id = id;
        table_row.className = "table_row";

        table_row.innerHTML = `
            <td class="cart_item_important" id="cart_item_quantity${id}"></td>
            <td class="cart_item_common" id="cart_item_name${id}"></td>
            <td class="cart_item_common" id="cart_item_brand${id}"></td>
            <td class="cart_item_common" id="cart_item_type${id}"></td>
            <td class="cart_item_common" id="cart_item_power${id}"></td>
            <td class="cart_item_important" id="cart_item_price${id}"></td>
            <td class="cart_item_handle" id="cart_item_plus${id}">
                <button class="handle_button" id="plus_button${id}">+1</button>
            </td>
            <td class="cart_item_handle" id="cart_item_minus${id}">
                <button class="handle_button" id="minus_button${id}">+1</button>
            </td>
            <td class="cart_item_handle" id="cart_item_drop${id}">
                <button class="handle_button" id="drop_button${id}">Drop</button>
            </td>
        `;

        table_row.querySelector(`#cart_item_quantity${id}`).innerText = String(items_in_cart[i]["quantity"]);
        table_row.querySelector(`#cart_item_name${id}`).innerText = String(items_in_cart[i]["name"]);
        table_row.querySelector(`#cart_item_brand${id}`).innerText = String(items_in_cart[i]["brand"]);
        table_row.querySelector(`#cart_item_type${id}`).innerText = String(items_in_cart[i]["type"]);
        table_row.querySelector(`#cart_item_power${id}`).innerText = String(items_in_cart[i]["power"]);
        table_row.querySelector(`#cart_item_price${id}`).innerText = String(items_in_cart[i]["price"]);

        cart_table.appendChild(table_row);

    }
    total_price_head.textContent = "Total: " + String(total_price);
}