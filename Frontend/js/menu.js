

// 获取产品容器
const productsContainer = document.getElementById('products-container');

// 循环遍历产品数组，创建产品卡片并添加到容器中
products.forEach(product => {
    const productCard = `
      <div>
        <div class="relative">
          <div class="relative h-72 w-full overflow-hidden rounded-lg">
            <img src="${product.image}" alt="${product.name}" class="h-full w-full object-cover object-center">
          </div>
          <div class="relative mt-4">
            <h3 class="text-sm font-medium text-gray-900">${product.name}</h3>
            <p class="mt-1 text-sm text-gray-500">${product.description}</p>
          </div>
          <div class="absolute inset-x-0 top-0 flex h-72 items-end justify-end overflow-hidden rounded-lg p-4">
            <div aria-hidden="true" class="absolute inset-x-0 bottom-0 h-36 bg-gradient-to-t from-black opacity-50"></div>
            <p class="relative text-lg font-semibold text-white">$${product.price}</p>
          </div>
        </div>
        <div class="mt-6">
          <!-- 选择口味 -->

          <select id="flavorSelect-${product.id}" class="border border-gray-300 rounded-md">
            ${product.flavors.map(flavor => `<option value="${flavor}">${flavor}</option>`).join('')}
          </select>
          <!-- 选择大小 -->
          <select id="sizeSelect-${product.id}" class="border border-gray-300 rounded-md">
            ${product.sizes.map(size => `<option value="${size}">${size}</option>`).join('')}
          </select>
          <!-- 加入购物车按钮 -->
          <button class="relative flex items-center justify-center rounded-md border border-transparent bg-gray-100 px-8 py-2 text-sm font-medium text-gray-900 hover:bg-gray-200" onclick="addToCart(${product.id}, 'flavorSelect-${product.id}', 'sizeSelect-${product.id}')">
              Add to cart
              <span class="sr-only">, ${product.name}</span>
          </button>
        </div>
      </div>
    `;
    // 将产品卡片添加到容器中
    productsContainer.insertAdjacentHTML('beforeend', productCard);
});


//购物车相关

(function() {
    /*建立模态框对象*/
    var modalBox = {};
    /*获取模态框*/
    modalBox.modal = document.querySelector(".fixed.inset-0.z-10.w-screen.overflow-y-auto");
    /*获得trigger按钮*/
    modalBox.triggerBtn = document.getElementById("cartBtn");
    /*获得关闭按钮*/
    modalBox.closeBtn = modalBox.modal.querySelector("button[type='button']");
    /*模态框显示*/
    modalBox.show = function() {
        console.log(this.modal);
        this.modal.style.display = "block";
    }
    /*模态框关闭*/
    modalBox.close = function() {
        this.modal.style.display = "none";
    }
    /*当用户点击模态框内容之外的区域，模态框也会关闭*/
    modalBox.outsideClick = function() {
        var modal = this.modal;
        window.onclick = function(event) {
            if(event.target == modal) {
                modal.style.display = "none";
            }
        }
    }
    /*模态框初始化*/
    modalBox.init = function() {
        var that = this;
        this.triggerBtn.onclick = function() {
            that.show();
        }
        this.closeBtn.onclick = function() {
            that.close();
        }
        this.outsideClick();
    }
    modalBox.init();
})();

//购物车增删
let shoppingCart = [];
let orderSubtotal = 0;
let orderTotal = 0;
const order = {
    shoppingCart: shoppingCart,
    subtotal: orderSubtotal,
    total: orderTotal,
    tax: 0.2,
    shipping: 5
};
function addToCart(productId, flavorSelectId, sizeSelectId) {
    // 获取所选口味和大小
    const flavorSelect = document.getElementById(flavorSelectId);
    const selectedFlavor = flavorSelect.value;

    const sizeSelect = document.getElementById(sizeSelectId);
    const selectedSize = sizeSelect.value;

    const product = products.find(product => product.id === productId);
    if (product) {
        // 添加口味和大小到产品信息中
        const productWithOptions = {
            ...product,
            flavor: selectedFlavor,
            size: selectedSize,
            quantity: 1
        };

        const existingProductIndex = shoppingCart.findIndex(item => item.id === productId && item.flavor === selectedFlavor && item.size === selectedSize);
        if (existingProductIndex === -1) {
            shoppingCart.push(productWithOptions);
        } else {
            shoppingCart[existingProductIndex].quantity++;
        }
        updateCartDisplay();
    }
}


function removeFromCart(productId) {
    const index = shoppingCart.findIndex(product => product.id === productId);
    if (index !== -1) {
        shoppingCart.splice(index, 1);
        updateCartDisplay();
    }
}

function updateCartDisplay() {
    const cartItemsElement = document.getElementById('cartItems');
    const subtotalElement = document.getElementById('subtotal');
    const shippingElement = document.getElementById('shipping');
    const taxElement = document.getElementById('tax');
    const totalElement = document.getElementById('total');
    cartItemsElement.innerHTML = '';
    let subtotal = 0; // 初始化小计总价
    let shipping = 5;
    let tax = 0;
    let total = 0;


    shoppingCart.forEach(item => {
        const itemElement = document.createElement('li');
        const infoBtnId = `infoBtn-${item.id}`;
        itemElement.classList.add('flex', 'py-8', 'text-sm', 'sm:items-center');
        itemElement.innerHTML = `
        <img src="${item.image}" alt="${item.name}" class="h-24 w-24 flex-none rounded-lg border border-gray-200 sm:h-32 sm:w-32">
        <div class="ml-4 grid flex-auto grid-cols-1 grid-rows-1 items-start gap-x-5 gap-y-3 sm:ml-6 sm:flex sm:items-center sm:gap-0">
            <div class="row-end-1 flex-auto sm:pr-6">
                <h3 class="font-medium text-gray-900">
                    <a href="#">${item.name}</a>
                </h3>
                <p class="mt-1 text-gray-500">${item.description}</p>
                <p class="mt-1 text-gray-500">Flavor: ${item.flavor}</p>
                <p class="mt-1 text-gray-500">Size: ${item.size}</p>
            </div>
            <p class="row-span-2 row-end-2 font-medium text-gray-900 sm:order-1 sm:ml-6 sm:w-1/3 sm:flex-none sm:text-right">$${(item.price * item.quantity).toFixed(2)}</p>
            <div class="flex items-center sm:block sm:flex-none sm:text-center">
                <label for="quantity-${item.id}" class="sr-only">Quantity, ${item.name}</label>
                <select id="quantity-${item.id}" name="quantity-${item.id}" class="block max-w-full rounded-md border border-gray-300 py-1.5 text-left text-base font-medium leading-5 text-gray-700 shadow-sm focus:border-indigo-500 focus:outline-none focus:ring-1 focus:ring-indigo-500 sm:text-sm">
                    ${generateQuantityOptions(item.quantity)}
                </select>
                <button id="${infoBtnId}" type="button" class="ml-4 font-medium text-indigo-600 hover:text-indigo-500 sm:ml-0 sm:mt-2" onclick="removeFromCart(${item.id})">
                    <span>Remove</span>
                </button>
            </div>
        </div>
    `;

    cartItemsElement.appendChild(itemElement);
        subtotal += item.price * item.quantity;
        tax = subtotal * 0.2;
        total = subtotal + tax + shipping;
        order.subtotal = subtotal;
        order.total = total;

    });
    // 更新小计显示
    subtotalElement.textContent = `$${subtotal.toFixed(2)}`;
    taxElement.textContent =  `$${tax.toFixed(2)}`;
    shippingElement.textContent =  `$${shipping.toFixed(2)}`;
    totalElement.textContent =  `$${total.toFixed(2)}`;
    order.shoppingCart = shoppingCart;
    // 将订单对象保存到本地存储
    localStorage.setItem('order', JSON.stringify(order));
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

// 初始化购物车显示
updateCartDisplay();







// 从本地存储中获取订单对象
// const savedOrder = JSON.parse(localStorage.getItem('order'));
