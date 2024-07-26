
    // 模拟从后端获取的餐厅数据
    const restaurants = [
    {
        name: 'Tasty Thai',
        cuisine: 'Thai',
        rating: 4.5,
        image: 'https://via.placeholder.com/300',
        distance: 1.2,
        menuUrl: 'ProductList.html' // 假设餐厅的菜单页面 URL
    },
    {
        name: 'Pizza Palace',
        cuisine: 'Italian',
        rating: 4.2,
        image: 'https://via.placeholder.com/300',
        distance: 0.8,
        menuUrl: 'ProductList.html' // 假设餐厅的菜单页面 URL
    },
        {
            name: 'Pizza Palace',
            cuisine: 'Italian',
            rating: 4.2,
            image: 'https://via.placeholder.com/300',
            distance: 0.8,
            menuUrl: 'ProductList.html' // 假设餐厅的菜单页面 URL
        },
        {
            name: 'Pizza Palace',
            cuisine: 'Italian',
            rating: 4.2,
            image: 'https://via.placeholder.com/300',
            distance: 0.8,
            menuUrl: 'ProductList.html' // 假设餐厅的菜单页面 URL
        },
        {
            name: 'Pizza Palace',
            cuisine: 'Italian',
            rating: 4.2,
            image: 'https://via.placeholder.com/300',
            distance: 0.8,
            menuUrl: 'ProductList.html' // 假设餐厅的菜单页面 URL
        },

    {
        name: 'Burger Bar',
        cuisine: 'American',
        rating: 4.0,
        image: 'https://via.placeholder.com/300',
        distance: 0.5,
        menuUrl: 'ProductList.html' // 假设餐厅的菜单页面 URL
    }
    // 可以添加更多餐厅信息
    ];

    // 获取餐厅列表容器
    const restaurantsList = document.getElementById('restaurants-list');

    // 循环遍历餐厅数组，创建餐厅卡片并添加到容器中
    restaurants.forEach(restaurant => {
        const restaurantCard = `
    <div>
      <div class="group relative border-b border-r border-gray-200 p-4 sm:p-6 dark:border-gray-700">
        <div class="aspect-h-1 aspect-w-1 overflow-hidden rounded-lg bg-gray-200 group-hover:opacity-75 ">
          <img src="${restaurant.image}" alt="${restaurant.name}" class="h-full w-full object-cover object-center ">
        </div>
        <div class="pb-4 pt-10 text-center">
          <h3 class="text-sm font-medium text-gray-900 dark:text-gray-300" >
            <a href="${restaurant.menuUrl}"> <!-- 修改链接地址为菜单页面 URL -->
              <span aria-hidden="true" class="absolute inset-0"></span>
              <a class="text-black dark:text-yellow-600"> ${restaurant.name} </a>
            </a>
          </h3>
          <p class="text-sm text-gray-500  dark:text-gray-300">${restaurant.cuisine}</p>
          <div class="mt-3 flex flex-col items-center">
            <p class="sr-only">${restaurant.rating} out of 5 stars</p>
            <div class="flex items-center">
              ${Array.from({ length: Math.floor(restaurant.rating) }, (_, index) => `
                <svg class="text-yellow-400 h-5 w-5 flex-shrink-0 " viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                  <path fill-rule="evenodd" d="M10.868 2.884c-.321-.772-1.415-.772-1.736 0l-1.83 4.401-4.753.381c-.833.067-1.171 1.107-.536 1.651l3.62 3.102-1.106 4.637c-.194.813.691 1.456 1.405 1.02L10 15.591l4.069 2.485c.713.436 1.598-.207 1.404-1.02l-1.106-4.637 3.62-3.102c.635-.544.297-1.584-.536-1.65l-4.752-.382-1.831-4.401z" clip-rule="evenodd" />
                </svg>
              `).join('')}
            </div>
            <p class="mt-1 text-sm text-gray-500 dark:text-white">${restaurant.rating} reviews</p>
          </div>
          <p class="mt-4 text-base font-medium text-gray-900 dark:text-white">$15</p>
        </div>
      </div>
    </div>
  `;
        // 将餐厅卡片添加到容器中
        restaurantsList.insertAdjacentHTML('beforeend', restaurantCard);
    });


