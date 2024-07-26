// 修改后的 displayOrders 函数，接受订单数组作为参数
function displayOrders(orders) {
    const ordersContainer = document.getElementById('orders-container');

    // 清空之前的内容
    ordersContainer.innerHTML = '';

    // 循环遍历每个订单并显示它们
    orders.forEach(order => {
        const orderSection = document.createElement('section');
        orderSection.setAttribute('aria-labelledby', `${order.id}-heading`);
        orderSection.classList.add('mb-8');

        orderSection.innerHTML = `
        <div class="space-y-1 md:flex md:items-baseline md:space-x-4 md:space-y-0">
            <h2 id="${order.id}-heading" class="text-lg font-medium text-gray-900 md:flex-shrink-0">Order #${order.id}</h2>
            <div class="space-y-5 sm:flex sm:items-baseline sm:justify-between sm:space-y-0 md:min-w-0 md:flex-1">
                <p class="text-sm font-medium text-gray-500">Delivered on ${order.deliveryDate}</p>
                <div class="flex text-sm font-medium">
                    <a href="../Product/orderSummaries" class="text-yellow-600 hover:text-yellow-700">Manage order</a>
                </div>
            </div>
        </div>

        <!-- Product and Rating Info -->
        <div class="divide-y divide-gray-200 border-t border-gray-200">
        <div class="-mb-6 mt-6 flow-root ">
            <div class="py-6 sm:flex">
                <div class="flex space-x-4 sm:min-w-0 sm:flex-1 sm:space-x-6 lg:space-x-8">
                    <div class="min-w-0 flex-1 pt-1.5 sm:pt-0">
                        <h3 class="text-sm font-medium text-gray-900">
                            <a href="#">${order.productName}</a>
                        </h3>
                        <p class="truncate text-sm text-gray-500">${order.productMaterial} · ${order.productSize}</p>
                        <p class="mt-1 font-medium text-gray-900">$${order.productPrice.toFixed(2)}</p>
                    </div>
                </div>
                <div class="mt-6 space-y-4 sm:ml-6 sm:mt-0 sm:w-40 sm:flex-none">
                    <div>Scoring</div>
                    <div class="flex items-center" id="rating-${order.id}">
                        <!-- Star SVGs with data-index attribute for identifying the star position -->
                        <!-- SVGs go here --><!-- Star SVGs with data-index attribute for identifying the star position -->
                        <svg class="star h-6 w-6 flex-shrink-0 text-gray-200 hover:text-yellow-400 cursor-pointer" data-index="1" fill="currentColor" viewBox="0 0 20 20">
                            <!-- SVG Path -->
                            <path fill-rule="evenodd" d="M10.868 2.884c-.321-.772-1.415-.772-1.736 0l-1.83 4.401-4.753.381c-.833.067-1.171 1.107-.536 1.651l3.62 3.102-1.106 4.637c-.194.813.691 1.456 1.405 1.02L10 15.591l4.069 2.485c.713.436 1.598-.207 1.404-1.02l-1.106-4.637 3.62-3.102c.635-.544.297-1.584-.536-1.65l-4.752-.382-1.831-4.401z" clip-rule="evenodd" />
                        </svg>
                        <svg class="star h-6 w-6 flex-shrink-0 text-gray-200 hover:text-yellow-400 cursor-pointer" data-index="2" fill="currentColor" viewBox="0 0 20 20">
                            <!-- SVG Path -->
                            <path fill-rule="evenodd" d="M10.868 2.884c-.321-.772-1.415-.772-1.736 0l-1.83 4.401-4.753.381c-.833.067-1.171 1.107-.536 1.651l3.62 3.102-1.106 4.637c-.194.813.691 1.456 1.405 1.02L10 15.591l4.069 2.485c.713.436 1.598-.207 1.404-1.02l-1.106-4.637 3.62-3.102c.635-.544.297-1.584-.536-1.65l-4.752-.382-1.831-4.401z" clip-rule="evenodd" />
                        
                        </svg>
                        <svg class="star h-6 w-6 flex-shrink-0 text-gray-200 hover:text-yellow-400 cursor-pointer" data-index="3" fill="currentColor" viewBox="0 0 20 20">
                            <!-- SVG Path -->
                            <path fill-rule="evenodd" d="M10.868 2.884c-.321-.772-1.415-.772-1.736 0l-1.83 4.401-4.753.381c-.833.067-1.171 1.107-.536 1.651l3.62 3.102-1.106 4.637c-.194.813.691 1.456 1.405 1.02L10 15.591l4.069 2.485c.713.436 1.598-.207 1.404-1.02l-1.106-4.637 3.62-3.102c.635-.544.297-1.584-.536-1.65l-4.752-.382-1.831-4.401z" clip-rule="evenodd" />
                        
                        </svg>
                        <svg class="star h-6 w-6 flex-shrink-0 text-gray-200 hover:text-yellow-400 cursor-pointer" data-index="4" fill="currentColor" viewBox="0 0 20 20">
                            <!-- SVG Path -->
                            <path fill-rule="evenodd" d="M10.868 2.884c-.321-.772-1.415-.772-1.736 0l-1.83 4.401-4.753.381c-.833.067-1.171 1.107-.536 1.651l3.62 3.102-1.106 4.637c-.194.813.691 1.456 1.405 1.02L10 15.591l4.069 2.485c.713.436 1.598-.207 1.404-1.02l-1.106-4.637 3.62-3.102c.635-.544.297-1.584-.536-1.65l-4.752-.382-1.831-4.401z" clip-rule="evenodd" />
                        
                        </svg>
                        <svg class="star h-6 w-6 flex-shrink-0 text-gray-200 hover:text-yellow-400 cursor-pointer" data-index="5" fill="currentColor" viewBox="0 0 20 20">
                            <!-- SVG Path -->
                            <path fill-rule="evenodd" d="M10.868 2.884c-.321-.772-1.415-.772-1.736 0l-1.83 4.401-4.753.381c-.833.067-1.171 1.107-.536 1.651l3.62 3.102-1.106 4.637c-.194.813.691 1.456 1.405 1.02L10 15.591l4.069 2.485c.713.436 1.598-.207 1.404-1.02l-1.106-4.637 3.62-3.102c.635-.544.297-1.584-.536-1.65l-4.752-.382-1.831-4.401z" clip-rule="evenodd" />
                        
                    </div>
                </div>
            
            </div>
             <form id="commentForm" action="#" class="relative mt-2 flex items-end">
    <div class="flex-grow">
        <div>Add a comment</div>
        <div class="overflow-hidden rounded-lg border border-gray-300 shadow-sm focus-within:border-gray-700 focus-within:ring-1 focus-within:ring-indigo-500">
            <label for="description" class="sr-only">Description</label>
            <textarea rows="2" name="description" id="description" class="pl-4 block w-full resize-none border-0 py-0 text-gray-900 placeholder:text-gray-400 focus:ring-0 sm:text-sm sm:leading-6" placeholder="Write a description..."></textarea>
        </div>
    </div>
    <button type="submit" class="ml-4 py-3 px-6 bg-yellow-500 text-white font-medium text-sm leading-6 rounded-md hover:bg-yellow-600 focus:outline-none focus:border-blue-700 focus:ring-2 focus:ring-blue-500">
    Submit
</button>

</form>


        </div>
        </div>
      `;

        // 将每个订单添加到容器中
        ordersContainer.appendChild(orderSection);

        // Add stars dynamically and attach event listeners
        const ratingContainer = document.createElement('div');
        ratingContainer.id = `rating-${order.id}`;
        orderSection.appendChild(ratingContainer);

        ordersContainer.appendChild(orderSection);
    });
}






// 模拟后端返回的订单数据
const orders = [
    {
        id: 4376,
        deliveryDate: "January 22, 2021",
        productImage: "https://tailwindui.com/img/ecommerce-images/order-history-page-07-product-01.jpg",
        productName: "Machined Brass Puzzle",
        productMaterial: "Brass",
        productSize: "3\" x 3\" x 3\"",
        productPrice: 95.00
    },
    {
        id: 123132,
        deliveryDate: "January 22, 2021",
        productImage: "https://tailwindui.com/img/ecommerce-images/order-history-page-07-product-01.jpg",
        productName: "Machined Brass Puzzle",
        productMaterial: "Brass",
        productSize: "3\" x 3\" x 3\"",
        productPrice: 95.00
    },
    {
        id: 123132,
        deliveryDate: "January 22, 2021",
        productImage: "https://tailwindui.com/img/ecommerce-images/order-history-page-07-product-01.jpg",
        productName: "Machined Brass Puzzle",
        productMaterial: "Brass",
        productSize: "3\" x 3\" x 3\"",
        productPrice: 95.00
    },
    {
        id: 123132,
        deliveryDate: "January 22, 2021",
        productImage: "https://tailwindui.com/img/ecommerce-images/order-history-page-07-product-01.jpg",
        productName: "Machined Brass Puzzle",
        productMaterial: "Brass",
        productSize: "3\" x 3\" x 3\"",
        productPrice: 95.00
    }

];

// 调用 displayOrders 函数显示订单信息
displayOrders(orders);



document.getElementById('commentForm').addEventListener('submit', function(event) {
    event.preventDefault(); // 阻止表单默认提交行为

    const description = document.getElementById('description').value;
    const messageDiv = document.getElementById('message');

    fetch('your-server-endpoint', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ description: description })
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            messageDiv.textContent = '评论成功!'; // 显示成功消息
            // 可以选择在这里清空文本域或保留评论文本
            // document.getElementById('description').value = ''; // 如果你想清空评论框
        })
        .catch((error) => {
            console.error('Error:', error);
            messageDiv.textContent = '提交评论失败，请重试。';
        });
});
