



// 动态加载左边菜品分类的
document.addEventListener('DOMContentLoaded', function() {
    const categoryList = document.getElementById('category-list');

    // 示例数据，可能来自API或其他数据源
    const categories = [
        { name: "Salads", emoji: "🥗" },
        { name: "Burgers", emoji: "🍔" },
        { name: "Pizzas", emoji: "🍕" },
        { name: "Drinks", emoji: "🍹" },
        { name: "Desserts", emoji: "🍰" }
    ];

    // 为每个分类创建HTML元素并添加到页面中
    categories.forEach(category => {
        const li = document.createElement('li');
        li.innerHTML = `
            <a href="#" class="category-item text-black bg-gray-200 text-4xl hover:bg-gray-300 group flex gap-x-3 rounded-md p-2 text-xl leading-6 font-semibold">
                ${category.emoji} ${category.name}
            </a>
        `;

        // 添加到列表中
        categoryList.appendChild(li);
    });

    // 添加点击事件监听，实现高亮效果
    categoryList.addEventListener('click', function(event) {
        if (event.target.matches('.category-item')) {
            // 移除之前所有高亮
            document.querySelectorAll('.category-item').forEach(item => {
                item.classList.remove('bg-gray-500', 'text-white');
            });

            // 高亮点击的项
            event.target.classList.add('bg-gray-500', 'text-white');
        }
    });
});

// 动态加载商品 同步到右侧购物车
document.addEventListener('DOMContentLoaded', function() {
    const shoppingCartList = document.getElementById('shopping-cart-list');
    const CartList = document.getElementById('cartList');
    const totalAmountElement = document.getElementById('total-amount');
    const totalAmountElement1 = document.getElementById('total-amount1');
    let shoppingCart = [];  // 存储购物车中的商品
    let orderSubtotal = 0;
    let orderTotal = 0;
    const order = {
        shoppingCart: shoppingCart,
        subtotal: orderSubtotal,
        total: orderTotal,
        tax: 0.2,
        shipping: 5
    };

    function updateShoppingCart() {
        shoppingCartList.innerHTML = ''; // 清空购物车列表
        CartList.innerHTML = '';
        let totalAmount = 0;

        shoppingCart.forEach((product, index) => {
            totalAmount += product.price * product.quantity;
            order.subtotal = totalAmount;
            order.total = totalAmount*1.2+5;
            const li = document.createElement('li');
            li.className = "flex gap-x-4 py-5";
            li.innerHTML = `
            <img class="h-12 w-12 flex-none rounded-md bg-gray-50" src="${product.imageUrl}" alt="${product.name}">
            <div class="flex-auto">
                <div class="flex items-baseline justify-between gap-x-4">
                    <p class="text-xl font-semibold leading-6 text-gray-900">${product.name}</p>
                    <p class="flex-none text-base text-gray-600">${product.price} $</p>
                </div>
                <div class="flex items-baseline justify-between gap-x-4 space-y-4">
                    <p class="text-base leading-6 text-gray-600">${product.description}</p>
                    <div class="flex items-center">
                        <button data-action="decrease" data-index="${index}" class="quantity-modify p-0.5 rounded-full bg-gray-200 hover:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" class="w-4 h-4 text-gray-600">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M6 12h12" />
                            </svg>
                        </button>   
                        <p class="px-2 text-base text-gray-600">${product.quantity}</p>
                        <button data-action="increase" data-index="${index}" class="quantity-modify p-0.5 rounded-full bg-gray-200 hover:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" class="w-4 h-4 text-gray-600">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M12 6v12m6-6H6" />
                            </svg>
                        </button>
                    </div>
                </div>
            </div>
        `;

            shoppingCartList.appendChild(li); // 添加新的列表项到购物车列表

        });
        // 更新模态框中的购物车内容
        CartList.innerHTML = shoppingCartList.innerHTML;
        order.shoppingCart = shoppingCart;
        // 将订单对象保存到本地存储

        localStorage.setItem('order', JSON.stringify(order));
        totalAmountElement.textContent = `$${totalAmount.toFixed(2)}`; // 更新总金额
        totalAmountElement1.textContent = `$${totalAmount.toFixed(2)}`; // 更新总金额
    }

    // 绑定事件监听器到购物车列表
    shoppingCartList.addEventListener('click', function(event) {

        // 确定点击的是增加或减少按钮
        if (event.target.closest('.quantity-modify')) {
            const button = event.target.closest('.quantity-modify');
            const action = button.dataset.action;
            const index = parseInt(button.dataset.index, 10);
            const product = shoppingCart[index];

            // 根据按钮的动作调整数量
            if (action === 'increase') {
                product.quantity++;
            } else if (action === 'decrease') {
                product.quantity--;
                // 如果数量小于或等于0，则从购物车中移除该商品
                if (product.quantity <= 0) {
                    shoppingCart.splice(index, 1);
                }
            }

            // 更新购物车显示
            updateShoppingCart();
        }
    });



    // 初始化购物车显示
    updateShoppingCart();

    // 吧商品添加到购物车
    // 添加到购物车函数可能需要进行小修改以处理规格
    function addToShoppingCart(product) {
        let existingProductIndex = shoppingCart.findIndex(item => item.name === product.name && item.specifications === product.specifications);

        if (existingProductIndex !== -1) {
            shoppingCart[existingProductIndex].quantity += product.quantity; // 如果产品已存在，只增加数量
        } else {
            shoppingCart.push(product); // 如果是新产品，添加到购物车
        }

        updateShoppingCart(); // 更新购物车显示

    }



    const productList = document.getElementById('product-list');

    // 动态加载商品
    products.forEach((product) => {
        const li = document.createElement('li');
        li.className = "inline-flex w-64 flex-col text-center lg:w-auto";

        let buttonHTML = product.specifications == null || product.specifications.length === 0
            ? `<a class="add-to-cart-button relative flex items-center justify-center rounded-md border border-transparent bg-yellow-500 px-8 py-2 text-sm font-medium text-gray-900 hover:bg-yellow-600" data-index="${product.id}">Add to bag<span class="sr-only">, ${product.name}</span></a>`
            : `<a class="select-specifications-button relative flex items-center justify-center rounded-md border border-transparent bg-yellow-500 px-8 py-2 text-sm font-medium text-gray-900 hover:bg-yellow-600" data-index="${product.id}">Select specifications<span class="sr-only">, ${product.name}</span></a>`;

        li.innerHTML = `
            <div class="group relative">
                <div class="aspect-h-1 aspect-w-1 w-full overflow-hidden rounded-md bg-gray-200 ">
                    <img src="${product.imageUrl}" class="h-full w-full object-cover object-center group-hover:opacity-75" data-id="${product.id}" onclick="showProductDetails(${product.id})">
                </div>
                <div class="mt-6">
                    <h3 class="mt-1 font-semibold text-lg text-gray-900">
                        ${product.name}
                    </h3>
                    <p class="mt-1 text-gray-900">$${product.price}</p>
                    <h5 class="mt-1 font-light text-gray-400">
                        ${product.description}
                    </h5>
                </div>
                <div class="mt-6">
                    ${buttonHTML}
                </div>
            </div>
            <!-- Hidden specifications form -->
            <div class="hidden specifications-form" data-index="${product.id}">
                <select class="mt-2 p-2 border border-gray-300 rounded">
                    ${product.specifications ? product.specifications.map(spec => `<option value="${spec}">${spec}</option>`).join('') : ''}
                </select>
                <button class="mt-2 p-2 bg-yellow-500 text-gray-700 rounded hover:bg-yellow-500">Add</button>
            </div>
            
        `;

        productList.appendChild(li);
    });


    // 事件监听器来显示/隐藏规格表单
    productList.addEventListener('click', function(event) {
        if (event.target.matches('.select-specifications-button')) {
            const index = event.target.dataset.index;
            const form = document.querySelector(`.specifications-form[data-index="${index}"]`);
            form.classList.toggle('hidden');  // 切换显示或隐藏
        }
    });

    // 实时添加到购物车中
    document.addEventListener('click', function(event) {
        // 处理添加到购物车按钮的点击事件
        if (event.target.matches('.add-to-cart-button')) {
            const button = event.target;
            const productId = button.dataset.index; // 使用产品ID来查找产品
            const product = products.find(p => p.id.toString() === productId);
            if (product) {
                addToShoppingCart({...product, quantity: 1, description: "Standard configuration"}); // 添加产品到购物车，使用标准描述
            }
        }
        // 处理规格表单内的Add按钮点击事件
        else if (event.target.matches('.specifications-form button')) {
            const form = event.target.closest('.specifications-form');
            const productId = form.dataset.index;
            const selectElement = form.querySelector('select');
            const selectedSpec = selectElement.value;
            const product = products.find(p => p.id.toString() === productId);

            if (product && selectedSpec) {
                const productToAdd = {
                    ...product,
                    description: `Specification: ${selectedSpec}`, // 更新描述为选中的规格
                    specifications: selectedSpec,
                    quantity: 1
                };
                addToShoppingCart(productToAdd);
                form.classList.add('hidden'); // 隐藏规格选择表单
            }
        }
    });



});



// 显示产品详细信息的函数
function showProductDetails(productId) {
    const product = products.find(p => p.id === productId);
    if (product) {
        document.getElementById('modalTitle').textContent = product.name;
        document.getElementById('modalImage').src = product.imageUrl;
        document.getElementById('modalImage').alt = "Image of " + product.name;
        document.getElementById('modalDescription').textContent = product.description;
        const specContainer = document.getElementById('modalSpecifications');
        specContainer.innerHTML = ''; // 清空之前的内容

        if (product.specifications) {
            const ul = document.createElement('ul');
            product.specifications.forEach(spec => {
                const li = document.createElement('li');
                li.textContent = spec;
                ul.appendChild(li);
            });
            specContainer.appendChild(ul);
        } else {
            specContainer.textContent = 'No specifications available.';
        }

        document.getElementById('productModal').classList.remove('hidden'); // 显示模态窗口
    }
}

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