
const loginform = document.getElementById("loginform");
const adminpage = document.getElementById("adminpage");

const backtomain_button = document.getElementById("backtomain");
const login_button = document.getElementById("login");
const logout_button = document.getElementById("logout");
const show_orders_button = document.getElementById("show_orders");

const name_input = document.getElementById("adminname");
const password_input = document.getElementById("adminpwd");

const order_review_section = document.getElementById("order_review");

window.onload = async function () {

    if(sessionStorage.getItem("admintoken") !== ""){
        loginform.classList.replace("loginform", "hidden");
        adminpage.classList.replace("hidden", "adminpage");
    } else {
        loginform.classList.replace("hidden", "loginform");
        adminpage.classList.replace("adminpage", "hidden");
    }
    name_input.value = "";
    password_input.value = "";
}

backtomain_button.onclick = function ()
{
    location.assign('/');
};

login_button.onclick = function ()
{
    aname_input = name_input.value;
    apwd_input = hash('SHA-256', password_input.value);

    console.log(hash('SHA-256', password_input.value))

    send_credentials(name=aname_input, password=apwd_input)

};

logout_button.onclick = function ()
{
    sessionStorage.setItem("admintoken", "");
    location.assign('/');
};

show_orders_button.onclick = function (){
    if(sessionStorage.getItem("admintoken") !== ""){
        refreshReviewTable();
    }
}

function hash(algorithm, message) {
    const shaObj = new jsSHA(algorithm, 'TEXT');
    shaObj.update(message);
    return shaObj.getHash('HEX');
}

function showNextOrder(order_with_customer){

    let customer_details = order_with_customer["customer_details"];

    const customer_detail_and_order_block = document.createElement('div');
    const c_id = customer_details["id"];
    customer_detail_and_order_block.id = c_id;
    customer_detail_and_order_block.className = 'customer_detail_and_order_block';

    const customer_detail_table = document.createElement('table');
    customer_detail_table.id = "customer_"+c_id;
    customer_detail_table.className = 'customer_detail_table';

    customer_detail_table.innerHTML = `
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Tel</th>
            <th>City</th>
            <th>Street</th>
            <th>Building</th>
        </tr>
        <tr>
            <td id="customer_${c_id}_id"></td>
            <td id="customer_${c_id}_first_name"></td>
            <td id="customer_${c_id}_last_name"></td>
            <td id="customer_${c_id}_email"></td>
            <td id="customer_${c_id}_tel"></td>
            <td id="customer_${c_id}_city"></td>
            <td id="customer_${c_id}_street"></td>
            <td id="customer_${c_id}_building"></td>
        </tr>
    `;

    customer_detail_table.querySelector(`#customer_${c_id}_id`).innerText = c_id;
    customer_detail_table.querySelector(`#customer_${c_id}_first_name`).innerText = customer_details["first_name"];
    customer_detail_table.querySelector(`#customer_${c_id}_last_name`).innerText = customer_details["last_name"];
    customer_detail_table.querySelector(`#customer_${c_id}_email`).innerText = customer_details["email"];
    customer_detail_table.querySelector(`#customer_${c_id}_tel`).innerText = customer_details["tel_num"];
    customer_detail_table.querySelector(`#customer_${c_id}_city`).innerText = customer_details["shipping_city"];
    customer_detail_table.querySelector(`#customer_${c_id}_street`).innerText = customer_details["shipping_street"];
    customer_detail_table.querySelector(`#customer_${c_id}_building`).innerText = customer_details["shipping_building"];

    customer_detail_and_order_block.appendChild(customer_detail_table);

    let total_price = 0;
    let customer_orders = order_with_customer["customer_orders"];

    const customer_orders_table = document.createElement('table');
    customer_orders_table.id = "customer_orders_table_"+c_id;
    customer_orders_table.className = 'customer_orders_table';

    const customer_orders_table_head = document.createElement('tr');
    customer_orders_table_head.id = "customer_orders_table_head_"+c_id;
    customer_orders_table_head.className = 'customer_orders_table_head';

    customer_orders_table_head.innerHTML = `
        <th>Product ID</th>
        <th>Product Name</th>
        <th>Unit Price</th>
        <th>Quantity</th>
        <th>Price</th>`;

    customer_orders_table.appendChild(customer_orders_table_head);

    for (let j = 0; j < customer_orders.length; j++) {

        total_price += customer_orders[j]["price_sum"];
        let p_id = customer_orders[j]["product_id"];

        const customer_orders_table_row = document.createElement('tr');
        customer_orders_table_row.id = "customer_orders_table_head_"+c_id+"_"+p_id;
        customer_orders_table_row.className = 'customer_orders_table_row';

        customer_orders_table_row.innerHTML = `
            <td id="product_id_${c_id}_${p_id}"></td>
            <td id="product_name_${c_id}_${p_id}"></td>
            <td id="unit_price_${c_id}_${p_id}"></td>
            <td id="quantity_${c_id}_${p_id}"></td>
            <td id="price_${c_id}_${p_id}"></td>
        `;

        customer_orders_table_row.querySelector(`#product_id_${c_id}_${p_id}`).innerText = p_id;
        customer_orders_table_row.querySelector(`#product_name_${c_id}_${p_id}`).innerText = customer_orders[j]["product_name"];
        customer_orders_table_row.querySelector(`#unit_price_${c_id}_${p_id}`).innerText = customer_orders[j]["price"];
        customer_orders_table_row.querySelector(`#quantity_${c_id}_${p_id}`).innerText = customer_orders[j]["quantity"];
        customer_orders_table_row.querySelector(`#price_${c_id}_${p_id}`).innerText = customer_orders[j]["price_sum"];

        customer_orders_table.appendChild(customer_orders_table_row);
    }

    if(customer_orders.length > 0){
        customer_detail_and_order_block.appendChild(customer_orders_table);

        const summary_block = document.createElement('div');
        summary_block.id = "summary_block_"+c_id;
        summary_block.className = 'summary_block';
        summary_block.innerHTML =`
            <h3 class="total_price_head" id="total_price_head_${c_id}"></h3>
            <button class="mark_as_completed" onclick="mark_as_completed(${c_id})">Order Completed</button>
        `;

        summary_block.querySelector(`#total_price_head_${c_id}`).innerText = "Total: " + total_price;

        customer_detail_and_order_block.appendChild(summary_block);

        order_review_section.appendChild(customer_detail_and_order_block);
    }
}

function refreshReviewTable(){
    while (order_review_section.firstChild) {
        order_review_section.removeChild(order_review_section.lastChild);
    }
    get_all_orders();
}

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
                loginform.classList.replace("loginform", "hidden");
                adminpage.classList.replace("hidden", "adminpage");
                refreshReviewTable();
            }
            else{
                loginform.classList.replace("hidden", "loginform");
                adminpage.classList.replace("adminpage", "hidden");
                alert("Invalid admin credentials!")
            }
        })
        .catch(err => console.log(err))
}

async function get_all_orders(){
    await fetch('/getallorders', {
        method: 'POST',
        body: JSON.stringify(
            {}
        )
    }).then(response => response.json())
        .then(response => {
            for (let i = 0; i < response.length; i++) {
                console.log(response[i]);
                showNextOrder(response[i]);
            }
        }).catch(err => console.log(err))
}

async function mark_as_completed(id){
    await fetch('/markascompleted', {
        method: 'POST',
        body: JSON.stringify(
            {
                customer_id: Number(id)
            }
        )
    }).then(response => response.text())
        .then(response => {
            refreshReviewTable();
            console.log(response)
        }).catch(err => console.log(err))
}
