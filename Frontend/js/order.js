const savedOrder = JSON.parse(localStorage.getItem('order'));

const order = {
    shoppingCart: savedOrder.shoppingCart,
    subtotal: savedOrder.subtotal,
    total: savedOrder.total,
    tax: savedOrder.tax,
    shipping: 5
};
function updateCartDisplay() {
    const cartItemsElement = document.getElementById('cartItems');
    const subtotalElement = document.getElementById('subtotal');
    const shippingElement = document.getElementById('shipping');
    const taxElement = document.getElementById('tax');
    const totalElement = document.getElementById('total');
    cartItemsElement.innerHTML = '';

    order.shoppingCart.forEach(item => {
        const itemElement = document.createElement('li');
        itemElement.classList.add('flex', 'py-8', 'text-sm', 'sm:items-center');
        itemElement.innerHTML = `
            <div class="flex-shrink-0">
                <img src="${item.imageUrl}" alt="${item.name}" class="h-24 w-24 flex-none rounded-lg border border-gray-200 sm:h-30 sm:w-30 sm:ml-4">
            </div>
            <div class="ml-6 flex flex-1 flex-col">
                <div class="flex">
                    <div class="min-w-0 flex-1">
                        <h4 class="text-sm">
                            <a href="#">${item.name}</a>
                        </h4>
                        <p class="mt-1 text-sm text-gray-500">${item.description}</p>
                        <!-- 显示风味和大小 -->
                        
                    </div>
                </div>
                <div class="flex flex-1 items-end justify-between pt-2">
                    <p class="mt-1 text-sm font-medium text-gray-900">$${(item.price * item.quantity).toFixed(2)}</p>
                    <div class="ml-4">
                        <label for="quantity-${item.id}" class="sr-only">Quantity, ${item.name}</label>
                        <select id="quantity-${item.id}" name="quantity-${item.id}" class="block max-w-full rounded-md border border-gray-300 py-1.5 text-left text-base font-medium leading-5 text-gray-700 shadow-sm focus:border-indigo-500 focus:outline-none focus:ring-1 focus:ring-indigo-500 sm:text-sm sm:mr-4">
                            ${generateQuantityOptions(item.quantity)}
                        </select>
                    </div>
                </div>
            </div>
        `;
        cartItemsElement.appendChild(itemElement);
    });

    // 更新小计显示
    subtotalElement.textContent = `$${order.subtotal.toFixed(2)}`;
    taxElement.textContent = `$${order.tax.toFixed(2)}`;
    shippingElement.textContent = `$${order.shipping.toFixed(2)}`;
    totalElement.textContent = `$${order.total.toFixed(2)}`;
}


function removeFromCart(productId) {
    const index = savedOrder.shoppingCart.findIndex(product => product.id === productId);
    if (index !== -1) {
        savedOrder.shoppingCart.splice(index, 1);
        updateCartDisplay();
    }
}



// 辅助函数，生成选项列表
function generateQuantityOptions(selectedQuantity) {
    let optionsHTML = '';
    for (let i = 1; i <= 8; i++) {
        if (i === selectedQuantity) {
            optionsHTML += `<option value="${i}" selected>${i}</option>`;
        } else {
            optionsHTML += `<option value="${i}">${i}</option>`;
        }
    }
    return optionsHTML;
}

function saveInput() {
    // Get the input field values

    const email = document.getElementById('email-address').value;
    const firstName = document.getElementById('first-name').value;
    const lastName = document.getElementById('last-name').value;
    const address = document.getElementById('address').value;
    const city = document.getElementById('city').value;
    const country = document.getElementById('country').value;
    const region = document.getElementById('region').value;
    const postalCode = document.getElementById('postal-code').value;
    const phone = document.getElementById('phone').value;
    const cardNum = document.getElementById('card-number').value;
    const nameCard = document.getElementById('name-on-card').value;
    const expDate = document.getElementById('expiration-date').value;
    const cvc = document.getElementById('cvc').value;
    const note = document.getElementById('note').value;

    // Create an object to store input values
    const orderInfo = {
        email: email,
        firstName: firstName,
        lastName: lastName,
        address: address,
        city: city,
        country: country,
        region: region,
        postalCode: postalCode,
        phone: phone,
        cardNum: cardNum,
        nameCard: nameCard,
        expDate:expDate,
        cvc:cvc,
        note:note

    };

    // Convert the inputData object to a JSON string
    const orderInfoJSON = JSON.stringify(orderInfo);
    // 本地这样写 现在先用一个虚拟的
    // var token = localStorage.getItem('token');
    var token = 'eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwiZXhwIjoxNzE1MjQ5MDAxLCJpYXQiOjE3MTQ2NDQyMDF9.UovfTYVYv5Wz8Rp6g_5CLv3cuZU_encJZAvuii9-Za3g-HGMd9weoTVYztqyJNjDE3l6iDSLQEQ4oYneIG8nokOdQawQDWJx11TnHUyy65FOCQy6cqUv-5y8jkVORzYfKwHUuh67W3W3JdhNaWDQACdKxXoVCwLLnPzT1MMsq-H6SQ_CxnxrhmN-_hrX6Rxga4MHbMQ7GfFge6ytEP_sTsB7aul-tOsmPfSFfdq_76_rOmwyxnekawlJQs7ICtpafkjArN_2fIGnwQ3RLpo1lwitUIgVgXDpcLlbWhhBY5O4rRx6KoK9o0cHLm6HhZ1Y7b6WtjehvhbiuvBsKwJgrQ';
    // 发送post请求调用后端order和order detail
    fetch('http://120.26.136.194:8000/api/order/addOrder', {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${token}`
        }
    }).then(response => response.text())
        .then(data => alert(data))
        .catch(error => console.error('Error:', error));
    // Save the JSON string to local storage
    localStorage.setItem('orderInfo', orderInfoJSON);

    alert('Payment successful!');
}


// 初始化购物车显示
updateCartDisplay();
