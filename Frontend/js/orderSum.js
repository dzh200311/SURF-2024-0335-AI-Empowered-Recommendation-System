const savedOrder = JSON.parse(localStorage.getItem('order'));
const savedOrderInfo = JSON.parse(localStorage.getItem('orderInfo'));

const order = {
    shoppingCart: savedOrder.shoppingCart,
    subtotal: savedOrder.subtotal,
    total: savedOrder.total,
    tax: savedOrder.tax,
    shipping: 5
};

const orderInfo = {
    email: savedOrderInfo.email,
    firstName: savedOrderInfo.firstName,
    lastName: savedOrderInfo.lastName,
    address: savedOrderInfo.address,
    city: savedOrderInfo.city,
    country: savedOrderInfo.country,
    region: savedOrderInfo.region,
    postalCode: savedOrderInfo.postalCode,
    phone: savedOrderInfo.phone,
    cardNum: savedOrderInfo.cardNum,
    nameCard: savedOrderInfo.nameCard,
    expDate: savedOrderInfo.expDate,
    cvc:savedOrderInfo.cvc

};

function updateCartDisplay() {
    const cartItemsElement = document.getElementById('cartItems');
    const subtotalElement = document.getElementById('subtotal');
    const shippingElement = document.getElementById('shipping');
    const taxElement = document.getElementById('tax');
    const totalElement = document.getElementById('total');
    const emailElement = document.getElementById('email');
    const firstNameElement = document.getElementById('first-name');
    const lastNameElement = document.getElementById('last-name');
    const addressElement = document.getElementById('address');
    const cityElement = document.getElementById('city');
    const countryElement = document.getElementById('country');
    const regionElement = document.getElementById('region');
    const cardNumElement = document.getElementById('card-number');
    const expElement = document.getElementById('expiration-date')
    cartItemsElement.innerHTML = '';

    order.shoppingCart.forEach(item => {
        const itemElement = document.createElement('li');
        itemElement.classList.add('flex', 'py-8', 'text-sm', 'sm:items-center');
        itemElement.innerHTML = `
                <img src="${item.image}" alt="${item.name}" " class="h-24 w-24 flex-none rounded-md bg-gray-100 object-cover object-center">
                        <div class="flex-auto space-y-1">
                            <h3 class="text-gray-900">
                                <a href="#">${item.name}</a>
                            </h3>
                             <p class="mt-1 text-sm text-gray-500">${item.description}</p>
                        </div>
                        <p class="flex-none font-medium text-gray-900">$${(item.price * item.quantity).toFixed(2)}</p>
                 
            `;
        cartItemsElement.appendChild(itemElement);



    });
    // 更新小计显示
    subtotalElement.textContent = `$${order.subtotal.toFixed(2)}`;
    taxElement.textContent =  `$${order.tax.toFixed(2)}`;
    shippingElement.textContent =  `$${order.shipping.toFixed(2)}`;
    totalElement.textContent =  `$${order.total.toFixed(2)}`;

    emailElement.textContent = `${savedOrderInfo.email}`;
    firstNameElement.textContent = `${savedOrderInfo.firstName}`;
    lastNameElement.textContent = `${savedOrderInfo.lastName}`;
    addressElement.textContent = `${savedOrderInfo.address}`;
    cityElement.textContent = `${savedOrderInfo.city}`;
    countryElement.textContent = `${savedOrderInfo.country}`;
    regionElement.textContent = `${savedOrderInfo.region}`;

    cardNumElement.textContent = `${savedOrderInfo.cardNum}`;
    expElement.textContent = `${savedOrderInfo.expDate}`;
}

updateCartDisplay();